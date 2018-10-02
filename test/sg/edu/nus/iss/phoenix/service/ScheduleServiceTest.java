/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.service;

import java.sql.SQLException;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

import sg.edu.nus.iss.phoenix.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.entity.schedule.AnnualSchedule;
import sg.edu.nus.iss.phoenix.entity.schedule.ProgramSlot;
import sg.edu.nus.iss.phoenix.entity.schedule.WeeklySchedule;

/**
 *
 * @author Karen Athaide
 */
public class ScheduleServiceTest {
    
    private ArrayList<AnnualSchedule> asList;
    private ArrayList<WeeklySchedule> wsList;
    private ArrayList<ProgramSlot> psList;
    
    private AnnualSchedule as1;
    private AnnualSchedule as2;
    private WeeklySchedule ws1;
    private WeeklySchedule ws2;
    private ProgramSlot ps1;
    private ProgramSlot ps2;
    
    public ScheduleServiceTest() {
    }
    
    @Mock
    private ScheduleDAO daoMock = mock(ScheduleDAO.class);
    
    @InjectMocks
    private ScheduleService service;
            
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        asList = new ArrayList();
        as1 = new AnnualSchedule("2018","KarenA");
        asList.add(as1);        
        as2 = new AnnualSchedule("2017","KarenA");
        asList.add(as2);
        
        wsList = new ArrayList();
        ws1 = new WeeklySchedule(1,"2018-09-27","KarenA","2018");
        wsList.add(ws1);
        ws2 = new WeeklySchedule(2,"2018-09-27","KarenA","2018");
        wsList.add(ws2);

        psList = new ArrayList();
        ps1 = new ProgramSlot(1,"KarenA",30,"2018-09-27 17:30:00","test","Leon","Zhi Kai",1);
        psList.add(ps1);
        ps2 = new ProgramSlot(2,"Wai Kin",30,"2018-09-27 17:30:00","test","Chang Ling","Neelima",1);
        psList.add(ps2);        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAS method, of class ScheduleService.
     */
    @Test
    public void testFindAS() throws SQLException {
        String year = "2018";
        when(daoMock.searchMatchingAS(any(AnnualSchedule.class))).thenReturn(asList);        
        assertEquals(as1,service.findAS(year));
    }

    /**
     * Test of findAllAS method, of class ScheduleService.
     */
    @Test
    public void testFindAllAS() throws SQLException {
        when(daoMock.loadAllAS()).thenReturn(asList);
        assertEquals(asList, service.findAllAS());
    }

    /**
     * Test of findWS method, of class ScheduleService.
     */
    @Test
    public void testFindWS() throws SQLException { 
        String year = "2018";
        when(daoMock.searchMatchingWS(any(WeeklySchedule.class))).thenReturn(wsList);        
        assertEquals(ws1, service.findWS(year));
    }

    /**
     * Test of findAllWS method, of class ScheduleService.
     */
    @Test
    public void testFindAllWS() throws SQLException {
        String year = "2018";
        when(daoMock.loadAllWS(year)).thenReturn(wsList);
        assertEquals(wsList, service.findAllWS(year));
    }

    /**
     * Test of findPSByDates method, of class ScheduleService.
     */
    @Test
    public void testFindPSByDates() throws SQLException {
        int weekId = 1;
        String year = "2018";
        when(daoMock.searchMatchingDates(weekId,"2018-01-01 00:00:00","2018-12-31 23:59:59")).thenReturn(psList);
        assertEquals(psList, service.findPSByDates(weekId, year));
    }

    /**
     * Test of findPSByCriteria method, of class ScheduleService.
     */
    @Test
    public void testFindPSByCriteria() throws SQLException {
        when(daoMock.searchMatching(any(ProgramSlot.class))).thenReturn(psList);
        assertEquals(psList, service.findPSByCriteria(ps1));
    }

    /**
     * Test of findPS method, of class ScheduleService.
     */
    @Test
    public void testFindPS() throws SQLException {
        when(daoMock.searchMatching(any(ProgramSlot.class))).thenReturn(psList);
        int programSlotId = 1;
        assertEquals(ps1, service.findPS(programSlotId));
    }

    /**
     * Test of findAllPS method, of class ScheduleService.
     */
    @Test
    public void testFindAllPS() throws SQLException {
        when(daoMock.loadAll()).thenReturn(psList);
        assertEquals(psList, service.findAllPS());
    }

    /**
     * Test of processCreate method, of class ScheduleService.
     */
    @Test
    public void testProcessCreate() throws SQLException {
        ProgramSlot ps = null;
        doNothing().when(daoMock).createPS(ps);
        service.processCreate(ps);
        assertNull(ps);
    }

    /**
     * Test of processModify method, of class ScheduleService.
     */
    @Test
    public void testProcessModify() throws NotFoundException, SQLException {
        ProgramSlot ps = null;
        doNothing().when(daoMock).save(ps);
        service.processModify(ps);
        assertNull(ps);
    }

    /**
     * Test of processDelete method, of class ScheduleService.
     */
    @Test
    public void testProcessDelete() throws NotFoundException, SQLException {
        doNothing().when(daoMock).delete(ps1);
        service.processDelete(1);
        assertNotNull(ps1);
    }    
}
