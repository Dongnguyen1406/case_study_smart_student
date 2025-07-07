package com.example.quan_ly_sinh_vien_codegym.repository;

import java.util.List;

public interface IRepository <T>{
    List<T> findAll();
    T select(int id);
    boolean update(T t);
    boolean delete(String id);
    boolean deleteById(int id);
    void add(T t);
}
