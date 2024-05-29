package org.example.Chatper10;

import org.example.DTO.UserDTO;
import org.example.entity.Chapter10.Member;
import org.example.entity.Chapter10.Team;
import org.example.entity.Chapter6.Product;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();
    public static void main(String[] args) {
        try {
            tx.begin();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    static void find() {
        String jpql = "select m from Member as m where m.username = 'kim'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
    }

    static void findCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Member> m = cq.from(Member.class);

        cq.multiselect(m.get("username"), m.get("age")).distinct(true);

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> resultList = query.getResultList();
//        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
//
//        Root<Member> m = cq.from(Member.class);
//
//        Predicate usernameEqual = cb.equal(m.get("username"), "회원1");
//
//        Order ageDesc = cb.desc(m.get("age"));
//
//        cq.select(m)
//                .where(usernameEqual)
//                .orderBy(ageDesc);

//        cq.select(m);
//
//        TypedQuery<Member> query = em.createQuery(cq);
//        List<Member> members = query.getResultList();
//        List<Member> members = em.createQuery(cq).getResultList();
    }

    static void findTypedQuery() {
        String userNameParam = "User1";

//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :username",
//                Member.class);
//
//        query.setParameter("username", userNameParam);
//        List<Member> resultList = query.getResultList();

        List<Object[]> resultList = em.createQuery("SELECT m.username, m.age FROM Member m")
                .getResultList();

        List<UserDTO> userDTOS = new ArrayList<>();

        for (Object[] row : resultList) {
            UserDTO userDTO = new UserDTO((String) row[0], (Integer) row[1]);
            userDTOS.add(userDTO);
        }
    }

    static void findQuery() {

        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
        List resultList = query.getResultList();

//        for (Object o : resultList) {
//            Object[] result = (Object[]) o;
//            System.out.println("username = " + result[0]);
//            System.out.println("age = " + result[1]);
//        }

        Iterator iterator = resultList.iterator();
        while (iterator.hasNext()) {
            Object[] row = (Object[]) iterator.next();
            String username = (String) row[0];
            Integer age = (Integer) row[1];
        }
    }

    static void findPaging() {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC", Member.class);

        query.setFirstResult(10);
        query.setMaxResults(20);
        query.getResultList();
    }

    static void findJoin() {
        String teamName = "팀A";
        String query = "SELECT m FROM Member m INNER JOIN m.team t WHERE t.name = :teamName";

        List<Member> members = em.createQuery(query, Member.class)
                .setParameter("teamName", teamName)
                .getResultList();
    }

    static void findFetchJoin() {
        String jpql = "select t from Team t join fetch t.members where t.name = '팀A'";
        List<Team> teams = em.createQuery(jpql, Team.class).getResultList();

//        for (Team team : teams) {
//            System.out.println("teamname = " + team.getName() + ", team = " + team);
//
//            for (Member member : team.getMembers()) {
//                System.out.println("->username = " + member.getUsername() + ", member = " + member);
//            }
//        }

//        List<Member> members = em.createQuery(jpql, Member.class)
//                .getResultList();

//        for (Member member : members) {
//            System.out.println("username = " + member.getUsername() + ", teamname = " + member.getTeam().name());
//        }
    }

    static void findDynamicQuery() {
        Integer age = 10;
        String username = null;
        String teamname = "팀A";

        StringBuilder jpql = new StringBuilder("select m from Member m join m.team t ");
        List<String> criteria = new ArrayList<>();

        if (age != null) criteria.add(" m.age = :age ");
        if (username != null) criteria.add(" m.username = :username ");
        if (teamname != null) criteria.add(" t.name = :teamName ");

        if (criteria.size() > 0) jpql.append(" where ");

        for (int i = 0; i < criteria.size(); i++) {
            if (i > 0) jpql.append(" and ");
            jpql.append(criteria.get(i));
        }

        TypedQuery<Member> query = em.createQuery(jpql.toString(), Member.class);

        if (age != null) query.setParameter("age", age);
        if (username != null) query.setParameter("username", username);
        if (teamname != null) query.setParameter("teamname", teamname);

        List<Member> resultList = query.getResultList();
    }
}
