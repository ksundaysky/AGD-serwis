package com.ksundaysky.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksundaysky.model.Role;
import com.ksundaysky.model.User;
import com.ksundaysky.repository.RoleRepository;
import com.ksundaysky.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
//        Role[] role = new Role[1];
//        user.getRole().toArray(role);
//        Role userRole = roleRepository.findByRole(role[0].getRole());
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    @Override
    public User findUserById(int id) { return userRepository.findById(id);}

    @Override
    public void updateUser(User user) {

//        Role[] role = new Role[1];
////        user.getRoles().toArray(role);
////        Role userRole = roleRepository.findByRole(role[0].getRole());
////        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int id){

        userRepository.deleteById(id);
        //userRepository.delete(Long.valueOf((long)id));
    }
}