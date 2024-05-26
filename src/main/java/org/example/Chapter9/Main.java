package org.example.Chapter9;

import org.example.entity.Chapter9.Address;
import org.example.entity.Chapter9.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();
    public static void main(String[] args) {
        try {
            tx.begin();
            save();
            find();
            update();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    static void save() {
        Member member = new Member();

        member.setHomeAddress(new Address("통영", "몽돌해수욕장", "660-123"));

        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        member.getAddressesHistory().add(new Address("서울", "강남", "123-123"));
        member.getAddressesHistory().add(new Address("서울", "강북", "000-000"));

        em.persist(member);
    }

    static void find() {
        Member member = em.find(Member.class, 1L);

        Address homeAddress = member.getHomeAddress();

        Set<String> favoriteFoods = member.getFavoriteFoods();

        for (String favoriteFood : favoriteFoods) {
            System.out.println("favoriteFood = " + favoriteFood);
        }

        List<Address> addressHistory = member.getAddressesHistory();

        addressHistory.get(0);
    }

    static void update() {
        Member member = em.find(Member.class, 1L);

        member.setHomeAddress(new Address("새로운도시", "신도시1", "123456"));

        Set<String> favoriteFoods = member.getFavoriteFoods();
        favoriteFoods.remove("탕수육");
        favoriteFoods.add("치킨");

        List<Address> addressHistory = member.getAddressesHistory();
        addressHistory.remove(new Address("서울", "강남", "123-123"));
        addressHistory.add(new Address("새로운도시", "새로운 주소", "123-456"));
    }
}
