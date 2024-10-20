package org.bmach01.AcKeyAPI.controller;

import org.bmach01.AcKeyAPI.domain.accessKey.AccessKey;
import org.bmach01.AcKeyAPI.domain.response.KeyResponse;
import org.bmach01.AcKeyAPI.service.AccessKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/key")
public class AccessKeyController {

    private final AccessKeyService accessKeyService;

    public AccessKeyController(AccessKeyService accessKeyService) {
        this.accessKeyService = accessKeyService;
    }

    @PostMapping("/accessKey")
    public ResponseEntity<AccessKey> generateKey(
            @RequestHeader("Authorization") String header
    ) {
        return ResponseEntity.ok(accessKeyService.generateKey(header));
    }

}
