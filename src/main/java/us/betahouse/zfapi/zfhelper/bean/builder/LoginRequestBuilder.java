/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean.builder;

import us.betahouse.zfapi.zfhelper.bean.LoginRequest;

/**
 * @author MessiahJK
 * @version : LoginDataBuilder.java 2020/02/21 22:27 MessiahJK
 */
public final class LoginRequestBuilder {
    private String viewState;
    private String eventValidation;
    private String userId;
    private String password;
    private String identity;

    private LoginRequestBuilder() {
    }

    public static LoginRequestBuilder aLoginData() {
        return new LoginRequestBuilder();
    }

    public LoginRequestBuilder withViewState(String viewState) {
        this.viewState = viewState;
        return this;
    }

    public LoginRequestBuilder withEventValidation(String eventValidation) {
        this.eventValidation = eventValidation;
        return this;
    }

    public LoginRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LoginRequestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginRequestBuilder withIdentity(String identity) {
        this.identity = identity;
        return this;
    }

    public LoginRequest build() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setViewState(viewState);
        loginRequest.setEventValidation(eventValidation);
        loginRequest.setUserId(userId);
        loginRequest.setPassword(password);
        loginRequest.setIdentity(identity);
        return loginRequest;
    }
}
