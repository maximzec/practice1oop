package com.company;

import java.io.Serializable;

public class ObjArray implements Serializable {
    private Integer [] x;
    private String message;
    public ObjArray(Integer[] a, String m){
        x=a; message = m;
    }
    public Integer[] getArray(){
        return x;
    }
    public String getM(){
        return message;
    }
}

