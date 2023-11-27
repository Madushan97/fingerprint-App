package com.cba.mpos.aoer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

@AllArgsConstructor
@NoRepositoryBean
@Data
public class StandardResponse {

    private int code;
    private String message;
    private Object data;
}
