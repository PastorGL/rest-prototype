package com.locomizer.resttest.service;

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

    @Inject
    public TestPojoService(TestPojoDAO testPojoDAO) {
        this.testPojoDAO = testPojoDAO;
    }

    public List<TestPojo> search(SearchPojo search) throws Exception {
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

}
