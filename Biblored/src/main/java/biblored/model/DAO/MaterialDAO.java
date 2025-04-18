package biblored.model.DAO;

import biblored.model.generic.Material;

import java.util.ArrayList;

public class MaterialDAO implements InterfaceDAO<Material>{
    private ArrayList<Material> materials = new ArrayList<Material>();

    public MaterialDAO() {
        materials = new ArrayList<Material>();
    }

    @Override
    public boolean add(Material material) {
        if(read(material.getId()) == null) {
            materials.add(material);
            return true;
        }
        return false;
    }

    @Override
    public Material read(int id) {
        Material material = null;
        for (Material b : materials) {
            if (b.getId() == id) {
                material = b;
            }
        }
        return material;
    }

    @Override
    public ArrayList<Material> readAll() {
        return materials;
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
            materials.remove(found);
            return true;
        }
        return false;
    }
}
