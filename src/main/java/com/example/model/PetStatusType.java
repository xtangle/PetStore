package com.example.model;

public enum PetStatusType {

  AVAILABLE("Available"), PENDING("Pending"), SOLD("Sold");

  private String name;

  public static PetStatusType getByName(String s) {
    if (s == null) {
      return null;
    }
    return valueOf(s.toUpperCase());
  }

  private PetStatusType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

}
