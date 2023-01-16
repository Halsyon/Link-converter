package com.linkconverter.model.dto;

import lombok.Data;

@Data
public class Code {

    private String uniqueLink;

    public static Code of(String uniqueLink) {
        Code code = new Code();
        code.uniqueLink = uniqueLink;
        return code;
    }
}
