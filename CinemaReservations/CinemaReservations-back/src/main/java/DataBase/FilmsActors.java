package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class FilmsActors {

        private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
        private static String tableName = "films_Actors";
        private static Connection conn = null;
        private static Statement stmt = null;

        public static void main(String[] args)
        {
            createConnection();
            insertFilmsActors(1, 5);
            insertFilmsActors(2, 2);
            insertFilmsActors(3, 2);
            insertFilmsActors(4, 1);
            insertFilmsActors(5, 2);

            selectFilmsActors();
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

        private static void insertFilmsActors(int film_id, int actors_id)
        {
            try
            {
                stmt = conn.createStatement();
                stmt.execute("insert into " + tableName + " values (" +
                        film_id + ',' + actors_id +')');
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void selectFilmsActors()
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
                    int film_id = results.getInt(1);
                    int actors_id = results.getInt(2);
                    System.out.println(film_id + "\t\t" + actors_id);
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