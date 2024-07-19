package org.example.minitest1.enumtype;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_GUEST("ROLE_GUEST");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
