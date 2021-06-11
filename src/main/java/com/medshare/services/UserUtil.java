package com.medshare.services;

import com.medshare.models.User;
import com.medshare.repositories.UserRepository;

import java.security.Principal;

public class UserUtil {
    public static String getFullName(Principal p, UserRepository userRepository) {

        String fullName;
        try {
            String user =  p.getName();
            User loggedInUser = userRepository.findByUsername(user);
            fullName = loggedInUser.getFirstname() + " " + loggedInUser.getLastname();
        } catch (Exception e) {
            System.out.println("Error in UserUtil: " + e);
        } finally {
            fullName = "Guest";
        }

        return fullName;
    }

    public static Long getId(Principal p, UserRepository userRepository) {
        Long id;
        try {
            String user =  p.getName();
            User loggedInUser = userRepository.findByUsername(user);
            id = loggedInUser.getId();
        } catch (Exception e) {
            System.out.println("Error in UserUtil: " + e);
        } finally {
            id = null;
        }

        return id;
    }
}
