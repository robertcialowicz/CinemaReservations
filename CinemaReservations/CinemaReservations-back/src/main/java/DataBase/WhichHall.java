package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class WhichHall {


    private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
    private static String tableName = "which_Hall";
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args)
    {
        createConnection();
        insertWhichHall(1, "Blue");
        insertWhichHall(2, "Black");
        insertWhichHall(3, "Green");
        insertWhichHall(4, "Red");
        insertWhichHall(5, "Yellow");

        selectWhichHall();
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

    private static void insertWhichHall(int whichHall_id, String hallName)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + whichHall_id + ",'" + hallName +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectWhichHall()
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
                int whichHall_id = results.getInt(1);
                String hallName = results.getString(2);
                System.out.println(whichHall_id + "\t\t" + hallName);
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
