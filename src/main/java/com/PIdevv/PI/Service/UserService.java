package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.User;
import com.PIdevv.PI.Repository.RoleRepository;
import com.PIdevv.PI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long IdUser) {
     return    userRepository.findById(IdUser).orElse(null);
    }


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

@Override

public User UpdateUser (User user ){

userRepository.save(user);
return user;
}


    @Override
    public User updateUser(Long UserId, User user) {
        User existingUser = userRepository.findById(UserId).orElse(null);
        if (existingUser != null) {

            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setPlaceBirth(user.getPlaceBirth());
            existingUser.setJob(user.getJob());
            existingUser.setAdress(user.getAdress());
            existingUser.setPostalCode(user.getPostalCode());
            existingUser.setGender(user.getGender());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setActived(true);
            existingUser.setBanned(false);
            existingUser.setBannedPeriode(null);
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Integer IdUser) {

    }

    @Override
    public void deleteUser(Long IdUser) {
        System.out.println(IdUser);
        userRepository.deleteById(IdUser);
    }

    @Override
    public User saveUser(String username, String password, String confirmedPassword, String role) {
        return null;
    }

    /*@Override
    public User saveUser(String username, String password, String confirmedPassword,String role) {
        User  user=userRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        User User=new User();
        User.setUsername(username);
        User.setActived(true);
        User.setPassword(passwordEncoder.encode(password));

        userRepository.save(User);

        return User;
    }*/





  /* @Override
    public void addRoleToUser(String username, String name) {
        User User=userRepository.findByUsername(username);
        Role Role= roleRepository.findByRoleName(name);
        User.getRoles().add(Role);
        userRepository.save(User);
    }*/
  @Override
  public User UpdatePassword(User user, String password) {
      PasswordEncoder encoder=new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(password));
      userRepository.save(user);
      return user;
  }

    @Override
    public User banUser(Long Iduser,int nbreDay) {
        User user=userRepository.findByIdUser(Iduser);
        user.setBanned(true);
        user.setBannedPeriode(new Date(new Date().getTime()+(nbreDay*1000 * 60 * 60 * 24)));
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getBannedUser() {

        return userRepository.findByBanned(true);
    }

    public int calculateUserScore(Long idUser) {
        int score = 0;
        User user= userRepository.findByIdUser(idUser);
        // Check user age
     /*   int userAge = calculateUserAge(user.getBirthDate());
        if (userAge < 18) {
            score -= 50;
        } else if (userAge >= 18 && userAge <= 30) {
            score += 20;
        } else if (userAge > 30 && userAge <= 50) {
            score += 10;
        } else {
            score -= 20;
        }*/

        // Check user gender
        if (user.getGender().equalsIgnoreCase("male")) {
            score += 10;
        } else if (user.getGender().equalsIgnoreCase("female")) {
            score += 20;
        }

        // Check user location
        if (user.getPlaceBirth().equalsIgnoreCase("New York")) {
            score += 30;
        } else if (user.getPlaceBirth().equalsIgnoreCase("Los Angeles")) {
            score += 20;
        } else if (user.getPlaceBirth().equalsIgnoreCase("Chicago")) {
            score += 10;
        }

        // Check user job


        // Check user address
        if (user.getAdress().toLowerCase().contains("Mallacine")) {
            score += 10;
        } else if (user.getAdress().toLowerCase().contains("Ibn sina")) {
            score += 20;
        } else if (user.getAdress().toLowerCase().contains("Rades")) {
            score += 30;
        }

        // Check if the user is banned
//        if (user.getBanned()== true) {
//            score -= 100;
//        }

        return score;
    }





}
