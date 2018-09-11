/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.core.exceptions.DAOException;
import sg.edu.nus.iss.phoenix.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule;
import sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule;

/**
 * ProgramSlot Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve ProgramSlot object
 * instances.
 * @author Karen Athaide
 */
public class ScheduleDAOImpl implements ScheduleDAO {
    
    Connection connection;
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createValueObject()
    */
    @Override
    public AnnualSchedule createValueObjectAS() {
        return new AnnualSchedule();
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#getObject(java.lang.String)
    */
    @Override
    public AnnualSchedule getObjectAS(String year) throws NotFoundException,
            SQLException {
        
        AnnualSchedule valueObjectAS = createValueObjectAS();
        valueObjectAS.setYear(year);
        loadAS(valueObjectAS);
        return valueObjectAS;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#load(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public void loadAS(AnnualSchedule valueObjectAS) throws NotFoundException,
            SQLException {
        
        if (valueObjectAS.getYear() == null) {
            // System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }
        
        String sql = "SELECT * FROM `tblannualschedule` WHERE (`year` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObjectAS.getYear());
            
            //singleQueryAS(stmt, valueObjectAS);
            
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#create(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public synchronized void createAS(AnnualSchedule valueObjectAS)
            throws SQLException {
        
        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            sql = "INSERT INTO `tblannualschedule` (`year`, `assignedBy`) VALUES (?,?); ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObjectAS.getYear());
            stmt.setString(2, valueObjectAS.getAssignedBy());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadAll()
    */
    @Override
    public List<AnnualSchedule> loadAllAS() throws SQLException {
        openConnection();
        String sql = "SELECT * FROM `tblannualschedule` ORDER BY `year` ASC; ";
        List<AnnualSchedule> searchResults = listQueryAS(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createValueObject()
    */
    @Override
    public WeeklySchedule createValueObjectWS() {
        return new WeeklySchedule();
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#getObject(java.lang.String)
    */
    @Override
    public WeeklySchedule getObjectWS(String year) throws NotFoundException,
            SQLException {
        
        WeeklySchedule valueObjectWS = createValueObjectWS();
        valueObjectWS.setYear(year);
        loadWS(valueObjectWS);
        return valueObjectWS;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#load(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public void loadWS(WeeklySchedule valueObjectWS) throws NotFoundException,
            SQLException {
        
        if (valueObjectWS.getWeekId() == 0) {
            // System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }
        
        String sql = "SELECT * FROM `tblweeklyschedule` WHERE (`yearAS` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObjectWS.getWeekId());
            
            singleQueryWS(stmt, valueObjectWS);
            
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#create(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public synchronized void createWS(WeeklySchedule valueObjectWS)
            throws SQLException {
        
        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            sql = "INSERT INTO `tblweeklyschedule` (`startDate`, `assignedBy`, `programSlotId`, `yearAS`) VALUES (?,?,?,?); ";
            stmt = connection.prepareStatement(sql);
            stmt.setDate(1, valueObjectWS.getStartDate());
            stmt.setString(2, valueObjectWS.getAssignedBy());
            stmt.setInt(3, valueObjectWS.getProgramSlotId());
            stmt.setString(4, valueObjectWS.getYearAS());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createValueObject()
    */
    @Override
    public ProgramSlot createValueObjectPS() {
        return new ProgramSlot();
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#getObject(java.lang.String)
    */
    @Override
    public ProgramSlot getObjectPS(int programSlotId) throws NotFoundException,
            SQLException {
        
        ProgramSlot valueObject = createValueObjectPS();
        valueObject.setProgramSlotId(programSlotId);
        loadPS(valueObject);
        return valueObject;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#load(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public void loadPS(ProgramSlot valueObject) throws NotFoundException,
            SQLException {
        
        if (valueObject.getProgramSlotId() == 0) {
            // System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }
        
        String sql = "SELECT * FROM `tblprogramslot` WHERE (`programSlotId` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getProgramSlotId());
            
            singleQueryPS(stmt, valueObject);
            
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadAll()
    */
    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
        openConnection();
        String sql = "SELECT * FROM `tblprogramslot` ORDER BY `programSlotId` ASC; ";
        List<ProgramSlot> searchResults = listQuery(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#create(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public synchronized void createPS(ProgramSlot valueObject)
            throws SQLException {
        
        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            //create a program slot
            sql = "INSERT INTO `tblprogramslot` (`assignedBy`, `duration`, `startDate`, `programName`, `presenterId`, `producerId`, `weekId`) VALUES (?,?,?,?,?,?,?); ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getAssignedBy());
            stmt.setInt(2, valueObject.getDuration());
            stmt.setDate(3, valueObject.getStartDate());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setInt(5, valueObject.getPresenterId());
            stmt.setInt(6, valueObject.getProducerId());
            stmt.setInt(7, valueObject.getWeekId());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
            
            //ResultSet rs = stmt.getGeneratedKeys();
            //int id = 0;
            //if (rs.next()) {
                //valueObject.setProgramSlotId(rs.getInt("programSlotId"));
            //}
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix..dao.impl.ScheduleDAO#save(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public void save(ProgramSlot valueObject) throws NotFoundException,
            SQLException {
        
        String sql = "UPDATE `tblprogramslot` SET `assignedBy` = ?,`duration` = ?, "
                + "`startDate` = ?, `programName` = ?, `presenterId` = ?, "
                + "`producerId` = ?, `weekId` = ? WHERE (`programSlotId` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getAssignedBy());
            stmt.setInt(2, valueObject.getDuration());
            stmt.setDate(3, valueObject.getStartDate());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setInt(5, valueObject.getPresenterId());
            stmt.setInt(6, valueObject.getProducerId());
            stmt.setInt(7, valueObject.getWeekId());            
            stmt.setInt(8, valueObject.getProgramSlotId());
            
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#delete(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException,
            SQLException {
        
        if (valueObject.getProgramSlotId() == 0) {
            // System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Can not delete without Primary-Key!");
        }
        
        String sql = "DELETE FROM `tblprogramslot` WHERE (`programSlotId` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getProgramSlotId());
            
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#deleteAll(java.sql.Connection)
    */
    @Override
    public void deleteAll(Connection conn) throws SQLException {
        
        String sql = "DELETE FROM `tblprogramslot";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
            System.out.println(""+rowcount);
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
            
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#countAll()
    */
    @Override
    public int countAll() throws SQLException {
        
        String sql = "SELECT count(*) FROM `tblprogramslot`";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();
            
            if (result.next())
                allRows = result.getInt(1);
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        return allRows;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#searchMatching(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {
        
        @SuppressWarnings("UnusedAssignment")
                List<ProgramSlot> searchResults = new ArrayList<>();
        openConnection();
        boolean first = true;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM `tblprogramslot` WHERE 1=1 ");
        
        if (valueObject.getProgramSlotId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND `programSlotId` LIKE '").append(valueObject.getProgramSlotId())
                    .append("%' ");
        }
        
        if (valueObject.getAssignedBy() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `assignedBy` = '")
                    .append(valueObject.getAssignedBy()).append("' ");
        }
        
        if (valueObject.getDuration() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND `duration` = '")
                    .append(valueObject.getDuration()).append("' ");
        }
        
        if (valueObject.getStartDate() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `startDate` = '")
                    .append(valueObject.getStartDate()).append("' ");
        }
        
        if (valueObject.getProgramName() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `programName` = '")
                    .append(valueObject.getProgramName()).append("' ");
        }
        
        if (valueObject.getPresenterId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND `presenterId` = '")
                    .append(valueObject.getPresenterId()).append("' ");
        }
        
        if (valueObject.getProducerId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND `producerId` = '")
                    .append(valueObject.getProducerId()).append("' ");
        }
        
        if (valueObject.getWeekId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND `weekId` = '")
                    .append(valueObject.getWeekId()).append("' ");
        }
        
        sql.append("ORDER BY `programSlotId` ASC ");
        
        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<>();
        else
            searchResults = listQuery(connection.prepareStatement(sql
                    .toString()));
        closeConnection();
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#searchMatching(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public List<AnnualSchedule> searchMatchingAS(AnnualSchedule valueObject) throws SQLException {
        
        @SuppressWarnings("UnusedAssignment")
                List<AnnualSchedule> searchResults = new ArrayList<>();
        openConnection();
        boolean first = true;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM `tblannualschedule` WHERE 1=1 ");
        
        if (valueObject.getYear() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `year` LIKE '").append(valueObject.getYear())
                    .append("%' ");
        }
        
        if (valueObject.getAssignedBy() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND `AssignedBy` = '")
                    .append(valueObject.getAssignedBy()).append("' ");
        }        
        sql.append("ORDER BY `year` ASC ");
        
        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<>();
        else
            searchResults = listQueryAS(connection.prepareStatement(sql
                    .toString()));
        closeConnection();
        return searchResults;
    }
    
    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be executed.
     * @return
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {
        int result = 0;
        try {
            result = stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }
    
    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return only one row. The
     * resultset will be converted to valueObject. If no rows were found,
     * NotFoundException will be thrown.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be executed.
     * @param valueObject
     *            Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQueryPS(PreparedStatement stmt, ProgramSlot valueObject)
            throws NotFoundException, SQLException {
        
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();
            
            if (result.next()) {
                
                valueObject.setProgramSlotId(result.getInt("programSlotId"));
                valueObject.setAssignedBy(result.getString("assignedBy"));
                valueObject.setDuration(result.getInt("duration"));
                valueObject.setStartDate(result.getDate("startDate"));
                valueObject.setProgramName(result.getString("programName"));
                valueObject.setPresenterId(result.getInt("presenterId"));
                valueObject.setProducerId(result.getInt("producerId"));
                valueObject.setWeekId(result.getInt("weekId"));
                
            } else {
                // System.out.println("RadioProgram Object Not Found!");
                throw new NotFoundException("ProgramSlot Object Not Found!");
            }
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return only one row. The
     * resultset will be converted to valueObject. If no rows were found,
     * NotFoundException will be thrown.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be executed.
     * @param valueObjectWS
     *            Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQueryWS(PreparedStatement stmt, WeeklySchedule valueObjectWS)
            throws NotFoundException, SQLException {
        
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();
            
            if (result.next()) {
                
                valueObjectWS.setWeekId(result.getInt("weekId"));
                valueObjectWS.setStartDate(result.getDate("startDate"));
                valueObjectWS.setAssignedBy(result.getString("assignedBy"));
                valueObjectWS.setProgramSlotId(result.getInt("programSlotId"));
                valueObjectWS.setYear(result.getString("yearAS"));
                
            } else {
                // System.out.println("RadioProgram Object Not Found!");
                throw new NotFoundException("WeeklySchedule Object Not Found!");
            }
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be executed.
     * @return
     * @throws java.sql.SQLException
     */
    protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {
        
        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();
            
            while (result.next()) {
                ProgramSlot temp = createValueObjectPS();
                
                temp.setProgramSlotId(result.getInt("programSlotId"));
                temp.setAssignedBy(result.getString("assignedBy"));
                temp.setDuration(result.getInt("duration"));
                temp.setStartDate(result.getDate("startDate"));
                temp.setProgramName(result.getString("programName"));
                temp.setPresenterId(result.getInt("presenterId"));
                temp.setProducerId(result.getInt("producerId"));
                temp.setWeekId(result.getInt("weekId"));
                
                searchResults.add(temp);
            }
            
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
        return (List<ProgramSlot>) searchResults;
    }
    
    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be executed.
     * @return
     * @throws java.sql.SQLException
     */
    protected List<AnnualSchedule> listQueryAS(PreparedStatement stmt) throws SQLException {
        
        ArrayList<AnnualSchedule> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();
            
            while (result.next()) {
                AnnualSchedule temp = createValueObjectAS();
                
                temp.setYear(result.getString("year"));
                temp.setAssignedBy(result.getString("assignedBy"));
                
                searchResults.add(temp);
            }
            
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
        return (List<AnnualSchedule>) searchResults;
    }
    
    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
        
    }
    
    private void closeConnection() {
        try {
            this.connection.close();
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
