package com.medshare.controllers;

import com.medshare.models.Items;
import com.medshare.repositories.ItemsRepository;
import com.medshare.repositories.UserRepository;
import com.medshare.services.ImageService;
import com.medshare.services.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ItemProviderController<T> {

    @Autowired
    ImageService imageService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemsRepository itemsRepository;


    // Gets the HTML form for adding items
    @GetMapping(value = "/add-item")
    public String displayAddItem(Model m, Principal p) {
        m.addAttribute("name", UserUtil.getFullName(p, userRepository));
        return "item_add.html";
    }

    // Adds new item to the database
    // TODO: implement location based items.
    @PostMapping(value = "/add-item")
    public RedirectView addItem(@RequestParam(value = "title") String title,
                                @RequestParam(value = "image") MultipartFile image,
                                @RequestParam(value = "description") String description,
                                Principal p, Model m) {

        m.addAttribute("name", UserUtil.getFullName(p, userRepository));
        System.out.println("image upload name and size: " + image.getName() + " " + image.getSize());
        System.out.println("existing service: " + imageService);
        ArrayList imageInfo = imageService.saveImage(image);
        System.out.println("IMAGE INFO" + imageInfo);

        Items item = new Items(title, (String) imageInfo.get(0), (String) imageInfo.get(1), description, userRepository.findByUsername(p.getName()));
        System.out.println(userRepository.findByUsername(p.getName()));
        System.out.println(item);

        itemsRepository.save(item);
        return new RedirectView("/profile");
    }
}
