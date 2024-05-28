package org.example.Chatper10;

import org.example.entity.Chapter10.Member;
import org.example.entity.Chapter6.Product;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    static void findTypedQuery() {
        String userNameParam = "User1";

//        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :username",
//                Member.class);
//
//        query.setParameter("username", userNameParam);
//        List<Member> resultList = query.getResultList();

        List<Object[]> resultList = em.createQuery("SELECT o.product, o.orderAmount FROM Order o")
                .getResultList();

        for (Object[] row : resultList) {
            Member member = (Member) row[0];
            Product product = (Product) row[1];
            int orderAmount = (Integer) row[2];
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
}
