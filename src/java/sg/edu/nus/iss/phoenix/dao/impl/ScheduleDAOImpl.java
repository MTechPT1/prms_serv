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
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#getObjectAS(java.lang.String)
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
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadAS(sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule)
    */
    @Override
    public void loadAS(AnnualSchedule valueObjectAS) throws NotFoundException,
            SQLException {
        
        if (valueObjectAS.getYear() == null) {
            throw new NotFoundException(DBConstants.exc_missing_primary_key);
        }
        
        String sql = "SELECT * FROM "+DBConstants.aScheduleTableName+" WHERE ("+DBConstants.a_year+" = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObjectAS.getYear());
            
            singleQueryAS(stmt, valueObjectAS);
            
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createAS(sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule)
    */
    @Override
    public synchronized void createAS(AnnualSchedule valueObjectAS)
            throws SQLException {
        
        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            sql = "INSERT INTO "+DBConstants.aScheduleTableName+" ("+DBConstants.a_year+","+DBConstants.a_assignedBy+") VALUES (?,?); ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObjectAS.getYear());
            stmt.setString(2, valueObjectAS.getAssignedBy());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException(DBConstants.exc_duplicate_primary_key);
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadAllAS()
    */
    @Override
    public List<AnnualSchedule> loadAllAS() throws SQLException {
        openConnection();
        String sql = "SELECT * FROM "+DBConstants.aScheduleTableName+" ORDER BY "+DBConstants.a_year+" ASC; ";
        List<AnnualSchedule> searchResults = listQueryAS(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadAllWS()
    */
    @Override
    public List<WeeklySchedule> loadAllWS(String year) throws SQLException {
        openConnection();
        String sql = "SELECT * FROM "+DBConstants.wScheduleTableName+" WHERE "+DBConstants.w_year+" LIKE "+year
                +" ORDER BY "+DBConstants.w_year+" ASC; ";
        List<WeeklySchedule> searchResults = listQueryWS(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createValueObjectWS()
    */
    @Override
    public WeeklySchedule createValueObjectWS() {
        return new WeeklySchedule();
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#getObjectWS(java.lang.String)
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
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadWS(sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule)
    */
    @Override
    public void loadWS(WeeklySchedule valueObjectWS) throws NotFoundException,
            SQLException {
        
        if (valueObjectWS.getWeekId() == 0) {
            throw new NotFoundException(DBConstants.exc_missing_primary_key);
        }
        
        String sql = "SELECT * FROM "+DBConstants.wScheduleTableName+" WHERE ("+DBConstants.w_year+" = ? ); ";
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
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createWS(sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule)
    */
    @Override
    public synchronized void createWS(WeeklySchedule valueObjectWS)
            throws SQLException {
        
        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            sql = "INSERT INTO "+DBConstants.wScheduleTableName+" ("+DBConstants.w_weekId+","+DBConstants.w_startDate+","+DBConstants.w_assignedBy+","+DBConstants.w_year+") VALUES (?,?,?,?); ";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObjectWS.getWeekId());
            stmt.setDate(2, valueObjectWS.getStartDate());
            stmt.setString(3, valueObjectWS.getAssignedBy());
            stmt.setString(4, valueObjectWS.getYear());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException(DBConstants.exc_duplicate_primary_key);
            }
        } finally {
            if (stmt != null)
                stmt.close();
            closeConnection();
        }
        
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createValueObjectPS()
    */
    @Override
    public ProgramSlot createValueObjectPS() {
        return new ProgramSlot();
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#getObjectPS(java.lang.Integer)
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
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#loadPS(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public void loadPS(ProgramSlot valueObject) throws NotFoundException,
            SQLException {
        
        if (valueObject.getProgramSlotId() == 0) {
            throw new NotFoundException("Can not select without Primary-Key!");
        }
        
        String sql = "SELECT * FROM "+DBConstants.scheduleTableName+" WHERE ("+DBConstants.p_id+" = ? ); ";
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
        String sql = "SELECT * FROM "+DBConstants.scheduleTableName+" ORDER BY "+DBConstants.p_id+" ASC; ";
        List<ProgramSlot> searchResults = listQuery(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#createPS(sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot)
    */
    @Override
    public synchronized void createPS(ProgramSlot valueObject)
            throws SQLException {
        
        String sql = "";
        PreparedStatement stmt = null;
        openConnection();
        try {
            //create a program slot
            sql = "INSERT INTO "+DBConstants.scheduleTableName+" ("+DBConstants.p_assignedBy+","
                    +DBConstants.p_duration+","+DBConstants.p_startDate+","+DBConstants.p_programName+","
                    +DBConstants.p_presenterId+","+DBConstants.p_producerId+","+DBConstants.p_weekId+") VALUES (?,?,?,?,?,?,?); ";
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
                throw new SQLException(DBConstants.exc_duplicate_primary_key);
            }            
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
        
        String sql = "UPDATE "+DBConstants.scheduleTableName+" SET "+DBConstants.p_assignedBy+" = ?, "+DBConstants.p_duration+" = ?, "
                +DBConstants.p_startDate+" = ?, "+DBConstants.p_programName+" = ?, "+DBConstants.p_presenterId+" = ?, "
                +DBConstants.p_producerId+" = ?, "+DBConstants.p_weekId+" = ? WHERE ("+DBConstants.p_id+" = ? ); ";
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
                throw new NotFoundException(DBConstants.exc_missing_primary_key);
            }
            if (rowcount > 1) {
                throw new SQLException(DBConstants.exc_duplicate_primary_key);
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
            throw new NotFoundException(DBConstants.exc_missing_primary_key);
        }
        
        String sql = "DELETE FROM "+DBConstants.scheduleTableName+" WHERE ("+DBConstants.p_id+" = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getProgramSlotId());
            
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                throw new NotFoundException(DBConstants.exc_missing_primary_key);
            }
            if (rowcount > 1) {
                throw new SQLException(DBConstants.exc_duplicate_primary_key);
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
        
        String sql = "DELETE FROM "+DBConstants.scheduleTableName+"; ";
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
        
        String sql = "SELECT count(*) FROM "+DBConstants.scheduleTableName+"; ";
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
                "SELECT * FROM "+DBConstants.scheduleTableName+" WHERE 1=1 ");
        
        if (valueObject.getProgramSlotId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_id+" LIKE '").append(valueObject.getProgramSlotId())
                    .append("%' ");
        }
        
        if (valueObject.getAssignedBy() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_assignedBy+" = '")
                    .append(valueObject.getAssignedBy()).append("' ");
        }
        
        if (valueObject.getDuration() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_duration+" = '")
                    .append(valueObject.getDuration()).append("' ");
        }
        
        if (valueObject.getStartDate() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_startDate+" = '")
                    .append(valueObject.getStartDate()).append("' ");
        }
        
        if (valueObject.getProgramName() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_programName+" = '")
                    .append(valueObject.getProgramName()).append("' ");
        }
        
        if (valueObject.getPresenterId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_presenterId+" = '")
                    .append(valueObject.getPresenterId()).append("' ");
        }
        
        if (valueObject.getProducerId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_producerId+" = '")
                    .append(valueObject.getProducerId()).append("' ");
        }
        
        if (valueObject.getWeekId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.p_weekId+" = '")
                    .append(valueObject.getWeekId()).append("' ");
        }
        
        sql.append("ORDER BY "+DBConstants.p_id+" ASC ");
        
        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<>();
        else
            searchResults = listQuery(connection.prepareStatement(sql.toString()));
        closeConnection();
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#searchMatchingWS(sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule)
    */
    @Override
    public List<WeeklySchedule> searchMatchingWS(WeeklySchedule valueObject) throws SQLException {
        
        @SuppressWarnings("UnusedAssignment")
                List<WeeklySchedule> searchResults = new ArrayList<>();
        openConnection();
        boolean first = true;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM "+DBConstants.wScheduleTableName+" WHERE 1=1 ");
        
        if (valueObject.getYear() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.w_year+" = '").append(valueObject.getYear())
                    .append("' ");
        }
        
        if (valueObject.getStartDate() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.w_startDate+" = '").append(valueObject.getStartDate())
                    .append("' ");
        }
        
        if (valueObject.getAssignedBy() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.w_assignedBy+" = '")
                    .append(valueObject.getAssignedBy()).append("' ");
        }
        sql.append("ORDER BY "+DBConstants.w_year+" ASC ");
        
        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<>();
        else
            searchResults = listQueryWS(connection.prepareStatement(sql.toString()));
        closeConnection();
        return searchResults;
    }
    
    /* (non-Javadoc)
    * @see sg.edu.nus.iss.phoenix.dao.impl.ScheduleDAO#searchMatchingAS(sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule)
    */
    @Override
    public List<AnnualSchedule> searchMatchingAS(AnnualSchedule valueObject) throws SQLException {
        
        @SuppressWarnings("UnusedAssignment")
                List<AnnualSchedule> searchResults = new ArrayList<>();
        openConnection();
        boolean first = true;
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM "+DBConstants.aScheduleTableName+" WHERE 1=1 ");
        
        if (valueObject.getYear() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.a_year+" = '").append(valueObject.getYear())
                    .append("' ");
        }
        
        if (valueObject.getAssignedBy() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND "+DBConstants.a_assignedBy+" = '")
                    .append(valueObject.getAssignedBy()).append("' ");
        }
        sql.append("ORDER BY "+DBConstants.a_year+" ASC ");
        
        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<>();
        else
            searchResults = listQueryAS(connection.prepareStatement(sql.toString()));
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
                valueObject.setProgramSlotId(result.getInt(DBConstants.p_id));
                valueObject.setAssignedBy(result.getString(DBConstants.p_assignedBy));
                valueObject.setDuration(result.getInt(DBConstants.p_duration));
                valueObject.setStartDate(result.getDate(DBConstants.p_startDate));
                valueObject.setProgramName(result.getString(DBConstants.p_programName));
                valueObject.setPresenterId(result.getInt(DBConstants.p_presenterId));
                valueObject.setProducerId(result.getInt(DBConstants.p_producerId));
                valueObject.setWeekId(result.getInt(DBConstants.p_weekId));
            } else {
                throw new NotFoundException(DBConstants.exc_missing_program_slot);
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
                
                valueObjectWS.setWeekId(result.getInt(DBConstants.w_weekId));
                valueObjectWS.setStartDate(result.getDate(DBConstants.w_startDate));
                valueObjectWS.setAssignedBy(result.getString(DBConstants.w_assignedBy));
                valueObjectWS.setYear(result.getString(DBConstants.w_year));
                
            } else {
                throw new NotFoundException(DBConstants.exc_missing_weekly_schedule);
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
     * @param valueObjectAS
     *            Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQueryAS(PreparedStatement stmt, AnnualSchedule valueObjectAS)
            throws NotFoundException, SQLException {
        
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();
            
            if (result.next()) {
                
                valueObjectAS.setYear(result.getString(DBConstants.a_year));
                valueObjectAS.setAssignedBy(result.getString(DBConstants.a_assignedBy));
                
            } else {
                throw new NotFoundException(DBConstants.exc_missing_annual_schedule);
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
     * @return List<ProgramSlot>
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
                
                temp.setProgramSlotId(result.getInt(DBConstants.p_id));
                temp.setAssignedBy(result.getString(DBConstants.p_assignedBy));
                temp.setDuration(result.getInt(DBConstants.p_duration));
                temp.setStartDate(result.getDate(DBConstants.p_startDate));
                temp.setProgramName(result.getString(DBConstants.p_programName));
                temp.setPresenterId(result.getInt(DBConstants.p_presenterId));
                temp.setProducerId(result.getInt(DBConstants.p_producerId));
                temp.setWeekId(result.getInt(DBConstants.p_weekId));
                
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
     * @return List<WeeklySchedule>
     * @throws java.sql.SQLException
     */
    protected List<WeeklySchedule> listQueryWS(PreparedStatement stmt) throws SQLException {
        
        ArrayList<WeeklySchedule> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();
            
            while (result.next()) {
                WeeklySchedule temp = createValueObjectWS();
                
                temp.setWeekId(result.getInt(DBConstants.w_weekId));
                temp.setYear(result.getString(DBConstants.w_year));
                temp.setStartDate(result.getDate(DBConstants.w_startDate));
                temp.setAssignedBy(result.getString(DBConstants.w_assignedBy));
                
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
        
        return (List<WeeklySchedule>) searchResults;
    }
    
    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt
     *            This parameter contains the SQL statement to be executed.
     * @return List<AnnualSchedule>
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
                
                temp.setYear(result.getString(DBConstants.a_year));
                temp.setAssignedBy(result.getString(DBConstants.a_assignedBy));
                
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
