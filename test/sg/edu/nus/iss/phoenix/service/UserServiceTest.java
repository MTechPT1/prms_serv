/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.service;

import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.entity.authenticate.User;

/**
 *
 * @author lechin
 */
public class UserServiceTest {
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUsers method, of class UserService.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        String roleType = "";
        UserService instance = new UserService();
        Response expResult = null;
        Response result = instance.getUsers(roleType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        String userId = "";
        UserService instance = new UserService();
        Response expResult = null;
        Response result = instance.getUser(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        User user = null;
        UserService instance = new UserService();
        Response expResult = null;
        Response result = instance.createUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyUser method, of class UserService.
     */
    @Test
    public void testModifyUser() {
        System.out.println("modifyUser");
        User user = null;
        UserService instance = new UserService();
        Response expResult = null;
        Response result = instance.modifyUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UserService.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String userId = "";
        UserService instance = new UserService();
        Response expResult = null;
        Response result = instance.deleteUser(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
