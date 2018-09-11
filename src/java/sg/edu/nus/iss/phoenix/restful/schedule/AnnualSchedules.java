/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.restful.schedule;

import java.util.List;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule;

/**
 *
 * @author Karen Athaide
 */
public class AnnualSchedules {
    private List<AnnualSchedule> asList;
    private List <ProgramSlot> psList;

    public List<AnnualSchedule> getAsList() {
        return asList;
    }
    
    public void setAsList(List<AnnualSchedule> asList) {
        this.asList = asList;
    }
    
    public List<ProgramSlot> getPsList() {
        return psList;
    }
 
    public void setPsList(List<ProgramSlot> psList) {
        this.psList = psList;
    }
}
