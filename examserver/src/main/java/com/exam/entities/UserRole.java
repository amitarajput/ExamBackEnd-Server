package com.exam.entities;

import jakarta.persistence.*;

@Entity
@Table(name="userRole")
public class UserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userRoleId;

  //user
  @ManyToOne(fetch = FetchType.EAGER)
  private User user;
  @ManyToOne
  private Role role;

  //constructor
  public UserRole() {

  }


  public Long getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(Long userRoleId) {
    this.userRoleId = userRoleId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
