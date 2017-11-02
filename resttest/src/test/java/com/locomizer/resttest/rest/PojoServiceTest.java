package com.locomizer.resttest.rest;

import com.locomizer.resttest.entities.SearchPojo;
import com.locomizer.resttest.entities.TestEnum;
import com.locomizer.resttest.entities.TestPojo;
import com.locomizer.resttest.service.TestPojoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PojoServiceTest extends TestBase {
    static TestPojoService te = injector.getInstance(TestPojoService.class);

    @Test
    public void testInsertDelete() {
        long count0 = te.getCount();

        te.insert(new TestPojo() {{
            setTestEnum(TestEnum.ONE);
            setFlag(false);
            setName("pojoname");
        }});
        te.insert(new TestPojo() {{
            setTestEnum(TestEnum.TWO);
            setFlag(null);
            setName("pojoname");
        }});
        te.insert(new TestPojo() {{
            setTestEnum(TestEnum.THREE);
            setFlag(true);
            setName("pojoname");
        }});

        long count1 = te.getCount();

        assertEquals(count0 + 3, count1);

        List<TestPojo> list0 = te.getAll();

        assertEquals(count1, list0.size());

        TestPojo tp0 = list0.get(0);

        te.deleteByPK(tp0.getId());

        List<TestPojo> list1 = te.getAll();

        assertFalse(list1.contains(tp0));
        assertEquals(list0.size() - 1, list1.size());
    }


    @Test
    public void testUpdateSelectByPK() {
        TestPojo tp0 = new TestPojo() {{
            setTestEnum(TestEnum.THREE);
            setFlag(true);
            setName("not a name");
        }};

        Long id0 = (Long)te.insert(tp0);

        TestPojo tp1 = new TestPojo() {{
            setId(id0);
            setTestEnum(TestEnum.ONE);
            setFlag(false);
            setName("changed");
        }};

        te.update(tp1);

        TestPojo tp2 = te.getByPK(id0);

        assertEquals(tp1, tp2);
    }

    @Test
    public void testSearchGetById() {
        TestPojo tp0 = new TestPojo() {{
            setTestEnum(TestEnum.ONE);
            setFlag(true);
            setName("string UNIQUESTRING string");
        }};

        TestPojo tp2 = new TestPojo() {{
            setTestEnum(TestEnum.ONE);
            setFlag(true);
            setName("string NOTTHATSTRING string");
        }};

        Long id0 = (Long)te.insert(tp0);
        tp2.setId((Long) te.insert(tp2));

        List<TestPojo> list0 = te.search(new SearchPojo() {{
            setName("UNIQUESTRING");
        }});

        assertTrue(list0.size() > 0);

        TestPojo tp1 = te.getByPK(id0);

        assertTrue(list0.contains(tp1));
        assertFalse(list0.contains(tp2));

        List<TestPojo> list1 = te.search(new SearchPojo() {{
            setTestEnum(TestEnum.ONE);
            setFlag(true);
        }});

        assertTrue(list1.size() > 0);

        assertTrue(list1.contains(tp1));
        assertTrue(list1.contains(tp2));
    }
}
