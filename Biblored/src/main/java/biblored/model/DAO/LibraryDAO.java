package biblored.model.DAO;

import biblored.model.generic.Library;

import java.util.ArrayList;

public class LibraryDAO implements InterfaceDAO<Library>{
    private final ArrayList<Library> libraries;

    public LibraryDAO() {
        libraries = new ArrayList<>();
    }

    @Override
    public boolean add(Library library) {
        if (read(library.getId()) == null) {
            libraries.add(library);
            return true;
        }
        return false;
    }

    @Override
    public Library read(int id) {
        Library library = null;
        for (Library l : libraries) {
            if (l.getId() == id) {
                library = l;
            }
        }
        return library;
    }

    @Override
    public boolean update(int id , Library library) {
        Library found = read(id);
        if (found != null) {
            found.setName(library.getName());
            found.setAddress(library.getAddress());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Library found = read(id);
        if (found != null) {
            libraries.remove(found);
            return true;
        }

        return false;
    }
}
