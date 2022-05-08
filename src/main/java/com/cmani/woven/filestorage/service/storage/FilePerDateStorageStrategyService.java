package com.cmani.woven.filestorage.service.storage;

import com.cmani.woven.filestorage.config.AppConfig;
import com.cmani.woven.filestorage.model.FileEntity;
import com.cmani.woven.filestorage.model.StorageDTO;
import com.cmani.woven.filestorage.repository.FileRepository;
import com.cmani.woven.filestorage.util.FileUtils;
import com.cmani.woven.filestorage.util.MapHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

import static com.cmani.woven.filestorage.util.FileUtils.getCurrentDateAsString;

/**
 * This class contains implementation for file storage rule:
 *          ${file.db.location} / ${tenant} / ${date} / ${file_id}
 * where:
 *  - ${file.db.location}   the main path to file storage;
 *  - ${tenant}             an generic identifier
 *  - ${date}               a folder, with yyyy-MM-dd date format
 *  - ${file_id}            an identifier given by system to content
 */

@Slf4j
@Service
public class FilePerDateStorageStrategyService implements StorageStrategyService {

    private final AppConfig appConfig;
    private final FileRepository fileRepository;

    @Autowired
    public FilePerDateStorageStrategyService(AppConfig appConfig,
                                             FileRepository fileRepository){
        this.appConfig = appConfig;
        this.fileRepository = fileRepository;
    }

    @Override
    public Map<String, String> store(StorageDTO storageDto) {
        final String tenant = storageDto.getTenant();
        final MultipartFile multipartFile = storageDto.getMultipartFile();

        final String directoryName = getCurrentDateAsString();
        final String fileId = FileUtils.createIdIfNull(storageDto.getFileId());
        final String filePath = FileUtils.computeAbsolutePath(
                appConfig.getFileDbLocation(), directoryName, fileId, tenant
        );

        try {
            FileUtils.checkAndCreateDirectories(computeLocation(tenant, directoryName));
            saveFile(fileId, filePath, directoryName, tenant);
            multipartFile.transferTo(new File(filePath));
        } catch (Exception exception) {
            log.error("Can not to save file: " + filePath + " exception: " + exception);
        }

        return MapHelper.create(fileId, filePath);
    }

    private FileEntity saveFile(final String fileId,
                                final String filePath,
                                final String directoryName,
                                final String tenant) {
        FileEntity fileEntity = new FileEntity(
                 fileId, directoryName, filePath, tenant
        );

        log.info("Save to database the file id: {}", fileId);

        return fileRepository.save(fileEntity);
    }

    private String computeLocation(final String tenant, final String directoryName) {
        return appConfig.getFileDbLocation() + "/" + tenant + "/" + directoryName;
    }
}
