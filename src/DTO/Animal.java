package DTO;

import java.time.LocalDate;

public class Animal {
    private int animalId;
    private String type;
    private String breed;
    private String name;
    private int age;
    private float weight;
    private boolean neutered;
    private String health;
    private LocalDate admitted;
    private String gender;
    private int donorId;

    //Constructor with an animal ID - for reading off the database
    public Animal(int animalId, String type, String breed, String name, int age, float weight, boolean neutered, String health, LocalDate admitted, String gender, int donorId) {
        this.animalId = animalId;
        this.type = type;
        this.breed = breed;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.neutered = neutered;
        this.health = health;
        this.admitted = admitted;
        this.gender = gender;
        this.donorId = donorId;
    }

    //Feature 4 - Constructor without the animal ID so I can assign the ID automatically when a new animal is made
    public Animal(String type, String breed, String name, int age, float weight, boolean neutered, String health, LocalDate admitted, String gender, int donorId){
        this.type = type;
        this.breed = breed;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.neutered = neutered;
        this.health = health;
        this.admitted = admitted;
        this.gender = gender;
        this.donorId = donorId;
    }

    //Getters
    public int getAnimalId() {
        return animalId;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public String getHealth() {
        return health;
    }

    public LocalDate getAdmitted() {
        return admitted;
    }

    public String getGender() {
        return gender;
    }

    public int getDonorId() {
        return donorId;
    }

    //Setters
    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setAdmitted(LocalDate admitted) {
        this.admitted = admitted;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    //toString
    @Override
    public String toString() {
        return "Animal{" +
                "animalId=" + animalId +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", neutered=" + neutered +
                ", health='" + health + '\'' +
                ", admitted=" + admitted +
                ", gender='" + gender + '\'' +
                ", donorId=" + donorId +
                '}';
    }
}
