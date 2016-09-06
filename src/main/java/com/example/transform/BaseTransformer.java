package com.example.transform;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseTransformer<BO, DTO> implements Transformer<BO, DTO> {

  @Override
  public abstract DTO toDTO(BO bo);

  @Override
  public List<DTO> toDTOList(Collection<? extends BO> boList) {
    if (boList == null) {
      return null;
    }
    List<DTO> dtoList = boList.stream()
        .map(bo -> this.toDTO(bo))
        .collect(Collectors.toList());
    return dtoList;
  }

}
