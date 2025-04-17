package biblored.model.DAO;

public interface InterfaceDAO <T> {
    boolean add(T t);
    T read(int id);
    boolean update(int id,T t);
    boolean delete(int id);
    /* T findById(int id);
     T findByName(String name);
     T findByAuthor(String author);
     T findByPublisher(String publisher);
     T findByYear(int year);
    */
}
