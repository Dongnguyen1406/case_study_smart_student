package com.example.quan_ly_sinh_vien_codegym.Service;

import com.example.quan_ly_sinh_vien_codegym.Entity.Account;

public interface IAccountService {
    public Account findByUsername(String username);
    Account checkLogin(String username, String password);
}
