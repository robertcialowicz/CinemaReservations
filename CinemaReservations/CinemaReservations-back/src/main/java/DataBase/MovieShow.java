package DataBase;

import java.sql.*;

public class MovieShow {

        private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
        private static String tableName = "movie_show";
        private static Connection conn = null;
        private static Statement stmt = null;


        public static void main(String[] args)
        {
            createConnection();
            insertMovieShow(1, "2019-06-11", "17.45", 1, 2);
            insertMovieShow(2, "2020-06-21", "20.00", 2, 3);
            insertMovieShow(3, "2020-07-11","20.00", 3, 3);
            insertMovieShow(4, "2020-07-11", "17.00", 5, 1);
            insertMovieShow(5, "2020-07-21", "18.00", 5, 1);

            selectMovieShow();
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

        private static void insertMovieShow(int movieShow_id, String movieDate, String movieTime, int film_id, int whichHall_id)
        {
            try
            {
                stmt = conn.createStatement();
                stmt.execute("insert into " + tableName + " values (" + movieShow_id + ',' + movieDate + ',' + movieTime + ',' + film_id + ',' + whichHall_id +')');
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void selectMovieShow()
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

                System.out.println("\n-------------------------------------------------------------------------------");

                while(results.next())
                {
                    int movieShow_id = results.getInt(1);
                    Date movieDate = results.getDate(2);
                    Time movieTime = results.getTime(3);
                    int film_id = results.getInt(4);
                    int whichHall_id = results.getInt(5);
                    System.out.println(movieShow_id + "\t\t" + movieDate + "\t\t" + movieTime + "\t\t" + film_id + "\t\t" + whichHall_id);
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