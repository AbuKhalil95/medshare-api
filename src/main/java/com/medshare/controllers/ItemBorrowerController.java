
package com.medshare.controllers;

import com.medshare.repositories.CommentsRepository;
import com.medshare.repositories.ItemsRepository;
import com.medshare.repositories.UserRepository;
import com.medshare.services.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ItemBorrowerController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    CommentsRepository commentsRepository;

    // Gets all items available to borrow
    @GetMapping("/items")
    public String displayItems(Model m, Principal p) {
        m.addAttribute("userFullName", UserUtil.getFullName(p, userRepository));
        m.addAttribute("allItems", itemsRepository.findAll());
        return "item_view.html";
    }

    // Gets specific item available to borrow
    @GetMapping("/items/{id}")
    public String displayOneItem(@PathVariable(value = "id") Long id, Model m, Principal p) {
        m.addAttribute("userFullName", UserUtil.getFullName(p, userRepository));
        m.addAttribute("Item", itemsRepository.findById(id).get());
        m.addAttribute("AllComment",commentsRepository.findComments(id));

        return "item_view_detail.html";
    }
    // TODO: Sends borrow request to user before toggling availability to ask for permission

    // Toggles borrowed into the item
    // TODO: Removes borrower from active list, but keeps borrow history.
    @PutMapping(value = "/add-borrower/{id}")
    public RedirectView addBorrower(@RequestParam(value = "item_id") Long itemId, Principal p) {
        itemsRepository.toggleItemAvailability(itemId);
        return new RedirectView("/ItemDetail/" + itemId);
    }


}

