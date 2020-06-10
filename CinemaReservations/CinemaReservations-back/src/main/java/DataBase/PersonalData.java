package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class PersonalData {

        private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
        private static String tableName = "personal_data";
        private static Connection conn = null;
        private static Statement stmt = null;

        public static void main(String[] args)
        {
            createConnection();
            insertPersonalData(1, "Quentin", "Tarantino", "quentin@gmail.com", "123456789");
            insertPersonalData(2, "Stanley", "Kubrick", "stanley@gmail.com", "234567890");
            insertPersonalData(3, "Sergio", "Leone", "sergio@gmail.com", "987654321");
            insertPersonalData(4, "Francis", "Ford Coppola", "francis@gmail.com", "654567890");
            insertPersonalData(5, "David", "Fincher", "david@gmail.com", "765432123");

            selectPersonalData();
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

        private static void insertPersonalData(int personalData_id, String name, String surname, String email, String phoneNumber)
        {
            try
            {
                stmt = conn.createStatement();
                stmt.execute("insert into " + tableName + " values (" +
                        personalData_id + ",'" + name + "','" + surname + "','" + email + "','" + phoneNumber +"')");
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void selectPersonalData()
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
                    int personalData_id = results.getInt(1);
                    String name = results.getString(2);
                    String surname = results.getString(3);
                    String email = results.getString(4);
                    String phoneNumber = results.getString(5);
                    System.out.println(personalData_id + "\t\t" + name + "\t\t" + surname + "\t\t" + email + "\t\t" + phoneNumber);
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
