package com.example.model;

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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PS_PETS")
public class Pet implements IStorable {

  @Id
  @Column(name = "pet_id")
  @SequenceGenerator(name = "ps_pet_sequence", sequenceName = "PS_PET_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ps_pet_sequence")
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Category category;

  @Column(name = "pet_name", nullable = false)
  private String name;

  @ElementCollection()
  @CollectionTable(name = "PS_PETS_PHOTOURLS")
  private List<String> photoURLs;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Tag> tags;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private PetStatusType status;

  public Pet() {
    this(null, null, null);
  }

  public Pet(Category category, String name, PetStatusType status) {
    super();
    this.category = category;
    this.name = name;
    this.photoURLs = new ArrayList<>();
    this.tags = new ArrayList<>();
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
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

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public PetStatusType getStatus() {
    return status;
  }

  public void setStatus(PetStatusType status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pet other = (Pet) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Pet [id=" + id + ", category=" + category + ", name=" + name + ", photoURLs=" + photoURLs + ", tags=" + tags
        + ", status=" + status + "]";
  }

}
