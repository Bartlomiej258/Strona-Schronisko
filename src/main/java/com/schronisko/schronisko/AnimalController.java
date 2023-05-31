package com.schronisko.schronisko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(required = false) String searchText,
                       @RequestParam(required = false, name = "gatunek") AnimalSpecies species,
                       @RequestParam(required = false) String order) {
        Set<Animal> animals;

        if (searchText != null) {
            animals = animalRepository.findByNameContains(searchText);
        } else {
            if (species != null) {
                animals = animalRepository.findBySpecies(species);
            } else {
                animals = animalRepository.findAll();
            }
        }

        List<Animal> animalList = new ArrayList<>(animals);

        if (order != null) {
            int asc = (order.equals("ASC")) ? 1 : -1;
            animalList.sort(new Comparator<Animal>() {
                @Override
                public int compare(Animal o1, Animal o2) {
                    return o1.getName().compareTo(o2.getName()) * asc;
                }
            });
        }


        model.addAttribute("animals", animalList);
        model.addAttribute("species", species);
        return "home"; // -> /resources/templates/home.html
    }

    @GetMapping("/zwierzak")
    public String details(@RequestParam String imie, Model model) {

        Animal animal = animalRepository.findByName(imie);

        if (animal != null) {
            model.addAttribute("animal", animal);
            return "animal"; // -> /resources/templates/animal.html
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("mode", "add");
        return "addOrEdit";
    }

    @PostMapping("/add")
    public String addForm(Animal animal) {
        animalRepository.add(animal);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editForm(Model model, @RequestParam String imie) {
        Animal animal = animalRepository.findByName(imie);
        model.addAttribute("animal", animal);
        model.addAttribute("mode", "edit");
        return "addOrEdit";
    }

    @PostMapping("/edit")
    public String edit(Animal animal) {
        Animal animalInDb = animalRepository.findById(animal.getId());
        animalInDb.setName(animal.getName());
        animalInDb.setDescription(animal.getDescription());
        animalInDb.setImg(animal.getImg());
        animalInDb.setSpecies(animal.getSpecies());
        animalRepository.update(animalInDb);
        return "redirect:/zwierzak?imie=" + animal.getName();
    }

//    @GetMapping("/search")
//    public String sortAnimal(Model model, Animal animal) {
//        Set<Animal> sortedSet = animalRepository.sortByName();
//        model.addAttribute("sorted", sortedSet);
//        model.addAttribute("animal", animal);
//        return "sortedForm";
//    }

}
