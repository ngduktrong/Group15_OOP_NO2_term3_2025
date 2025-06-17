package com.group15.dao;
import java.util.ArrayList;
import java.util.List;

public class GenericCRUD<T> {
    private List<T> list = new ArrayList<>();

    public void add(T obj) {
        list.add(obj);
    }

    public List<T> getAll() {
        return list;
    }

    public boolean update(int index, T obj) {
        if (index >= 0 && index < list.size()) {
            list.set(index, obj);
            return true;
        }
        return false;
    }

    public boolean delete(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            return true;
        }
        return false;
    }

    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        for (T obj : list) {
            System.out.println(obj);
        }
    }
}