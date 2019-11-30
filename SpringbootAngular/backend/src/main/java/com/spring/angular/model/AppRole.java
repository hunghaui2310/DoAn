package com.spring.angular.model;

import javax.persistence.*;

@Entity
@Table(name = "app_role")
public class AppRole {

    private Long roleId;
    private String roleName;

    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "Role_Name", length = 30, nullable = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
