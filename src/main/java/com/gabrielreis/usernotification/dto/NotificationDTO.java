package com.gabrielreis.usernotification.dto;

public class NotificationDTO {
  
  private String title;
  private String message;
  private String user;
  private String email;

  public NotificationDTO(String title, String message, String user, String email) {
    this.title = title;
    this.message = message;
    this.user = user;
    this.email = email;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
