package com.docp.user_service.services;


import com.docp.user_service.dto.UserRequest;
import com.docp.user_service.dto.UserResponse;
import com.docp.user_service.model.Address;
import com.docp.user_service.model.User;
import com.docp.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User newUser = new User();
        mapUserFromRequest(newUser, userRequest);
        User savedUser =userRepository.save(newUser);
        return mapToUserResponse(savedUser);
    }
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User existingUser = optionalUser.get();
        mapUserFromRequest(existingUser,userRequest);
    User UpdatedUser = userRepository.save(existingUser);
        return mapToUserResponse(UpdatedUser);
    }

    public UserResponse mapUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());
        if (userRequest.address() != null) {
            Address address = new Address();
            address.setStreetName(userRequest.address().streetName());
            address.setCity(userRequest.address().city());
            address.setState(userRequest.address().state());
            address.setPinCode(userRequest.address().pinCode());
            address.setCountry(userRequest.address().country());
            user.setAddress(address);

        }


        return null;
    }


    public UserResponse findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::mapToUserResponse).orElse(null);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public List<User> findAllUsers(){
        List<User> allUsers =  userRepository.findAll();

        return allUsers;
    }


    public Boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Boolean validateUser(Long id) {
        if(userRepository.existsById(id)){
            return true;
        }
        return false;
    }
}
