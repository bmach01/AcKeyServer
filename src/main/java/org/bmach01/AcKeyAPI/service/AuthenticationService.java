package org.bmach01.AcKeyAPI.service;


import org.bmach01.AcKeyAPI.dao.ActivationCodeRepository;
import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.activationCode.ActivationCode;
import org.bmach01.AcKeyAPI.domain.response.AuthenticationResponse;
import org.bmach01.AcKeyAPI.domain.user.AccountStatus;
import org.bmach01.AcKeyAPI.domain.user.Role;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ActivationCodeRepository activationCodeRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository repository,
            ActivationCodeRepository otpRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = repository;
        this.activationCodeRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(String request) {

        ActivationCode code = activationCodeRepository.findByCode(request).orElseThrow();

        if (code.isUsed())
            return new AuthenticationResponse("Invalid activation code");

        User user = userRepository.findById(code.getUserId()).orElseThrow();

        if (user.getStatus() != AccountStatus.INACTIVE)
            return new AuthenticationResponse("Invalid activation code");


        user.setPassword(passwordEncoder.encode("password"));
        user.setStatus(AccountStatus.ACTIVE);
        user.setRole(Role.USER);

        code.setUsed(true);
        code.setUsedOn(new Date());

        userRepository.save(user);
        activationCodeRepository.save(code);

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(User request) {
        // If this throws then authentication failed and controller will send out 403
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
