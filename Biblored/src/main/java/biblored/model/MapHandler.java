package biblored.model;

import biblored.model.DTOs.*;
import biblored.model.generic.*;

public class MapHandler {

    public static AddressDTO AddressToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

    public static Address AddressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZipCode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

    public static LibraryDTO LibraryToLibraryDTO(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setId(library.getId());
        libraryDTO.setName(library.getName());
        libraryDTO.setAddress(AddressToAddressDTO(library.getAddress()));
        return libraryDTO;
    }

    public static Library LibraryDTOToLibrary(LibraryDTO libraryDTO) {
        Library library = new Library();
        library.setId(libraryDTO.getId());
        library.setName(libraryDTO.getName());
        library.setAddress(AddressDTOToAddress(libraryDTO.getAddress()));
        return library;
    }

    public static MaterialDTO MaterialToMaterialDTO(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(material.getId());
        materialDTO.setName(material.getName());
        materialDTO.setAuthor(material.getAuthor());
        materialDTO.setDateRegistered(material.getDateRegistered());
        materialDTO.setLibrary(LibraryToLibraryDTO(material.getLibrary()));
        materialDTO.setLanguage(material.getLanguage());
        return materialDTO;
    }
}
