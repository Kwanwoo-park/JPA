package org.example.Chapter7;

import org.example.entity.Chapter7.Board;
import org.example.entity.Chapter7.BoardDetail;
import org.example.entity.Chapter7.Parent;
import org.example.entity.Chapter7.ParentId;

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
            save();
            //find();
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
        Board board = new Board();
        board.setTitle("제목");
        em.persist(board);

        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setContent("내용");
        boardDetail.setBoard(board);
        em.persist(boardDetail);
    }

    static void find() {
        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent = em.find(Parent.class, parentId);

        System.out.println("parent = " + parent.getName());
    }
}
