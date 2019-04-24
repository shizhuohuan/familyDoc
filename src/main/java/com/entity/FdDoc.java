package com.entity;

import java.util.List;

public class FdDoc extends MongoBase{

    private String hospital;
    private String dptId;
    private String produce;
    private String level;
    private String address;
    private List<String> charge;

    public FdDoc(String relName, String idcard, Integer age, String sex) {
        super(relName, idcard, age, sex);
    }


    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDptId() {
        return dptId;
    }

    public void setDptId(String dptId) {
        this.dptId = dptId;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCharge() {
        return charge;
    }

    public void setCharge(List<String> charge) {
        this.charge = charge;
    }
}
