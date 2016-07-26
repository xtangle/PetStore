package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PS_TAGS")
@JsonIgnoreProperties({ "pets" })
public class Tag implements IStorable {

  @Id
  @Column(name = "tag_id", nullable = false)
  @SequenceGenerator(name = "ps_tag_sequence", sequenceName = "PS_TAG_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ps_tag_sequence")
  private long id;

  @Column(name = "tag_name", nullable = false, unique = true, length = 64)
  private String name;

  @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
  private Set<Pet> pets;

  public Tag() {
    this(null);
  }

  public Tag(String name) {
    super();
    this.name = name;
    this.pets = new HashSet<>();
  }

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

  public Set<Pet> getPets() {
    return pets;
  }

  public void setPets(Set<Pet> pets) {
    this.pets = pets;
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
    Tag other = (Tag) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Tag [id=" + id + ", name=" + name + "]";
  }

}
