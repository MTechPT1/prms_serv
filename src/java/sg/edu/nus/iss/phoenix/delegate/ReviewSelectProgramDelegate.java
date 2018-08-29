package sg.edu.nus.iss.phoenix.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.phoenix.service.ReviewSelectProgramService;

public class ReviewSelectProgramDelegate {
    private ReviewSelectProgramService service;
    
	public ReviewSelectProgramDelegate() {
		service = new ReviewSelectProgramService();
	}
	
	public List<RadioProgram> reviewSelectRadioProgram() {
		return service.reviewSelectRadioProgram();	
	}

}
