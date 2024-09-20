package com.exam.service.impl;

import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        //check user already in database
        //get username
      User local =   this.userRepository.findByUsername(user.getUsername());
      if(local!=null){
          System.out.println("User is already there !!");
          throw new UserFoundException();
      }else{
          //now get role one by one and save in database
          for(UserRole ur: userRoles)
          {
              roleRepository.save(ur.getRole());
          }
          // set all roles
          user.getUserRoles().addAll(userRoles);//assign roles to user
          local = this.userRepository.save(user);//user save

      }
        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleleUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
