package com.company;

import java.util.ArrayList;

public class Variant implements Result  {
    private Integer []x;
    @Override
    public Integer getResult() {
        int imax =0;
        for(int i=0;i< x.length; i++) {
            if (x[i] > x[imax]){
                imax =i;
            }

        }
        return x[imax];
    }
    public Variant(String s){
        String [] s1 = s.split(",\\s|\\.");
        x = new Integer[s1.length];
        for(int i=0;i<s1.length;i++){
            x[i] =(Integer.parseInt(s1[i]));
        }
    };
    public Variant(Integer[]a){
        x=a;
    }
    public Variant(ArrayList<Integer> L){
        //L.toArray(x);
        x = new Integer[L.size()];
        L.toArray(x);

    }
}
