package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.Models.User;
import com.example.FinanceTracker.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;

    public List<User> getUserList(){
        return userRepository.findAll();
    }
    public long addUser(User user) {

        return userRepository.save(user).getId();
    }
    public void  deleteUser(long  Id) {
         userRepository.deleteById(Id);
    }

    public long updateUser(User user) {
        return userRepository.save(user).getId();
    }

    public User getUser(long  Id) {
        return userRepository.findById(Id).orElse(null);
    }
}

