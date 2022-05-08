package com.cmani.woven.filestorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StorageStrategy {

    /**
     * File storage rule: ${file.db.location} / ${tenant} / ${file_id}
     */
    FILE("FILE"),

    /**
     * File storage rule: ${file.db.location} / ${tenant} / ${date} / ${file_id}
     */
    FILE_PER_DATE("FILE_PER_DATE");


    private final String name;

}
