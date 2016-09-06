package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PS_USERS")
public class UserBO implements IStorable, Serializable {

  private static final long serialVersionUID = 1923146508106527972L;

  @Id
  @Column(name = "user_id")
  @SequenceGenerator(name = "ps_user_sequence", sequenceName = "PS_USER_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ps_user_sequence")
  private long id;

  @Column(name = "user_name", nullable = false, unique = true, length = 32)
  private String username;

  @Column(name = "first_name", length = 32)
  private String firstName;

  @Column(name = "last_name", length = 32)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false, length = 64)
  private String password;

  @Column(name = "phone_number", length = 10)
  private String phoneNumber;

  @Column(name = "user_status")
  private int status;

  public UserBO() {
    this(null, null, null, null, null, null);
  }

  public UserBO(String username, String firstName, String lastName, String email, String password, String phoneNumber) {
    super();
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.status = 1;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserBO other = (UserBO) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "UserBO [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
        + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber + ", status=" + status + "]";
  }

}
