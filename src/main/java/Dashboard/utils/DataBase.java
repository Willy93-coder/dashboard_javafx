package Dashboard.utils;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;

/**
 * @author Chen, S. Folgueras y Willy
 * @version 1.0.0
 * 12/05/2023
 */

/**
 * Clase que contiene toda la lógica relacionada con la base de datos
 */
public class DataBase {
    // Atributos
    /**
     * Titulo del libro
     */
    private String title;
    /**
     * Autor del libro
     */
    private String author;
    /**
     * Fecha de publicacón del libro
     */
    private Date publish_day;
    /**
     * Cantidad que tenemos del libro
     */
    private int book_quantity;
    /**
     * Sinopsis del libro
     */
    private String sinopsis;
    /**
     * Email del usuario
     */
    private String user_email;
    /**
     * Fecha de alquiler del libro
     */
    private Date rent_day;
    /**
     * Fecha de devolución del libro
     */
    private Date return_day;

    /**
     * Atributo estatico del tipo Statement
     * para ejecutar las querys de la base de datos
     */
    public static Statement smt;
    /**
     * Atributo estatico del tipo Connection
     * para establecer la consexión con la base de datos
     */
    public static Connection dbConnection;

    // Constructores
    /**
     * Constructor por defecto
     */
    public DataBase() {
    }
    /**
     * Constructor para crear los datos del libro
     * @param title titulo del libro
     * @param author autor del libro
     * @param publish_day publicación del libro
     * @param book_quantity cantidad del libro
     * @param sinopsis sinopsis del libro
     */
    // Books table constructor
    public DataBase(String title, String author, Date publish_day, int book_quantity, String sinopsis) {
        this.title = title;
        this.author = author;
        this.publish_day = publish_day;
        this.book_quantity = book_quantity;
        this.sinopsis = sinopsis;
    }

    /**
     * Constructor para crear los del alquiler
     * @param title titulo del libro
     * @param rent_day día de alquiler del libro
     * @param return_day día de devolución del libro
     * @param email email del usuario que ha alquilado el libro
     */
    public DataBase(String title, Date rent_day, Date return_day, String email) {
        this.title = title;
        this.rent_day = rent_day;
        this.return_day = return_day;
        this.user_email = email;
    }

    // Getters
    /**
     * Método que retorna el título del libro
     */
    public String getTitle() {
        return title;
    }
    /**
     * Método que retorna el autor del libro
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Método que retorna el día de publicación del libro
     */
    public Date getPublish_day() {
        return publish_day;
    }
    /**
     * Método que retorna la cantidad que tenemos del libro
     */
    public int getBook_quantity() {
        return book_quantity;
    }
    /**
     * Método que retorna la sinopsis del libro
     */
    public String getSinopsis() {
        return sinopsis;
    }
    /**
     * Método que retorna el email del usuario
     */
    public String getUser_email() {
        return user_email;
    }
    /**
     * Método que retorna la fecha de alquiler del libro
     */
    public Date getRent_day() {
        return rent_day;
    }
    /**
     * Método que retorna la fecha de devolución del libro
     */
    public Date getReturn_day() {
        return return_day;
    }
    /**
     * Método para crear la base de datos
     */
    public static void createDB () throws SQLException {
        try {
            // Establecemos conexión a base de datos
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/","library", "Cide2023");
            smt = dbConnection.createStatement();

            // Eliminar la Base de Datos por si acaso
            smt.executeUpdate("drop database if exists library");

            // Crear base de datos llamada biblioteca
            smt.executeUpdate("create database if not exists library");

            // Utilizar la base de datos que acabamos de crear
            smt.executeUpdate("use library");

            // Crear todas las tablas necesarias:
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

           //Agregar información a la tabla libro:
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

            // Agregar información a la tabla autor:
            smt.executeUpdate("insert into lib_author values(1,'Gabriel','García','Márquez','1927-1-1','2014-1-1')");
            smt.executeUpdate("insert into lib_author values(2,'Herman','Melville',null,'1819-1-1','1891-1-1')");
            smt.executeUpdate("insert into lib_author values(3,'Francis','Fitzgerald','Key','1896-1-1','1940-1-1')");
            smt.executeUpdate("insert into lib_author values(4,'Jane','Austen',null,'1775-1-1','1817-1-1')");
            smt.executeUpdate("insert into lib_author values(5,'Louisa','Alcott','May','1832-1-1','1888-1-1')");
            smt.executeUpdate("insert into lib_author values(6,'John Ronald Reuel','Tolkien',null,'1892-1-1','1973-1-1')");

            // Agregar información a la tabla:
            smt.executeUpdate("insert into lib_author_book values(1,1,1)");
            smt.executeUpdate("insert into lib_author_book values(2,2,2)");
            smt.executeUpdate("insert into lib_author_book values(3,3,3)");
            smt.executeUpdate("insert into lib_author_book values(4,4,4)");
            smt.executeUpdate("insert into lib_author_book values(5,5,5)");
            smt.executeUpdate("insert into lib_author_book values(6,6,6)");

            //Agregar información a la tabla usuarios:
            smt.executeUpdate("insert into lib_user values(1,'test@gmail.com','test')");
            smt.executeUpdate("insert into lib_user values(2,'willy@gmail.com','willy')");

            //Agregar información a la tabla de alquiler:
            smt.executeUpdate("insert into lib_rent(user_id, book_id, rent_day,return_day,rent_quantity) values(1,1,'2023-06-12','2023-07-20',1)");

            // Cerrar conexión con la Dase de Datos
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que devuelve los datos de una consulta a la Base de Datos
     * @param query query de la consulta
     */
    public static ResultSet getData(String query) throws SQLException{
        ResultSet data = smt.executeQuery(query);
        return data;
    }

    /**
     * Método para iniciar la conexión a Base de Datos
     */
    public static void initDB() throws SQLException {
        try {
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","library", "Cide2023");
            smt = dbConnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para cerrar la conexión con la Base de Datos
     */
    public static void closeBD() throws SQLException {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para mostra la información del libro en la tabla
     * @param title columna del titulo del libro
     * @param author columna del autor del libro
     * @param publish_day columna de la fecha de publicación del libro
     * @param book_quantity columna de la cantidad del libro
     * @param sinopsis columna de sinopsis del libro
     * @param table tabla donde van los datos del libro
     */
    public static void showAllBooks(TableColumn<DataBase, String> title, TableColumn<DataBase, String> author, TableColumn<DataBase, Date> publish_day, TableColumn<DataBase, Integer> book_quantity, TableColumn<DataBase, String> sinopsis, TableView<DataBase> table) throws SQLException{
        try {
            initDB();
            String query = "SELECT title, author, publish_day, book_quantity, sinopsis FROM lib_book;";
            ResultSet rs = smt.executeQuery(query);
            ObservableList<DataBase> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                DataBase book = new DataBase(rs.getString("title"), rs.getString("author"), rs.getDate("publish_day"), rs.getInt("book_quantity"), rs.getString("sinopsis"));
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

    /**
     * Método para mostra la información del alquiler del libro en la tabla
     * @param title columna del titulo del libro
     * @param rentDate columna de la fecha de alquiler del libro
     * @param returnDate columna de la fecha de devolución del libro
     * @param email columna del email del usuario que ha alquilado el libro
     * @param table tabla donde van los datos del alquiler
     */
    public static void rentBooksTable(TableColumn<DataBase, String> title, TableColumn<DataBase, Date> rentDate, TableColumn<DataBase, Date> returnDate, TableColumn<DataBase, String> email, TableView<DataBase> table) throws SQLException {
           initDB();
           String query = "select b.title, r.rent_day, r.return_day, u.user_email from lib_rent r inner join lib_user u on r.user_id = u.user_id inner join lib_book b on b.book_id = r.book_id;";
           ResultSet rs = smt.executeQuery(query);
           ObservableList<DataBase> rentList = FXCollections.observableArrayList();
           while (rs.next()) {
               DataBase rentBook = new DataBase(rs.getString("title"), rs.getDate("rent_day"), rs.getDate("return_day"), rs.getString("user_email"));
               rentList.add(rentBook);
           }
           table.setItems(rentList);
           table.refresh();
           title.setCellValueFactory(new PropertyValueFactory<>("title"));
           rentDate.setCellValueFactory(new PropertyValueFactory<>("rent_day"));
           returnDate.setCellValueFactory(new PropertyValueFactory<>("return_day"));
           email.setCellValueFactory(new PropertyValueFactory<>("user_email"));
           closeBD();
    }

    /**
     * Método para dar de alta usuarios
     * @param email email del usuario
     * @param password contraseña del usuario
     */
    public static void insertUser(String email, String password) throws SQLException {
        initDB();
        String query = ("insert into lib_user (user_email, user_password) values('"+ email +"','"+ password +"');");
        smt.executeUpdate(query);
        closeBD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Registro completado correctamente");
        alert.showAndWait();
    }

    /**
     * Método para insertar libros en la Base de Datos
     * @param title columna del titulo del libro
     * @param author columna del autor del libro
     * @param sinopsis columna de sinopsis del libro
     * @param date columna de la fecha de publicación del libro
     * @param quantity columna de la cantidad del libro
     */
    public static void insertBook(String title, String author, String sinopsis, String date, String quantity) throws ParseException, SQLException {
        initDB();
        int quantityDB = parseInt(quantity);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date formatDate = format.parse(date);
        long numberDate = formatDate.getTime();
        Date dateDB = new Date(numberDate);
        String query = ("insert into lib_book(title, author,publish_day,favourite,sinopsis,book_quantity) values('"+ title +"','"+ author +"','"+dateDB+"',false, '" + sinopsis +"',"+ quantityDB+");");
        smt.executeUpdate(query);
        closeBD();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Registro completado correctamente");
        alert.showAndWait();
    };

}
