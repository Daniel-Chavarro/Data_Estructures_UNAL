package biblored.model.DTOs;

import biblored.model.Status;
import biblored.model.generic.Address;
import biblored.model.generic.Material;
import biblored.model.generic.Phone;

public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private Phone phone;
    private AddressDTO address;
    private Material borrowedMaterial;

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    private void setBorrowedMaterial(Material material) {
        this.borrowedMaterial = material;
    }

    private Material getBorrowedMaterial() {
        return borrowedMaterial;
    }

    public void borrowMaterial(Material material) {
        if (borrowedMaterial == null) {
            setBorrowedMaterial(material);
            material.setStatus(Status.RESERVED);

            System.out.println("Material borrowed successfully.");
        } else {
            System.out.println("You already have a borrowed material.");
        }
    }

    public void returnMaterial() {
        if (borrowedMaterial != null) {
            borrowedMaterial.setStatus(Status.AVAILABLE);
            setBorrowedMaterial(null);

            System.out.println("Material returned successfully.");
        } else {
            System.out.println("You have no borrowed material.");
        }
    }
}
