package com.restapi.service;

import java.util.List;
import java.util.Optional;

import com.restapi.entity.AddressBook;
import com.restapi.repository.AddressBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public AddressBook createAddressBook(AddressBook addressBook) {
        try {
            return addressBookRepository.save(addressBook);
        } catch (Exception e) {
            return null;
        }
    }

    public List<AddressBook> getAddressBookByBoardNo(Long BoardNo) {
        return addressBookRepository.findByBoardNoEquals(BoardNo);
    }

    public AddressBook patchAddressBook(Long id, AddressBook addressBook){
        final Optional<AddressBook> fetchedAddressBook = addressBookRepository.findById(id);
        if(fetchedAddressBook.isPresent()){
            if(addressBook.getZipCode() > 0) fetchedAddressBook.get().setZipCode(addressBook.getZipCode());
            if(addressBook.getAddr1() != null) fetchedAddressBook.get().setAddr1(addressBook.getAddr1());
            if(addressBook.getAddr2() != null) fetchedAddressBook.get().setAddr2(addressBook.getAddr2());
            if(addressBook.getAddr3() != null) fetchedAddressBook.get().setAddr3(addressBook.getAddr3());
            
            return addressBookRepository.save(fetchedAddressBook.get());
        }
        else return null;
    }

    public boolean deleteAddressBook(Long id){
        final Optional<AddressBook> fetchedProgressTime = addressBookRepository.findById(id);
        if(fetchedProgressTime.isPresent()){
            addressBookRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}