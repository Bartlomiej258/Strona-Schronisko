package com.schronisko.schronisko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/zwierzak")
    public String details(@RequestParam String imie, Model model) {
        Animal animal = animalRepository.findByName(imie);
        if (animal != null) {
            model.addAttribute("name", animal.getName());
            model.addAttribute("description", animal.getDescription());
            return "animal";
        } else {
            return "redirect:/";
        }

    }
}
