/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean;

import lombok.Data;

/**
 * @author MessiahJK
 * @version : CommonRequest.java 2020/02/22 1:44 MessiahJK
 */
@Data
public class CommonRequest {

    /**
     * 视图状态
     */
    protected String viewState;
    /**
     * 事件验证
     */
    protected String eventValidation;
    /**
     * 事件理由/主题
     */
    protected String eventArgument;
    /**
     * 最后焦点
     */
    protected String lastFocus;
    /**
     * 事件目标
     */
    protected String eventTarget;
}
