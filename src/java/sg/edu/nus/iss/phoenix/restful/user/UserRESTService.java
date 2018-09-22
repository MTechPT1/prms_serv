/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.restful.user;

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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(
            @QueryParam("role_type") String roleType) {
        return service.getUsers(roleType);
    }
    
    @GET
    @Path("{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("user_id") String userId) {
        return service.getUser(userId);
    }
    
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        return service.createUser(user);
    }
    
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        return service.modifyUser(user);
    }
    
    @DELETE
    @Path("delete/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("user_id") String userId) {
        return service.deleteUser(userId);
    }
    
}
