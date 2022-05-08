package com.cmani.woven.filestorage.controller;

import com.cmani.woven.filestorage.model.CreateFileResponseDTO;
import com.cmani.woven.filestorage.service.TenantService;
import com.cmani.woven.filestorage.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * In this class are implemented all end-points related to file upload.
 */
@Slf4j
@RestController
@Api("Download File Controller API")
public class UploadFileController {

    private final UploadFileService uploadFileService;
    private final TenantService tenantService;

    @Autowired
    public UploadFileController(UploadFileService uploadFileService,
                                TenantService tenantService) {
        this.uploadFileService = uploadFileService;
        this.tenantService = tenantService;
    }

    @PostMapping("uploadNewFile/{tenant}")
    @ApiOperation("upload new file by tenant")
    public CreateFileResponseDTO upload(@PathVariable String tenant,
                                        @RequestPart("file") MultipartFile multipartFile) {
        log.info("Receive request to upload file for tenant: {}", tenant);

        tenantService.checkIfTenantIsAllowed(tenant);

        return uploadFileService.upload(multipartFile, tenant);
    }
}
