package com.fxeye.foreignexchangeeye.entity.international_my;

import java.io.Serializable;

public class NewUser implements Serializable {
    private String IDNumber;
    private String areaCode;
    private String areaFlag;
    private int authStatus;
    private String avatar;
    private String balance;
    private String email;
    private String gender;
    private String identity;

    /* renamed from: ip */
    private String f2255ip;
    private boolean isCertified;
    private boolean isDefaultAvatar;
    private boolean isDefaultNick;
    private boolean isEmailConfirmed;
    private boolean isLifeVip;
    private boolean isPhoneConfirmed;
    private Object lastName;
    private String mdp;
    private String nick;
    private String password;
    private String phone;
    private String realName;
    private int remainViews;
    private int roleType;
    private int shoppingAddressCount;
    private String spentAmount;
    private int status;
    private int type;
    private String userId;
    private String vipExpiredAt;

    public int getShoppingAddressCount() {
        return this.shoppingAddressCount;
    }

    public void setShoppingAddressCount(int i) {
        this.shoppingAddressCount = i;
    }

    public String getAreaFlag() {
        return this.areaFlag;
    }

    public void setAreaFlag(String str) {
        this.areaFlag = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String str) {
        this.identity = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String str) {
        this.mdp = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public boolean isIsDefaultAvatar() {
        return this.isDefaultAvatar;
    }

    public void setIsDefaultAvatar(boolean z) {
        this.isDefaultAvatar = z;
    }

    public boolean isIsDefaultNick() {
        return this.isDefaultNick;
    }

    public void setIsDefaultNick(boolean z) {
        this.isDefaultNick = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getIp() {
        return this.f2255ip;
    }

    public void setIp(String str) {
        this.f2255ip = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public boolean isIsCertified() {
        return this.isCertified;
    }

    public void setIsCertified(boolean z) {
        this.isCertified = z;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String str) {
        this.realName = str;
    }

    public String getIDNumber() {
        return this.IDNumber;
    }

    public void setIDNumber(String str) {
        this.IDNumber = str;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public String getSpentAmount() {
        return this.spentAmount;
    }

    public void setSpentAmount(String str) {
        this.spentAmount = str;
    }

    public String getVipExpiredAt() {
        return this.vipExpiredAt;
    }

    public void setVipExpiredAt(String str) {
        this.vipExpiredAt = str;
    }

    public int getRemainViews() {
        return this.remainViews;
    }

    public void setRemainViews(int i) {
        this.remainViews = i;
    }

    public boolean isIsLifeVip() {
        return this.isLifeVip;
    }

    public void setIsLifeVip(boolean z) {
        this.isLifeVip = z;
    }

    public int getRoleType() {
        return this.roleType;
    }

    public void setRoleType(int i) {
        this.roleType = i;
    }

    public int getAuthStatus() {
        return this.authStatus;
    }

    public void setAuthStatus(int i) {
        this.authStatus = i;
    }

    public boolean isIsEmailConfirmed() {
        return this.isEmailConfirmed;
    }

    public void setIsEmailConfirmed(boolean z) {
        this.isEmailConfirmed = z;
    }

    public boolean isIsPhoneConfirmed() {
        return this.isPhoneConfirmed;
    }

    public void setIsPhoneConfirmed(boolean z) {
        this.isPhoneConfirmed = z;
    }

    public Object getLastName() {
        return this.lastName;
    }

    public void setLastName(Object obj) {
        this.lastName = obj;
    }
}
