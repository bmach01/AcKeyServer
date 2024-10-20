package org.bmach01.AcKeyAPI.service;


import org.bmach01.AcKeyAPI.dao.ActivationCodeRepository;
import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.activationCode.ActivationCode;
import org.bmach01.AcKeyAPI.domain.response.LoginResponse;
import org.bmach01.AcKeyAPI.domain.response.RegisterResponse;
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

    private final PasswordGeneratorService passwordGeneratorService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            ActivationCodeRepository activationCodeRepository,
            PasswordGeneratorService passwordGeneratorService, PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.activationCodeRepository = activationCodeRepository;
        this.passwordGeneratorService = passwordGeneratorService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(String request) {

        ActivationCode code = activationCodeRepository.findByCodeAndUsed(request, false).orElseThrow();

        User user = userRepository.findByIdAndStatus(code.getUserId(), AccountStatus.INACTIVE).orElseThrow();

        String password = passwordGeneratorService.generatePassword();

        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(AccountStatus.ACTIVE);
        user.setRole(Role.USER);

        code.setUsed(true);
        code.setUsedOn(new Date());

        userRepository.save(user);
        activationCodeRepository.save(code);

        return new RegisterResponse(user.getUsername(), password);
    }

    public LoginResponse authenticate(User request) {
        // If this throws then authentication failed and controller will send out 403
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}
