package com.jk.service.login;


import com.jk.model.staff.EmpBean;

public interface LoginService {

    String getVerification(String loginnumber);

    EmpBean getlogin(String loginnumber);

    String getVerificationTwo(String verification, String loginnumber);

}
