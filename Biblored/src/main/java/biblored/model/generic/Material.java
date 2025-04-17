package biblored.model.generic;

import biblored.model.Status;

import java.time.LocalDate;


public class Material {

    private int id;
    private String name;
    private String author;
    private LocalDate dateRegistered;
    private Library library;
    private String language;
    private Status status;

    public Material(){
        this.id = 0;
        this.name = "";
        this.author = "";
        this.dateRegistered = LocalDate.now();
        this.library = new Library();
        this.language = "";
        this.status = Status.AVAILABLE;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
