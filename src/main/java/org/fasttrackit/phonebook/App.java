package org.fasttrackit.phonebook;


import org.fasttrackit.phonebook.domain.PhoneBookItem;
import org.fasttrackit.phonebook.persistance.PhoneBookItemRepository;
import org.fasttrackit.phonebook.transfer.CreatePhoneBookItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        CreatePhoneBookItemRequest request = new CreatePhoneBookItemRequest();
        //request.setFirstName("Iulian");
        //request.setLastName("Deac");
        //request.setPhoneNumber("0729485562");
        PhoneBookItemRepository phoneBookItemRepository = new PhoneBookItemRepository();

        //phoneBookItemRepository.createPhoneBookItem(request);
        //phoneBookItemRepository.updatePhoneBookItem(1, "Ana-Maria", "Vlad", "0245671823");
        //phoneBookItemRepository.deletePhoneBookItem(1);

        List<PhoneBookItem> phoneBookItems = phoneBookItemRepository.getPhoneBookItems();

        System.out.println(phoneBookItems);
    }
}
