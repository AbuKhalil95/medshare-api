package com.medshare.controllers;

import com.medshare.models.User;
import com.medshare.repositories.UserRepository;
import com.medshare.services.UploadFileService;
import com.medshare.services.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public
class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UploadFileService uploadFileService;

    @GetMapping("/")
    public String home(Principal p,Model m) {

        m.addAttribute("userFullName", UserUtil.getFullName(p, userRepository));
        return "index.html";
    }

    // Gets the login page
    @GetMapping("/login")
    public String login() {
        return "user_login.html";
    }

    // Posts the login information to the database and authorizes a new session
    @PostMapping("/login")
    public RedirectView login(@RequestParam(value="username") String username, @RequestParam(value="password") String password, Model m, Principal p) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return  new RedirectView("/login?error");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return  new RedirectView("/");
    }

    // Gets the signup page
    @GetMapping("/signup")
    public String signup() {
        return "user_signup.html";
    }

    // Posts the signup information and redirects to login to continue
    @PostMapping("/signup")
    public RedirectView addNewUser(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "middleName") String middleName,
                                   @RequestParam(value = "lastName") String lastName,
                                   @RequestParam(value = "profilePictures") MultipartFile profilePicture) {

        String profileImage = "default.jpg";
        String fileName = uploadFileService.uploadFile(profilePicture);
        if (fileName != null) {
            profileImage = fileName;
        }

        User user = new User(
                username,
                passwordEncoder.encode(password),
                email,
                firstName,
                middleName,
                lastName,
                profileImage,
                new Date()
        );
        userRepository.save(user);
        return new RedirectView("/login");
    }
}
