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

            System.out.println("Enter your input: ");

            int input = keyboard.nextInt();

            switch (input) {
                case 1 -> getAllAnimals();
                case 2 -> {
                    System.out.println("Finsihed");
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    //FIX THE FEATURE ITS NOT BRINGING THROUGH THE DATA XX
    //Feature 1 - Get all entities (in this case animals)
    private void getAllAnimals() {
        try {
            List<Animal> animals = animal.getAllAnimals(); // use the instance
            if (animals.isEmpty()) {
                System.out.println("No animals found");
            } else {
                animals.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or better: log it properly
        }
    }
}

