package biblored.controller;

import biblored.model.LibraryNetwork;
import biblored.model.Status;
import biblored.model.generic.*;
import biblored.view.ConsoleView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private ConsoleView console;
    private LibraryNetwork libraryNetwork;

    public Controller() {
        this.console = new ConsoleView();
        this.libraryNetwork = new LibraryNetwork();
    }

    public ConsoleView getConsole() {
        return console;
    }

    public void setConsole(ConsoleView console) {
        this.console = console;
    }

    public LibraryNetwork getLibraryNetwork() {
        return libraryNetwork;
    }

    public void setLibraryNetwork(LibraryNetwork libraryNetwork) {
        this.libraryNetwork = libraryNetwork;
    }

    /**
     * Starts the application and displays the main menu
     */
    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int option = console.getIntegerInput("Enter your choice: ");

            switch (option) {
                case 1:
                    libraryManagementMenu();
                    break;
                case 2:
                    userManagementMenu();
                    break;
                case 3:
                    materialManagementMenu();
                    break;
                case 4:
                    borrowReturnMenu();
                    break;
                case 5:
                    exit = true;
                    console.printMessage("Thank you for using Biblored. Goodbye!");
                    break;
                default:
                    console.printMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays the main menu options
     */
    private void displayMainMenu() {
        console.printMessage("\n===== BIBLORED LIBRARY NETWORK =====\n");
        console.printMessage("1. Library Management");
        console.printMessage("2. User Management");
        console.printMessage("3. Material Management");
        console.printMessage("4. Borrow/Return Materials");
        console.printMessage("5. Exit");
    }

    /**
     * Displays and handles the library management menu
     */
    private void libraryManagementMenu() {
        boolean back = false;
        while (!back) {
            console.printMessage("\n===== LIBRARY MANAGEMENT =====\n");
            console.printMessage("1. Add Library");
            console.printMessage("2. View Library Details");
            console.printMessage("3. Update Library");
            console.printMessage("4. Delete Library");
            console.printMessage("5. List All Libraries");
            console.printMessage("6. Back to Main Menu");

            int option = console.getIntegerInput("Enter your choice: ");

            switch (option) {
                case 1:
                    addLibrary();
                    break;
                case 2:
                    viewLibraryDetails();
                    break;
                case 3:
                    updateLibrary();
                    break;
                case 4:
                    deleteLibrary();
                    break;
                case 5:
                    listAllLibraries();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    console.printMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Adds a new library to the system
     */
    private void addLibrary() {
        console.printMessage("\n===== ADD LIBRARY =====\n");

        int id = console.getIntegerInput("Enter library ID: ");
        String name = console.getStringInput("Enter library name: ");

        // Create address
        Address address = new Address();
        address.setStreet(console.getStringInput("Enter street: "));
        address.setNumber(console.getStringInput("Enter number: "));
        address.setCity(console.getStringInput("Enter city: "));
        address.setZipCode(console.getStringInput("Enter zip code: "));
        address.setCountry(console.getStringInput("Enter country: "));

        // Create library
        Library library = new Library();
        library.setId(id);
        library.setName(name);
        library.setAddress(address);

        // Add library to the system
        boolean success = libraryNetwork.addLibrary(library);

        if (success) {
            console.printMessage("Library added successfully!");
        } else {
            console.printMessage("Failed to add library. A library with ID " + id + " may already exist.");
        }
    }

    /**
     * Displays details of a specific library
     */
    private void viewLibraryDetails() {
        console.printMessage("\n===== VIEW LIBRARY DETAILS =====\n");

        int id = console.getIntegerInput("Enter library ID: ");
        Library library = libraryNetwork.getLibrary(id);

        if (library != null) {
            displayLibraryDetails(library);
        } else {
            console.printMessage("Library with ID " + id + " not found.");
        }
    }

    /**
     * Updates an existing library
     */
    private void updateLibrary() {
        console.printMessage("\n===== UPDATE LIBRARY =====\n");

        int id = console.getIntegerInput("Enter library ID to update: ");
        Library existingLibrary = libraryNetwork.getLibrary(id);

        if (existingLibrary != null) {
            String name = console.getStringInput("Enter new library name (current: " + existingLibrary.getName() + "): ");

            // Create new address
            Address address = new Address();
            Address currentAddress = existingLibrary.getAddress();

            address.setStreet(console.getStringInput("Enter new street (current: " + currentAddress.getStreet() + "): "));
            address.setNumber(console.getStringInput("Enter new number (current: " + currentAddress.getNumber() + "): "));
            address.setCity(console.getStringInput("Enter new city (current: " + currentAddress.getCity() + "): "));
            address.setZipCode(console.getStringInput("Enter new zip code (current: " + currentAddress.getZipCode() + "): "));
            address.setCountry(console.getStringInput("Enter new country (current: " + currentAddress.getCountry() + "): "));

            // Create updated library
            Library updatedLibrary = new Library();
            updatedLibrary.setId(id);
            updatedLibrary.setName(name);
            updatedLibrary.setAddress(address);

            // Update library in the system
            boolean success = libraryNetwork.updateLibrary(id, updatedLibrary);

            if (success) {
                console.printMessage("Library updated successfully!");
            } else {
                console.printMessage("Failed to update library.");
            }
        } else {
            console.printMessage("Library with ID " + id + " not found.");
        }
    }

    /**
     * Deletes a library from the system
     */
    private void deleteLibrary() {
        console.printMessage("\n===== DELETE LIBRARY =====\n");

        int id = console.getIntegerInput("Enter library ID to delete: ");
        Library library = libraryNetwork.getLibrary(id);

        if (library != null) {
            console.printMessage("Are you sure you want to delete the following library?");
            displayLibraryDetails(library);

            String confirmation = console.getStringInput("Type 'yes' to confirm deletion: ");

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean success = libraryNetwork.removeLibrary(id);

                if (success) {
                    console.printMessage("Library deleted successfully!");
                } else {
                    console.printMessage("Failed to delete library.");
                }
            } else {
                console.printMessage("Deletion cancelled.");
            }
        } else {
            console.printMessage("Library with ID " + id + " not found.");
        }
    }

    /**
     * Lists all libraries in the system
     */
    private void listAllLibraries() {
        console.printMessage("\n===== ALL LIBRARIES =====\n");

        ArrayList<Library> libraries = libraryNetwork.getLibraries();

        if (libraries.isEmpty()) {
            console.printMessage("No libraries found in the system.");
        } else {
            for (Library library : libraries) {
                console.printMessage("ID: " + library.getId() + ", Name: " + library.getName());
            }

            console.printMessage("\nTotal libraries: " + libraries.size());
        }
    }

    /**
     * Helper method to display library details
     */
    private void displayLibraryDetails(Library library) {
        console.printMessage("Library ID: " + library.getId());
        console.printMessage("Name: " + library.getName());
        console.printMessage("Address: ");
        console.printMessage("  Street: " + library.getAddress().getStreet());
        console.printMessage("  Number: " + library.getAddress().getNumber());
        console.printMessage("  City: " + library.getAddress().getCity());
        console.printMessage("  Zip Code: " + library.getAddress().getZipCode());
        console.printMessage("  Country: " + library.getAddress().getCountry());
    }

    /**
     * Displays and handles the user management menu
     */
    private void userManagementMenu() {
        boolean back = false;
        while (!back) {
            console.printMessage("\n===== USER MANAGEMENT =====\n");
            console.printMessage("1. Register User");
            console.printMessage("2. View User Details");
            console.printMessage("3. Update User");
            console.printMessage("4. Delete User");
            console.printMessage("5. List All Users");
            console.printMessage("6. Back to Main Menu");

            int option = console.getIntegerInput("Enter your choice: ");

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    viewUserDetails();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    listAllUsers();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    console.printMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Registers a new user in the system
     */
    private void registerUser() {
        console.printMessage("\n===== REGISTER USER =====\n");

        int id = console.getIntegerInput("Enter user ID (citizenship card): ");
        String name = console.getStringInput("Enter user name: ");
        String email = console.getStringInput("Enter user email: ");
        String password = console.getStringInput("Enter user password: ");

        // Create phone
        Phone phone = new Phone();
        phone.setCountryCode(console.getStringInput("Enter country code: "));
        phone.setAreaCode(console.getStringInput("Enter area code: "));
        phone.setNumber(console.getStringInput("Enter phone number: "));

        // Create address
        Address address = new Address();
        address.setStreet(console.getStringInput("Enter street: "));
        address.setNumber(console.getStringInput("Enter number: "));
        address.setCity(console.getStringInput("Enter city: "));
        address.setZipCode(console.getStringInput("Enter zip code: "));
        address.setCountry(console.getStringInput("Enter country: "));

        // Create user
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);

        // Add user to the system
        boolean success = libraryNetwork.addUser(user);

        if (success) {
            console.printMessage("User registered successfully!");
        } else {
            console.printMessage("Failed to register user. A user with ID " + id + " may already exist.");
        }
    }

    /**
     * Displays details of a specific user
     */
    private void viewUserDetails() {
        console.printMessage("\n===== VIEW USER DETAILS =====\n");

        int id = console.getIntegerInput("Enter user ID: ");
        User user = libraryNetwork.getUser(id);

        if (user != null) {
            displayUserDetails(user);
        } else {
            console.printMessage("User with ID " + id + " not found.");
        }
    }

    /**
     * Updates an existing user
     */
    private void updateUser() {
        console.printMessage("\n===== UPDATE USER =====\n");

        int id = console.getIntegerInput("Enter user ID to update: ");
        User existingUser = libraryNetwork.getUser(id);

        if (existingUser != null) {
            String name = console.getStringInput("Enter new name (current: " + existingUser.getName() + "): ");
            String email = console.getStringInput("Enter new email (current: " + existingUser.getEmail() + "): ");
            String password = console.getStringInput("Enter new password (current: " + existingUser.getPassword() + "): ");

            // Create new phone
            Phone phone = new Phone();
            Phone currentPhone = existingUser.getPhone();

            phone.setCountryCode(console.getStringInput("Enter new country code (current: " + currentPhone.getCountryCode() + "): "));
            phone.setAreaCode(console.getStringInput("Enter new area code (current: " + currentPhone.getAreaCode() + "): "));
            phone.setNumber(console.getStringInput("Enter new phone number (current: " + currentPhone.getNumber() + "): "));

            // Create new address
            Address address = new Address();
            Address currentAddress = existingUser.getAddress();

            address.setStreet(console.getStringInput("Enter new street (current: " + currentAddress.getStreet() + "): "));
            address.setNumber(console.getStringInput("Enter new number (current: " + currentAddress.getNumber() + "): "));
            address.setCity(console.getStringInput("Enter new city (current: " + currentAddress.getCity() + "): "));
            address.setZipCode(console.getStringInput("Enter new zip code (current: " + currentAddress.getZipCode() + "): "));
            address.setCountry(console.getStringInput("Enter new country (current: " + currentAddress.getCountry() + "): "));

            // Create updated user
            User updatedUser = new User();
            updatedUser.setId(id);
            updatedUser.setName(name);
            updatedUser.setEmail(email);
            updatedUser.setPassword(password);
            updatedUser.setPhone(phone);
            updatedUser.setAddress(address);

            // Update user in the system
            boolean success = libraryNetwork.updateUser(id, updatedUser);

            if (success) {
                console.printMessage("User updated successfully!");
            } else {
                console.printMessage("Failed to update user.");
            }
        } else {
            console.printMessage("User with ID " + id + " not found.");
        }
    }

    /**
     * Deletes a user from the system
     */
    private void deleteUser() {
        console.printMessage("\n===== DELETE USER =====\n");

        int id = console.getIntegerInput("Enter user ID to delete: ");
        User user = libraryNetwork.getUser(id);

        if (user != null) {
            console.printMessage("Are you sure you want to delete the following user?");
            displayUserDetails(user);

            String confirmation = console.getStringInput("Type 'yes' to confirm deletion: ");

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean success = libraryNetwork.removeUser(id);

                if (success) {
                    console.printMessage("User deleted successfully!");
                } else {
                    console.printMessage("Failed to delete user.");
                }
            } else {
                console.printMessage("Deletion cancelled.");
            }
        } else {
            console.printMessage("User with ID " + id + " not found.");
        }
    }

    /**
     * Lists all users in the system
     */
    private void listAllUsers() {
        console.printMessage("\n===== ALL USERS =====\n");

        ArrayList<User> users = libraryNetwork.getUsers();

        if (users.isEmpty()) {
            console.printMessage("No users found in the system.");
        } else {
            for (User user : users) {
                console.printMessage("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
            }

            console.printMessage("\nTotal users: " + users.size());
        }
    }

    /**
     * Helper method to display user details
     */
    private void displayUserDetails(User user) {
        console.printMessage("User ID: " + user.getId());
        console.printMessage("Name: " + user.getName());
        console.printMessage("Email: " + user.getEmail());
        console.printMessage("Password: " + user.getPassword());
        console.printMessage("Phone: ");
        console.printMessage("  Country Code: " + user.getPhone().getCountryCode());
        console.printMessage("  Area Code: " + user.getPhone().getAreaCode());
        console.printMessage("  Number: " + user.getPhone().getNumber());
        console.printMessage("Address: ");
        console.printMessage("  Street: " + user.getAddress().getStreet());
        console.printMessage("  Number: " + user.getAddress().getNumber());
        console.printMessage("  City: " + user.getAddress().getCity());
        console.printMessage("  Zip Code: " + user.getAddress().getZipCode());
        console.printMessage("  Country: " + user.getAddress().getCountry());
    }

    /**
     * Displays and handles the material management menu
     */
    private void materialManagementMenu() {
        boolean back = false;
        while (!back) {
            console.printMessage("\n===== MATERIAL MANAGEMENT =====\n");
            console.printMessage("1. Add Book");
            console.printMessage("2. Add Audiovisual Material");
            console.printMessage("3. View Material Details");
            console.printMessage("4. Update Material");
            console.printMessage("5. Delete Material");
            console.printMessage("6. List All Materials");
            console.printMessage("7. Back to Main Menu");

            int option = console.getIntegerInput("Enter your choice: ");

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addAudiovisualMenu();
                    break;
                case 3:
                    viewMaterialDetails();
                    break;
                case 4:
                    updateMaterial();
                    break;
                case 5:
                    deleteMaterial();
                    break;
                case 6:
                    listAllMaterials();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    console.printMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays and handles the audiovisual material menu
     */
    private void addAudiovisualMenu() {
        boolean back = false;
        while (!back) {
            console.printMessage("\n===== ADD AUDIOVISUAL MATERIAL =====\n");
            console.printMessage("1. Add Film");
            console.printMessage("2. Add Documentary");
            console.printMessage("3. Add Compact Disk");
            console.printMessage("4. Back to Material Management");

            int option = console.getIntegerInput("Enter your choice: ");

            switch (option) {
                case 1:
                    addFilm();
                    break;
                case 2:
                    addDocumentary();
                    break;
                case 3:
                    addCompactDisk();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    console.printMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Adds a new book to the system
     */
    private void addBook() {
        console.printMessage("\n===== ADD BOOK =====\n");

        Material book = createMaterial();

        // Add book to the system
        boolean success = libraryNetwork.addBook(book);

        if (success) {
            console.printMessage("Book added successfully!");
        } else {
            console.printMessage("Failed to add book. A book with ID " + book.getId() + " may already exist.");
        }
    }

    /**
     * Adds a new film to the system
     */
    private void addFilm() {
        console.printMessage("\n===== ADD FILM =====\n");

        Material film = createMaterial();

        // Add film to the system
        boolean success = libraryNetwork.addFilm(film);

        if (success) {
            console.printMessage("Film added successfully!");
        } else {
            console.printMessage("Failed to add film. A film with ID " + film.getId() + " may already exist.");
        }
    }

    /**
     * Adds a new documentary to the system
     */
    private void addDocumentary() {
        console.printMessage("\n===== ADD DOCUMENTARY =====\n");

        Material documentary = createMaterial();

        // Add documentary to the system
        boolean success = libraryNetwork.addDocumentary(documentary);

        if (success) {
            console.printMessage("Documentary added successfully!");
        } else {
            console.printMessage("Failed to add documentary. A documentary with ID " + documentary.getId() + " may already exist.");
        }
    }

    /**
     * Adds a new compact disk to the system
     */
    private void addCompactDisk() {
        console.printMessage("\n===== ADD COMPACT DISK =====\n");

        Material compactDisk = createMaterial();

        // Add compact disk to the system
        boolean success = libraryNetwork.addCompactDisks(compactDisk);

        if (success) {
            console.printMessage("Compact disk added successfully!");
        } else {
            console.printMessage("Failed to add compact disk. A compact disk with ID " + compactDisk.getId() + " may already exist.");
        }
    }

    /**
     * Helper method to create a material from user input
     */
    private Material createMaterial() {
        int id = console.getIntegerInput("Enter material ID: ");
        String name = console.getStringInput("Enter material name: ");
        String author = console.getStringInput("Enter author: ");
        String language = console.getStringInput("Enter language: ");

        // Get library for the material
        console.printMessage("\nAvailable libraries:");
        ArrayList<Library> libraries = libraryNetwork.getLibraries();

        if (libraries.isEmpty()) {
            console.printMessage("No libraries found. Please add a library first.");
            return null;
        }

        for (Library library : libraries) {
            console.printMessage("ID: " + library.getId() + ", Name: " + library.getName());
        }

        int libraryId = console.getIntegerInput("Enter library ID for this material: ");
        Library library = libraryNetwork.getLibrary(libraryId);

        if (library == null) {
            console.printMessage("Library with ID " + libraryId + " not found. Using default library.");
            library = new Library();
        }

        // Create material
        Material material = new Material();
        material.setId(id);
        material.setName(name);
        material.setAuthor(author);
        material.setLanguage(language);
        material.setLibrary(library);
        material.setDateRegistered(LocalDate.now());
        material.setStatus(Status.AVAILABLE);

        return material;
    }

    /**
     * Displays details of a specific material
     */
    private void viewMaterialDetails() {
        console.printMessage("\n===== VIEW MATERIAL DETAILS =====\n");
        console.printMessage("1. View Book");
        console.printMessage("2. View Audiovisual");
        console.printMessage("3. View Film");
        console.printMessage("4. View Documentary");
        console.printMessage("5. View Compact Disk");

        int option = console.getIntegerInput("Enter your choice: ");
        int id = console.getIntegerInput("Enter material ID: ");

        Material material = null;

        switch (option) {
            case 1:
                material = libraryNetwork.getBook(id);
                break;
            case 2:
                material = libraryNetwork.getAudiovisual(id);
                break;
            case 3:
                material = libraryNetwork.getFilm(id);
                break;
            case 4:
                material = libraryNetwork.getDocumentary(id);
                break;
            case 5:
                material = libraryNetwork.getCompactDisk(id);
                break;
            default:
                console.printMessage("Invalid option.");
                return;
        }

        if (material != null) {
            displayMaterialDetails(material);
        } else {
            console.printMessage("Material with ID " + id + " not found.");
        }
    }

    /**
     * Updates an existing material
     */
    private void updateMaterial() {
        console.printMessage("\n===== UPDATE MATERIAL =====\n");
        console.printMessage("1. Update Book");
        console.printMessage("2. Update Audiovisual");
        console.printMessage("3. Update Film");
        console.printMessage("4. Update Documentary");
        console.printMessage("5. Update Compact Disk");

        int option = console.getIntegerInput("Enter your choice: ");
        int id = console.getIntegerInput("Enter material ID to update: ");

        Material existingMaterial = null;

        switch (option) {
            case 1:
                existingMaterial = libraryNetwork.getBook(id);
                break;
            case 2:
                existingMaterial = libraryNetwork.getAudiovisual(id);
                break;
            case 3:
                existingMaterial = libraryNetwork.getFilm(id);
                break;
            case 4:
                existingMaterial = libraryNetwork.getDocumentary(id);
                break;
            case 5:
                existingMaterial = libraryNetwork.getCompactDisk(id);
                break;
            default:
                console.printMessage("Invalid option.");
                return;
        }

        if (existingMaterial != null) {
            String name = console.getStringInput("Enter new name (current: " + existingMaterial.getName() + "): ");
            String author = console.getStringInput("Enter new author (current: " + existingMaterial.getAuthor() + "): ");
            String language = console.getStringInput("Enter new language (current: " + existingMaterial.getLanguage() + "): ");

            // Get library for the material
            console.printMessage("\nAvailable libraries:");
            ArrayList<Library> libraries = libraryNetwork.getLibraries();

            for (Library library : libraries) {
                console.printMessage("ID: " + library.getId() + ", Name: " + library.getName());
            }

            int libraryId = console.getIntegerInput("Enter new library ID (current: " + existingMaterial.getLibrary().getId() + "): ");
            Library library = libraryNetwork.getLibrary(libraryId);

            if (library == null) {
                console.printMessage("Library with ID " + libraryId + " not found. Using current library.");
                library = existingMaterial.getLibrary();
            }

            // Create updated material
            Material updatedMaterial = new Material();
            updatedMaterial.setId(id);
            updatedMaterial.setName(name);
            updatedMaterial.setAuthor(author);
            updatedMaterial.setLanguage(language);
            updatedMaterial.setLibrary(library);
            updatedMaterial.setDateRegistered(existingMaterial.getDateRegistered());
            updatedMaterial.setStatus(existingMaterial.getStatus());

            boolean success = false;

            switch (option) {
                case 1:
                    success = libraryNetwork.updateBook(id, updatedMaterial);
                    break;
                case 2:
                    success = libraryNetwork.updateAudiovisual(id, updatedMaterial);
                    break;
                case 3:
                    success = libraryNetwork.updateFilm(id, updatedMaterial);
                    break;
                case 4:
                    success = libraryNetwork.updateDocumentary(id, updatedMaterial);
                    break;
                case 5:
                    success = libraryNetwork.updateCompactDisk(id, updatedMaterial);
                    break;
            }

            if (success) {
                console.printMessage("Material updated successfully!");
            } else {
                console.printMessage("Failed to update material.");
            }
        } else {
            console.printMessage("Material with ID " + id + " not found.");
        }
    }

    /**
     * Deletes a material from the system
     */
    private void deleteMaterial() {
        console.printMessage("\n===== DELETE MATERIAL =====\n");
        console.printMessage("1. Delete Book");
        console.printMessage("2. Delete Audiovisual");
        console.printMessage("3. Delete Film");
        console.printMessage("4. Delete Documentary");
        console.printMessage("5. Delete Compact Disk");

        int option = console.getIntegerInput("Enter your choice: ");
        int id = console.getIntegerInput("Enter material ID to delete: ");

        Material material = null;

        switch (option) {
            case 1:
                material = libraryNetwork.getBook(id);
                break;
            case 2:
                material = libraryNetwork.getAudiovisual(id);
                break;
            case 3:
                material = libraryNetwork.getFilm(id);
                break;
            case 4:
                material = libraryNetwork.getDocumentary(id);
                break;
            case 5:
                material = libraryNetwork.getCompactDisk(id);
                break;
            default:
                console.printMessage("Invalid option.");
                return;
        }

        if (material != null) {
            console.printMessage("Are you sure you want to delete the following material?");
            displayMaterialDetails(material);

            String confirmation = console.getStringInput("Type 'yes' to confirm deletion: ");

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean success = false;

                switch (option) {
                    case 1:
                        success = libraryNetwork.removeBook(id);
                        break;
                    case 2:
                        success = libraryNetwork.removeAudiovisual(id);
                        break;
                    case 3:
                        success = libraryNetwork.removeFilm(id);
                        break;
                    case 4:
                        success = libraryNetwork.removeDocumentary(id);
                        break;
                    case 5:
                        success = libraryNetwork.removeCompactDisk(id);
                        break;
                }

                if (success) {
                    console.printMessage("Material deleted successfully!");
                } else {
                    console.printMessage("Failed to delete material.");
                }
            } else {
                console.printMessage("Deletion cancelled.");
            }
        } else {
            console.printMessage("Material with ID " + id + " not found.");
        }
    }

    /**
     * Lists all materials in the system
     */
    private void listAllMaterials() {
        console.printMessage("\n===== ALL MATERIALS =====\n");
        console.printMessage("1. List Books");
        console.printMessage("2. List Audiovisuals");
        console.printMessage("3. List Films");
        console.printMessage("4. List Documentaries");
        console.printMessage("5. List Compact Disks");

        int option = console.getIntegerInput("Enter your choice: ");

        ArrayList<Material> materials = null;
        String materialType = "";

        switch (option) {
            case 1:
                materials = libraryNetwork.getBooks();
                materialType = "books";
                break;
            case 2:
                materials = libraryNetwork.getAudiovisuals();
                materialType = "audiovisuals";
                break;
            case 3:
                materials = libraryNetwork.getFilms();
                materialType = "films";
                break;
            case 4:
                materials = libraryNetwork.getDocumentaries();
                materialType = "documentaries";
                break;
            case 5:
                materials = libraryNetwork.getCompactDisks();
                materialType = "compact disks";
                break;
            default:
                console.printMessage("Invalid option.");
                return;
        }

        if (materials.isEmpty()) {
            console.printMessage("No " + materialType + " found in the system.");
        } else {
            for (Material material : materials) {
                console.printMessage("ID: " + material.getId() + ", Name: " + material.getName() + ", Author: " + material.getAuthor() + ", Status: " + material.getStatus());
            }

            console.printMessage("\nTotal " + materialType + ": " + materials.size());
        }
    }

    /**
     * Helper method to display material details
     */
    private void displayMaterialDetails(Material material) {
        console.printMessage("Material ID: " + material.getId());
        console.printMessage("Name: " + material.getName());
        console.printMessage("Author: " + material.getAuthor());
        console.printMessage("Language: " + material.getLanguage());
        console.printMessage("Date Registered: " + material.getDateRegistered());
        console.printMessage("Status: " + material.getStatus());
        console.printMessage("Library: " + material.getLibrary().getName() + " (ID: " + material.getLibrary().getId() + ")");
    }

    /**
     * Displays and handles the borrow/return menu
     */
    private void borrowReturnMenu() {
        boolean back = false;
        while (!back) {
            console.printMessage("\n===== BORROW/RETURN MATERIALS =====\n");
            console.printMessage("1. Borrow Material");
            console.printMessage("2. Return Material");
            console.printMessage("3. View Borrowed Materials by Library");
            console.printMessage("4. Back to Main Menu");

            int option = console.getIntegerInput("Enter your choice: ");

            switch (option) {
                case 1:
                    borrowMaterial();
                    break;
                case 2:
                    returnMaterial();
                    break;
                case 3:
                    viewBorrowedMaterialsByLibrary();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    console.printMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays all materials currently borrowed from a specific library
     */
    private void viewBorrowedMaterialsByLibrary() {
        console.printMessage("\n===== VIEW BORROWED MATERIALS BY LIBRARY =====\n");

        // List all libraries
        console.printMessage("Available libraries:");
        ArrayList<Library> libraries = libraryNetwork.getLibraries();

        if (libraries.isEmpty()) {
            console.printMessage("No libraries found in the system.");
            return;
        }

        for (Library library : libraries) {
            console.printMessage("ID: " + library.getId() + ", Name: " + library.getName());
        }

        // Get library ID
        int libraryId = console.getIntegerInput("\nEnter library ID: ");
        Library library = libraryNetwork.getLibrary(libraryId);

        if (library == null) {
            console.printMessage("Library with ID " + libraryId + " not found.");
            return;
        }

        // Get borrowed materials from the library
        ArrayList<Material> borrowedMaterials = library.getBorrowedMaterials();

        if (borrowedMaterials.isEmpty()) {
            console.printMessage("\nNo materials are currently borrowed from " + library.getName() + " library.");
            return;
        }

        // Display borrowed materials
        console.printMessage("\nMaterials currently borrowed from " + library.getName() + " library:");
        for (Material material : borrowedMaterials) {
            console.printMessage("\n-----------------------------------");
            displayMaterialDetails(material);
        }

        console.printMessage("\nTotal borrowed materials: " + borrowedMaterials.size());
    }

    /**
     * Handles the process of borrowing a material
     */
    private void borrowMaterial() {
        console.printMessage("\n===== BORROW MATERIAL =====\n");

        // Get user
        int userId = console.getIntegerInput("Enter user ID: ");
        User user = libraryNetwork.getUser(userId);

        if (user == null) {
            console.printMessage("User with ID " + userId + " not found.");
            return;
        }

        // Check if user already has a borrowed material
        if (user.getBorrowedMaterial() != null) {
            console.printMessage("This user already has a borrowed material. Please return it before borrowing another one.");
            return;
        }

        // Select material type
        console.printMessage("\nSelect material type:");
        console.printMessage("1. Book");
        console.printMessage("2. Audiovisual");
        console.printMessage("3. Film");
        console.printMessage("4. Documentary");
        console.printMessage("5. Compact Disk");

        int materialTypeOption = console.getIntegerInput("Enter your choice: ");

        // Get material ID
        int materialId = console.getIntegerInput("Enter material ID: ");

        Material material = null;

        // Get the material based on type
        switch (materialTypeOption) {
            case 1:
                material = libraryNetwork.getBook(materialId);
                break;
            case 2:
                material = libraryNetwork.getAudiovisual(materialId);
                break;
            case 3:
                material = libraryNetwork.getFilm(materialId);
                break;
            case 4:
                material = libraryNetwork.getDocumentary(materialId);
                break;
            case 5:
                material = libraryNetwork.getCompactDisk(materialId);
                break;
            default:
                console.printMessage("Invalid option.");
                return;
        }

        if (material == null) {
            console.printMessage("Material with ID " + materialId + " not found.");
            return;
        }

        // Check if material is available
        if (material.getStatus() != Status.AVAILABLE) {
            console.printMessage("Material is not available for borrowing. Current status: " + material.getStatus());
            return;
        }

        // Get the library that owns the material
        Library library = material.getLibrary();
        if (library == null || library.getId() == 0) {
            console.printMessage("This material is not associated with a valid library.");
            return;
        }

        // Display confirmation information
        console.printMessage("\nBorrowing Details:");
        console.printMessage("User: " + user.getName() + " (ID: " + user.getId() + ")");
        console.printMessage("Material: " + material.getName() + " (ID: " + material.getId() + ")");
        console.printMessage("Library: " + library.getName() + " (ID: " + library.getId() + ")");

        String confirmation = console.getStringInput("\nConfirm borrowing? (yes/no): ");
        if (!confirmation.equalsIgnoreCase("yes")) {
            console.printMessage("Borrowing cancelled.");
            return;
        }

        // Borrow the material
        user.borrowMaterial(material);

        // Check if borrowing was successful by verifying the user's borrowed material
        if (user.getBorrowedMaterial() != null && user.getBorrowedMaterial().getId() == material.getId()) {
            console.printMessage("\nMaterial successfully borrowed!");
            console.printMessage("Due date: " + LocalDate.now().plusDays(14) + " (14 days from today)");
        } else {
            console.printMessage("\nFailed to borrow material. Please try again.");
        }
    }

    /**
     * Handles the process of returning a material
     */
    private void returnMaterial() {
        console.printMessage("\n===== RETURN MATERIAL =====\n");

        // Get user
        int userId = console.getIntegerInput("Enter user ID: ");
        User user = libraryNetwork.getUser(userId);

        if (user == null) {
            console.printMessage("User with ID " + userId + " not found.");
            return;
        }

        // Check if user has a borrowed material
        Material borrowedMaterial = user.getBorrowedMaterial();
        if (borrowedMaterial == null) {
            console.printMessage("This user has no borrowed materials.");
            return;
        }

        // Display borrowed material details
        console.printMessage("\nBorrowed Material Details:");
        displayMaterialDetails(borrowedMaterial);

        // Get the library that owns the material
        Library library = borrowedMaterial.getLibrary();
        console.printMessage("\nReturning to Library: " + library.getName() + " (ID: " + library.getId() + ")");

        String confirmation = console.getStringInput("\nConfirm return? (yes/no): ");
        if (!confirmation.equalsIgnoreCase("yes")) {
            console.printMessage("Return cancelled.");
            return;
        }

        // Return the material
        user.returnMaterial();

        // Check if return was successful by verifying the user no longer has a borrowed material
        if (user.getBorrowedMaterial() == null) {
            console.printMessage("\nMaterial successfully returned to " + library.getName() + " library!");
        } else {
            console.printMessage("\nFailed to return material. Please try again.");
        }
    }

    /**
     * Main method to run the application
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
    }
}
