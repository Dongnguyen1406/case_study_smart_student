package com.example.quan_ly_sinh_vien_codegym.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncodeUtil {
    public static String encode (String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static Boolean check (String password, String encodedPassword){
        return BCrypt.checkpw(password, encodedPassword);
    }
}
