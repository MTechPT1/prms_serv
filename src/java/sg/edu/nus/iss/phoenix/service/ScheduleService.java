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
    ScheduleDAO psdao;
    
    public ScheduleService() {
        super();
        // Sorry. This implementation is wrong. To be fixed.
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
    }
    
    public AnnualSchedule findAS (String year) {
        AnnualSchedule currentas = new AnnualSchedule();
        currentas.setYear(year);
        try {
            currentas = ((ArrayList<AnnualSchedule>) psdao
                    .searchMatchingAS(currentas)).get(0);
            return currentas;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentas;
        
    }
    
    public ArrayList<AnnualSchedule> findAllAS() {
        ArrayList<AnnualSchedule> currentList = new ArrayList<AnnualSchedule>();
        try {
            currentList = (ArrayList<AnnualSchedule>) psdao.loadAllAS();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;
        
    }
    
    
    public ArrayList<ProgramSlot> searchSchedules(ProgramSlot ps) {
        ArrayList<ProgramSlot> list = new ArrayList<ProgramSlot>();
        try {
            list = (ArrayList<ProgramSlot>) psdao.searchMatching(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<ProgramSlot> findPSByCriteria(ProgramSlot ps) {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        
        try {
            currentList = (ArrayList<ProgramSlot>) psdao.searchMatching(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return currentList;
        
    }
    
    public ProgramSlot findPS(int programSlotId) {
        ProgramSlot currentps = new ProgramSlot();
        currentps.setProgramSlotId(programSlotId);
        try {
            currentps = ((ArrayList<ProgramSlot>) psdao
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
            currentList = (ArrayList<ProgramSlot>) psdao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;
        
    }
    
    public void processCreate(ProgramSlot ps) {
        //WeeklySchedule ws = new WeeklySchedule();
        //ws.setStartDate(ps.getStartDate());
        //ws.setAssignedBy(ps.getAssignedBy());
        //ws.setYear("2018"); //TODO
        try {
            psdao.createPS(ps);
            //psdao.createWS(ws);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void processModify(ProgramSlot ps) {        
        try {
            psdao.save(ps);
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
            psdao.delete(ps);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
