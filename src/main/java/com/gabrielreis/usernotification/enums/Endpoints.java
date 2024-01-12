package com.gabrielreis.usernotification.enums;

public enum Endpoints {
   
  PEDING_NOTIFICATION("http://localhost:8080/notifications/pending"),
  DONE_NOTIFICATION("http://localhost:8080/notifications/done"),
  SEND_NOTIFICATION("http://localhost:8080/notifications/send");

  public String url;

  private Endpoints(String url) {
    this.url = url;
  }

}
