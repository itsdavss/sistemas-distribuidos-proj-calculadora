package util;

import java.io.Serializable;

public class MsqReq implements Serializable{
    private char oper;
    private double value1, value2;
    
    public MsqReq(char oper, double value1, double value2) {
        this.oper = oper;
        this.value1 = value1;
        this.value2 = value2;
    }

    public char getOper() {
        return oper;
    }

    public double getValue1() {
        return value1;
    }

    public double getValue2() {
        return value2;
    }


}
