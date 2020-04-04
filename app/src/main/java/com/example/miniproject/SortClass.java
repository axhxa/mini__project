package com.example.miniproject;
import com.example.miniproject.InfoResults;


import java.util.Comparator;

public class SortClass implements Comparator{
    @Override
    public  int compare(Object o,Object o1) {
        InfoResults infoResults = (InfoResults) o;
        InfoResults infoResults1 = (InfoResults) o1;
        int flag=infoResults1.date.compareTo(infoResults.date);
        return flag;
    }
}