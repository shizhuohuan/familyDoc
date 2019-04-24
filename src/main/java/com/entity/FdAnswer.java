package com.entity;


public class FdAnswer{

  private String id;
  private String quessionId;
  private String content;
  protected String createTime;
  private String userId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getQuessionId() {
    return quessionId;
  }

  public void setQuessionId(String quessionId) {
    this.quessionId = quessionId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
