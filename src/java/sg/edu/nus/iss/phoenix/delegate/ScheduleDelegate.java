/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.delegate;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.service.ScheduleService;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
/**
 *
 * @author Karen Athaide
 */
public class ScheduleDelegate {
    public ArrayList<ProgramSlot> findAllPS() throws SQLException {
        ScheduleService service = new ScheduleService();
        return service.findAllPS();
        
    }
    
    public void processCreate(ProgramSlot ps) throws SQLException {
        ScheduleService service = new ScheduleService();
        service.processCreate(ps);
        
    }
    public void processModify(ProgramSlot ps) throws NotFoundException, SQLException {
        ScheduleService service = new ScheduleService();
        service.processModify(ps);
        
    }
    
    public void processDelete(int programSlotId) throws NotFoundException, SQLException {
        ScheduleService service = new ScheduleService();
        service.processDelete(programSlotId);
    }
    
}
