package com.medshare.controllers;

import com.medshare.repositories.LocationRepository;
import com.medshare.repositories.UserRepository;
import com.medshare.services.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LocationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;

    // TODO: Gets the location view to click and save into the item (new or update).
    @GetMapping("/location")
    public String getLocation(Model m , Principal p){
        m.addAttribute("userFullName", UserUtil.getFullName(p, userRepository));
        return "location.html";
    }
}
