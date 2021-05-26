package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Role;
import de.hhbk.jahresprojekt.model.RoleType;
import de.hhbk.jahresprojekt.model.User;

import java.util.List;

public final class RoleBuilder {
    private int id;
    private RoleType roleType;
    private List<User> userList;

    private RoleBuilder() {
    }

    public static RoleBuilder aRole() {
        return new RoleBuilder();
    }

    public RoleBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public RoleBuilder withRoleType(RoleType roleType) {
        this.roleType = roleType;
        return this;
    }

    public RoleBuilder withUserList(List<User> userList) {
        this.userList = userList;
        return this;
    }

    public Role build() {
        return new Role(id, roleType, userList);
    }
}
