package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.repository.IAccountRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.AccountRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IAccountService;
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
        if(account != null ){//&& PasswordEncodeUtil.check(password, account.getPassword())){
//            account.setPassword(null);
            return account;
        }
        return null;
    }

    @Override
    public void updatePassword(int id, String encodePassword) {
        iAccountRepository.updatePassword(id,encodePassword);
    }
}
