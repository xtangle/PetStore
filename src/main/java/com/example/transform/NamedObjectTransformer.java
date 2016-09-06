package com.example.transform;

import org.springframework.stereotype.Component;

import com.example.bo.INamedObject;
import com.example.dto.NamedObjectDTO;

@Component
public class NamedObjectTransformer extends BaseTransformer<INamedObject, NamedObjectDTO> {

  @Override
  public NamedObjectDTO toDTO(INamedObject bo) {
    if (bo == null) {
      return null;
    }

    NamedObjectDTO dto = new NamedObjectDTO();
    dto.setName(bo.getName());
    return dto;
  }

}
