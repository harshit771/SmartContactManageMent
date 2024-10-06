package com.scm.scm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm.entities.User;
import com.scm.scm.helper.Helper;
import com.scm.scm.services.UserServices;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserServices userServices;

    @ModelAttribute
    public void addLogginUserInfo(Model model, Authentication authentication){
        if(authentication == null){
            return;
        }
         String username=Helper.getEmailOfLoggedInUser(authentication);

         User user=userServices.findByMail(username);

         model.addAttribute("loggedInUser", user);


    }

}
