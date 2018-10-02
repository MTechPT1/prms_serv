/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule;
import sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule;

/**
 *
 * @author Karen Athaide
 */
public class ScheduleService {
    DAOFactoryImpl factory;
    ScheduleDAO scheduleDAO;
    
    /**
     *
     */
    public ScheduleService() {
        super();
        // Sorry. This implementation is wrong. To be fixed.
        factory = new DAOFactoryImpl();
        scheduleDAO = factory.getScheduleDAO();
    }
    
    /**
     * Find Annual Schedule for a particular year
     * @param year
     * @return
     */
    public AnnualSchedule findAS (String year) throws SQLException {
        ArrayList<AnnualSchedule> currentasList;
        AnnualSchedule currentas = new AnnualSchedule();
        currentas.setYear(year);
        try {
            currentasList = ((ArrayList<AnnualSchedule>) scheduleDAO
                    .searchMatchingAS(currentas));
            if (!currentasList.isEmpty())
                currentas = currentasList.get(0);
            else
                currentas = null;
            //   return currentas;
        } catch (SQLException e) {
            throw(e);
        }
        return currentas;
    }
    
    /**
     * Find list of all Annual Schedules
     * @return
     */
    public ArrayList<AnnualSchedule> findAllAS() throws SQLException {
        ArrayList<AnnualSchedule> currentList = new ArrayList<AnnualSchedule>();
        try {
            currentList = (ArrayList<AnnualSchedule>) scheduleDAO.loadAllAS();
        } catch (SQLException e) {
            throw(e);
        }
        return currentList;
    }
    
    /**
     *Find Weekly Schedule for a particular year
     * @param year
     * @return
     */
    public WeeklySchedule findWS (String year) throws SQLException {
        ArrayList<WeeklySchedule> currentwsList;
        WeeklySchedule currentws = new WeeklySchedule();
        currentws.setYear(year);
        try {
            currentwsList = ((ArrayList<WeeklySchedule>) scheduleDAO
                    .searchMatchingWS(currentws));
            if (!currentwsList.isEmpty())
                currentws = currentwsList.get(0);
            else
                currentws = null;
        } catch (SQLException e) {
            throw(e);
        }
        return currentws;
    }
    
    /**
     * Find list of Weekly Schedules
     * @param year
     * @return
     */
    public ArrayList<WeeklySchedule> findAllWS(String year) throws SQLException {
        ArrayList<WeeklySchedule> currentList = new ArrayList<WeeklySchedule>();
        try {
            currentList = (ArrayList<WeeklySchedule>) scheduleDAO.loadAllWS(year);
        } catch (SQLException e) {
            throw(e);
        }
        return currentList;
    }
    
    /**
     * Find list of Program Slots for a weekId and year
     * @param weekId
     * @param year
     * @return
     */
    public ArrayList<ProgramSlot> findPSByDates(int weekId, String year) throws SQLException {
        String startDate=year+"-01-01 00:00:00";
        String endDate=year+"-12-31 23:59:59";
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) scheduleDAO.searchMatchingDates(weekId,startDate,endDate);
        } catch (SQLException e) {
            throw(e);
        }
        return currentList;
    }
    
    /**
     * Find list of Program Slots for given matching criteria
     * @param ps
     * @return
     */
    public ArrayList<ProgramSlot> findPSByCriteria(ProgramSlot ps) throws SQLException {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) scheduleDAO.searchMatching(ps);
        } catch (SQLException e) {
            throw(e);
        }
        return currentList;
    }
    
    /**
     * Find ProgramSlot by Id of resource
     * @param programSlotId
     * @return
     */
    public ProgramSlot findPS(int programSlotId) throws SQLException {
        ProgramSlot currentps = new ProgramSlot();
        currentps.setProgramSlotId(programSlotId);
        try {
            currentps = ((ArrayList<ProgramSlot>) scheduleDAO
                    .searchMatching(currentps)).get(0);
        } catch (SQLException e) {
            throw(e);
        }
        return currentps;
    }
    
    /**
     * Find list of all Program Slots
     * @return
     */
    public ArrayList<ProgramSlot> findAllPS() throws SQLException {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) scheduleDAO.loadAll();
        } catch (SQLException e) {
            throw(e);
        }
        return currentList;
    }
    
    /**
     * Create a ProgramSlot
     * @param ps
     */
    public void processCreate(ProgramSlot ps) throws SQLException {
        try {
            scheduleDAO.createPS(ps);
        } catch (SQLException e) {
            throw(e);
        }
    }
    
    /**
     * Modify a ProgramSlot
     * @param ps
     */
    public void processModify(ProgramSlot ps) throws NotFoundException, SQLException {
        try {
            scheduleDAO.save(ps);
        } catch (NotFoundException e) {
            throw(e);
        } catch (SQLException e) {
            throw(e);
        }
        
    }
    
    /**
     * Delete a ProgramSlot
     * @param programSlotId
     */
    public void processDelete(int programSlotId) throws NotFoundException, SQLException {
        try {
            ProgramSlot ps = new ProgramSlot(programSlotId);
            scheduleDAO.delete(ps);
        } catch (NotFoundException e) {
            throw(e);
        } catch (SQLException e) {
            throw(e);
        }
    }
}
