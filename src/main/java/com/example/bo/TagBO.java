package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PS_TAGS")
@JsonIgnoreProperties({"pets"})
public class TagBO implements IStorable, INamedObject, Serializable {

	private static final long serialVersionUID = -8161026493237883515L;

	@Id
	@Column(name = "tag_id", nullable = false)
	@SequenceGenerator(name = "ps_tag_sequence", sequenceName = "PS_TAG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ps_tag_sequence")
	private long id;

	@Column(name = "tag_name", nullable = false, unique = true, length = 64)
	private String name;

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private Set<PetBO> pets = new HashSet<>();

	public TagBO() {
	}

	public TagBO(String name) {
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
