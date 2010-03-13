package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class IMAGES implements Serializable {
	@Id
	private String id;

	private String gallery;

	@Lob
	private byte[] image;

	private static final long serialVersionUID = 1L;

	public IMAGES() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGallery() {
		return this.gallery;
	}

	public void setGallery(String gallery) {
		this.gallery = gallery;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
