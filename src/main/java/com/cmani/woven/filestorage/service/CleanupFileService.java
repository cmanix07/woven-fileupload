package com.cmani.woven.filestorage.service;

import com.cmani.woven.filestorage.config.AppConfig;
import com.cmani.woven.filestorage.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Slf4j
@Service
public class CleanupFileService {

    private final FileRepository fileRepository;
    private final AppConfig appConfig;

    private final String SEPARATOR = FileSystems.getDefault().getSeparator();

    @Autowired
    public CleanupFileService(FileRepository fileRepository, AppConfig appConfig) {
        this.fileRepository = fileRepository;
        this.appConfig = appConfig;
    }

    public void deleteFile(String fileName,String tenant) throws IOException {
        System.out.println("To be Cleanup the file is : "+fileName);
        String basePath = appConfig.getFileDbLocation();
        Path path = Paths.get(basePath+SEPARATOR+tenant+SEPARATOR+fileName);
        Files.delete(path);
        System.out.println("File "+fileName+ " has been deleted at absolute default path  : "+path.toString());

    }
}
