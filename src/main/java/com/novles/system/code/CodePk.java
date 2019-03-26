package com.novles.system.code;

import java.io.Serializable;

import lombok.Data;

@Data
public class CodePk implements Serializable {
    private static final long serialVersionUID = -321135553240828135L;

    private String key;
    private String value;
}
