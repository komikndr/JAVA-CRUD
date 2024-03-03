package com.database.Services.CRUDInterface;

import com.database.Services.Items.Drone;
import java.sql.SQLException;
import java.util.List;

public interface CRUDInterface{

    public int insert(Drone drone) throws SQLException;
    public int update(Drone drone) throws SQLException;
    public int delete(int droneID) throws SQLException;
    public Drone  select(int droneID) throws SQLException;
    public List<Drone> select() throws SQLException;

}