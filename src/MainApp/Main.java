package MainApp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import DAO.MySQLAnimal;
import DAO.MySQLDonor;
import DAO.AnimalDao;
import DTO.Animal;
import DTO.Donor;
import org.json.JSONObject;

public class Main {

    private MySQLAnimal animal = new MySQLAnimal();
    private MySQLDonor donors = new MySQLDonor();
    private Scanner keyboard = new Scanner(System.in);

    //Animal ID cache
    private final Map<Integer, Animal> animalCache = new HashMap<>();
    private final Set<Integer> animalCacheId = new HashSet<>();

    //Donor ID cache
    private final Map<Integer, Donor> donorCache = new HashMap<>();
    private final Set<Integer> donorCacheId = new HashSet<>();

    public static void main(String[] args) {
        //Running the program
        new Main().run();
    }

    public void run() {
        try {
            animalIDcache();    // build cache at startup
        } catch (SQLException e) {
            System.out.println("Could not build animal caches: " + e.getMessage());
        }

        try {
            donorIDcache();
        } catch (SQLException e) {
            System.out.println("Could not build donor caches: " + e.getMessage());
        }


        while (true) {
            System.out.println("Welcome to our Animal Shelter");
            System.out.println("Menu:");
            System.out.println("1) Display all animals");
            System.out.println("2) Filter out animal by ID");
            System.out.println("3) Delete an animal by ID");
            System.out.println("4) Add a new animal");
            System.out.println("5) Filter out a Donor by second name");
            System.out.println("6) Build cache for animal ID and donor ID");
            System.out.println("7) Convert a list of animals into JSON String");
            System.out.println("8) Exit");

            System.out.println("Enter your input: ");

            int input = keyboard.nextInt();

            switch (input) {
                case 1 -> getAllAnimals();
                case 2 -> getAnimalById();
                case 3 -> deleteAnimalById();
                case 4 -> addAnimal();
                case 5 -> filteringSecondName();
                case 6 -> cache();
                case 7 -> convertListToJson();
                case 8 -> findAnimalByIdJson();
                case 9 -> {
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

        try {
            Animal animalById = animal.getAnimalById(id);
            if (animalById != null) {
                System.out.println(animalById);
            } else {
                System.out.println("Animal not found");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Feature 3 - Delete animal by Id - DRY : inputting id in the try function
    private void deleteAnimalById() {
        System.out.println("Enter animal ID you would like to delete: ");
        int id;

        try {
            id = keyboard.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a valid ID");
            return;
        }

        try {
            animal.deleteAnimalById(id);
            System.out.println("Animal deleted");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Feature 4 - Add a new animal into the database and assigns new ID for it(Doesn't update the animals.sql)
    private void addAnimal() {
        try {
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
            } else if (donorRegistered == 2) {
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

    //Feature 5 - Filtering donors second name
    private void filteringSecondName() {
        System.out.println("You have chosen to filter out donor's second name.");
        System.out.println("Please enter the name you want to filer out");
        keyboard.nextLine();
        String secondName = keyboard.nextLine();

        try {
            List<Donor> f5 = donors.filterSecondName(secondName);
            System.out.println("Filtered donors second names");

            if (f5.size() > 0) {
                System.out.println(f5);
            }
            else if (f5.size() == 0) {
                System.out.println("No donors found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error filtering donor second names");
        }
    }

    //Feature 6 - Creating a cache using a hash map for animal ID + donor id
    //I'm going to do a loop to input every animal Id into the hash map so I don't need to input every ID manually
    //especially since there is a feature that can create a new animal
    private void animalIDcache() throws SQLException {
        //Clearing the map and hash
        animalCache.clear();
        animalCacheId.clear();

        List<Animal> animals = animal.getAllAnimals();

        for (Animal animal : animals) {
            int id = animal.getAnimalId();
            animalCacheId.add(animal.getAnimalId());
            animalCache.put(animal.getAnimalId(), animal);
        }
    }

    private void donorIDcache() throws SQLException {
        //Clearing the map and hash
        donorCache.clear();
        donorCacheId.clear();

        List<Donor> Donor = donors.getAllDonors();

        for (Donor donor : Donor) {
            int id = donor.getDonorId();
            donorCacheId.add(donor.getDonorId());
            donorCache.put(donor.getDonorId(), donor);
        }
    }

    //Just to check if it did build up the cache
    private void cache() {
        System.out.println("animalCache (how many animals there are) : " + animalCache.size() + " animalCacheId (how many animal id's there are) : " + animalCacheId.size());
        System.out.println("donorCache (how many donors there are) : " + donorCache.size() + " donorCacheId (how many donor id's there are) : " + donorCacheId.size());
    }

    //Feature 7 - Converting list to JSON
    private void convertListToJson() {
        try{
            //Getting all animals
            List<Animal> animals = animal.getAllAnimals();
            //Checking if the list is empty or not
            if (animals.isEmpty()) {
                System.out.println("No animals found.");
            }
            //if there is contents in the list
            else {
                //takes the animal list into the method
                String animalJson = Animal.animalListToJson(animals);
                System.out.println(animalJson);
            }
        }
        catch (Exception e) {
            System.out.println("Error converting animals to JSON");
        }
    }

    //Feature 8 - Find a single entity by key JSON
    private void findAnimalByIdJson(){
        System.out.println("Enter animal ID you would like to filter: ");
        int id = keyboard.nextInt();

        try {
            Animal animalById = animal.getAnimalById(id);

            if (animalById == null) {
                System.out.println("No animal found");
            }
            else {
                List<Animal> animalf8 = new ArrayList<>();
                animalf8.add(animalById);

                String jsonf8 = Animal.animalListToJson(animalf8);
                System.out.println(jsonf8);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

