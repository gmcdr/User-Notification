package com.gabrielreis.usernotification.enums;

public enum Endpoint {
   
  PEDING_NOTIFICATION("http://localhost:8080/notifications/pending");

  public String url;

  private Endpoint(String url) {
    this.url = url;
  }

}
