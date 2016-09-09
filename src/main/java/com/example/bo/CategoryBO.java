package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PS_CATEGORIES")
@JsonIgnoreProperties({"pets"})
public class CategoryBO implements IStorable, INamedObject, Serializable {

	private static final long serialVersionUID = 7258630973067387309L;

	@Id
	@Column(name = "category_id")
	@SequenceGenerator(name = "ps_category_sequence", sequenceName = "PS_CATEGORY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ps_category_sequence")
	private long id;

	@Column(name = "category_name", nullable = false, unique = true, length = 64)
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<PetBO> pets = new HashSet<>();

	public CategoryBO() {
	}

	public CategoryBO(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PetBO> getPets() {
		return pets;
	}

	public void setPets(Set<PetBO> pets) {
		this.pets = pets;
	}

}
