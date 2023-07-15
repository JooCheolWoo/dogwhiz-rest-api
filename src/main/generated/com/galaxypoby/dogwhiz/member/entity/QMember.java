package com.galaxypoby.dogwhiz.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 238511004L;

    public static final QMember member = new QMember("member1");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final BooleanPath emailAuth = createBoolean("emailAuth");

    public final StringPath emailKey = createString("emailKey");

    public final BooleanPath gender = createBoolean("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final StringPath imageUrl = createString("imageUrl");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    public final StringPath loginIp = createString("loginIp");

    public final ListPath<MemberAddress, QMemberAddress> memberAddresses = this.<MemberAddress, QMemberAddress>createList("memberAddresses", MemberAddress.class, QMemberAddress.class, PathInits.DIRECT2);

    public final SetPath<MemberRole, QMemberRole> memberRoles = this.<MemberRole, QMemberRole>createSet("memberRoles", MemberRole.class, QMemberRole.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath status = createString("status");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatePwdDate = createDateTime("updatePwdDate", java.time.LocalDateTime.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

