package com.williamsonoma;

public class ZipcodeInterval {
    private int lower;
    private int upper;

    public ZipcodeInterval(){
        this.lower = 0;
        this.upper = 0;
    }
    public ZipcodeInterval(int lower, int upper){
        this.lower = lower;
        this.upper = upper;
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }

    @Override
    public String toString() {
        return "[" + lower + "," + upper + "]";
    }
}
