package org.bmach01.AcKeyAPI.controller;

import org.bmach01.AcKeyAPI.domain.response.UserResponse;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.bmach01.AcKeyAPI.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserManagementService userManagementService;

    public UserController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/deactivate")
    public ResponseEntity<UserResponse> deactivateUser(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(userManagementService.deactivateUser(request));
    }
}
