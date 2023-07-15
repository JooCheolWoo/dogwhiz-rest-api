package com.galaxypoby.dogwhiz.banner.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBannerFile is a Querydsl query type for BannerFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBannerFile extends EntityPathBase<BannerFile> {

    private static final long serialVersionUID = -909005092L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBannerFile bannerFile = new QBannerFile("bannerFile");

    public final QBanner banner;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath url = createString("url");

    public QBannerFile(String variable) {
        this(BannerFile.class, forVariable(variable), INITS);
    }

    public QBannerFile(Path<? extends BannerFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBannerFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBannerFile(PathMetadata metadata, PathInits inits) {
        this(BannerFile.class, metadata, inits);
    }

    public QBannerFile(Class<? extends BannerFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.banner = inits.isInitialized("banner") ? new QBanner(forProperty("banner"), inits.get("banner")) : null;
    }

}

