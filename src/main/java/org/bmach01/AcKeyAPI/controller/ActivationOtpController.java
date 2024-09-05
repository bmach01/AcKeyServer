package org.bmach01.AcKeyAPI.controller;

import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
public class ActivationOtpController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private activationOTPsRepository;



    @PostMapping
    public ResponseEntity<String> registerNewDevice(
            @RequestHeader("Authorization") String request
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        User user = userRepository.findAll().getFirst();
        return ResponseEntity.ok(user.toString());
    }
}
