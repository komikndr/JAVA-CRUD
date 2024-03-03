package com.database;

import com.database.Services.JDBC.DroneJDBC;
import com.database.Services.MySQLConnection;
import com.database.Services.CRUDInterface.CRUDInterface;
import com.database.Services.Items.Drone;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HandlerDrones{
    private final CRUDInterface DroneJDBC;

    public HandlerDrones() {
        this.DroneJDBC = new DroneJDBC();
    }

    public int showMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("\t\t DRONES ");
        System.out.println("------------------------------------------------------\n");
        System.out.println("1. Insert new drone");
        System.out.println("2. See all drones");
        System.out.println("3. See single drone");
        System.out.println("4. Update drone");
        System.out.println("5. Delete drone");
        System.out.println("6. Config database");
        System.out.println("7. Exit");
        return scanner.nextInt();
    }

    public void createProduct(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please insert the drone's information.");
            System.out.println("Owner Name: ");
            String OwnerName = scanner.nextLine();
            System.out.println("Drone Class(micro,nano,...): ");
            String DroneClass = scanner.nextLine();
            System.out.println("Speed(Km/h): ");
            double Speed = scanner.nextDouble();

            int insert = this.DroneJDBC.insert(new Drone(OwnerName, DroneClass, Speed));
            if(insert > 0) {
                System.out.println("Drone Listed!");
            }else {
                System.out.println("Drone cannot be listed");
            }
            scanner.nextLine();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch(InputMismatchException e) {
            System.out.println("The input was type wrong.\ntry again.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void showAll() {
        try {
            System.out.println("Drones Listed");
            List<Drone> drones= this.DroneJDBC.select();
            drones.forEach(System.out::println);
            new Scanner(System.in).nextLine();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showOne() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Insert Drone ID !");
            int id = scanner.nextInt();
            Drone drone= DroneJDBC.select(id);
            scanner.nextLine(); //consume \n
            if(drone!= null) {
                System.out.println(drone);
            }else {
                System.out.println("Drone not found");
            }
            scanner.nextLine();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("oh no! the input was type wrong.\n come back and try again.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Insert Drone ID for update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Drone drone= DroneJDBC.select(id);
            if(drone!= null) {
                System.out.print("Owner Name["+drone.getOwnerName()+"]:");
                String OwnerName = scanner.nextLine();
                System.out.print("Drone Class["+drone.getDroneClass()+"]:");
                String DroneClass = scanner.nextLine();
                System.out.print("Speed["+drone.getSpeed()+"]:");
                String Speed = scanner.nextLine();
                if(!OwnerName.equals("")){
                    drone.setOwnerName(OwnerName);;
                }
                if(!DroneClass.equals("")){
                    drone.setDroneClass(DroneClass);;
                }
                if(tryParseDouble(Speed)){
                    drone.setSpeed(Double.parseDouble(Speed));
                }
                int result = DroneJDBC.update(drone);
                if(result > 0) {
                    System.out.println("List updated!");
                }else {
                    System.out.println("List not updated");
                }
            }else {
                System.out.println("Drone not found");
            }
            scanner.nextLine();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("oh no! the input was type wrong.\n come back and try again.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Insert ID to delete a drone");
            int id = scanner.nextInt();
            int result = DroneJDBC.delete(id);
            scanner.nextLine(); //consume \n
            if(result > 0) {
                System.out.println("Product deleted!");
            }else {
                System.out.println("Product not found");
            }
            scanner.nextLine();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch (InputMismatchException e) {
            System.out.println("oh no! the input was type wrong.\n come back and try again.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void updateDatabase() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Let's update the database config!");
            System.out.println("Give me all the data to update database connection.");
            System.out.print("Host["+ MySQLConnection.getHost() +"]:");
            String host = scanner.nextLine();
            System.out.print("Port["+MySQLConnection.getPort()+"]:");
            String port = scanner.nextLine();
            System.out.print("Database["+ MySQLConnection.getDatabase() +"]:");
            String database = scanner.nextLine();
            System.out.print("User["+MySQLConnection.getUser()+"]:");
            String user = scanner.nextLine();
            System.out.print("Password:");
            String password = scanner.nextLine();
            // update info
            if(!host.equals("")){
                MySQLConnection.setHost(host);
            }
            if(tryParseInt(port)){
               MySQLConnection.setPort(Integer.parseInt(port));
            }
            if(!database.equals("")){
                MySQLConnection.setDatabase(database);
            }
            if(!user.equals("")){
                MySQLConnection.setDatabaseUser(user);
            }
            //password can be empty
            MySQLConnection.setDatabasePassword(password);
            //reload connection with database
            MySQLConnection.reloadStringConnection();
            // show changes
            //System.out.println(MySQLConnection.getConnectionString());
            //System.out.println(MySQLConnection.getUser());
            //System.out.println(MySQLConnection.getPassword());
            System.out.println("database information was updated!");
            scanner.nextLine();
        }catch (InputMismatchException ex) {
            System.out.println("oh no! the input was type wrong.\n come back and try again.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
