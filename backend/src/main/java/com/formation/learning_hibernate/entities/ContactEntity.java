package com.formation.learning_hibernate.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Le nom est obligatoire")
    @Pattern(
        regexp = "^[\\p{L}\\- ]{4,100}$",
        flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.DOTALL},
        // Voir message personnalisé en bas du fichier "ValidationMessages.properties"
        message = "{contact.name.regex.message}"
    )
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Email
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @NotBlank
    @Size(min = 10, max = 1000, message = "Le message doit contenir entre 10 et 1000 caractères")
    private String message;
}
