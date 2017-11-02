package com.locomizer.resttest.rest;

import com.locomizer.resttest.contraints.EnumValidator;
import com.locomizer.resttest.dao.EntityCountDAO;
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
    private TestPojoService testPojoService;

    @Inject
    public PojoEndpoint(TestPojoDAO testPojoDAO, TestPojoService testPojoService, EntityCountDAO entityCountDAO) {
        this.testPojoService = testPojoService;
    }

    /**
     * @response.200.content.type
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestPojo> getPojo() throws Exception {
        return testPojoService.getAll();
    }

    @GET
    @Path("{pojoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TestPojo getPojo(@PathParam("pojoId") Long pojoId) throws Exception {
        return testPojoService.getByPK(pojoId);
    }

    @PUT
    public Long putPojo(TestPojo pojo) throws Exception {
        return (Long) testPojoService.insert(pojo);
    }

    @DELETE
    @Path("{pojoId}")
    public void deletePojo(@PathParam("pojoId") Long pojoId) throws Exception {
        testPojoService.deleteByPK(pojoId);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public void updatePojo(TestPojo pojo) throws Exception {
        testPojoService.update(pojo);
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

    @GET
    public Long count() throws Exception {
        return testPojoService.getCount();
    }
}
