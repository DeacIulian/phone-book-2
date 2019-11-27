package org.fasttrackit.phonebook.persistance;

import org.fasttrackit.phonebook.domain.PhoneBookItem;
import org.fasttrackit.phonebook.transfer.CreatePhoneBookItemRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookItemRepository {

    public void createPhoneBookItem(CreatePhoneBookItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO phone_book_item (firstName, lastName, phoneNumber) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }

    public void updatePhoneBookItem(long id, String firstName, String lastName, String phoneNumber) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE phone_book_item SET firstName=?, lastName=?, phoneNumber=? WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setLong(4, id);


            preparedStatement.executeUpdate();
        }
    }

    public void deletePhoneBookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phone_book_item WHERE id=?";

        try(Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public List<PhoneBookItem> getPhoneBookItems() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, firstName, lastName, phoneNumber FROM phone_book_item";

        List<PhoneBookItem> phoneBookItems = new ArrayList<>();

        try(Connection connection = DatabaseConfiguration.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {


            while(resultSet.next()){
                PhoneBookItem phoneBookItem = new PhoneBookItem();

                phoneBookItem.setId(resultSet.getLong("id"));
                phoneBookItem.setFirstName(resultSet.getString("firstName"));
                phoneBookItem.setLastName(resultSet.getString("lastName"));
                phoneBookItem.setPhoneNumber(resultSet.getString("phoneNumber"));

                phoneBookItems.add(phoneBookItem);
            }
        }
        return phoneBookItems;
    }
}
