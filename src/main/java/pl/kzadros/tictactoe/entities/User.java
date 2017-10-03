package pl.kzadros.tictactoe.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kzadros
 */
public class User {
    private String id;
    private String name;
    private List<Role> roles;

    public User() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    // ******************
    
    public void addRole(Role added) throws Exception {
        for (Role role : roles) {
            if (role.compare(added))
                throw new Exception();
        }
        roles.add(added);
    }
    
}
