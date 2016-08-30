package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PS_CATEGORIES")
@JsonIgnoreProperties({ "pets" })
public class Category implements IStorable {

  @Id
  @Column(name = "category_id")
  @SequenceGenerator(name = "ps_category_sequence", sequenceName = "PS_CATEGORY_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ps_category_sequence")
  private long id;

  @Column(name = "category_name", nullable = false, unique = true, length = 64)
  private String name;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private Set<Pet> pets;

  public Category() {
    this(null);
  }

  public Category(String name) {
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
    Category other = (Category) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Category [id=" + id + ", name=" + name + "]";
  }

}
