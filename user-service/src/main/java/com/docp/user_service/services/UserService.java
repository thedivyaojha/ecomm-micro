package com.docp.user_service.services;


import com.docp.user_service.dto.UserRequest;
import com.docp.user_service.dto.UserResponse;
import com.docp.user_service.model.Address;
import com.docp.user_service.model.User;
import com.docp.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest user) {
        User newUser = new User();
        updateUserFromRequest(newUser, user);
        userRepository.save(newUser);
        return mapToUserResponse(newUser);
    }

    private void updateUserFromRequest(User newUser, UserRequest userRequest) {
        newUser.setFirstName(userRequest.firstName());
        newUser.setLastName(userRequest.lastName());
        newUser.setEmail(userRequest.email());
        if (userRequest.address() != null) {
            Address address = new Address();
            address.setStreetName(userRequest.address().streetName());
            address.setCity(userRequest.address().city());
            address.setState(userRequest.address().state());
            address.setPinCode(userRequest.address().pinCode());
            address.setCountry(userRequest.address().country());
            newUser.setAddress(address);
            newUser.setUsername(userRequest.username());
            newUser.setPassword(userRequest.password());
        }
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
