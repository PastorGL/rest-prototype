package com.locomizer.resttest.service;

import com.locomizer.resttest.dao.EntityCountDAO;
import com.locomizer.resttest.dao.TestPojoDAO;
import com.locomizer.resttest.entities.SearchPojo;
import com.locomizer.resttest.entities.TestPojo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Singleton
public class TestPojoService {
    private TestPojoDAO testPojoDAO;
    private EntityCountDAO entityCountDAO;

    @Inject
    public TestPojoService(
            TestPojoDAO testPojoDAO,
            EntityCountDAO entityCountDAO
    ) {
        this.testPojoDAO = testPojoDAO;
        this.entityCountDAO = entityCountDAO;
    }

    public List<TestPojo> search(SearchPojo search) {
        List args = new ArrayList();
        StringJoiner sb = new StringJoiner(" AND ");
        if (search.getId() != null) {
            sb.add("id=?");
            args.add(search.getId());
        }
        if (search.getName() != null) {
            sb.add(TestPojo.NAME_NAME + " LIKE ?");
            args.add("%" + search.getName() + "%");
        }
        if (search.getFlag() != null) {
            sb.add(TestPojo.FLAG_NAME + "=?");
            args.add(search.getFlag());
        }
        if (search.getTestEnum() != null) {
            sb.add(TestPojo.ENUM_NAME + "=?");
            args.add(search.getTestEnum());
        }

        if (args.size() == 0) {
            return null;
        }

        return testPojoDAO.select("SELECT * FROM " + TestPojo.TABLE_NAME + " WHERE " + sb.toString(), args.toArray());
    }

    public List<TestPojo> getAll() {
        return testPojoDAO.getAll();
    }

    public TestPojo getByPK(Long pojoId) {
        return testPojoDAO.getByPK(pojoId);
    }

    public Object insert(TestPojo pojo) {
        return testPojoDAO.insert(pojo);
    }

    public void deleteByPK(Long pojoId) {
        testPojoDAO.deleteByPK(pojoId);
    }

    public void update(TestPojo pojo) {
        testPojoDAO.update(pojo);
    }

    public Long getCount() {
        return entityCountDAO.getCount(TestPojo.class);
    }
}
