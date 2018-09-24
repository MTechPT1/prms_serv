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
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
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
     * @param programSlotId
     * @param assignedBy
     * @param duration
     * @param startDate
     * @param programName
     * @param presenterId
     * @param producerId
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
     * @return 
     */
    public boolean hasEqualMapping(ProgramSlot valueObject) {

          if (this.programSlotId == 0) {
                    if (valueObject.getProgramSlotId() != 0)
                           return(false);
          } else if (!(this.programSlotId == valueObject.getProgramSlotId())) {
                    return(false);
          }
          /**
          if (this.description == null) {
                    if (valueObject.getDescription() != null)
                           return(false);
          } else if (!this.description.equals(valueObject.getDescription())) {
                    return(false);
          }
          if (this.typicalDuration == null) {
                    if (valueObject.getTypicalDuration() != null)
                           return(false);
          } else if (!this.typicalDuration.equals(valueObject.getTypicalDuration())) {
                    return(false);
          }
          */

          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nRadioProgram class, mapping to table radio-program\n");
        out.append("Persistent attributes: \n"); 
        //out.append("name = ").append(this.name).append("\n"); 
        //out.append("description = ").append(this.description).append("\n"); 
        //out.append("typicalDuration = ").append(this.typicalDuration).append("\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    public Object clone() throws CloneNotSupportedException {
        ProgramSlot cloned = new ProgramSlot();

        if (this.programSlotId != 0)
             cloned.setProgramSlotId(this.programSlotId); 
        if (this.duration != 0)
             cloned.setDuration(this.duration); 
        return cloned;
    }
}
