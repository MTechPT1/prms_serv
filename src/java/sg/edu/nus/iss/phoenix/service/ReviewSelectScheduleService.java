/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;

/**
 *
 * @author Karen Athaide
 */
public class ReviewSelectScheduleService {
    DAOFactoryImpl factory;
    ScheduleDAO psdao;
    
    /**
     * Constructor. 
     * Takes no arguments and provides the most simple
     * way to create object instance. 
     */
    public ReviewSelectScheduleService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
    }
    
    /**
     * Loads all the ProgramSlots available 
     * @return a list of ProgramSlot
     */
    public List<ProgramSlot> reviewSelectProgramSlot() {
        List<ProgramSlot> data = null;
        try {
            data = psdao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}