package com.example.dto;

import java.io.Serializable;
import java.util.List;

public class PetDTO implements Serializable {

  private static final long serialVersionUID = -5937309100534475164L;

  private long id;
  private String name;
  private String category;
  private List<String> photoURLs;
  private List<String> tags;
  private String status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public List<String> getPhotoURLs() {
    return photoURLs;
  }

  public void setPhotoURLs(List<String> photoURLs) {
    this.photoURLs = photoURLs;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
