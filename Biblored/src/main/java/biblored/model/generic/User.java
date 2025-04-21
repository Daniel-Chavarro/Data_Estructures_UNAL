package biblored.model.generic;

import biblored.model.Status;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Phone phone;
    private Address address;
    private Material borrowedMaterial;


    public User() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.password = "";
        this.phone = new Phone();
        this.address = new Address();
        this.borrowedMaterial = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private void setBorrowedMaterial(Material material) {
        this.borrowedMaterial = material;
    }

    public Material getBorrowedMaterial() {
        return borrowedMaterial;
    }

    /**
     * Borrows a material from a library
     * @param material The material to borrow
     */
    public void borrowMaterial(Material material) {
        if (borrowedMaterial == null) {
            // Get the library that owns the material
            Library library = material.getLibrary();

            // Process the borrowing through the library
            Material borrowedMaterial = library.borrowMaterial(material, this);

            // If the status was changed to RESERVED, the borrowing was successful
            if (borrowedMaterial.getStatus() == Status.RESERVED) {
                setBorrowedMaterial(borrowedMaterial);
                System.out.println("User " + name + " has borrowed the material successfully.");
            }
        } else {
            System.out.println("You already have a borrowed material.");
        }
    }

    /**
     * Returns the currently borrowed material
     */
    public void returnMaterial() {
        if (borrowedMaterial != null) {
            // Get the library that owns the material
            Library library = borrowedMaterial.getLibrary();

            // Process the return through the library
            Material returnedMaterial = library.returnMaterial(borrowedMaterial);

            // If the status was changed to AVAILABLE, the return was successful
            if (returnedMaterial.getStatus() == Status.AVAILABLE) {
                setBorrowedMaterial(null);
                System.out.println("User " + name + " has returned the material successfully.");
            }
        } else {
            System.out.println("You have no borrowed material.");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", address=" + address +
                '}';
    }
}
