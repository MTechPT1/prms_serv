/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.restful.user;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sg.edu.nus.iss.phoenix.entity.authenticate.User;

/**
 *
 * @author lechin
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"users"})
public class Users {
    
    @JsonProperty("users")
    private List<User> users;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    @JsonProperty("users")
    public List<User> getUsers() {
        return this.users;
    }
    
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
