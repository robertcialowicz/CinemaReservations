package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Reservations {

        private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
        private static String tableName = "reservation";
        private static Connection conn = null;
        private static Statement stmt = null;

        public static void main(String[] args)
        {
            createConnection();
            insertReservation(1, "Jan", "Kowalski", "janatkowalski.pl",4, 3);
            insertReservation(2, "Anna", "Nowak", "annaatnowak.pl",4, 3);
            insertReservation(3, "Jan", "Kowalski", "janatkowalski.pl",1, 1);
            insertReservation(4, "Anna", "Nowak", "annaatnowak.pl",4, 2);
            insertReservation(5, "Izabela", "Kowalczyk", "izabelaatkowalczyk.pl", 3, 1);

            selectReservation();
            shutdown();
        }

        private static void createConnection()
        {
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                conn = DriverManager.getConnection(dbURL);
            }
            catch (Exception except)
            {
                except.printStackTrace();
            }
        }

        private static void insertReservation(int reservation_id, String name, String surname, String email, int booked_Seats_id, int movie_Show_id)
        {
            try
            {
                stmt = conn.createStatement();
                stmt.execute("insert into " + tableName + " values (" +
                        reservation_id + ',' + name + ',' + surname + ',' + email + ',' + booked_Seats_id + ',' + movie_Show_id +')');
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void selectReservation()
        {
            try
            {
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from " + tableName);
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                for (int i=1; i<=numberCols; i++)
                {

                    System.out.print(rsmd.getColumnLabel(i)+"\t\t");
                }

                System.out.println("\n-------------------------------------------------");

                while(results.next())
                {
                    int reservation_id = results.getInt(1);
                    String name = results.getString(2);
                    String surname = results.getString(2);
                    String email = results.getString(4);
                    int booked_Seats_id = results.getInt(5);
                    int movie_Show_id = results.getInt(6);
                    System.out.println(reservation_id + "\t\t" + name + "\t\t" + surname + "\t\t" + email + "\t\t" + booked_Seats_id + "\t\t" + movie_Show_id);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void shutdown()
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if (conn != null)
                {
                    DriverManager.getConnection(dbURL + ";shutdown=true");
                    conn.close();
                }
            }
            catch (SQLException sqlExcept)
            {

            }

        }
    }

