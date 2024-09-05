package org.bmach01.AcKeyAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
public class ActivationOTPsController {

//    @Autowired
//    private final UserRepository;

//    @Autowired
//    private final activationOTPsRepository;



    @PostMapping
    public ResponseEntity<String> registerNewDevice(
            @RequestHeader("Authorization") String request
    ) {
        return ResponseEntity.ok().build();
    }
}
