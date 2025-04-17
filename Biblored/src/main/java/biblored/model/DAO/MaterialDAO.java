package biblored.model.DAO;

import biblored.model.generic.Material;

import java.util.ArrayList;

public class MaterialDAO implements InterfaceDAO<Material>{
    private ArrayList<Material> books = new ArrayList<Material>();
    
    public MaterialDAO() {
        books = new ArrayList<Material>();
    }
    
    @Override
    public boolean add(Material material) {
        if(read(material.getId()) == null) {
            books.add(material);
            return true;
        }
        return false;
    }

    @Override
    public Material read(int id) {
        Material material = null;
        for (Material b : books) {
            if (b.getId() == id) {
                material = b;
            }
        }
        return material;
    }

    @Override
    public boolean update(int id, Material material) {
        Material found = read(id);
        if (found != null) {
            found.setAuthor(material.getAuthor());
            found.setName(material.getName());
            found.setDateRegistered(material.getDateRegistered());
            found.setLibrary(material.getLibrary());
            found.setLanguage(material.getLanguage());
            found.setStatus(material.getStatus());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Material found = read(id);
        if (found != null) {
            books.remove(found);
            return true;
        }
        return false;
    }
}
