package com.example.transform;

import com.example.bo.UserBO;
import com.example.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer extends BaseTransformer<UserBO, UserDTO> {

  @Override
  public UserDTO toDTO(UserBO bo) {
    if (bo == null) {
      return null;
    }

    UserDTO dto = new UserDTO();
    dto.setUsername(bo.getUsername());
    dto.setEmail(bo.getEmail());
    dto.setFirstName(bo.getFirstName());
    dto.setLastName(bo.getLastName());
    dto.setPassword(bo.getPassword());
    dto.setPhoneNumber(bo.getPhoneNumber());
    dto.setStatus(bo.getStatus());

    return dto;
  }

}
