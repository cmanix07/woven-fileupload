package com.cmani.woven.filestorage.controller;

import com.cmani.woven.filestorage.service.CleanupFileService;
import com.cmani.woven.filestorage.service.DownloadFileService;
import com.cmani.woven.filestorage.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * In this class are implemented all end-points related to file delete.
 */
@Slf4j
@RestController
@Api("Cleanup File Controller API")
public class CleanupFileController {

    CleanupFileService cleanupFileService;
    TenantService tenantService;

    @Autowired
    public CleanupFileController(CleanupFileService cleanupFileService, TenantService tenantService){
        this.cleanupFileService = cleanupFileService;
        this.tenantService = tenantService;
    }
    @GetMapping(value = "delete/files/{tenant}/{fileName}")
    @ApiOperation("delete files functionality")
    @ResponseBody
    public ResponseEntity deleteFile(@PathVariable String fileName,String tenant){
        try {
            cleanupFileService.deleteFile(fileName,tenant);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
