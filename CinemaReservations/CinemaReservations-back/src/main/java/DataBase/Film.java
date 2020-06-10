package DataBase;

import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.sql.ResultSetMetaData;

public class Film
{
    private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
    private static String tableName = "film";
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args)
    {
        createConnection();
        insertFilm(1, "The Shawshank Redemption", "Adaptacja opowiadania Stephena Kinga.");
        insertFilm(2, "Intouchables", "Sparaliżowany milioner zatrudnia do opieki młodego chłopaka.");
        insertFilm(3, "The Green Mile", "Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie.");
        insertFilm(4, "The Godfather", "Opowieść o nowojorskiej rodzinie mafijnej.");
        insertFilm(5, "12 Angry Men", "Dwunastu przysięgłych ma wydać wyrok w procesie o morderstwo.");

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

    private static void insertFilm(int film_id, String filmTitle, String filmDescription)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + film_id + ",'" + filmTitle + "','" + filmDescription +"')");
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
                System.out.println(film_id + "\t\t" + filmTitle + "\t\t" + filmDescription);
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