package com.LifePilot.LifePilot.service;


import com.LifePilot.LifePilot.dto.CreateUserRequest;
import com.LifePilot.LifePilot.dto.UserReponse;
import com.LifePilot.LifePilot.entity.Role;
import com.LifePilot.LifePilot.entity.User;
import com.LifePilot.LifePilot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;




    // Logic to Register the new user if not exists
    public UserReponse createUser(CreateUserRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already Registered. Try Logging In");
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        return new UserReponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }



}
