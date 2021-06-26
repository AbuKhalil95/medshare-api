package com.medshare.controllers;

import com.medshare.models.User;
import com.medshare.repositories.UserRepository;
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

    @GetMapping("/")
    public String home(Principal p,Model m) {
        System.out.println("inside home");
        m.addAttribute("name", UserUtil.getFullName(p, userRepository));
        return "index.html";
    }

    // Gets the login page
    @GetMapping("/login")
    public String login() {
        System.out.println("inside login");
        if (!UserUtil.isLoggedIn()) return "user_login.html";
        return "redirect:/";
    }

    // Gets the signup page
    @GetMapping("/signup")
    public String signup() {
        System.out.println("inside signup");
        if (!UserUtil.isLoggedIn()) return "user_signup.html";
        return "redirect:/";
    }

    // Posts the signup information and redirects to login to continue
    @PostMapping("/signup")
    public RedirectView addNewUser(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "middleName") String middleName,
                                   @RequestParam(value = "lastName") String lastName,
                                   @RequestParam(value = "profilePicture") MultipartFile profilePicture) {

        String profileImage = "default.jpg";
//        String fileName = uploadFileService.uploadFile(profilePicture);
//        if (fileName != null) {
//            profileImage = fileName;
//        }

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
