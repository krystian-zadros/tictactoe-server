package pl.kzadros.tictactoe.entities;

/**
 *
 * @author kzadros
 */
public class Role {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean compare(Role role) {
        return this.name.equals(role.getName());
    }
    
    public static Role createUser() {
        return new Role("USER");
    }
    
    public static Role createPlayer() {
        return new Role("PLAYER");
    }
    
    public static Role createAdmin() {
        return new Role("ADMIN");
    }
}
