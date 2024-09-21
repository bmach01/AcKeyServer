package org.bmach01.AcKeyAPI.service;

import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.response.UserResponse;
import org.bmach01.AcKeyAPI.domain.user.AccountStatus;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserManagementService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public UserResponse deactivateUser(User request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        user.setStatus(AccountStatus.INACTIVE);
        userRepository.save(user);

        return new UserResponse("User deactivated successfully");
    }

}
