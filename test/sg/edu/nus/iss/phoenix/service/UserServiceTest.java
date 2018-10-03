/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.dao.UserDao;
import sg.edu.nus.iss.phoenix.entity.authenticate.Role;
import sg.edu.nus.iss.phoenix.entity.authenticate.User;
import sg.edu.nus.iss.phoenix.restful.user.Users;

/**
 *
 * @author lechin
 */
public class UserServiceTest {
    
    public UserServiceTest() {
    }
    
    List<User> userList;
    
    @Mock
    private UserDao userDao = Mockito.mock(UserDao.class);
    
    @InjectMocks
    private UserService service;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        
        User u1 = new User();
        u1.setAll("12345", "password12", "Leon", "Producer", "01012018");
        
        User u2 = new User();
        u2.setAll("12346", "password12", "Karen", "Manager", "01022018");
        
        User u3 = new User();
        u3.setAll("12347", "password12", "Neelima", "Presenter", "01032018");
        
        User u4 = new User();
        u4.setAll("12348", "password12", "Changling", "SysAdmin", "01042018");
        
        User u5 = new User();
        u5.setAll("12349", "password12", "Zhi Kai", "Producer", "01052018");
        Role role1 = new Role();
        role1.setRole("Producer");
        Role role2 = new Role();
        role2.setRole("MANAGER");
        u5.setRoles(Arrays.asList(role1, role2));
        
        User u6 = new User();
        u6.setAll("12350", "password12", "Wai Kin", "Manager", "01062018");
        
        userList = Arrays.asList(u1, u2, u3, u4, u5, u6);
        Mockito.when(userDao.loadAll()).thenReturn(userList);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetNullUsers() throws SQLException {        
        Users users = (Users) service.getUsers(null).getEntity();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.getUsers());
        Assert.assertEquals(users.getUsers().size(), 6);
    }
    
    @Test
    public void testGetManagers() throws SQLException {
        Users users = (Users) service.getUsers("MANAGER").getEntity();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.getUsers());
        Assert.assertEquals(users.getUsers().size(), 3);
    }
    
    @Test
    public void testGetProducers() throws SQLException {
        Users users = (Users) service.getUsers("PRODUCER").getEntity();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.getUsers());
        Assert.assertEquals(users.getUsers().size(), 2);
    }
    
    @Test
    public void testGetPresenters() throws SQLException {
        Users users = (Users) service.getUsers("PRESENTER").getEntity();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.getUsers());
        Assert.assertEquals(users.getUsers().size(), 1);
    }
    
    @Test
    public void testGetSysAdmin() throws SQLException {
        Users users = (Users) service.getUsers("SYSADMIN").getEntity();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.getUsers());
        Assert.assertEquals(users.getUsers().size(), 1);
    }
    
    @Test
    public void testGetRandom() throws SQLException {
        Users users = (Users) service.getUsers("GARBAGE").getEntity();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.getUsers());
        Assert.assertEquals(users.getUsers().size(), 0);
    }
    
    @Test
    public void testGetAvailableUser() throws SQLException, NotFoundException {
        Mockito.when(userDao.getObject("12345")).thenReturn(userList.get(0));
        
        User user = (User) service.getUser("12345").getEntity();

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "Leon");
        Assert.assertEquals(user.getRoles().size(), 1);
        Assert.assertEquals(user.getJoinDate(), "01012018");
    }
    
    @Test(expected = NotFoundException.class)
    public void testGetUnavailableUser() throws NotFoundException, SQLException {
        Mockito.when(userDao.getObject("12345")).thenThrow(NotFoundException.class);
        
        User user = (User) service.getUser("12345").getEntity();
        
        Assert.assertNotNull(user);
        Assert.assertNull(user.getId());
    }
    
    @Test
    public void testCreateUser() throws SQLException {
        User user = new User();
        user.setAll("12345", "password12", "Leon", "Producer", "01012018");
        
        Mockito.doNothing().when(userDao).create(user);
        
        Assert.assertNotNull(service.createUser(user));
        Assert.assertEquals(service.createUser(user).getStatus(), 200);
    }
    
    @Test
    public void testModifyUser() throws NotFoundException, SQLException {
        User user = new User();
        user.setAll("12345", "password12", "Leon", "Producer", "01012018");
        
        Mockito.doNothing().when(userDao).save(user);
        
        Assert.assertNotNull(service.modifyUser(user));
        Assert.assertEquals(service.modifyUser(user).getStatus(), 200);
    }
    
    @Test
    public void testDeleteUser() throws NotFoundException, SQLException {
        User user = new User();
        user.setAll("12345", "password12", "Leon", "Producer", "01012018");
        
        Mockito.doNothing().when(userDao).delete(user);
        
        Assert.assertNotNull(service.deleteUser("12345"));
        Assert.assertEquals(service.deleteUser("12345").getStatus(), 200);
    }
}
