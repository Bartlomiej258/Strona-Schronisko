package com.schronisko.schronisko;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AnimalRepository {

    private Set<Animal> animals;

    public AnimalRepository() {
        animals = new HashSet<>();
        animals.add(new Animal("Azor", "Azor to super piesek"));
        animals.add(new Animal("Rudy", "Rudy to super piesek"));
    }


    public Animal findByName(String imie) {
        for (Animal animal : animals) {
            if (animal.getName().equals(imie))
                return animal;
        }
        return null;
    }

}
