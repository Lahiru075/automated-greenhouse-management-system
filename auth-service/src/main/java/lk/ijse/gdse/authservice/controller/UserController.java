package lk.ijse.gdse.authservice.controller;

import lk.ijse.gdse.authservice.dto.ApiResponse;
import lk.ijse.gdse.authservice.dto.UserDTO;
import lk.ijse.gdse.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth"})
@CrossOrigin({"*"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    private ResponseEntity<ApiResponse> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        "User registered successfully",
                        userService.register(userDTO)
                )
        );
    }

}
