package com.ravi.documentupload.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Document {
	@Id
	private long id;
	private String name;
	@Lob
	private byte[] data;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}