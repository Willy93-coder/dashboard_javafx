package Dashboard.utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DataBase {
    // Atributos
    private String title;
    private String author;
    private Date publish_day;
    private int book_quantity;
    private String sinopsis;

    public static Statement smt;
    public static Connection dbConnection;

    // Constructors
    public DataBase() {
    }

    public DataBase(String title, String author, Date publish_day, int book_quantity, String sinopsis) {
        this.title = title;
        this.author = author;
        this.publish_day = publish_day;
        this.book_quantity = book_quantity;
        this.sinopsis = sinopsis;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublish_day() {
        return publish_day;
    }

    public int getBook_quantity() {
        return book_quantity;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public static void createDB () throws SQLException {
        try {
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/","library", "Cide2023");
            System.out.println("Conexión ha sido establecida");
            smt = dbConnection.createStatement();

            //  Eliminate Data Base just in case if we need to.
            smt.executeUpdate("drop database if exists library");
            //  smt.execute("Drop database library");

            //Create database named library
            smt.executeUpdate("create database if not exists library");

            //Use the database which we just created
            smt.executeUpdate("use library");

            //Create all tables necessary:
            smt.executeUpdate("create table if not exists  lib_user(" +
                    "    user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    user_email VARCHAR(50) NOT NULL," +
                    "    user_password VARCHAR(50) NOT NULL" +
                    ")");
            smt.executeUpdate("create table if not exists lib_profile(" +
                    "    pfl_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    pfl_name VARCHAR(50) NOT NULL," +
                    "    pfl_surnombre1 VARCHAR(50) NOT NULL," +
                    "    pfl_surnombre2 VARCHAR(50) ," +
                    "    pfl_Description text NOT NULL," +
                    "    user_id INT UNSIGNED," +
                    "    FOREIGN KEY(user_id) REFERENCES lib_user(user_id)" +
                    ")");
            smt.executeUpdate("create table if not exists lib_book(" +
                    "    book_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    title VARCHAR(100) NOT NULL," +
                    "    author VARCHAR(100) NOT NULL," +
                    "    publish_day date NOT NULL," +
                    "    favourite boolean NOT NULL," +
                    "    sinopsis  text NOT NULL," +
                    "    book_quantity int" +
                    ")");
            smt.executeUpdate("create table if not exists lib_Rent(" +
                    "    rent_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    user_id INT UNSIGNED," +
                    "    book_id INT unsigned," +
                    "    rent_day date not null," +
                    "    return_day date not null," +
                    "    rent_quantity int," +
                    "    FOREIGN KEY(user_id) REFERENCES lib_user(user_id)," +
                    "    FOREIGN KEY(book_id) REFERENCES lib_book(book_id)" +
                    ")");
            smt.executeUpdate("create table if not exists lib_author(" +
                    "    author_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    author_name VARCHAR(100) NOT NULL," +
                    "    author_surnombre1 VARCHAR(100) NOT NULL," +
                    "    author_surnombre2 VARCHAR(100) ," +
                    "    birthday date not null," +
                    "    death_date date" +
                    ")");
            smt.executeUpdate("create table if not exists lib_author_book(" +
                    "    author_book_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                    "    author_id int unsigned," +
                    "    book_id int unsigned," +
                    "    FOREIGN KEY(author_id) REFERENCES lib_author(author_id)," +
                    "    FOREIGN KEY(book_id) REFERENCES lib_book(book_id)" +
                    ")");

            //Add information to table book:
           smt.executeUpdate("insert into lib_book values(1,'Cien años de soledad','Gabriel García Márquez','1967-5-30',false," +
                    "'La novela cuenta la historia de la familia Buendía a lo largo de varias generaciones en el pueblo ficticio de Macondo. A través de la vida de los Buendía, García Márquez explora temas como la soledad, el amor y la mortalidad, y utiliza técnicas narrativas como el realismo mágico para crear una obra maestra de la literatura latinoamericana.',25);");

            smt.executeUpdate("insert into lib_book values(2,'Moby Dick','Herman Melville','1851-10-18',false," +
                    "'La novela sigue al capitán Ahab en su obsesiva búsqueda de la ballena blanca Moby Dick, que le arrancó una pierna en un encuentro" +
                    "previo. A través de la narrativa, Melville explora temas como la venganza, la locura y la naturaleza humana.',34);");
            smt.executeUpdate("insert into lib_book values(3,'El gran Gatsby','F. Scott Fitzgerald','1925-4-10',false,'La novela sigue al millonario Jay Gatsby" +
                    " y su obsesión por reconquistar a su antigua amante, Daisy Buchanan. A través de la narrativa, Fitzgerald explora temas como la decadencia " +
                    "de la sociedad estadounidense en la década de 1920, la búsqueda del sueño americano y la fragilidad de las relaciones humanas.',62);");
            smt.executeUpdate("insert into lib_book values(4,'Orgullo y prejuicio','Jane Austen','1813-1-28',false,'La novela sigue a la joven Elizabeth Bennet " +
                    "y su relación con el rico y arrogante Sr. Darcy. A través de la narrativa, Austen explora temas como la clase social, el matrimonio " +
                    "y el papel de la mujer en la sociedad.',83);");
            smt.executeUpdate("insert into lib_book values(5,'Mujercitas','Louisa May Alcott','1868-9-30',false,'La novela sigue a las cuatro hermanas March" +
                    " mientras crecen y enfrentan desafíos en la Nueva Inglaterra del siglo XIX. A través de la narrativa, Alcott explora temas como la familia, " +
                    "la amistad y el papel de la mujer en la sociedad.',16);");
            smt.executeUpdate("insert into lib_book values(6,'El hobbit','J.R.R. Tolkien','1937-9-21',false,'La novela sigue al hobbit Bilbo Bolsón" +
                    " mientras se embarca en una aventura para recuperar un tesoro robado por el dragón Smaug. A través de la narrativa, Tolkien explora temas como la valentía," +
                    " la amistad y la naturaleza humana.',15);");

            //Add information to table author:
            smt.executeUpdate("insert into lib_author values(1,'Gabriel','García','Márquez','1927-1-1','2014-1-1')");
            smt.executeUpdate("insert into lib_author values(2,'Herman','Melville',null,'1819-1-1','1891-1-1')");
            smt.executeUpdate("insert into lib_author values(3,'Francis','Fitzgerald','Key','1896-1-1','1940-1-1')");
            smt.executeUpdate("insert into lib_author values(4,'Jane','Austen',null,'1775-1-1','1817-1-1')");
            smt.executeUpdate("insert into lib_author values(5,'Louisa','Alcott','May','1832-1-1','1888-1-1')");
            smt.executeUpdate("insert into lib_author values(6,'John Ronald Reuel','Tolkien',null,'1892-1-1','1973-1-1')");

            //Add information to table author_book
            smt.executeUpdate("insert into lib_author_book values(1,1,1)");
            smt.executeUpdate("insert into lib_author_book values(2,2,2)");
            smt.executeUpdate("insert into lib_author_book values(3,3,3)");
            smt.executeUpdate("insert into lib_author_book values(4,4,4)");
            smt.executeUpdate("insert into lib_author_book values(5,5,5)");
            smt.executeUpdate("insert into lib_author_book values(6,6,6)");

            //this is a test to insert to table user
            smt.executeUpdate("insert into lib_user values(1,'test@gmail.com','test')");

            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getData(String query) throws SQLException{
        ResultSet data = smt.executeQuery(query);
        return data;
    }

    public static void initDB() throws SQLException {
        try {
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","library", "Cide2023");
            smt = dbConnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeBD() throws SQLException {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showAllBooks(TableColumn<DataBase, String> title, TableColumn<DataBase, String> author, TableColumn<DataBase, Date> publish_day, TableColumn<DataBase, Integer> book_quantity, TableColumn<DataBase, String> sinopsis, TableView<DataBase> table) throws SQLException{
        try {
            initDB();
            String query = "SELECT title, author, publish_day, book_quantity, sinopsis FROM lib_book;";
            ResultSet rs = smt.executeQuery("SELECT title, author, publish_day, book_quantity, sinopsis FROM lib_book;");
            ObservableList<DataBase> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                DataBase book = new DataBase(rs.getString("title"), rs.getString("author"), rs.getDate("publish_day"), rs.getInt("book_quantity"), rs.getString("sinopsis"));
                System.out.println(book);
                bookList.add(book);
            }
            table.setItems(bookList);
            table.refresh();
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            publish_day.setCellValueFactory(new PropertyValueFactory<>("publish_day"));
            book_quantity.setCellValueFactory(new PropertyValueFactory<>("book_quantity"));
            sinopsis.setCellValueFactory(new PropertyValueFactory<>("sinopsis"));
            closeBD();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
