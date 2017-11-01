package com.locomizer.resttest.rest;

import com.locomizer.resttest.contraints.EnumValidator;
import com.locomizer.resttest.dao.TestPojoDAO;
import com.locomizer.resttest.entities.SearchPojo;
import com.locomizer.resttest.entities.TestEnum;
import com.locomizer.resttest.entities.TestPojo;
import com.locomizer.resttest.service.TestPojoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * This is sample POJO Endpoint.
 */
@Path("pojo")
public class PojoEndpoint {
    private TestPojoDAO testPojoDAO;
    private TestPojoService testPojoService;

    @Inject
    public PojoEndpoint(TestPojoDAO testPojoDAO, TestPojoService testPojoService) {
        this.testPojoDAO = testPojoDAO;
        this.testPojoService = testPojoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestPojo> getPojo() throws Exception {
        return testPojoDAO.getAll();
    }

    @GET
    @Path("{pojoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TestPojo getPojo(@PathParam("pojoId") Integer pojoId) throws Exception {
        return testPojoDAO.getByPK(pojoId);
    }

    @PUT
    public Long putPojo(TestPojo pojo) throws Exception {
        return (Long) testPojoDAO.insert(pojo);
    }

    @DELETE
    @Path("{pojoId}")
    public void deletePojo(@PathParam("pojoId") Integer pojoId) throws Exception {
        testPojoDAO.deleteByPK(pojoId);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public void updatePojo(TestPojo pojo) throws Exception {
        testPojoDAO.update(pojo);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestPojo> search(@BeanParam SearchPojo search) throws Exception {
        return testPojoService.search(search);
    }

    @POST
    @Path("enum")
    public boolean isThisEnum(@FormParam("enum") @EnumValidator(enumClass = TestEnum.class) String search) throws Exception {
        return search != null;
    }
}
