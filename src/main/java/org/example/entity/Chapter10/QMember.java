package org.example.entity.Chapter10;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QMember extends EntityPathBase<Member> {

    public static final QMember member = new QMember("member");

    public final NumberPath<Long> id = createNumber("id", Long.class);
    public final StringPath username = createString("username");
    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    // 생성자
    public QMember(String variable) {
        super(Member.class, variable);
    }
}