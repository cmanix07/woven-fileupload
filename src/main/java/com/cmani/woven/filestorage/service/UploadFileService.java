package com.cmani.woven.filestorage.service;

import com.cmani.woven.filestorage.config.AppConfig;
import com.cmani.woven.filestorage.model.CreateFileResponseDTO;
import com.cmani.woven.filestorage.model.FileEntity;
import com.cmani.woven.filestorage.model.StorageDTO;
import com.cmani.woven.filestorage.repository.FileRepository;
import com.cmani.woven.filestorage.service.storage.FileStorageStrategyFactory;
import com.cmani.woven.filestorage.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;

/**
 * This class contains implementation related to file upload methods
 */
@Slf4j
@Service
public class UploadFileService {

    private final AppConfig appConfig;
    private final FileRepository fileRepository;
    private final FileStorageStrategyFactory fileStorageStrategyFactory;

    @Autowired
    public UploadFileService(AppConfig appConfig,
                             FileRepository fileRepository,
                             FileStorageStrategyFactory fileStorageStrategyFactory) {
        this.appConfig = appConfig;
        this.fileRepository = fileRepository;
        this.fileStorageStrategyFactory = fileStorageStrategyFactory;
    }

    public CreateFileResponseDTO upload(MultipartFile multipartFile, String tenant) {

        log.info("Receive multipart file request for tenant id: {}", tenant);

        StorageDTO storageDto = new StorageDTO();
        storageDto.setTenant(tenant);
        storageDto.setMultipartFile(multipartFile);

        Map<String, String> map = fileStorageStrategyFactory.getStorageStrategyMode().store(storageDto);

        return new CreateFileResponseDTO(map.get("FILE_ID"), tenant, map.get("FILE_PATH"), Instant.now());
    }



    private void saveFile(ByteArrayResource multipartFile, final String filePath) {
        try {
            Files.write(Paths.get(filePath), multipartFile.getByteArray());
        } catch (Exception exception) {
            log.error("Can not to save file: {} with exception: {}", filePath, exception);
        }
    }

    private FileEntity saveDocument(final String fileId,
                                    final String filePath,
                                    final String directoryName,
                                    final String tenant) {
        FileEntity fileEntity = new FileEntity(
                fileId, directoryName, filePath, tenant
        );

        log.info("Save to database the file id: {}", fileId);

        return fileRepository.save(fileEntity);
    }

    private String computeAbsoluteFilePath(final String directoryName, final String fileId, final String tenant) {
        return appConfig.getFileDbLocation() + "/" + tenant + "/" + directoryName + "/" + fileId;
    }

    private void checkAndCreateDirectoryByTenant(final String tenant, final String directoryName) {
        try {
            final String location = appConfig.getFileDbLocation() + "/" + tenant + "/" + directoryName;
            FileUtils.checkAndCreateDirectory(location);
            Files.createDirectories(Paths.get(location));
        } catch (Exception exception) {
            log.error("Could not check or create directory: {}", exception);
        }
    }

}
