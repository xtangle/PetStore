package com.example.transform;

import org.springframework.stereotype.Component;

import com.example.bo.INamedObject;

@Component
public class NamedObjectTransformer extends BaseTransformer<INamedObject, String> {

  @Override
  public String toDTO(INamedObject bo) {
    if (bo == null) {
      return null;
    }
    return bo.getName();
  }

}
