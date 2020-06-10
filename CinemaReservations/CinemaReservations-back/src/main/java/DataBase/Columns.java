package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Columns {

        private static String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
        private static String tableName = "columns";
        private static Connection conn = null;
        private static Statement stmt = null;

        public static void main(String[] args)
        {
            createConnection();
            insertColumns(1, 15);
            insertColumns(2, 15);
            insertColumns(3, 12);
            insertColumns(4, 12);
            insertColumns(5, 12);

            selectRows();
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

        private static void insertColumns(int column_id, int columnsNumber)
        {
            try
            {
                stmt = conn.createStatement();
                stmt.execute("insert into " + tableName + " values (" + column_id + ',' + columnsNumber +')');
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
        }

        private static void selectRows()
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
                    int column_id = results.getInt(1);
                    int columnsNumber = results.getInt(2);
                    System.out.println(column_id + "\t\t" + columnsNumber);
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


