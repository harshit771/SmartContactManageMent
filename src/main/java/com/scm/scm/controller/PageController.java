package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm.entities.User;
import com.scm.scm.form.UserForm;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserServices userServices;

    @RequestMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(){
        System.err.println("home cnorko");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/register")
    public String register(Model model){
        UserForm userForm=new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegistration(@Valid @ModelAttribute UserForm userForm,
    BindingResult bindingResult, HttpSession session) {
        
        if(bindingResult.hasErrors()){
            return "register";
        }
        User user=new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://www.google.com/imgres?q=profile%20picture&imgurl=https%3A%2F%2Fstatic.vecteezy.com%2Fsystem%2Fresources%2Fthumbnails%2F002%2F387%2F693%2Fsmall_2x%2Fuser-profile-icon-free-vector.jpg&imgrefurl=https%3A%2F%2Fwww.vecteezy.com%2Ffree-vector%2Fprofile-icon&docid=RBpRIqik_jZCqM&tbnid=rv5TFp52pt_EQM&vet=12ahUKEwjU8cDy0MWIAxVsxzgGHRQBBM8QM3oECBkQAA..i&w=400&h=400&hcb=2&ved=2ahUKEwjU8cDy0MWIAxVsxzgGHRQBBM8QM3oECBkQAA");

        User savedUser = userServices.savUser(user);

        Message msg=Message.builder().content("Registration successfully").type(MessageType.green).build();
        System.out.println("in controller "+msg);
        session.setAttribute("message", msg);
        System.out.println("in controller "+session.getAttribute("message"));
        return "redirect:/register";
    }
    
    
}
