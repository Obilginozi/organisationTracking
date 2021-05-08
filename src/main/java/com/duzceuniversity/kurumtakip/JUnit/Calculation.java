package com.duzceuniversity.kurumtakip.JUnit;

import com.duzceuniversity.kurumtakip.DataBase.Model.Staff.Staff;

import java.util.List;

public class Calculation {

    public static int findMax(List<Staff> staffList){
        int max=11;
        List<Staff> length = null;
        assert false;
        for(int i=1;i<staffList.size();i++){
            if(max<staffList.get(i).getTcPass().length())
                max=staffList.get(i).getTcPass().length();
            if (staffList.get(i).getTcPass().length() != 11){
                length.add(staffList.get(i));
            }
        }
        return (length == null ? 0 : length.size());
    }
}