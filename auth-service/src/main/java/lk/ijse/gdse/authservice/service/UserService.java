package lk.ijse.gdse.authservice.service;

import lk.ijse.gdse.authservice.dto.UserDTO;
import lk.ijse.gdse.authservice.entity.User;

public interface UserService {
    User register(UserDTO userDTO);
}
