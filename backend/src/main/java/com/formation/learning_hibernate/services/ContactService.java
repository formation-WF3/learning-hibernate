package com.formation.learning_hibernate.services;

import com.formation.learning_hibernate.entities.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.formation.learning_hibernate.repositories.ContactRepository;


@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Iterable<ContactEntity> findAll() {
        return contactRepository.findAll();
    }

    public void save(ContactEntity contact) {
        contactRepository.save(contact);
    }

    public void deleteById(Integer id) {
        contactRepository.deleteById(id);
    }

    public ContactEntity findById(Integer id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Iterable<ContactEntity> findByName(String name) {
        return contactRepository.findByNameLike(name);
    }


}
