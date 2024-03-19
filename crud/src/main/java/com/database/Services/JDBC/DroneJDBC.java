package com.database.Services.JDBC;

import com.database.Services.CRUDInterface.CRUDInterface;
import com.database.Services.Items.Drone;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import static com.database.Services.MySQLConnection.*;
import java.sql.*;
import java.util.*;

public class DroneJDBC implements CRUDInterface{

    private Connection transConnection;

    private static final String SQL_SELECT = "SELECT DroneId, OwnerName, DroneClass, Speed FROM Drone";
    private static final String SQL_SELECT_ONE = "SELECT DroneId, OwnerName, DroneClass, Speed FROM Drone WHERE DroneId = ?";
    private static final String SQL_INSERT = "INSERT INTO Drone (OwnerName, DroneClass, Speed) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Drone SET OwnerName=?, DroneClass=?, Speed=? WHERE DroneId = ?";
    private static final String SQL_DELETE = "DELETE FROM Drone WHERE DroneID = ?";


    public DroneJDBC(Connection conn){
        this.transConnection = conn;
    }

    public DroneJDBC(){
        super();
    }

    public List<Drone> select() throws SQLException{
       Connection conn = null;
       PreparedStatement pStatement = null;
       ResultSet rs = null;
       List<Drone> drones = new ArrayList<>();

       try {
           conn = this.transConnection !=null?this.transConnection:getConnection();
           pStatement = conn.prepareStatement(SQL_SELECT);
           rs = pStatement.executeQuery();
           while (rs.next()){
               drones.add(new Drone(
                       rs.getInt("DroneId"),
                       rs.getString("OwnerName"),
                       rs.getString("DroneClass"),
                       rs.getDouble("Speed")
               ));
           }
       }catch (SQLSyntaxErrorException e){
           System.err.println("Error: "+e.getMessage());
       }catch (CommunicationsException e){
           System.err.println("Error: Can't connect with database server");
       }
       finally {
           try {
               if(rs != null)close(rs);
               if(pStatement != null)close(pStatement);
               if(conn != null) {
                   if(this.transConnection == null )close(conn);
               }
           } catch (SQLException throwables) {
               //throwables.printStackTrace();
           }
       }
    return drones;
   }

    public Drone select(int DroneId) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Drone drone = null;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_SELECT_ONE);
            pStatement.setInt(1,DroneId);
            rs = pStatement.executeQuery();
            while (rs.next()){
                drone = new Drone(
                       rs.getInt("DroneId"),
                       rs.getString("OwnerName"),
                       rs.getString("DroneClass"),
                       rs.getDouble("Speed")
                );
            }


        }catch (SQLSyntaxErrorException e){
            System.err.println("Error: "+e.getMessage());
        }catch (CommunicationsException e){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(rs != null)close(rs);
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return drone;
    }

    public int insert(Drone drone) throws SQLException{
       // vaidate name
        if(drone.getOwnerName() == null) {
            return 0;
        }
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_INSERT);
            pStatement.setString(1,drone.getOwnerName());
            pStatement.setString(2,drone.getDroneClass());
            pStatement.setDouble(3,drone.getSpeed());
            result = pStatement.executeUpdate();
            
        }catch (SQLSyntaxErrorException e){
            System.err.println("Error: "+e.getMessage());
        }catch (CommunicationsException e){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }

    public int update(Drone drone) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_UPDATE);
            pStatement.setString(1,drone.getOwnerName());
            pStatement.setString(2,drone.getDroneClass());
            pStatement.setDouble(3,drone.getSpeed());
            pStatement.setInt(4,drone.getDroneId());
            result = pStatement.executeUpdate();
        }catch (SQLSyntaxErrorException ex){
            System.err.println("Error: "+ex.getMessage());
        }catch (CommunicationsException ex){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }

    public int delete(int DroneId) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_DELETE);
            pStatement.setInt(1,DroneId);
            result = pStatement.executeUpdate();
        }catch (SQLSyntaxErrorException ex){
            System.err.println("Error: "+ex.getMessage());
        }catch (CommunicationsException ex){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }

}
