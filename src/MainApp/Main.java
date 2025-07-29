package MainApp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import DAO.AnimalDao;
import DAO.DonorDao;
import DAO.MySQLAnimal;
import DAO.MySQLDonor;
import DTO.Animal;
import DTO.Donor;

import java.util.Scanner;

public class Main {

    private MySQLAnimal animal = new MySQLAnimal();
    private MySQLDonor donors = new MySQLDonor();
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
            System.out.println("3) Delete an animal by ID");
            System.out.println("4) Add a new animal");
            System.out.println("5) Exit");

            System.out.println("Enter your input: ");

            int input = keyboard.nextInt();

            switch (input) {
                case 1 -> getAllAnimals();
                case 2 -> getAnimalById();
                case 3 -> deleteAnimalById();
                case 4 -> addAnimal();
                case 5 -> {
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

    //Feature 3 - Delete animal by Id - DRY : inputting id in the try function
    private void deleteAnimalById() {
        System.out.println("Enter animal ID you would like to delete: ");
        int id;

        try{
            id = keyboard.nextInt();
        }
        catch (Exception e){
            System.out.println("Invalid input. Enter a valid ID");
            return;
        }

        try{
            animal.deleteAnimalById(id);
            System.out.println("Animal deleted");
        }
        catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Feature 4 - Add a new animal into the database and assigns new ID for it(Doesn't update the animals.sql)
    private void addAnimal() {
        try{
            System.out.println("An ID will auto assign at the end.");
            System.out.println("Enter type: ");
            String type = keyboard.next();

            System.out.println("Enter breed: ");
            String breed = keyboard.next();

            System.out.println("Enter name: ");
            String name = keyboard.next();

            System.out.println("Enter age: ");
            int age = keyboard.nextInt();

            System.out.println("Enter weight: ");
            float weight = keyboard.nextFloat();

            System.out.println("Is the animal neutered? (True = yes / False = no): ");
            boolean neutered = keyboard.nextBoolean();
            keyboard.nextLine();

            System.out.println("Enter health status (Good/Bad): ");
            String healthStatus = keyboard.next();

            System.out.println("Enter admission date (YYYY-MM-DD): ");
            LocalDate admitted = LocalDate.parse(keyboard.next());
            keyboard.nextLine();

            System.out.println("Enter gender (Boy/Girl): ");
            String gender = keyboard.next();

            System.out.println("Is the donor registered? (1 = No / 2 = Yes): ");
            int donorRegistered = keyboard.nextInt();
            keyboard.nextLine();

            int donorId = 0;

            if (donorRegistered == 1) {
                System.out.println("A donor needs to be registered to add the animal");
                System.out.println("A donor ID will be assigned later");

                System.out.println("Enter donor first name: ");
                String firstName = keyboard.nextLine();

                System.out.println("Enter donor surname: ");
                String surname = keyboard.nextLine();

                System.out.println("Enter donor telephone number: ");
                String telephoneNumber = keyboard.nextLine();

                System.out.println("Creating donor");
                Donor newDonor = new Donor(firstName, surname, telephoneNumber);
                MySQLDonor donorDao = new MySQLDonor();
                donorId = donorDao.createDonor(newDonor);
                System.out.println("Donor added. ID: " + donorId);
            }

            else if (donorRegistered == 2) {
                System.out.println("Enter the donor's ID: ");
                donorId = keyboard.nextInt();
                keyboard.nextLine();
            }

            System.out.println("Creating animal");
            Animal animal = new Animal(type, breed, name, age, weight, neutered, healthStatus, admitted, gender, donorId);
            MySQLAnimal animalDao = new MySQLAnimal();
            int animalId = animalDao.createAnimal(animal);
            System.out.println("Animal added. ID: " + animalId);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

