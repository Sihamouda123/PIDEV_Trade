package com.PIdevv.PI.Controller;


import com.PIdevv.PI.Entities.PasswordResetToken;
import com.PIdevv.PI.Entities.User;
import com.PIdevv.PI.Exception.UserNotFoundException;
import com.PIdevv.PI.Repository.UserRepository;
import com.PIdevv.PI.Service.*;
import com.PIdevv.PI.payload.response.MessageResponse;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private TwilioService twilioService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    CodeConfirmationService codeConfirmationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private HttpSession session;

  /*  @PostMapping("/register")
    public User register(@RequestBody  UserForm userForm){
        return  userService.saveUser(
                userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword(),userForm.getRole());
    }*/

    //http://localhost:8080/user/retrieve-user
    @GetMapping("/retrieve-user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //http://localhost:8080/user/get-user/{id}
    @GetMapping("/get-user/{user-id}")
    public User getUserById(@PathVariable("user-id") Long IdUser) {
        return userService.getUserById(IdUser);
    }

    //http://localhost:8080/user/create-user
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //http://localhost:8080/user/update/{user-id}
    @PutMapping("/update/{user-id}")
    public User updateUser(@PathVariable("user-id") Long UserId, @RequestBody User user) {
        return userService.updateUser(UserId, user);
    }
    @Value("${app.twillio.toPhoneNo}")
    private String to;
    @Value("${app.twillio.fromPhoneNo}")
    private String from;
    //http://localhost:8080/user/delete/{UserId}
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long IdUser) {

        String body = "fassakhnek maadch tji ";
        twilioService.sendSms(to, from, body);
        userService.deleteUser(IdUser);
    }




    @GetMapping("/banAgent/{IdUser}/{nbr}")
    public void banUser(@PathVariable("IdUser") Long IdUser,@PathVariable("nbr") int nbr) {
         userService.banUser(IdUser, nbr);

    }
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response, @RequestBody User c) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=User" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        //Account acc= AccSercice.retrieveAccount(c.getRib());
        AccountPdfExporter exporter = new AccountPdfExporter(c);
        exporter.export(response);
    }

    @GetMapping(value = "/PDF")
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
        List<User> p = (List<User>) userService.getAllUsers();

        ByteArrayInputStream bis = PDFGeneratorService.employeePDFReport(p);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Users.pdf");

        return ResponseEntity.ok().headers(headers)
                .body(new InputStreamResource(bis));


    }



    @GetMapping("/resetPassword/{token}/{pass}")
    public void resetPasswod(@PathVariable("token") String token,@PathVariable("pass") String pass) {
        resetPasswordService.ConfirmPasswordReset(token, pass);
    }


    @GetMapping("/resetUsername/{email}")
    public ResponseEntity<?> resetPasswod(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username does not exist!"));
        } else {
            String code = codeConfirmationService.generateCode();
            twilioService.sendSms("+21653448321", "+14754053188", code);
            session.setAttribute("expectedCode", code);
            session.setAttribute("user", user);
            return ResponseEntity
                    .ok(new MessageResponse("Merci de verifier votre code"));
        }
    }
    @PutMapping ("/confirm-code/{code}/{password}")
    public void confirmCode(@PathVariable String code,@PathVariable String password, HttpSession session, Model model) {
        String expectedCode = (String) session.getAttribute("expectedCode");
        // Vérifier si le code de confirmation est valide
        System.out.println(code);
        System.out.println(password);
        if (codeConfirmationService.verifyCode(code, expectedCode) ){
            User user = (User) session.getAttribute("user");
            PasswordEncoder encoder=new BCryptPasswordEncoder();
            user.setPassword(encoder.encode("123456"));
            userRepository.save(user);

        } else {

            model.addAttribute("error", "Le code de confirmation est invalide !");
        }
    }
    @GetMapping("/calculateUserScore/{IdUser}")
    public Integer calculateUserScore(@PathVariable("IdUser") Long IdUser) {
        return userService.calculateUserScore(IdUser);

    }

}