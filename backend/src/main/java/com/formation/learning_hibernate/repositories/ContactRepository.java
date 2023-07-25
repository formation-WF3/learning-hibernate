package com.formation.learning_hibernate.repositories;

import com.formation.learning_hibernate.entities.ContactEntity;
import org.springframework.data.repository.CrudRepository;


public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {
    // Create   => INSERT INTO
    // Read     => SELECT * FROM
    // Update   => UPDATE
    // Delete   => DELETE FROM
    Iterable<ContactEntity> findByNameLike(String name);
}
