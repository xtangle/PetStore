package com.example.bo;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PS_PETS")
public class PetBO implements IStorable, Serializable {

  private static final long serialVersionUID = 5228540098964594713L;

  @Id
  @Column(name = "pet_id")
  @SequenceGenerator(name = "ps_pet_sequence", sequenceName = "PS_PET_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ps_pet_sequence")
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private CategoryBO category;

  @Column(name = "pet_name", nullable = false)
  private String name;

  @ElementCollection()
  @CollectionTable(name = "PS_PETS_PHOTOURLS")
  private List<String> photoURLs = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  private List<TagBO> tags = new ArrayList<>();

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private PetStatus status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CategoryBO getCategory() {
    return category;
  }

  public void setCategory(CategoryBO category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getPhotoURLs() {
    return photoURLs;
  }

  public void setPhotoURLs(List<String> photoURLs) {
    this.photoURLs = photoURLs;
  }

  public List<TagBO> getTags() {
    return tags;
  }

  public void setTags(List<TagBO> tags) {
    this.tags = tags;
  }

  public PetStatus getStatus() {
    return status;
  }

  public void setStatus(PetStatus status) {
    this.status = status;
  }

}
