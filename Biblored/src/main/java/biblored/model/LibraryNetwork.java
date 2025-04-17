package biblored.model;


import biblored.model.DAO.LibraryDAO;
import biblored.model.DAO.MaterialDAO;
import biblored.model.DAO.UserDAO;
import biblored.model.generic.Library;
import biblored.model.generic.Material;
import biblored.model.generic.User;

public class LibraryNetwork {
    private LibraryDAO libraryDAO;
    private UserDAO userDAO;
    private MaterialDAO bookDAO;
    private MaterialDAO audiovisualDAO;
    private MaterialDAO filmsDAO;
    private MaterialDAO compactDisksDAO;
    private MaterialDAO documentaryDAO;

    public LibraryNetwork() {
        this.libraryDAO = new LibraryDAO();
        this.userDAO = new UserDAO();
        this.bookDAO = new MaterialDAO();
        this.audiovisualDAO = new MaterialDAO();
        this.filmsDAO = new MaterialDAO();
        this.compactDisksDAO = new MaterialDAO();
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

    public MaterialDAO getFilmsDAO() {
        return filmsDAO;
    }

    public void setFilmsDAO(MaterialDAO filmsDAO) {
        this.filmsDAO = filmsDAO;
    }

    public MaterialDAO getCompactDisksDAO() {
        return compactDisksDAO;
    }

    public void setCompactDisksDAO(MaterialDAO compactDisksDAO) {
        this.compactDisksDAO = compactDisksDAO;
    }

    public MaterialDAO getDocumentaryDAO() {
        return documentaryDAO;
    }

    public void setDocumentaryDAO(MaterialDAO documentaryDAO) {
        this.documentaryDAO = documentaryDAO;
    }





}
