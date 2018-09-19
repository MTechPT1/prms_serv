package sg.edu.nus.iss.phoenix.core.dao;

public class DBConstants {
    
    // Data Connection Variables
    public static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String dbUrl = "jdbc:mysql://localhost:3306/phoenix";
    public static final String dbUserName = "phoenix";
    public static final String dbPassword = "password";
    
    //Not yet refactored!!!!!!!!!!!!!!!!!!!
    
    // Table names
    public static final String usersTableName = "user";
    public static final String rolesTableName = "role";
    public static final String programTableName = "radio-programs";
    public static final String scheduleTableName = "tblprogramslot";
    public static final String wScheduleTableName = "tblweeklyschedule";
    public static final String aScheduleTableName = "tblannualschedule";
    
    
    //Users table field names
    public static final String u_id = "id";
    public static final String u_name = "name";
    public static final String u_password = "password";
    public static final String u_role ="role";
    
    //roles table field names
    public static final String r_role = "role";
    public static final String r_access ="accessPrivilege";
    
    //radio program
    public static final String r_name = "name";
    public static final String r_desc = "desc";
    public static final String r_duration = "duration";
    
    //schedule
    public static final String p_id = "programSlotId";
    public static final String p_assignedBy = "assignedBy";
    public static final String p_duration = "duration";
    public static final String p_startDate = "startDate";
    public static final String p_programName = "programName";
    public static final String p_presenterId = "presenterId";
    public static final String p_producerId = "producerId";
    public static final String p_weekId = "weekId";
    
    //weekly schedule
    public static final String w_weekId = "weekId";
    public static final String w_startDate = "startDate";
    public static final String w_assignedBy = "assignedBy";
    public static final String w_year = "year";
    
    //annual schedule
    public static final String a_year = "year";
    public static final String a_assignedBy = "assignedBy";
 
    //Exceptions
    public static final String exc_duplicate_primary_key = "Duplicate primary key !";
    public static final String exc_missing_primary_key = "Primary key not found !";
    public static final String exc_missing_program_slot = "Program slot object not found !";
    public static final String exc_missing_weekly_schedule = "Weekly schedule object not found !";
    public static final String exc_missing_annual_schedule = "Annualschedule object not found !";
}
