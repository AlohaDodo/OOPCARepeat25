package MainApp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.AnimalDao;
import DAO.MySQLAnimal;
import DAO.MySQLDonor;
import DTO.Animal;

import java.util.Scanner;

public class Main {

    private MySQLAnimal animal = new MySQLAnimal();
    private MySQLDonor donor = new MySQLDonor();
    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        while (true) {
            System.out.println("Welcome to our Animal Shelter");
            System.out.println("Menu:");
            System.out.println("1) Display all animals");
            System.out.println("2) Filter out animal by ID");

            System.out.println("Enter your input: ");

            int input = keyboard.nextInt();

            switch (input) {
                case 1 -> getAllAnimals();
                case 2 -> getAnimalById();
                case 3 -> {
                    System.out.println("Finished");
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    //Feature 1 - Get all entities (in this case animals)
    private void getAllAnimals() {
        try {
            List<Animal> animals = animal.getAllAnimals();
            if (animals.isEmpty()) {
                System.out.println("No animals found");
            } else {
                animals.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Feature 2 - Filter animal by a single ID
    private void getAnimalById() {
        System.out.println("Enter animal ID you would like to filter: ");
        int id = keyboard.nextInt();

        try{
            Animal animalById = animal.getAnimalById(id);
            if (animalById != null) {
                System.out.println(animalById);
            }
            else {
                System.out.println("Animal not found");
            }
        }
        catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

