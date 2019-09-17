package com.cloud.ftl.ftlbasic.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderBy {

    private String field;

    private String mode;
}
