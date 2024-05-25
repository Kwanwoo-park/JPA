package org.example.entity.Chapter7;

import javax.persistence.*;

@Entity
public class Parent {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

//    public String getId1() {
//        return id1;
//    }
//
//    public void setId1(String id1) {
//        this.id1 = id1;
//    }
//
//    public String getId2() {
//        return id2;
//    }
//
//    public void setId2(String id2) {
//        this.id2 = id2;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
