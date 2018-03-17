package com.jk.controller.login;


import com.jk.model.staff.EmpBean;
import com.jk.service.login.LoginService;

import com.jk.utils.ConBean;
import com.jk.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * getVerification
     * 获取手机验证码
     */
    @RequestMapping(value="getVerification")
    @ResponseBody
    public Map<String, Object> getVerification(EmpBean e) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        if (e != null) {
            EmpBean newuser = loginService.getlogin(e.getLoginnumber());
            if (newuser != null) {
                if (newuser.getLoginnumber() != null) {

                    String checkCode=loginService.getVerification(newuser.getLoginnumber());
                    if(checkCode.equals(ConBean.RETURN_MESSAGE_ONE)){
                        map.put("msg",ConBean.RETURN_MESSAGE_ONE);
                        map.put("success", false);
                        return map;
                    }
                    map.put("msg",checkCode);
                    map.put("success", true);
                    return map;
                }
            } else {
                map.put("msg", "请输入正确的用户账号！");
                map.put("success", false);
                return map;
            }

        }
        return null;
    }

    /**
     * 登录验证用户名密码与短信验证码
     * @param e 员工
     * @param verification 验证码
     * @param request
     * @param session
     * @return map
     */
    @RequestMapping(value="getlogin")
    @ResponseBody
    public Map<String, Object> getlogin(EmpBean e, String verification, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        String lpwd = e.getPassword();
        String s = Md5Util.get32(lpwd);

        if (e != null) {
            EmpBean newuser = loginService.getlogin(e.getLoginnumber());
            if (newuser != null) {
                if (newuser.getPassword().equals(s)) {

                    if(verification!=null&&verification!=""){
                        String yesNo=loginService.getVerificationTwo(verification,e.getLoginnumber());
                        if(yesNo.equals("1")){
                            HttpSession sessions = request.getSession();
                            sessions.setAttribute(session.getId(),newuser);
                            map.put("success", true);
                        }else{
                            map.put("success", false);
                            map.put("msg", "验证码错误！");
                            return map;
                        }
                    }else{
                        map.put("success", false);
                        map.put("msg", "请填写验证码");
                        return map;
                    }
                }else{
                    map.put("success", false);
                    map.put("msg", "密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("msg", "用户不存在！");
            }
            return map;
        }
        return null;
    }


}
