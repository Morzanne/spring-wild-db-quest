package com.wildcodeschool.myProjectWithDB.repositories;

import com.wildcodeschool.myProjectWithDB.entities.School;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolRepository {

    public static School SelectById(int id){
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT", "root", "80ee480bd30"
                );

                PreparedStatement statement = connection.prepareStatement("SELECT * FROM school WHERE id=?")
        ){
            statement.setInt(1, id);

            try(
                    ResultSet resultSet = statement.executeQuery()

            ){

                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int capacity = resultSet.getInt("capacity");
                    String country = resultSet.getString("country");

                    return new School(id,name,capacity,country);

                }
                else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR, "unknown id in table school"
                    );
                }
                }
            }
            catch (SQLException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "", e
                );
            }
        }
    public static int insertInto(
            String name,
            int capacity,
            String country

    ) {
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT", "root", "80ee480bd30"
                );
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO school (name, capacity, country) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            statement.setString(1, name);
            statement.setInt(2, capacity);
            statement.setString(3, country);

            if(statement.executeUpdate() != 1) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "failed to insert datas"
                );
            }

            try(
                    ResultSet generatedKeys = statement.getGeneratedKeys();
            ) {
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR, "failed to get inserted id"
                    );
                }
            }
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }

    public static int update(int id, String name, int capacity, String country){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT", "root", "80ee480bd30");
            PreparedStatement statement = connection.prepareStatement("UPDATE school set name=?, capacity=?, country=? WHERE id=?")
        ){
            statement.setString(1, name);
            statement.setInt(2, capacity);
            statement.setString(3, country);
            statement.setInt(4, id);

            return statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }



    }

