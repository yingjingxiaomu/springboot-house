package com.jk.service.login;

import com.jk.mapper.login.LoginMapper;

import com.jk.model.staff.EmpBean;
import com.jk.utils.ConBean;
import com.jk.utils.HttpClient;
import com.jk.utils.Md5Util;
import com.jk.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送手机验证码
     * @param loginnumber
     * @return
     */
    @Override
    public String getVerification(String loginnumber) {

            ValueOperations value = redisTemplate.opsForValue();

            Object o = value.get(loginnumber + ConBean.REDIS_CACHING_INDENTITY_CHECKED);

            if(o==null){
                //发送验证码
                String url= ConBean.SMS_URL;

                HashMap<String, Object> params = new HashMap<String, Object>();

                params.put("accountSid",ConBean.ACCOUNT_SID);

                params.put("templateid",ConBean.TMPLATE_ID);

                int random = (int)((Math.random()*9+1)*100000);

                params.put("param",random+ConBean.REDIS_CACHING_TINE);

                params.put("to",loginnumber);

                params.put("timestamp", TimeUtil.dateTostr(new Date(),"yyyyMMddHHmmss"));

                params.put("sig", Md5Util.get32(params.get("accountSid").toString()+ConBean.TO_KEN+params.get("timestamp").toString()));

                String s = HttpClient.post(url, params);

                value.set(loginnumber+ConBean.REDIS_CACHING_INDENTITY,random,5, TimeUnit.MINUTES);

                value.set(loginnumber+ConBean.REDIS_CACHING_INDENTITY_CHECKED,random,1, TimeUnit.MINUTES);

                System.out.println("redis中的缓存"+value.get(loginnumber+ConBean.REDIS_CACHING_INDENTITY));

                return random + "";

            }else{
                System.out.println("被拦截了哦");
                return ConBean.RETURN_MESSAGE_ONE;
            }

        }

    /**
     * 查询数据库返回单条数据
     * @param loginnumber
     * @return
     */
    @Override
    public EmpBean getlogin(String loginnumber) {
        return loginMapper.getlogin(loginnumber);
    }

    /**
     * 登录验证码效验
     * @param verification
     * @param loginnumber
     * @return
     */
    @Override
    public String getVerificationTwo(String verification, String loginnumber) {

        ValueOperations value = redisTemplate.opsForValue();

        System.out.println("redis中的缓存"+value.get(loginnumber+ConBean.REDIS_CACHING_INDENTITY));

        String s = value.get(loginnumber + ConBean.REDIS_CACHING_INDENTITY).toString();

        if(verification.toString().equals(s)){

            return  ConBean.NUMBERONE;

        }else{

            return  ConBean.NUMBERTWO;

        }
    }
}
