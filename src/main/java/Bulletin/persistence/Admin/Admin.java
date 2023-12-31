package Bulletin.persistence.Admin;

import java.io.Serializable;

import jakarta.persistence.*;

/**
 *
 * @author dazai
 */
@Entity @Table(name = "Admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Admin(){
        this("none","none", "name", Rules.UKNOWN, "null");
    }
    public Admin(String username, String password, String name, Rules rule, String poste) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.rule = rule;
        this.poste = poste;
    }

    public int getId() {
        return Math.toIntExact(id);
    }
    
    private String username;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Rules rule;

    private String poste;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rules getRule() {
        return rule;
    }

    public void setRule(Rules rule) {
        this.rule = rule;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
