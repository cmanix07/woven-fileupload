package com.cmani.woven.filestorage.service.storage;

import com.cmani.woven.filestorage.model.StorageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStorageStrategyFactory {

    private final StorageStrategy storageStrategy;

    private final FilePerDateStorageStrategyService filePerDateStorageStrategyService;
    private final FileStorageStrategyService fileStorageStrategyService;

    @Autowired
    public FileStorageStrategyFactory(@Value("${storage.strategy}") StorageStrategy storageStrategy,
                                      FilePerDateStorageStrategyService filePerDateStorageStrategyService,
                                      FileStorageStrategyService fileStorageStrategyService
    ) {
        this.storageStrategy = storageStrategy;
        this.filePerDateStorageStrategyService = filePerDateStorageStrategyService;
        this.fileStorageStrategyService = fileStorageStrategyService;
    }

    public StorageStrategyService getStorageStrategyMode() {

        switch (storageStrategy) {
            case FILE:
                return fileStorageStrategyService;
            case FILE_PER_DATE:
                return filePerDateStorageStrategyService;
            default:
                throw new RuntimeException("No strategy was found!");
        }
    }

}
