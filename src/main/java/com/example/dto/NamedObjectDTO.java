package com.example.dto;

import java.io.Serializable;

public class NamedObjectDTO implements Serializable {

  private static final long serialVersionUID = -7873785570210196983L;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
