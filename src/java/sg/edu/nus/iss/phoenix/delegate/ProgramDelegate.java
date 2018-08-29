package sg.edu.nus.iss.phoenix.delegate;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.entity.radioprogram.RPSearchObject;
import sg.edu.nus.iss.phoenix.entity.radioprogram.RadioProgram;
import sg.edu.nus.iss.phoenix.service.ProgramService;

public class ProgramDelegate {
/*	
	public ArrayList<RadioProgram> searchPrograms(RPSearchObject rpso) {
		RadioProgram rp = new RadioProgram(rpso.getName());
		rp.setDescription(rpso.getDescription());
		ProgramService service = new ProgramService();
		return service.searchPrograms(rp);	
	}
	
	public ArrayList<RadioProgram> findRPByCriteria(RPSearchObject rpso) {
		RadioProgram rp = new RadioProgram(rpso.getName());
		rp.setDescription(rpso.getDescription());
		ProgramService service = new ProgramService();
		return service.searchPrograms(rp);	
	}
	
	public RadioProgram findRP(String rpName) {
		RadioProgram rp = new RadioProgram(rpName);
		ProgramService service = new ProgramService();
		return (service.searchPrograms(rp)).get(0);	
		
	}
    */
	public ArrayList<RadioProgram> findAllRP() {
		ProgramService service = new ProgramService();
		return service.findAllRP();
		
	}
	
	public void processCreate(RadioProgram rp) {
		ProgramService service = new ProgramService();
		service.processCreate(rp);
		
	}
	public void processModify(RadioProgram rp) {
		ProgramService service = new ProgramService();
		service.processModify(rp);
		
	}

	public void processDelete(String name) {
		ProgramService service = new ProgramService();
		service.processDelete(name);
	}
}
