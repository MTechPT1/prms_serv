/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.DAOException;
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
    
    public ScheduleService() {
        super();
        // Sorry. This implementation is wrong. To be fixed.
        factory = new DAOFactoryImpl();
        scheduleDAO = factory.getScheduleDAO();
    }
    
    public AnnualSchedule findAS (String year) {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentas;        
    }
    
    public ArrayList<AnnualSchedule> findAllAS() {
        ArrayList<AnnualSchedule> currentList = new ArrayList<AnnualSchedule>();
        try {
            currentList = (ArrayList<AnnualSchedule>) scheduleDAO.loadAllAS();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;    
    }
    
    public WeeklySchedule findWS (String year) {
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
            //   return currentas;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentws;
    }
    
    public ArrayList<WeeklySchedule> findAllWS(String year) {
        ArrayList<WeeklySchedule> currentList = new ArrayList<WeeklySchedule>();
        try {
            currentList = (ArrayList<WeeklySchedule>) scheduleDAO.loadAllWS(year);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;
        
    }
    
    public ArrayList<ProgramSlot> searchSchedules(ProgramSlot ps) {
        ArrayList<ProgramSlot> list = new ArrayList<ProgramSlot>();
        try {
            list = (ArrayList<ProgramSlot>) scheduleDAO.searchMatching(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<ProgramSlot> findPSByCriteria(ProgramSlot ps) {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        
        try {
            currentList = (ArrayList<ProgramSlot>) scheduleDAO.searchMatching(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return currentList;
        
    }
    
    public ProgramSlot findPS(int weekId) {
        ProgramSlot currentps = new ProgramSlot();
        currentps.setWeekId(weekId);
        try {
            currentps = ((ArrayList<ProgramSlot>) scheduleDAO
                    .searchMatching(currentps)).get(0);
            return currentps;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentps;
        
    }
    
    public ArrayList<ProgramSlot> findAllPS() {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) scheduleDAO.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;        
    }
    
    public void processCreate(ProgramSlot ps) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ps.getStartDate());
        int year = cal.get(Calendar.YEAR);
        try {
            if (findAS(Integer.toString(year))==null) {
                //populate annual schedule
                AnnualSchedule as = new AnnualSchedule(Integer.toString(year),ps.getAssignedBy());
                scheduleDAO.createAS(as);
                
                cal.set(year, 1, 1);
                //populate weekly schedule
                for (int i=1; i<53; i++) {
                    java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
                    WeeklySchedule ws = new WeeklySchedule(i,date,ps.getAssignedBy(),Integer.toString(year));
                    scheduleDAO.createWS(ws);
                    cal.add(Calendar.WEEK_OF_YEAR, 1);
                }
            }
            scheduleDAO.createPS(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DAOException(e);
        }
    }
    
    public void processModify(ProgramSlot ps) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ps.getStartDate());
        int year = cal.get(Calendar.YEAR);
        
        try {
            if (findAS(Integer.toString(year))==null) {
                //populate annual schedule
                AnnualSchedule as = new AnnualSchedule(Integer.toString(year),ps.getAssignedBy());
                scheduleDAO.createAS(as);
                
                cal.set(year, 1, 1);
                //populate weekly schedule
                for (int i=1; i<53; i++) {
                    java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
                    WeeklySchedule ws = new WeeklySchedule(i,date,ps.getAssignedBy(),Integer.toString(year));
                    scheduleDAO.createWS(ws);
                    cal.add(Calendar.WEEK_OF_YEAR, 1);
                }
            }
            scheduleDAO.save(ps);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void processDelete(int programSlotId) {
        try {
            ProgramSlot ps = new ProgramSlot(programSlotId);
            scheduleDAO.delete(ps);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
