package com.schronisko.schronisko;

public class Animal {

    private String name;
    private String description;
    private String img;
    private AnimalSpecies species;

    public Animal(String name, String description, String img, AnimalSpecies species) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.species = species;
    }

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public void setSpecies(AnimalSpecies species) {
        this.species = species;
    }
}
