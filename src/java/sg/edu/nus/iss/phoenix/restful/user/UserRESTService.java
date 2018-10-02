/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.restful.user;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.entity.authenticate.User;
import sg.edu.nus.iss.phoenix.service.UserService;

/**
 *
 * @author lechin
 */
@Path("/user/")
public class UserRESTService {
    
    @Context
    private UriInfo context;
    
    private UserService service;
    
    public UserRESTService () {
        service = new UserService();
    }
    
    /**
     * Get users based on role type
     * 
     * @param roleType
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers (
            @QueryParam("role_type") String roleType) throws NotFoundException, SQLException{
        return service.getUsers(roleType);
    }
    
    /**
     * Get user based on user ID
     * 
     * @param userId
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    @GET
    @Path("{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("user_id") String userId) throws NotFoundException, SQLException{
        return service.getUser(userId);
    }
    
    /**
     * Creates a new user
     * 
     * @param user
     * @return
     * @throws SQLException 
     */
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) throws SQLException{
        return service.createUser(user);
    }
    
    /**
     * Updates a user's data stored in the database
     * 
     * @param user
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) throws NotFoundException, SQLException{
        return service.modifyUser(user);
    }
    
    /**
     * Deletes a user from the database based on the ID
     * 
     * @param userId
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    @DELETE
    @Path("delete/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("user_id") String userId) throws NotFoundException, SQLException{
        return service.deleteUser(userId);
    }
    
}
