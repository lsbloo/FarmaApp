package com.farmawebservice.delivery.user.service;


import com.farmawebservice.delivery.user.model.User;
import com.farmawebservice.delivery.user.model.UserDTO;
import com.farmawebservice.delivery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepositoryx) {
        this.userRepository = userRepositoryx;
    }


    public Long saveUser(UserDTO userDTO) {
        User user = this.userRepository.findByUserAuthId(userDTO.getUser_auth_id());
        if (user == null) {
            User u = new User();
            u.setUserAuthId(userDTO.getUser_auth_id());
            User re = this.userRepository.save(u);
            return re.getId();
        } else {
            return user.getId();
        }
    }
}
