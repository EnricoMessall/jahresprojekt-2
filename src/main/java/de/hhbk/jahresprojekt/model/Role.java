package de.hhbk.jahresprojekt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Role {
    @Id
    private Long id;
    private RoleType roleType;
    @OneToMany
    private List<User> userList;

    public Role() {
    }

    public Role(Long id, RoleType roleType, List<User> userList) {
        this.id = id;
        this.roleType = roleType;
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
