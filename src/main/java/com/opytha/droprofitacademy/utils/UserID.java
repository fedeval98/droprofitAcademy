package com.opytha.droprofitacademy.utils;

public class UserID {
    public static int getAccountNumber(int min, int max){
        return (int) ((Math.random())*(max-min)+min);
    }

}
