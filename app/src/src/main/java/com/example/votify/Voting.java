package com.example.votify;

public class Voting {
    public String getSelectedCand() {
        return selectedCand;
    }

    public void setSelectedCand(String selectedCand) {
        this.selectedCand = selectedCand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String selectedCand;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;
    public Voting(){}
}
