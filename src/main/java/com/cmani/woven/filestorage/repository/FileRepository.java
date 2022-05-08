package com.cmani.woven.filestorage.repository;

import com.cmani.woven.filestorage.model.FileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<FileEntity, Long> {

    @Query(name = "select * from file_entity where file_id = ?1 limit 1", nativeQuery = true)
    List<FileEntity> findByFileId(final String fileId);

    @Query(value = "select * from file_entity", nativeQuery = true)
    List<FileEntity> getFileForDeleting(final Integer age);


}
