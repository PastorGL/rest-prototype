package com.locomizer.resttest.entities;

import io.github.pastorgl.fastdao.Column;
import io.github.pastorgl.fastdao.FastEntity;

public class EntityCount extends FastEntity {
    @Column("count")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
