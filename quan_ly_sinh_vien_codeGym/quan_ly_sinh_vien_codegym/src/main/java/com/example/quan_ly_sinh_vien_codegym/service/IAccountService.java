package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;

public interface IAccountService {
    public Account findByUsername(String username);
    Account checkLogin(String username, String password);
}
