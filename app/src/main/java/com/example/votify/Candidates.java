package com.example.votify;

public class Candidates {
    String person1, person2, person3, person4, code;

    public Candidates(){

    }

    public Candidates(String person1, String person2, String person3, String person4, String code) {
        this.person1 = person1;
        this.person2 = person2;
        this.person3 = person3;
        this.person4 = person4;
        this.code = code;
    }

    public String getPerson1() {
        return person1;
    }

    public String getPerson2() {
        return person2;
    }

    public String getPerson3() {
        return person3;
    }

    public String getPerson4() {
        return person4;
    }
    public String getCode() {
        return code;
    }
}
