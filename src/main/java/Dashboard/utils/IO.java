package Dashboard.utils;

import java.io.*;

public class IO {
    public static void writeUser(String email, String password) {
        try {
            String txt = "User Email: " + email + ", Password: " + password;
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("user_data.txt", true));
            osw.write("\r\n" + txt);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDataBook(String title, String author, String sinopsis, String publish_day, String quantity) {
        try {
            String txt = "Book{\r\n"+"\tTitle: " + title + "\r\n\tAuthor: " + author + "\r\n\tSinopsis: " + sinopsis + "\r\n\tPublish_day: " + publish_day + "\r\n\tQuantity: " + quantity +"\r\n}";
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("book_data.txt", true));
            osw.write("\r\n" + txt);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
