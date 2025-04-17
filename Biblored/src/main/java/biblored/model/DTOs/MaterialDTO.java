package biblored.model.DTOs;

import biblored.model.Status;

import java.io.Serializable;
import java.time.LocalDate;

public class MaterialDTO implements Serializable {
    private int id;
    private String name;
    private String author;
    private LocalDate dateRegistered;
    private LibraryDTO library;
    private String language;
    private Status status;

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

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
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
