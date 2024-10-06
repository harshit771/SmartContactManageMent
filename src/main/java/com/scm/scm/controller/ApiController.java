package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.scm.entities.Contact;
import com.scm.scm.services.ContactServices;



@RestController
@RequestMapping("/api")
public class ApiController {

    // get contact

    @Autowired
    private ContactServices contactService;

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId) {
        
        Contact contact= contactService.getById(contactId);
        System.err.println("User comta "+ contact.isFavourite());
        return contact;
    }

}