package org.example.Chapter2;

import org.example.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //[엔티티 매니저 팩토리] - 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        //[엔티티 매니저] - 생성
        EntityManager em = emf.createEntityManager();
        //[트랜잭션] - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();         //[트랙잭션] - 시작
            logic(em);          //비즈니스 로직 실행
            tx.commit();        //[트랜잭션] - 커밋
        } catch (Exception e) {
            tx.rollback();      //[트랜잭션] - 롤백
        } finally {
            em.close();         //[엔티티 매니저 팩토리] - 종료
        }

        emf.close();
    }

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("관우");
        member.setAge(2);

        //등록
        em.persist(member);

        //Update
        member.setAge(20);

        //Select One
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername()
                + ", age=" + findMember.getAge());

        //Select List
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //Remove
        em.remove(member);
    }
}
