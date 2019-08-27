package com.cloud.ftl.ftlbasic.webEntity;

import lombok.Data;

@Data
public class BasePage {

    private transient Integer page;

    private transient Integer pageSize;

    private transient Integer index;
}
