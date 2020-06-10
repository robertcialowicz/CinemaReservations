package DataBase;

import java.sql.*;

public class ReservationsForSeans {


        public static void main(String[] args) throws Exception {
            String dbURL = "jdbc:derby://localhost:1527/C:/Users/dawid/OneDrive/Pulpit/ProjektTO/CinemaReservations/DataBase/db-derby-10.2.1.6-bin/DataBase;create=true;";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = null;
            ResultSet rs = null;

            try{
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT APP.SEAT_STATUS.NAME, COUNT() FROM APP.SEAT_STATUS WHERE NAME = RESERVATION");
                while(rs.next()){
                    System.out.println(rs.getInt(1) + " " + rs.getString(2));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        DriverManager.getConnection(dbURL + ";shutdown=true");
                        conn.close();
                    }
                } catch (SQLException sqlExcept) {

                }
        }
    }
