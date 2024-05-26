package org.example.Chapter8;

import org.example.entity.Chapter8.Child;
import org.example.entity.Chapter8.Member;
import org.example.entity.Chapter8.Parent;
import org.example.entity.Chapter8.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();
    public static void main(String[] args) {
        try {
            tx.begin();
            //saveNoCascade();
            saveWithCascade();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    static void saveNoCascade() {
        Parent parent = new Parent();
        em.persist(parent);

        Child child1 = new Child();
        child1.setParent(parent);
        parent.getChildren().add(child1);
        em.persist(child1);

        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);
        em.persist(child2);
    }

    static void saveWithCascade() {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);

        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        em.persist(parent);
    }

    static void printUser(String memberId) {
        Member member = em.getReference(Member.class, memberId);

        boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
        System.out.println("isLoad = " + isLoad);

        System.out.println("회원 이름: " + member.getUsername());
    }

    static void printUserAndTeam(String memberId) {
        Member member = em.find(Member.class, memberId);
        Team team = em.getReference(Team.class, "team1");
        member.setTeam(team);
        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("소속팀: " + team.getName());
    }
}
