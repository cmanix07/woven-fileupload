package com.cmani.woven.filestorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private String code;
    private String description;
    private String fullStack;

}
