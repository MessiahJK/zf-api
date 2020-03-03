/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

import static us.betahouse.zfapi.zfhelper.constant.EventValue.*;

/**
 * @author MessiahJK
 * @version : LoginData.java 2020/02/21 22:04 MessiahJK
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends CommonRequest {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份
     */
    private String identity;

    public Map<String,String> toMap(){
        Map<String,String> map=new HashMap<>(8);
        map.put(VIEW_STATE,viewState);
        map.put(EVENT_VALIDATION,eventValidation);
        map.put(LOGIN_USER_ID,userId);
        map.put(LOGIN_PASSWORD,password);
        map.put(LOGIN_IDENTITY, identity);
        //"%D1%A7%C9%FA"
        map.put(LOGIN_BUTTON,"");
        return map;
    }
}
