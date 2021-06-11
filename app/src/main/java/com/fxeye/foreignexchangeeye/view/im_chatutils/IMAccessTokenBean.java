package com.fxeye.foreignexchangeeye.view.im_chatutils;

public class IMAccessTokenBean {
    private String access_token;
    private long expires_in;
    private long getSavedTime;
    private boolean status;
    private String token_type;

    public long getGetSavedTime() {
        return this.getSavedTime;
    }

    public void setGetSavedTime(long j) {
        this.getSavedTime = j;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String str) {
        this.access_token = str;
    }

    public long getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(long j) {
        this.expires_in = j;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setToken_type(String str) {
        this.token_type = str;
    }
}
