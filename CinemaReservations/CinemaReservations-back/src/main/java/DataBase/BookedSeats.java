package DataBase;

import java.sql.*;

public class BookedSeats {

        private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
        private static String tableName = "booked_Seats";
        private static Connection conn = null;
        private static Statement stmt = null;

        public static void main(String[] args)
        {
            createConnection();
            insertBookedSeats(1, 5, 1);
            insertBookedSeats(2, 5, 3);
            insertBookedSeats(3, 5, 4);
            insertBookedSeats(4, 1, 3);
            insertBookedSeats(5, 2, 2);

            selectBookedSeats();
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

        private static void insertBookedSeats(int booked_Seats_id, int which_Hall_id, int theatre_Hall_id)
        {
            try
            {
                stmt = conn.createStatement();
                stmt.execute("insert into " + tableName + " values (" +
                        booked_Seats_id + ',' + which_Hall_id + ',' + theatre_Hall_id + ')');
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void selectBookedSeats()
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
                    int booked_Seats_id = results.getInt(1);
                    //int which_Hall_id = results.getInt(2);
                    PreparedStatement which_Hall_id = conn.prepareStatement("SELECT " + results.getString("which_Hall_id") + " FROM " + results.getString("which_Hall"));
                   // int theatre_Hall_id = results.getInt(2);
                    PreparedStatement theatre_Hall_id = conn.prepareStatement("SELECT " + results.getString("theatre_Hall_id") + " FROM " + results.getString("theatre_Hall"));
                    System.out.println(booked_Seats_id + "\t\t" + which_Hall_id + "\t\t" + theatre_Hall_id);
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



