package com.locomizer.resttest.entities;

import javax.ws.rs.FormParam;

public class SearchPojo extends TestPojo {
    @Override
    @FormParam("id")
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    @FormParam("name")
    public void setName(String name) {
        super.setName(name);
    }

    @FormParam("flag")
    public void setFlag(String flag) {
        if (!flag.isEmpty()) {
            super.setFlag(Boolean.valueOf(flag));
        }
    }

    @FormParam("enum")
    public void setTestEnum(String testEnum) {
        if (!testEnum.isEmpty()) {
            super.setTestEnum(TestEnum.valueOf(testEnum));
        }
    }
}
