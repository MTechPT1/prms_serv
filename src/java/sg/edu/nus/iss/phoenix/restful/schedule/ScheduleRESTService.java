/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.restful.schedule;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule;
import sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule;
import sg.edu.nus.iss.phoenix.service.ScheduleService;

/**
 * REST Web Service
 * @author Karen Athaide
 */
@Path("schedule")
@RequestScoped
public class ScheduleRESTService {

    @Context
    private UriInfo context;
    
    private ScheduleService service;

    /**
     * Creates a new instance of ProgramSlotRESTService
     */
    public ScheduleRESTService() {
        service = new ScheduleService();
    }

    
    @GET
    @Path("/annual/all")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedules getAllAnnualSchedules() {
        ArrayList<AnnualSchedule> aslist = service.findAllAS();
        AnnualSchedules asList = new AnnualSchedules();
        asList.setAsList(new ArrayList<AnnualSchedule>());
        
        for (int i = 0; i < aslist.size(); i++) {
            asList.getAsList().add(
                new AnnualSchedule(aslist.get(i).getYear(),
                aslist.get(i).getAssignedBy()));
        }

        return asList;
    }
    
    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Path("/annual/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedule getAnnualSchedule(@PathParam("year") String year) {
        AnnualSchedule as = new AnnualSchedule();
        as = service.findAS(year);
        return as;
    }
    
    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Path("/weekly/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedules getAllWeeklySchedules(@PathParam("year") String year) {
        ArrayList<WeeklySchedule> wslist = service.findAllWS(year);
        AnnualSchedules wsList = new AnnualSchedules();
        wsList.setWsList(new ArrayList<WeeklySchedule>());
        
        for (int i = 0; i < wslist.size(); i++) {
            wsList.getWsList().add(
                new WeeklySchedule(wslist.get(i).getWeekId(),wslist.get(i).getStartDate(),
                        wslist.get(i).getAssignedBy(),wslist.get(i).getYear()));
        }

        return wsList;
    }
    
    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Path("/{programSlotId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlot(@PathParam("programSlotId") int programSlotId) {
        ProgramSlot ps = new ProgramSlot();
        ps = service.findPS(programSlotId);
        return ps;
    }
    
    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Path("/{weekId}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedules getProgramSlots(@PathParam("weekId") int weekId, @PathParam("year") String year) {
        ArrayList<ProgramSlot> pslist = service.findPSByDates(weekId, year);
        AnnualSchedules psList = new AnnualSchedules();
        psList.setPsList(new ArrayList<>()); 
        for (int i = 0; i < pslist.size(); i++) {
            psList.getPsList().add(
                new ProgramSlot(pslist.get(i).getProgramSlotId(), 
                    pslist.get(i).getAssignedBy(), 
                    pslist.get(i).getDuration(),
                    pslist.get(i).getStartDate(),
                    pslist.get(i).getProgramName(),
                    pslist.get(i).getPresenterId(),
                    pslist.get(i).getProducerId(),
                    pslist.get(i).getWeekId()));
        }
        return psList;
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedules getAllProgramSlots() {
        ArrayList<ProgramSlot> pslist = service.findAllPS();
        AnnualSchedules psList = new AnnualSchedules();
        psList.setPsList(new ArrayList<>()); 
        for (int i = 0; i < pslist.size(); i++) {
            psList.getPsList().add(
                new ProgramSlot(pslist.get(i).getProgramSlotId(), 
                    pslist.get(i).getAssignedBy(), 
                    pslist.get(i).getDuration(),
                    pslist.get(i).getStartDate(),
                    pslist.get(i).getProgramName(),
                    pslist.get(i).getPresenterId(),
                    pslist.get(i).getProducerId(),
                    pslist.get(i).getWeekId()));
        }
        return psList;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param rp content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot ps) {
        service.processModify(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param rp content representation for the resource
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot ps) {
        service.processCreate(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param ps content representation for the resource
     */
    @PUT
    @Path("/copy")
    @Consumes(MediaType.APPLICATION_JSON)
    public void copyProgramSlot(ProgramSlot ps) {
        service.processCreate(ps);
    }
    
    /**
     * DELETE method for deleting an instance of resource
     * @param programSlotId Id of the resource
     */
    @DELETE
    @Path("/delete/{programSlotId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(@PathParam("programSlotId") int programSlotId) {
        service.processDelete(programSlotId);
    }    
}
