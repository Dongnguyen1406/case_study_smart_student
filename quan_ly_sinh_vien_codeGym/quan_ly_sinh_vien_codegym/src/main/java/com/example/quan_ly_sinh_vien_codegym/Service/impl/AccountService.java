package com.example.quan_ly_sinh_vien_codegym.Service.impl;

import com.example.quan_ly_sinh_vien_codegym.Entity.Account;
import com.example.quan_ly_sinh_vien_codegym.Repository.IAccountRepository;
import com.example.quan_ly_sinh_vien_codegym.Repository.impl.AccountRepository;
import com.example.quan_ly_sinh_vien_codegym.Service.IAccountService;
import com.example.quan_ly_sinh_vien_codegym.util.PasswordEncodeUtil;

public class AccountService implements IAccountService {
    private IAccountRepository iAccountRepository= new AccountRepository();
    @Override
    public Account findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public Account checkLogin(String username, String password) {
        Account account = iAccountRepository.findByUsername(username);
        if(account != null && PasswordEncodeUtil.check(password, account.getPassword())){
            account.setPassword(null);
            return account;
        }
        return null;
    }
}
