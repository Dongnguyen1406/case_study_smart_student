package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;

public interface IAccountService {
     Account findByUsername(String username);
    Account checkLogin(String username, String password);
    void updatePassword(int Id, String encodePassword);
}
