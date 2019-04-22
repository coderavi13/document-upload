package com.ravi.documentupload;

import org.springframework.data.repository.CrudRepository;

import com.ravi.documentupload.model.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

}
