package de.hhbk.jahresprojekt.model;

import java.util.List;

public class Role {
    private int id;
    private RoleType roleType;
    private List<User> userList;

    public Role() {
    }

    public Role(int id, RoleType roleType, List<User> userList) {
        this.id = id;
        this.roleType = roleType;
        this.userList = userList;
    }

    public int getId() {
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
