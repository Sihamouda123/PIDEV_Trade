package com.PIdevv.PI.Service;

import com.PIdevv.PI.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    User getUserById(Long IdUser);



    User createUser(User user);
    User updateUser(Long IdUser, User user);

    User UpdateUser(User user);

    void deleteUser(Integer  IdUser);

    void deleteUser(Long IdUser);

    User saveUser(String username, String password, String confirmedPassword, String role);

    public User UpdatePassword(User user, String password);

    // public User saveUser(String username, String password, String confirmedPassword,String role);

   // public UserDetails loadUserByUsername(String username);

   // public void addRoleToUser(String username,String name);

   // public User findUserByUserName(String userName) ;

   public User banUser(Long Iduser,int nbreDay);
    List<User> getBannedUser();


}
