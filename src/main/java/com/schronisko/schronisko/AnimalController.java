package com.schronisko.schronisko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;


@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        Set<Animal> animals = animalRepository.getAllAnimals();
        model.addAttribute("animals", animals);
        return "home"; // -> /resources/templates/home.html
    }

    @GetMapping("/zwierzak")
    public String details(@RequestParam String imie, Model model) {

        Animal animal = animalRepository.findByName(imie);

        if(animal != null) {
            model.addAttribute("animal", animal);
            return "animal"; // -> /resources/templates/animal.html
        } else  {
            return "redirect:/";
        }
    }
}
