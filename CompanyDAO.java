package com.example;
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM companies";

        try (Connection connection = Databaseconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id")); 
                company.setCategory(resultSet.getString("category"));
                company.setPrice(resultSet.getDouble("price"));
                company.setStocked(resultSet.getBoolean("stocked"));
                company.setName(resultSet.getString("name")); 
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }
}
*/