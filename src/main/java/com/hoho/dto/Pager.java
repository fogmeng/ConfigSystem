package com.hoho.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silence on 2016/9/12.
 */
public class Pager<T> {
    private int allPage;
    private int currentPage;
    private int begin;
    private int end;
    private ArrayList<Integer> pageList;
    private List<T> data;

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public ArrayList<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(ArrayList<Integer> pageList) {
        this.pageList = pageList;
    }

    public Pager(int currentPage, int allPage) {
        this.currentPage = currentPage;
        this.allPage = allPage;
        this.begin = currentPage * 10 - 10;
        this.end = currentPage * 10;
        this.pageList = getPageList(currentPage,allPage);
    }

    public ArrayList<Integer> getPageList(int currentPage, int allPage){
        ArrayList<Integer> list =  new ArrayList<Integer>();
        if(allPage<10){
            for (int i = 1; i <= allPage; i++) {
                list.add(i);
            }
        }else{
            if (currentPage<5){
                for (int i = 1; i < 10; i++) {
                    list.add(i);
                }
            }else if(currentPage>5&&currentPage<=allPage-4){
                for (int i = currentPage - 4; i < currentPage + 5; i++) {
                    list.add(i);
                }
            }else{
                for (int i = allPage - 9; i < allPage; i++) {
                    list.add(i+1);
                }
            }
        }
        return list;
    }

    public Pager() {
    }
}
