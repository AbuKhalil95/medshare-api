package com.medshare.controllers;

import com.medshare.models.User;
import com.medshare.models.Comments;
import com.medshare.repositories.CommentsRepository;
import com.medshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class CommentsController {

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserRepository userRepository;

    // Adds a comment to the specified item
    @PostMapping("/add-comment")
    public RedirectView addComment(
        @RequestParam(value = "body") String body,
        @RequestParam(value = "itemId") Long itemId,
        Principal p) {

        User userDetails = userRepository.findByUsername(p.getName());
        String commenterName = userDetails.getFirstname() + " " + userDetails.getLastname();

        Date date = new Date();
        Comments comments = new Comments(userDetails.getId(), commenterName, date, body, itemId);
        commentsRepository.save(comments);

        return new RedirectView("/item-details" + itemId);
    }
}
