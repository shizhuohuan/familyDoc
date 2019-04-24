package com.entity;


public class FdRecord {

  private String id;
  private String createTime;
  private String deptId;
  private String currentIll;
  private String diagnose;
  private String docAdvice;
  private String remark;
  private String talk;
  private String docId;
  private String userId;

  public FdRecord(){

  }

  public FdRecord(String deptId, String currentIll, String talk) {
    this.deptId = deptId;
    this.currentIll = currentIll;
    this.talk = talk;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }


  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }


  public String getCurrentIll() {
    return currentIll;
  }

  public void setCurrentIll(String currentIll) {
    this.currentIll = currentIll;
  }


  public String getDiagnose() {
    return diagnose;
  }

  public void setDiagnose(String diagnose) {
    this.diagnose = diagnose;
  }


  public String getDocAdvice() {
    return docAdvice;
  }

  public void setDocAdvice(String docAdvice) {
    this.docAdvice = docAdvice;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getTalk() {
    return talk;
  }

  public void setTalk(String talk) {
    this.talk = talk;
  }

  public String getDocId() {
    return docId;
  }

  public void setDocId(String docId) {
    this.docId = docId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
