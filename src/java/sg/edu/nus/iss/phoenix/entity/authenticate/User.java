package sg.edu.nus.iss.phoenix.entity.authenticate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User Value Object. This class is value object representing database table
 * user This class is intended to be used together with associated DAO object.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "password", "roles"})
public class User implements Cloneable, Serializable {

    /**
     * For eclipse based unique identity
     */
    private static final long serialVersionUID = -3737184031423373198L;
    /**
     * Persistent Instance variables. This data is directly mapped to the
     * columns of database table.
     */
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("roles")
    private List<Role> roles = new ArrayList<Role>();

    /**
     * Constructors. The first one takes no arguments and provides the most
     * simple way to create object instance. The another one takes one argument,
     * which is the primary key of the corresponding table.
     */
    public User() {

    }

    public User(String idIn) {

        this.id = idIn;

    }

    /**
     * Get- and Set-methods for persistent variables. The default behavior does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     */
    
    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("id")
    public void setId(String idIn) {
        this.id = idIn;
    }

    @JsonProperty("password")
    public String getPassword() {
        return this.password;
    }

    @JsonProperty("password")
    public void setPassword(String passwordIn) {
        this.password = passwordIn;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    @JsonProperty("roles")
    public List<Role> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * setAll allows to set all persistent variables in one method call. This is
     * useful, when all data is available and it is needed to set the initial
     * state of this object. Note that this method will directly modify instance
     * variables, without going trough the individual set-methods.
     */
    public void setAll(String idIn, String passwordIn, String nameIn,
            String roleIn) {
        this.id = idIn;
        this.password = passwordIn;
        this.name = nameIn;
        Role e = new Role(roleIn);
        this.roles.add(e);
    }

    /**
     * hasEqualMapping-method will compare two User instances and return true if
     * they contain same values in all persistent instance variables. If
     * hasEqualMapping returns true, it does not mean the objects are the same
     * instance. However it does mean that in that moment, they are mapped to
     * the same row in database.
     */
    public boolean hasEqualMapping(User valueObject) {

        if (valueObject.getId() != this.id) {
            return (false);
        }
        if (this.password == null) {
            if (valueObject.getPassword() != null) {
                return (false);
            }
        } else if (!this.password.equals(valueObject.getPassword())) {
            return (false);
        }
        if (this.name == null) {
            if (valueObject.getName() != null) {
                return (false);
            }
        } else if (!this.name.equals(valueObject.getName())) {
            return (false);
        }
        if (this.roles.get(0).getRole() != null) {
            if (valueObject.roles.get(0).getRole() != null) {
                return (false);
            }
        } else if (!this.roles.get(0).equals(valueObject.roles.get(0).getRole())) {
            return (false);
        }

        return true;
    }

    /**
     * toString will return String object representing the state of this
     * valueObject. This is useful during application development, and possibly
     * when application is writing object states in console logs.
     */
    public String toString() {
        StringBuffer out = new StringBuffer("toString: ");
        out.append("\nclass User, mapping to table user\n");
        out.append("Persistent attributes: \n");
        out.append("id = " + this.id + "\n");
        out.append("password = " + this.password + "\n");
        out.append("name = " + this.name + "\n");
        out.append("role = " + this.roles.get(0).getRole() + "\n");
        return out.toString();
    }

    /**
     * Clone will return identical deep copy of this valueObject. Note, that
     * this method is different than the clone() which is defined in
     * java.lang.Object. Here, the returned cloned object will also have all its
     * attributes cloned.
     */
    public Object clone() {
        User cloned = new User();
        if (this.password != null) {
            cloned.setPassword(new String(this.password));
        }
        if (this.name != null) {
            cloned.setName(new String(this.name));
        }
        if (this.roles.get(0) != null) {
            cloned.roles.add(new Role(this.roles.get(0).getRole()));
        }
        return cloned;
    }

}
