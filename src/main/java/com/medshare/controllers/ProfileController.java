package com.medshare.controllers;

import com.medshare.models.User;
import com.medshare.models.UserInfo;
import com.medshare.repositories.UserInfoRepository;
import com.medshare.repositories.UserRepository;
import com.medshare.services.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInfoRepository userInfoRepository;

    // Gets the profile page of the current user
    @GetMapping("/profile")
    public String profile(Principal p, Model m) {

        m.addAttribute("name", UserUtil.getFullName(p, userRepository));
        m.addAttribute("user", userRepository.findByUsername(p.getName()));
        return "user_profile.html";
    }

    // Updates the image to the profile image
    @PutMapping("/profile_image")
    public RedirectView editProfileImage(
            @RequestParam(value = "profilePictures")MultipartFile updateImage, Principal p) {

        String loggedInUserName = p.getName();
        User user = userRepository.findByUsername(loggedInUserName);

        String profileImage = "default.jpg";
//        String fileName=uploadFileService.uploadFile(updateImage);
//        if (fileName!=null) {
//            profileImage = fileName;
//        }

        user.setProfileImage(profileImage);
        userRepository.save(user);
        return new RedirectView("/profile");
    }

    // Edits user profile info back to the database
    @PutMapping("/profile")
    public RedirectView editProfile(
            @RequestParam (value ="firstName")String firstName,
            @RequestParam (value ="middleName")String middleName,
            @RequestParam (value ="lastName")String lastName,
            @RequestParam (value ="email")String email,
            Principal p){

        User user = userRepository.findByUsername(p.getName());
        user.setFirstname(firstName);
        user.setMiddlename(middleName);
        user.setLastname(lastName);
        user.setEmail(email);
        userRepository.save(user);
        return new RedirectView("/profile");
    }

    // Edits contact info to match most current information
    // TODO: Fix the edit contact info
    @PutMapping("/contact-info")
    public RedirectView addContact_info(
            @RequestParam (value="phone-number") String phoneNumber,
            @RequestParam (value="university") String university){

        UserInfo userInfo = new UserInfo(phoneNumber, university);
        userInfoRepository.save(userInfo);

        return new  RedirectView("/profile");
    }
}


