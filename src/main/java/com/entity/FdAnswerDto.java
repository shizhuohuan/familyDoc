package com.entity;

public class FdAnswerDto extends FdAnswer implements Comparable<FdAnswerDto>{
    private String docData;

    public String getDocData() {
        return docData;
    }

    public void setDocData(String docData) {
        this.docData = docData;
    }


    @Override
    public int compareTo(FdAnswerDto o) {
        int flag = this.createTime.compareTo(o.createTime);
        return flag;
    }
}
