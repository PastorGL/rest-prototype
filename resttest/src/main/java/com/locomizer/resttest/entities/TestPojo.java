package com.locomizer.resttest.entities;

import io.github.pastorgl.fastdao.Column;
import io.github.pastorgl.fastdao.FastEntity;
import io.github.pastorgl.fastdao.PK;
import io.github.pastorgl.fastdao.Table;

@Table(TestPojo.TABLE_NAME)
public class TestPojo extends FastEntity {
    public static final String TABLE_NAME = "test_pojo";
    public static final String NAME_NAME = "test_varchar";
    public static final String FLAG_NAME = "test_bool";
    public static final String ENUM_NAME = "tet_enum";

    @PK
    private Long id;

    @Column(NAME_NAME)
    private String name;

    @Column(FLAG_NAME)
    private Boolean flag;

    @Column(ENUM_NAME)
    private TestEnum testEnum;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public TestEnum getTestEnum() {
        return testEnum;
    }

    public void setTestEnum(TestEnum testEnum) {
        this.testEnum = testEnum;
    }
}
