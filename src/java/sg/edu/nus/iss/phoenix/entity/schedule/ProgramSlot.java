/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.entity.schedule;

import java.io.Serializable;

/**
 *
 * @author Karen Athaide
 */
public class ProgramSlot implements Cloneable, Serializable {
 
    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int programSlotId;
    private String assignedBy;
    private int duration;  //does this belong to raad program?
    private String startDate;
    private String programName;
    String presenterId;
    String producerId;
    int weekId;
    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */
    public ProgramSlot () {

    }

    public ProgramSlot (int programSlotId) {
          this.programSlotId = programSlotId;
    }
    
    public ProgramSlot (int programSlotId, String assignedBy, int duration, String startDate, String programName, String presenterId, String producerId, int weekId) {
        this.programSlotId = programSlotId;
        this.assignedBy = assignedBy;
        this.duration = duration;
        this.startDate = startDate;
        this.programName = programName;
        this.presenterId = presenterId;
        this.producerId = producerId;
        this.weekId = weekId;
    }
    
    /**
     * Get- and Set-methods for persistent variables. The default
     * behavior does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return int, String, int, String, String, String, String, int
     */
    public int getProgramSlotId() {
        return programSlotId;
    }
    
    public void setProgramSlotId(int programSlotId) {
        this.programSlotId = programSlotId;
    }
    
    public String getAssignedBy() {
        return this.assignedBy;
    }
    
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getProgramName() {
        return this.programName;
    }
    
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
    public String getPresenterId() {
        return this.presenterId;
    }
    
    public void setPresenterId(String presenterId) {
        this.presenterId = presenterId;
    }
    
    public String getProducerId() {
        return this.producerId;
    }
    
    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }
    
    public int getWeekId() {
        return this.weekId;
    }
    
    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
    
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param programSlotId Id of ProgramSlot
     * @param assignedBy Created by userId
     * @param duration Length of ProgramSlot
     * @param startDate Start date and time of ProgramSlot
     * @param programName Name of radio program for this ProgramSlot
     * @param presenterId Presenter userId
     * @param producerId Producer userId
     * @param weekId Id of WeeklySchedule
     */
    public void setAll(int programSlotId, String assignedBy, int duration, String startDate, String programName, String presenterId, String producerId, int weekId) {
        this.programSlotId = programSlotId;
        this.assignedBy = assignedBy;
        this.duration = duration;
        this.startDate = startDate;
        this.programName = programName;
        this.presenterId = presenterId;
        this.producerId = producerId;
        this.weekId = weekId;
    }


    /** 
     * hasEqualMapping-method will compare two ProgramSlot instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return Boolean
     */
    public boolean hasEqualMapping(ProgramSlot valueObject) {

          if (this.assignedBy == null) {
                    if (valueObject.getAssignedBy() != null)
                           return(false);
          } else if (!this.assignedBy.equals(valueObject.getAssignedBy())) {
                    return(false);
          }
          if (this.duration == 0) {
                    if (valueObject.getDuration() != 0)
                           return(false);
          } else if (!(this.duration == valueObject.getDuration())) {
                    return(false);
          }          
          if (this.startDate == null) {
                    if (valueObject.getStartDate() != null)
                           return(false);
          } else if (!this.startDate.equals(valueObject.getStartDate())) {
                    return(false);
          }          
          if (this.programName == null) {
                    if (valueObject.getProgramName() != null)
                           return(false);
          } else if (!this.programName.equals(valueObject.getProgramName())) {
                    return(false);
          }          
          if (this.presenterId == null) {
                    if (valueObject.getPresenterId() != null)
                           return(false);
          } else if (!this.presenterId.equals(valueObject.getPresenterId())) {
                    return(false);
          }          
          if (this.producerId == null) {
                    if (valueObject.producerId != null)
                           return(false);
          } else if (!this.producerId.equals(valueObject.getProducerId())) {
                    return(false);
          }          
          if (this.weekId == 0) {
                    if (valueObject.getWeekId() != 0)
                           return(false);
          } else if (!(this.weekId == valueObject.getWeekId())) {
                    return(false);
          }

          return true;
    }

    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     * @return String
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nProgramSlot class, mapping to table tblprogramslot\n");
        out.append("Persistent attributes: \n"); 
        out.append("programSlotId = ").append(this.programSlotId).append("\n"); 
        out.append("assignedBy = ").append(this.assignedBy).append("\n"); 
        out.append("duration = ").append(this.duration).append("\n"); 
        out.append("startDate = ").append(this.startDate).append("\n"); 
        out.append("programName = ").append(this.programName).append("\n"); 
        out.append("presenterId = ").append(this.presenterId).append("\n"); 
        out.append("producerId = ").append(this.producerId).append("\n"); 
        out.append("weekId = ").append(this.weekId).append("\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return Object
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    public Object clone() throws CloneNotSupportedException {
        ProgramSlot cloned = new ProgramSlot();

        if (this.programSlotId != 0)
             cloned.setProgramSlotId(this.programSlotId); 
        if (this.assignedBy != null)
             cloned.setAssignedBy(this.assignedBy); 
        if (this.duration != 0)
             cloned.setDuration(this.duration); 
        if (this.startDate != null)
             cloned.setStartDate(this.startDate); 
        if (this.programName != null)
             cloned.setProgramName(this.programName); 
        if (this.presenterId != null)
             cloned.setPresenterId(this.presenterId); 
        if (this.producerId != null)
             cloned.setProducerId(this.producerId); 
        if (this.weekId != 0)
             cloned.setWeekId(this.weekId); 
        return cloned;
    }
}
