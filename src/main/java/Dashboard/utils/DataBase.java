package Dashboard.utils;
import java.sql.*;

public class DataBase {
    public static void main(String[] args) throws SQLException {
        Connection dbConnection=null;
        try{
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/","library", "Cide2023");
            System.out.println("Conexión ha sido establecida");
            Statement smt = dbConnection.createStatement();
            smt.executeUpdate("create database if not exists library");
            smt.executeUpdate("use library");
            smt.executeUpdate("CREATE TABLE lib_user (user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    user_email VARCHAR(50) NOT NULL," + "user_password VARCHAR(50) NOT NULL)");
            smt.executeUpdate("create table lib_profile(" +
                    "pfl_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "pfl_name VARCHAR(50) NOT NULL," +
                    "    pfl_surnombre1 VARCHAR(50) NOT NULL," +
                    "    pfl_surnombre2 VARCHAR(50) ," +
                    "    pfl_Description VARCHAR(50) NOT NULL," +
                    "user_id INT UNSIGNED," +
                    "    FOREIGN KEY(user_id) REFERENCES lib_user(user_id)" +
                    ")");
            smt.executeUpdate("create table book(" +
                    "book_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "title VARCHAR(50) NOT NULL," +
                    "    author VARCHAR(50) NOT NULL," +
                    "    publish_day date NOT NULL," +
                    "    favourite boolean NOT NULL," +
                    "    sinopsis VARCHAR(50) NOT NULL," +
                    "    book_quantity int" +
                    ")");
            smt.executeUpdate("create table Rent(" +
                    "rent_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT UNSIGNED," +
                    "    book_id INT unsigned," +
                    "    rent_day date not null," +
                    "    return_day date not null," +
                    "    rent_quantity int," +
                    "FOREIGN KEY(user_id) REFERENCES lib_user(user_id)," +
                    "FOREIGN KEY(book_id) REFERENCES book(book_id)" +
                    ")");
            smt.executeUpdate("create table author(" +
                    "author_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "author_name VARCHAR(50) NOT NULL," +
                    "    author_surnombre1 VARCHAR(50) NOT NULL," +
                    "    author_surnombre2 VARCHAR(50) ," +
                    "    birthday date not null," +
                    "    death_date date" +
                    ")");
            smt.executeUpdate("create table author_book(" +
                    "author_book_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "author_id int unsigned," +
                    "    book_id int unsigned," +
                    "    FOREIGN KEY(author_id) REFERENCES author(author_id)," +
                    "FOREIGN KEY(book_id) REFERENCES book(book_id)" +
                    ")");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
