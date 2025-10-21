package com.docp.user_service.services;


import com.docp.user_service.dto.UserRequest;
import com.docp.user_service.dto.UserResponse;
import com.docp.user_service.model.Address;
import com.docp.user_service.model.User;
import com.docp.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest user) {
        User newUser = new User();
        mapToUser(newUser, user);
        userRepository.save(newUser);
        return mapToUserResponse(newUser);
    }

    private void mapToUser(User newUser, UserRequest user) {
        Address userAddress = new Address();
        newUser.setEmail(user.email());
        newUser.setUsername(user.username());
        newUser.setPassword(user.password());
        newUser.setFirstName(user.firstName());
        newUser.setLastName(user.lastName());
        if (user.address() != null) {
            userAddress.setCity(user.address().city());
            userAddress.setCountry(user.address().country());
            userAddress.setState(user.address().state());
            userAddress.setPinCode(user.address().pinCode());
            newUser.setAddress(userAddress);
        }
    }


    public UserResponse findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::mapToUserResponse).orElse(null);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }


}
