/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.phoenix.service.ReviewSelectScheduleService;

/**
 *
 * @author Karen Athaide
 */
public class ReviewSelectScheduleDelegate {
    private ReviewSelectScheduleService service;
    
    public ReviewSelectScheduleDelegate() {
        service = new ReviewSelectScheduleService();
    }
    
    public List<ProgramSlot> reviewSelectProgramSlot() {
        return service.reviewSelectProgramSlot();
    }
    
}
