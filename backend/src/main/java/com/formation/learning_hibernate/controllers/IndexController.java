package com.formation.learning_hibernate.controllers;


import com.formation.learning_hibernate.entities.ContactEntity;
import com.formation.learning_hibernate.services.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


@Controller
public class IndexController {

    private final ContactService contactService;

    @Autowired
    public IndexController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/page")
    public String firstPage(Model model) {
        model.addAttribute("title", "Première page de Learning Hibernate");
        model.addAttribute("contacts", contactService.findAll());
        return "page.html";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new ContactEntity());
        return "contact/form.html";
    }

    @PostMapping("/contact")
    public String contactSend(
            @Valid @ModelAttribute(name ="contact") ContactEntity contact,
            BindingResult contactBinding,
            Model model
        ) {
        if (contactBinding.hasErrors()) {
            model.addAttribute("contact", contact);
            return "contact/form";
        }
        // save contact
        try {
            contactService.save(contact);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("contact", contact);
            model.addAttribute("emailError", "L'email existe déjà");
            return "contact/form";
        }
        return "redirect:/";
    }

    @GetMapping("/contact/{id}/delete")
    public String contactDelete(@PathVariable Integer id,
                                RedirectAttributes redirectAttributes) {
        if (id != null) {
            if (id > 0) {
                contactService.deleteById(id);
                redirectAttributes.addAttribute("message", "Le contact a bien été supprimé");
            } else {
                redirectAttributes.addAttribute("message", "Je ne connais ce contact");
            }
        }
        return "redirect:/";
    }
}
