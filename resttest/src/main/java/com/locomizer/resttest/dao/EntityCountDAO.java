package com.locomizer.resttest.dao;

import com.locomizer.resttest.entities.EntityCount;
import io.github.pastorgl.fastdao.FastDAO;
import io.github.pastorgl.fastdao.FastEntity;
import io.github.pastorgl.fastdao.Table;

import javax.inject.Singleton;

@Singleton
public class EntityCountDAO extends FastDAO<EntityCount> {
    public Long getCount(Class<? extends FastEntity> entityClass) {
        return select("SELECT COUNT(*) AS count FROM " + entityClass.getAnnotation(Table.class).value()).get(0).getId();
    }
}
