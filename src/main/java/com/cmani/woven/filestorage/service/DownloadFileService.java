package com.cmani.woven.filestorage.service;

import com.cmani.woven.filestorage.model.FileEntity;
import com.cmani.woven.filestorage.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class contains implementation related to file download methods
 */
@Slf4j
@Service
public class DownloadFileService {

    private final FileRepository fileRepository;

    @Autowired
    public DownloadFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public byte[] download(String fileId) {
        log.info("Find data for file id: {}", fileId);
        List<FileEntity> fileEntityList = fileRepository.findByFileId(fileId.trim());

        FileEntity fileEntity;
        if(!fileEntityList.isEmpty()) {
            fileEntity = fileEntityList.get(0);
        } else {
            throw new RuntimeException("No record was found for file ID: " + fileId);
        }

        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(fileEntity.getPath()));
        } catch(Exception exception) {
            log.error("Can not to download fom this path: {}, throw exception: {}", fileEntity.getPath(), exception);
        }

        return bytes;
    }

}
