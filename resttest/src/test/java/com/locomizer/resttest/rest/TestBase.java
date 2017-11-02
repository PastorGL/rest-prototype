package com.locomizer.resttest.rest;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.github.pastorgl.fastdao.FastDAO;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBase {
    static Injector injector;

    @BeforeClass
    static public void setup() throws Exception {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        FastDAO.setDataSource(ds);

        ds.getConnection().createStatement().execute("CREATE TABLE test_pojo (id int8 auto_increment primary key, test_varchar varchar, test_bool boolean, tet_enum varchar)");

        List<Module> modules = new ArrayList<>();
        modules.add(new AbstractModule() {
            @Override
            protected void configure() {

            }
        });

        injector = Guice.createInjector(modules);
    }

    @AfterClass
    static public void teardown() {
        injector = null;
    }
}