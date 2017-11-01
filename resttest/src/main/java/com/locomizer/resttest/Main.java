package com.locomizer.resttest;

import com.google.inject.Guice;
import com.google.inject.Module;
import com.locomizer.resttest.init.DocletWadlGeneratorConfig;
import io.github.pastorgl.fastdao.FastDAO;
import io.logz.guice.jersey.JerseyModule;
import io.logz.guice.jersey.JerseyServer;
import io.logz.guice.jersey.configuration.JerseyConfiguration;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) throws Exception {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://localhost/test");
        ds.setUser("postgres");
        FastDAO.setDataSource(ds);

        ResourceConfig config = new ResourceConfig()
                .property(ServerProperties.WADL_GENERATOR_CONFIG, DocletWadlGeneratorConfig.class.getCanonicalName())
                .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
                ;

        JerseyConfiguration configuration = JerseyConfiguration.builder()
                .withResourceConfig(config)
                .addPackage("com.locomizer.resttest")
                .addPort(8080)
                .build();

        List<Module> modules = new ArrayList<>();
        modules.add(new JerseyModule(configuration));

        Guice.createInjector(modules)
                .getInstance(JerseyServer.class).start();
    }
}