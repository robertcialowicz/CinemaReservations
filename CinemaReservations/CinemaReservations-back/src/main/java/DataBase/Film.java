package DataBase;

import java.sql.*;

public class Film
{
    private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
    private static String tableName = "film";
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args)
    {
        createConnection();
        insertFilm(6, "The Shawshank Redemption", "Adaptacja opowiadania Stephena Kinga.", "action","Quentin Tarantino","2020/11/11", "Morgan Freeman", 16);
        insertFilm(7, "Intouchables", "Sparaliżowany milioner zatrudnia do opieki młodego chłopaka.", "action","Frank Darabont","2020/11/11", "Morgan Freeman", 16);
        insertFilm(8, "The Green Mile", "Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie.", "action","Quentin Tarantino", "2020/11/11", "Morgan Freeman", 16);
        insertFilm(9, "The Godfather", "Opowieść o nowojorskiej rodzinie mafijnej.", "action","Quentin Tarantino","2020/11/11", "Frank Darabont", 13);
        insertFilm(10, "12 Angry Men", "Dwunastu przysięgłych ma wydać wyrok w procesie o morderstwo.", "action","Quentin Tarantino","2020/11/11", "Morgan Freeman", 18);

        selectFilms();
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

    private static void insertFilm(int film_id, String filmTitle, String filmDescription, String type, String scenario, String release_date, String cast, int age_limit)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + film_id + ',' + filmTitle + ',' + filmDescription + ',' + type + ',' + scenario + ',' + release_date + ',' + cast + ',' + age_limit + ')');
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectFilms()
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
                String filmTitle = results.getString(2);
                String filmDescription = results.getString(3);
                String type = results.getString(4);
                String scenario = results.getString(5);
                Date release_date = results.getDate(6);
                String cast = results.getString(7);
                int age_Limit = results.getInt(8);

                System.out.println(film_id + "\t\t" + filmTitle + "\t\t" + filmDescription + "\t\t" + type  + "\t\t" + scenario  + "\t\t" + release_date  + "\t\t" + cast  + "\t\t" +age_Limit);
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