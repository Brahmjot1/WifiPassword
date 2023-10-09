package com.prowify.wifimanager.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseModel implements Serializable {
    private String status, message, json, token, sideMenuJson, homeDataJson, txnStatus;
    private ArrayList<CategoryModel> homeSlider;
    private ArrayList<CategoryModel> sideMenuList;
    private ArrayList<CategoryModel> homeDataList;
    private ArrayList<CategoryModel> walletList;
    private ArrayList<CategoryModel> withdrawList;
    private ArrayList<CategoryModel> moreApps;
    private CategoryModel homeDiloage;
    private CategoryModel exitDialoge;
    private CategoryModel miniAds;
    private CategoryModel topAds, buttomeAds, flotingAds;
    public ArrayList<CategoryModel> howToEarnList;
    public ArrayList<CategoryModel> notificationList;
    public String earningPoint, referralCode, totalreferralIncome, totalreferrals, homeNote, referralLink, totalRefreal, displayTitle, displayMessage, shareMessage;
    public String shareImage, displayImage, IncomedisplayImage, btnName,indstrialAds,privacyPolicy;
    public ArrayList<CategoryModel> howtowork;
    public String checkSpinNum, lastDate, todayDate, daily_spinner_limit, remain_spin, spinTime, adFailUrl;
    public String point, creditPoint, tigerInApp, topImage, firstName;
    public ArrayList<CategoryModel> data;
    private CategoryModel taskDetails;
    private CategoryModel walletPageButtome;
    String pointValue, isOTPManintance;
    private CategoryModel walletPageTop;
    private String totalPage, totalIteam, currentPage;
    private String appVersion, appUrl, isForupdate, updateMessage, telegamUrl, youtubeUrl, instagramUrl, referImage;

    private  ArrayList<String> googole_appOpenID,googole_bannerID,googole_indstrialID,googole_reawardID,googole_nativeID,lovin_bannerID,lovin_indstrialID,lovin_reawardID;

    public String getIndstrialAds() {
        return indstrialAds;
    }

    public void setIndstrialAds(String indstrialAds) {
        this.indstrialAds = indstrialAds;
    }

    public String getFirstName() {
        return firstName;
    }

    public CategoryModel getMiniAds() {
        return miniAds;
    }

    public void setMiniAds(CategoryModel miniAds) {
        this.miniAds = miniAds;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ArrayList<String> getGoogole_appOpenID() {
        return googole_appOpenID;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public void setGoogole_appOpenID(ArrayList<String> googole_appOpenID) {
        this.googole_appOpenID = googole_appOpenID;
    }

    public ArrayList<CategoryModel> getMoreApps() {
        return moreApps;
    }

    public void setMoreApps(ArrayList<CategoryModel> moreApps) {
        this.moreApps = moreApps;
    }

    public ArrayList<String> getGoogole_bannerID() {
        return googole_bannerID;
    }

    public void setGoogole_bannerID(ArrayList<String> googole_bannerID) {
        this.googole_bannerID = googole_bannerID;
    }

    public ArrayList<String> getGoogole_indstrialID() {
        return googole_indstrialID;
    }

    public void setGoogole_indstrialID(ArrayList<String> googole_indstrialID) {
        this.googole_indstrialID = googole_indstrialID;
    }

    public ArrayList<String> getGoogole_reawardID() {
        return googole_reawardID;
    }

    public void setGoogole_reawardID(ArrayList<String> googole_reawardID) {
        this.googole_reawardID = googole_reawardID;
    }

    public ArrayList<String> getGoogole_nativeID() {
        return googole_nativeID;
    }

    public void setGoogole_nativeID(ArrayList<String> googole_nativeID) {
        this.googole_nativeID = googole_nativeID;
    }

    public ArrayList<String> getLovin_bannerID() {
        return lovin_bannerID;
    }

    public void setLovin_bannerID(ArrayList<String> lovin_bannerID) {
        this.lovin_bannerID = lovin_bannerID;
    }

    public ArrayList<String> getLovin_indstrialID() {
        return lovin_indstrialID;
    }

    public void setLovin_indstrialID(ArrayList<String> lovin_indstrialID) {
        this.lovin_indstrialID = lovin_indstrialID;
    }

    public ArrayList<String> getLovin_reawardID() {
        return lovin_reawardID;
    }

    public void setLovin_reawardID(ArrayList<String> lovin_reawardID) {
        this.lovin_reawardID = lovin_reawardID;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getIsOTPManintance() {
        return isOTPManintance;
    }

    public void setIsOTPManintance(String isOTPManintance) {
        this.isOTPManintance = isOTPManintance;
    }



    public String getReferImage() {
        return referImage;
    }

    public void setReferImage(String referImage) {
        this.referImage = referImage;
    }

    public String getTelegamUrl() {
        return telegamUrl;
    }

    public void setTelegamUrl(String telegamUrl) {
        this.telegamUrl = telegamUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getIsForupdate() {
        return isForupdate;
    }

    public void setIsForupdate(String isForupdate) {
        this.isForupdate = isForupdate;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public String getPointValue() {
        return pointValue;
    }

    public void setPointValue(String pointValue) {
        this.pointValue = pointValue;
    }

    public CategoryModel getFlotingAds() {
        return flotingAds;
    }

    public void setFlotingAds(CategoryModel flotingAds) {
        this.flotingAds = flotingAds;
    }

    public CategoryModel getTopAds() {
        return topAds;
    }

    public void setTopAds(CategoryModel topAds) {
        this.topAds = topAds;
    }

    public CategoryModel getButtomeAds() {
        return buttomeAds;
    }

    public void setButtomeAds(CategoryModel buttomeAds) {
        this.buttomeAds = buttomeAds;
    }

    public CategoryModel getWalletPageButtome() {
        return walletPageButtome;
    }

    public void setWalletPageButtome(CategoryModel walletPageButtome) {
        this.walletPageButtome = walletPageButtome;
    }

    public CategoryModel getWalletPageTop() {
        return walletPageTop;
    }

    public void setWalletPageTop(CategoryModel walletPageTop) {
        this.walletPageTop = walletPageTop;
    }

    private ArrayList<CategoryModel> taskOffers;

    public ArrayList<CategoryModel> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(ArrayList<CategoryModel> withdrawList) {
        this.withdrawList = withdrawList;
    }

    public ArrayList<CategoryModel> getWalletList() {
        return walletList;
    }

    public void setWalletList(ArrayList<CategoryModel> walletList) {
        this.walletList = walletList;
    }

    public ArrayList<CategoryModel> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(ArrayList<CategoryModel> notificationList) {
        this.notificationList = notificationList;
    }

    public String getEarningPoint() {
        return earningPoint;
    }

    public void setEarningPoint(String earningPoint) {
        this.earningPoint = earningPoint;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getTotalreferralIncome() {
        return totalreferralIncome;
    }

    public void setTotalreferralIncome(String totalreferralIncome) {
        this.totalreferralIncome = totalreferralIncome;
    }

    public String getTotalreferrals() {
        return totalreferrals;
    }

    public void setTotalreferrals(String totalreferrals) {
        this.totalreferrals = totalreferrals;
    }

    public String getHomeNote() {
        return homeNote;
    }

    public void setHomeNote(String homeNote) {
        this.homeNote = homeNote;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public void setReferralLink(String referralLink) {
        this.referralLink = referralLink;
    }

    public String getTotalRefreal() {
        return totalRefreal;
    }

    public void setTotalRefreal(String totalRefreal) {
        this.totalRefreal = totalRefreal;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getShareMessage() {
        return shareMessage;
    }

    public void setShareMessage(String shareMessage) {
        this.shareMessage = shareMessage;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public String getIncomedisplayImage() {
        return IncomedisplayImage;
    }

    public void setIncomedisplayImage(String incomedisplayImage) {
        IncomedisplayImage = incomedisplayImage;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public ArrayList<CategoryModel> getHowtowork() {
        return howtowork;
    }

    public void setHowtowork(ArrayList<CategoryModel> howtowork) {
        this.howtowork = howtowork;
    }

    public String getCheckSpinNum() {
        return checkSpinNum;
    }

    public void setCheckSpinNum(String checkSpinNum) {
        this.checkSpinNum = checkSpinNum;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getDaily_spinner_limit() {
        return daily_spinner_limit;
    }

    public void setDaily_spinner_limit(String daily_spinner_limit) {
        this.daily_spinner_limit = daily_spinner_limit;
    }

    public String getRemain_spin() {
        return remain_spin;
    }

    public void setRemain_spin(String remain_spin) {
        this.remain_spin = remain_spin;
    }

    public String getSpinTime() {
        return spinTime;
    }

    public void setSpinTime(String spinTime) {
        this.spinTime = spinTime;
    }

    public String getAdFailUrl() {
        return adFailUrl;
    }

    public void setAdFailUrl(String adFailUrl) {
        this.adFailUrl = adFailUrl;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(String creditPoint) {
        this.creditPoint = creditPoint;
    }

    public String getTigerInApp() {
        return tigerInApp;
    }

    public void setTigerInApp(String tigerInApp) {
        this.tigerInApp = tigerInApp;
    }

    public ArrayList<CategoryModel> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryModel> data) {
        this.data = data;
    }

    public CategoryModel getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(CategoryModel taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalIteam() {
        return totalIteam;
    }

    public void setTotalIteam(String totalIteam) {
        this.totalIteam = totalIteam;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<CategoryModel> getTaskOffers() {
        return taskOffers;
    }

    public void setTaskOffers(ArrayList<CategoryModel> taskOffers) {
        this.taskOffers = taskOffers;
    }

    public ArrayList<CategoryModel> getHowToEarnList() {
        return howToEarnList;
    }

    public void setHowToEarnList(ArrayList<CategoryModel> howToEarnList) {
        this.howToEarnList = howToEarnList;
    }

    public String getSideMenuJson() {
        return sideMenuJson;
    }

    public void setSideMenuJson(String sideMenuJson) {
        this.sideMenuJson = sideMenuJson;
    }

    public String getHomeDataJson() {
        return homeDataJson;
    }

    public void setHomeDataJson(String homeDataJson) {
        this.homeDataJson = homeDataJson;
    }

    public ArrayList<CategoryModel> getSideMenuList() {
        return sideMenuList;
    }

    public void setSideMenuList(ArrayList<CategoryModel> sideMenuList) {
        this.sideMenuList = sideMenuList;
    }

    public CategoryModel getExitDialoge() {
        return exitDialoge;
    }

    public void setExitDialoge(CategoryModel exitDialoge) {
        this.exitDialoge = exitDialoge;
    }

    public CategoryModel getHomeDiloage() {
        return homeDiloage;
    }

    public void setHomeDiloage(CategoryModel homeDiloage) {
        this.homeDiloage = homeDiloage;
    }

    public ArrayList<CategoryModel> getHomeDataList() {
        return homeDataList;
    }

    public void setHomeDataList(ArrayList<CategoryModel> homeDataList) {
        this.homeDataList = homeDataList;
    }

    public ArrayList<CategoryModel> getHomeSlider() {
        return homeSlider;
    }

    public void setHomeSlider(ArrayList<CategoryModel> homeSlider) {
        this.homeSlider = homeSlider;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
