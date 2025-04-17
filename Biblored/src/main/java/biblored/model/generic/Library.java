package biblored.model.generic;

public class Library {
    private int id;
    private String name;
    private Address address;

    public Library() {
        this.id = 0;
        this.name = "";
        this.address = new Address();
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

    public Material borrowMaterial(Material material) {
        // Logic to borrow material
        return material;
    }
}
