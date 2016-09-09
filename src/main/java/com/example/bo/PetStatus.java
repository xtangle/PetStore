package com.example.bo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public enum PetStatus implements INamedObject {

  AVAILABLE, PENDING, SOLD;

  public static PetStatus getByName(String name) {
    return valueOf(StringUtils.upperCase(name));
  }

  @Override
  public String getName() {
    return WordUtils.capitalizeFully(this.name());
  }

}
