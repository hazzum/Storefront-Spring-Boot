package com.hazzum.storefront.payload.response;

public class JwtResponse {
  private String authToken;
  private String type = "Bearer";
  private int id;
  private String username;

  public JwtResponse(String authToken, int id, String username) {
    this.id = id;
    this.authToken = authToken;
    this.username = username;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public int getUser_id() {
    return id;
  }

  public void setUser_id(int id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
