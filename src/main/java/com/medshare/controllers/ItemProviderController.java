package com.medshare.controllers;

import com.medshare.models.Items;
import com.medshare.repositories.ItemsRepository;
import com.medshare.repositories.UserRepository;
import com.medshare.services.UploadFileService;
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
import java.util.Date;
import java.util.List;

@Controller
public class ItemProviderController<T> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    UploadFileService uploadFileService;

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

        String itemImage = "";
        String file = uploadFileService.uploadFile(image);
        if (file != null) {
            itemImage = file;
        }

        Items item = new Items(title, itemImage, description, true, new Date(), userRepository.findByUsername(p.getName()));
        System.out.println(userRepository.findByUsername(p.getName()));
        System.out.println(item);

        itemsRepository.save(item);
        return new RedirectView("/profile");
    }
}
