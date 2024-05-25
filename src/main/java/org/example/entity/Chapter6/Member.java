package org.example.entity.Chapter6;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id @Column(name = "MEMBER_ID")
    private String id;
    //private Long id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT",
//            joinColumns = @JoinColumn(name = "MEMBER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
//    )
//    private List<Product> products = new ArrayList<>();

//    @OneToOne(mappedBy = "member")
//    private Locker locker;
//
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
//    private Team team;

//    public Member(String username) {
//        this.username = username;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public List<MemberProduct> getMemberProducts() {
//        return memberProducts;
//    }
//
//    public void setMemberProducts(List<MemberProduct> memberProducts) {
//        this.memberProducts = memberProducts;
//    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//
//    public void addProduct(Product product) {
//        products.add(product);
//        product.getMembers().add(this);
//    }

    //    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//
//        if (!team.getMembers().contains(this)) {
//            team.getMembers().add(this);
//        }
//    }
//
//    public Locker getLocker() {
//        return locker;
//    }
//
//    public void setLocker(Locker locker) {
//        this.locker = locker;
//    }
}
