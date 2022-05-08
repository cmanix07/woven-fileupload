package com.cmani.woven.filestorage.service.storage;

import com.cmani.woven.filestorage.model.StorageDTO;

import java.util.Map;

/**
 * StorageStrategyService class contains the skeleton for implementation.
 */
public interface StorageStrategyService {

    Map<String, String> store(StorageDTO storageDto);

}
