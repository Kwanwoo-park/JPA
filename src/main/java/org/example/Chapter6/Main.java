package org.example.Chapter6;

import org.example.entity.Chapter6.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        try {
            tx.begin();
            //testSave();
            save();
            find();
            //findInverse();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

//    static void testSave() {
//        Member member1 = new Member("member1");
//        Member member2 = new Member("member2");
//
//        Team team1 = new Team("team1");
//        team1.getMembers().add(member1);
//        team1.getMembers().add(member2);
//
//        em.persist(member1);
//        em.persist(member2);
//        em.persist(team1);
//    }

    static void save() {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        //member1.getProducts().add(productA);
        em.persist(member1);

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Orders order = new Orders();
        order.setMember(member1);
        order.setProduct(productA);
        order.setOrderAmount(2);
        em.persist(order);

//        MemberProduct memberProduct = new MemberProduct();
//        memberProduct.setMember(member1);
//        memberProduct.setProduct(productA);
//        memberProduct.setOrderAmount(2);
//
//        em.persist(memberProduct);
    }

    static void find() {
        Long orderId = 1L;
        Orders orders = em.find(Orders.class, orderId);

        Member member = orders.getMember();
        Product product = orders.getProduct();

        System.out.println("member = " + member.getUsername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + orders.getOrderAmount());

//        MemberProductId memberProductId = new MemberProductId();
//        memberProductId.setMember("member1");
//        memberProductId.setProduct("productA");
//
//        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
//
//        Member member = memberProduct.getMember();
//        Product product = memberProduct.getProduct();
//
//        System.out.println("member = " + member.getUsername());
//        System.out.println("product = " + product.getName());
//        System.out.println("orderAmount = " + memberProduct.getOrderAmount());
    }

//    static void findInverse() {
//        Product product = em.find(Product.class, "productA");
//
//        List<Member> members = product.getMembers();
//
//        for (Member member : members) {
//            System.out.println("member = " + member.getUsername());
//        }
//    }
}
