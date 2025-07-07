package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;

public interface IAccountRepository {
     Account findByUsername(String username);
     void updatePassword(int Id, String encodePassword);
}
