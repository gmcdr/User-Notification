package com.gabrielreis.usernotification.enums;

public enum Endpoint {
   
  PEDING_NOTIFICATION("http://localhost:8080/notifications/pending"),
  DONE_NOTIFICATION("http://localhost:8080/notifications/done");

  public String url;

  private Endpoint(String url) {
    this.url = url;
  }

}
