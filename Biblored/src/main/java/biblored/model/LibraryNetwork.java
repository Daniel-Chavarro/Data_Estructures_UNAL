package biblored.model;


import biblored.model.DAO.LibraryDAO;
import biblored.model.DAO.MaterialDAO;
import biblored.model.DAO.UserDAO;
import biblored.model.generic.Library;
import biblored.model.generic.Material;
import biblored.model.generic.User;

import java.util.ArrayList;

public class LibraryNetwork {
    private LibraryDAO libraryDAO;
    private UserDAO userDAO;
    private MaterialDAO bookDAO;
    private MaterialDAO audiovisualDAO;
    private MaterialDAO filmDAO;
    private MaterialDAO compactDiskDAO;
    private MaterialDAO documentaryDAO;

    public LibraryNetwork() {
        this.libraryDAO = new LibraryDAO();
        this.userDAO = new UserDAO();
        this.bookDAO = new MaterialDAO();
        this.audiovisualDAO = new MaterialDAO();
        this.filmDAO = new MaterialDAO();
        this.compactDiskDAO = new MaterialDAO();
        this.documentaryDAO = new MaterialDAO();
    }

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }

    public void setLibraryDAO(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public MaterialDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(MaterialDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public MaterialDAO getAudiovisualDAO() {
        return audiovisualDAO;
    }

    public void setAudiovisualDAO(MaterialDAO audiovisualDAO) {
        this.audiovisualDAO = audiovisualDAO;
    }

    public MaterialDAO getFilmDAO() {
        return filmDAO;
    }

    public void setFilmDAO(MaterialDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    public MaterialDAO getCompactDiskDAO() {
        return compactDiskDAO;
    }

    public void setCompactDiskDAO(MaterialDAO compactDiskDAO) {
        this.compactDiskDAO = compactDiskDAO;
    }

    public MaterialDAO getDocumentaryDAO() {
        return documentaryDAO;
    }

    public void setDocumentaryDAO(MaterialDAO documentaryDAO) {
        this.documentaryDAO = documentaryDAO;
    }

    public boolean addLibrary(Library library) {
        return libraryDAO.add(library);
    }

    public boolean addUser(User user) {
        return userDAO.add(user);
    }

    public boolean addBook(Material book) {
        return bookDAO.add(book);
    }

    public boolean addAudiovisual(Material audiovisual) {
        return audiovisualDAO.add(audiovisual);
    }

    public boolean addFilm(Material films) {
        return filmDAO.add(films);
    }

    public boolean addCompactDisks(Material compactDisks) {
        return compactDiskDAO.add(compactDisks);
    }

    public boolean addDocumentary(Material documentary) {
        return documentaryDAO.add(documentary);
    }

    public boolean removeLibrary(int id) {
        return libraryDAO.delete(id);
    }

    public boolean removeUser(int id) {
        return userDAO.delete(id);
    }

    public boolean removeBook(int id) {
        return bookDAO.delete(id);
    }

    public boolean removeAudiovisual(int id) {
        return audiovisualDAO.delete(id);
    }

    public boolean removeFilm(int id) {
        return filmDAO.delete(id);
    }

    public boolean removeCompactDisk(int id) {
        return compactDiskDAO.delete(id);
    }

    public boolean removeDocumentary(int id) {
        return documentaryDAO.delete(id);
    }

    public Library getLibrary(int id) {
        return libraryDAO.read(id);
    }

    public User getUser(int id) {
        return userDAO.read(id);
    }

    public Material getBook(int id) {
        return bookDAO.read(id);
    }

    public Material getAudiovisual(int id) {
        return audiovisualDAO.read(id);
    }

    public Material getFilm(int id) {
        return filmDAO.read(id);
    }

    public Material getCompactDisk(int id) {
        return compactDiskDAO.read(id);
    }

    public Material getDocumentary(int id) {
        return documentaryDAO.read(id);
    }

    public boolean updateLibrary(int id, Library library) {
        return libraryDAO.update(id, library);
    }

    public boolean updateUser(int id, User user) {
        return userDAO.update(id, user);
    }

    public boolean updateBook(int id, Material book) {
        return bookDAO.update(id, book);
    }

    public boolean updateAudiovisual(int id, Material audiovisual) {
        return audiovisualDAO.update(id, audiovisual);
    }

    public boolean updateFilm(int id, Material films) {
        return filmDAO.update(id, films);
    }

    public boolean updateCompactDisk(int id, Material compactDisks) {
        return compactDiskDAO.update(id, compactDisks);
    }

    public boolean updateDocumentary(int id, Material documentary) {
        return documentaryDAO.update(id, documentary);
    }

    public ArrayList<Library> getLibraries() {
        return libraryDAO.readAll();
    }

    public ArrayList<User> getUsers() {
        return userDAO.readAll();
    }

    public ArrayList<Material> getBooks() {
        return bookDAO.readAll();
    }

    public ArrayList<Material> getAudiovisuals() {
        return audiovisualDAO.readAll();
    }

    public ArrayList<Material> getFilms() {
        return filmDAO.readAll();
    }

    public ArrayList<Material> getCompactDisks() {
        return compactDiskDAO.readAll();
    }

    public ArrayList<Material> getDocumentaries() {
        return documentaryDAO.readAll();
    }





    // Missing methods for searching by name, author, etc.
    /* Also, the searches methods from Material's children classes
    uses the Material DAO class because has the same attributes and methods, however
    they should be implemented in their respective DAO classes. */


}
