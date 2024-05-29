package org.example.entity.Chapter10;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Member")
@NamedQueries({
        @NamedQuery(
                name = "Member.findByUsername",
                query = "select m from Member m where m.username = :username"
        ),
        @NamedQuery(
                name = "Member.count",
                query = "select count(m) from Member m"
        )
})
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;
    private int age;

    @ManyToOne
    private Team team;

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
