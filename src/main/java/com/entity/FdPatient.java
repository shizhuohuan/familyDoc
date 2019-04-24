package com.entity;

import java.util.List;

public class FdPatient extends MongoBase {
    private String address;
    private String marray;
    private String allergic;
    private String profession;
    private List<String> record;

    public FdPatient(String relName, String idcard, Integer age, String sex) {
        super(relName, idcard, age, sex);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarray() {
        return marray;
    }

    public void setMarray(String marray) {
        this.marray = marray;
    }

    public String getAllergic() {
        return allergic;
    }

    public void setAllergic(String allergic) {
        this.allergic = allergic;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getRecord() {
        return record;
    }

    public void setRecord(List<String> record) {
        this.record = record;
    }
}
