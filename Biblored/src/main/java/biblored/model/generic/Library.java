package biblored.model.generic;

import biblored.model.Status;

import java.util.ArrayList;

public class Library {
    private int id;
    private String name;
    private Address address;
    private ArrayList<Material> borrowedMaterials;

    public Library() {
        this.id = 0;
        this.name = "";
        this.address = new Address();
        this.borrowedMaterials = new ArrayList<>();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Material> getBorrowedMaterials() {
        return borrowedMaterials;
    }

    /**
     * Processes a material being borrowed from this library
     * @param material The material to be borrowed
     * @param user The user borrowing the material
     * @return The borrowed material with updated status
     */
    public Material borrowMaterial(Material material, User user) {
        // Check if material belongs to this library
        if (material.getLibrary().getId() != this.id) {
            System.out.println("This material does not belong to this library.");
            return material;
        }

        // Check if material is available
        if (material.getStatus() != Status.AVAILABLE) {
            System.out.println("Material is not available for borrowing. Current status: " + material.getStatus());
            return material;
        }

        // Update material status and add to borrowed materials list
        material.setStatus(Status.RESERVED);
        borrowedMaterials.add(material);

        System.out.println("Material borrowed from " + this.name + " library successfully.");
        return material;
    }

    /**
     * Processes a material being returned to this library
     * @param material The material to be returned
     * @return The returned material with updated status
     */
    public Material returnMaterial(Material material) {
        // Check if material belongs to this library
        if (material.getLibrary().getId() != this.id) {
            System.out.println("This material does not belong to this library.");
            return material;
        }

        // Check if material is in borrowed list
        if (!borrowedMaterials.contains(material)) {
            System.out.println("This material was not borrowed from this library.");
            return material;
        }

        // Update material status and remove from borrowed materials list
        material.setStatus(Status.AVAILABLE);
        borrowedMaterials.remove(material);

        System.out.println("Material returned to " + this.name + " library successfully.");
        return material;
    }

    /**
     * Gets a list of all materials currently borrowed from this library
     * @return List of borrowed materials
     */
    public ArrayList<Material> getAllBorrowedMaterials() {
        return borrowedMaterials;
    }
}
