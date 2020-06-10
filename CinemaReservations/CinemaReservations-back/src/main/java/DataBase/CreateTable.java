package DataBase;// Create BOOKS table in APP schema
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class CreateTable {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:derby:c:\\Users\\dawid\\OneDrive\\Pulpit\\ProjektTO\\CinemaReservations\\DataBase\\db-derby-10.2.1.6-bin\\testdb");
        Statement st = con.createStatement();
        st.executeUpdate("create table app.books (id int primary key GENERATED ALWAYS AS IDENTITY, title varchar(50),  price int)");
        st.close();
        con.close();
    }
}