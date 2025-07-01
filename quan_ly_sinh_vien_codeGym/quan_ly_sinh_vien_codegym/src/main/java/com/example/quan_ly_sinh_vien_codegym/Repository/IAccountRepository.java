package com.example.quan_ly_sinh_vien_codegym.Repository;

import com.example.quan_ly_sinh_vien_codegym.Entity.Account;

public interface IAccountRepository {
     Account findByUsername(String username);
}
