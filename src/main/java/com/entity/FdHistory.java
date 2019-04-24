package com.entity;


public class FdHistory {

  private String id;
  private String content;
  private String createDate;
  private String sendId;
  private String reciveId;
  private String confirm;
  private String type;
  private String messageId;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getSendId() {
    return sendId;
  }

  public void setSendId(String sendId) {
    this.sendId = sendId;
  }

  public String getReciveId() {
    return reciveId;
  }

  public void setReciveId(String reciveId) {
    this.reciveId = reciveId;
  }

  public String getConfirm() {
    return confirm;
  }

  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }
}
