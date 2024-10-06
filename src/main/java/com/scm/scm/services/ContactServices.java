package com.scm.scm.services;

import org.springframework.data.domain.Page;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

public interface ContactServices {

    Contact save(Contact contact);

    Contact getById(String id);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);

    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
            User user);

    void delete(String contactId);

    Contact update(Contact con);
}
