/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.constant;

import java.io.UnsupportedEncodingException;

/**
 * @author MessiahJK
 * @version : IdentityType.java 2020/02/22 2:24 MessiahJK
 */
public  enum IdentityType {
    /**
     * 用于验证身份信息
     */
    IDENTITY_STUDENT("学生"),
    IDENTITY_TEACHER("老师"),
    IDENTITY_DEPARTMENT("部门"),
    IDENTITY_VISITOR("访客")
    ;


    private String value;

    IdentityType(String value){
        try {
            this.value=new String(value.getBytes("gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}
