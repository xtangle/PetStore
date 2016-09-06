package com.example.transform;

import java.util.Collection;

public interface Transformer<BO, DTO> {

  DTO toDTO(BO bo);
  Collection<DTO> toDTOList(Collection<? extends BO> boList);

}
