package com.locomizer.resttest.dao;

import com.locomizer.resttest.entities.TestPojo;
import io.github.pastorgl.fastdao.FastDAO;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TestPojoDAO extends FastDAO<TestPojo> {
    @Override
    public List<TestPojo> select(String query, Object... args) {
        return super.select(query, args);
    }

    @Override
    public Object insert(TestPojo object) {
        return super.insert(object);
    }

    @Override
    public void update(TestPojo object) {
        super.update(object);
    }

    @Override
    public List<TestPojo> getAll() {
        return super.getAll();
    }

    @Override
    public TestPojo getByPK(Object pk) {
        return super.getByPK(pk);
    }

    @Override
    public void deleteByPK(Object pk) throws Exception {
        super.deleteByPK(pk);
    }
}
