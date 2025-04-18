package biblored.model.DAO;

import java.util.ArrayList;

public interface InterfaceDAO <T> {
    boolean add(T t);
    T read(int id);
    ArrayList<T> readAll();
    boolean update(int id,T t);
    boolean delete(int id);


    /* T findById(int id);
     T findByName(String name);
     T findByAuthor(String author);
     T findByPublisher(String publisher);
     T findByYear(int year);
    */
}
