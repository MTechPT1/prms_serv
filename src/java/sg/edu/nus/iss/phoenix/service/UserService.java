/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.dao.UserDao;
import sg.edu.nus.iss.phoenix.entity.authenticate.Role;
import sg.edu.nus.iss.phoenix.entity.authenticate.User;
import sg.edu.nus.iss.phoenix.restful.user.Users;

/**
 *
 * @author lechin
 */
public class UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    
    private DAOFactory daoFactory;
    private UserDao userDAO;
    
    public UserService() {
        daoFactory = new DAOFactoryImpl();
        userDAO = daoFactory.getUserDAO();
    }
    
    public Response getUsers(String roleType) {
        if (roleType == null) {
            roleType = "ALL";
        }
        
        logger.debug("Fetching users with role type: " + roleType);
        
        Users users = new Users();
        List<User> userList = new ArrayList<>();
        
        try {
            userList = userDAO.loadAll();
        } catch (SQLException e) {
            logger.error("SQL Exception");
            e.printStackTrace();
        }
        
        if (!"ALL".equals(roleType)) {
            userList = filterUsers(userList, roleType);
        }
        users.setUsers(userList);
        
        return Response.ok(users, MediaType.APPLICATION_JSON).build();
    }
    
    public Response getUser(String userId) {
        User user = new User();
        
        logger.debug("Fetching user with user id: " + userId);
        
        try {
            user = userDAO.getObject(userId);
        } catch (NotFoundException ex) {
            logger.error("Not able to find user with id: " + userId);
        } catch (SQLException ex) {
            logger.error("SQL Exception");
            ex.printStackTrace();
        }
        
        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }
    
    public Response createUser(User user) {
        logger.debug("Creating a new user: " + user.toString());
        
        try {
            userDAO.create(user);
        } catch (SQLException ex) {
            logger.error("Unable to create user due to sql exception");
            ex.printStackTrace();
        }
        
        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }
    
    public Response modifyUser(User user) {
        logger.debug("Modifying user: " + user.toString());
        
        try {
            userDAO.save(user);
        } catch (NotFoundException ex) {
            logger.error("Unable to find and modify user with id: " + user.getId());
        } catch (SQLException ex) {
            logger.error("SQL Exception");
            ex.printStackTrace();
        }
        
        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }
    
    public Response deleteUser(String userId) {
        logger.debug("Deleting user with userId: " + userId);
        
        User user = new User();
        user.setId(userId);
        
        try {
            userDAO.delete(user);
        } catch (NotFoundException ex) {
            logger.error("Unable to find and delete user with id: " + user.getId());
        } catch (SQLException ex) {
            logger.error("SQL Exception");
            ex.printStackTrace();
        }
        
        return Response.ok().build();
    }
    
    private List<User> filterUsers(List<User> userList, String roleType) {
        List<User> filteredList = new ArrayList<>();
        
        for(User user : userList) {
            List<Role> userRoles = user.getRoles();
            
            if(userRoles == null) {
                continue;
            }
            
            for(Role role : userRoles) {
                if (role.getRole().equalsIgnoreCase(roleType)) {
                    filteredList.add(user);
                }
            }
        }
        
        return filteredList;
    }
}
