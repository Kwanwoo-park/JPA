package org.example.entity.Chapter10;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Member.class)
public abstract class Member_ {
    public static volatile SingularAttribute<Member, Long> id;
    public static volatile SingularAttribute<Member, String> username;
    public static volatile SingularAttribute<Member, Integer> age;
    public static volatile ListAttribute<Member, Order> orders;
    public static volatile SingularAttribute<Member, Team> team;
}
