package com.example.quan_ly_sinh_vien_codegym.repository;

import java.util.List;

public interface IRepository <T>{
    List<T> findAll();
}
