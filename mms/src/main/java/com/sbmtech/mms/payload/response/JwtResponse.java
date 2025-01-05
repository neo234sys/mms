package com.sbmtech.mms.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long userId;
  private String username;
  private Long mobileNo;
  private List<String> roles;

  public JwtResponse(String accessToken, Long userId, String username, Long mobileNo, List<String> roles) {
    this.token = accessToken;
    this.userId = userId;
    this.username = username;
    this.mobileNo = mobileNo;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(Long mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
