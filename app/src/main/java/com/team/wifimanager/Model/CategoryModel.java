package com.team.wifimanager.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CategoryModel implements Parcelable {
    String image,images, id, title, jsonImage, url, screenNo,isBorder, btnName, btnColor, btnTextColor, sliderIndex, bgColor, lable, lableColor, offerId, gameId, taskId, isActive, startDate, endDate, entryDate;
    String isLoginNeed,titleColor,isBlink,icone,type,isViewAll,isCardView,icon,points,description,displayImage,packagename,isForce,appOpen,txtButtone1,isNewLable;
    String tagList,isShowDetails,hint,hintName;
    int earningPoint;
    ArrayList<CategoryModel>subMenu,data;
    private String stapes,tnc,status,number,indexId,isNew,bgImage,isDeliverd;
    private String payoutpoints,youtubeImage,youtubeLink,refrealCode,campaignId,companyName;
    private String payout,isPayoutOffer,note,tIndex,isTrending,isHighPoint,isProTask,isDepositTask;
    private String taskType,groupNo,isReferPointEnable,updateDate,imageUploadTitle,isImageUpload;
    private String block_id,block_points,block_bg,campaignName;
    private String earning_type,settleAmount,amount;
    private String couponeCode,txnID,emailID,mobileNo,comment;
    private String minPoint,isMultipleUsed,txtTypeID,details,regxPatten;
    private String date,clickUrl,referImage;
    private String dicountLable,offerDescription,bannerType,couponCode,rating,discountPrice,orignalPrice,cashback;

    protected CategoryModel(Parcel in) {
        image = in.readString();
        images = in.readString();
        id = in.readString();
        title = in.readString();
        jsonImage = in.readString();
        url = in.readString();
        screenNo = in.readString();
        isBorder = in.readString();
        btnName = in.readString();
        btnColor = in.readString();
        btnTextColor = in.readString();
        sliderIndex = in.readString();
        bgColor = in.readString();
        lable = in.readString();
        lableColor = in.readString();
        offerId = in.readString();
        gameId = in.readString();
        taskId = in.readString();
        isActive = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        entryDate = in.readString();
        isLoginNeed = in.readString();
        titleColor = in.readString();
        isBlink = in.readString();
        icone = in.readString();
        type = in.readString();
        isViewAll = in.readString();
        isCardView = in.readString();
        icon = in.readString();
        points = in.readString();
        description = in.readString();
        displayImage = in.readString();
        packagename = in.readString();
        isForce = in.readString();
        appOpen = in.readString();
        txtButtone1 = in.readString();
        isNewLable = in.readString();
        tagList = in.readString();
        isShowDetails = in.readString();
        hint = in.readString();
        hintName = in.readString();
        earningPoint = in.readInt();
        subMenu = in.createTypedArrayList(CategoryModel.CREATOR);
        data = in.createTypedArrayList(CategoryModel.CREATOR);
        stapes = in.readString();
        tnc = in.readString();
        status = in.readString();
        number = in.readString();
        indexId = in.readString();
        isNew = in.readString();
        bgImage = in.readString();
        payoutpoints = in.readString();
        youtubeImage = in.readString();
        youtubeLink = in.readString();
        refrealCode = in.readString();
        campaignId = in.readString();
        companyName = in.readString();
        payout = in.readString();
        isPayoutOffer = in.readString();
        note = in.readString();
        tIndex = in.readString();
        isTrending = in.readString();
        isHighPoint = in.readString();
        isProTask = in.readString();
        isDepositTask = in.readString();
        taskType = in.readString();
        groupNo = in.readString();
        isReferPointEnable = in.readString();
        updateDate = in.readString();
        imageUploadTitle = in.readString();
        isImageUpload = in.readString();
        block_id = in.readString();
        block_points = in.readString();
        block_bg = in.readString();
        campaignName = in.readString();
        earning_type = in.readString();
        settleAmount = in.readString();
        amount = in.readString();
        couponeCode = in.readString();
        txnID = in.readString();
        emailID = in.readString();
        mobileNo = in.readString();
        comment = in.readString();
        minPoint = in.readString();
        isMultipleUsed = in.readString();
        txtTypeID = in.readString();
        details = in.readString();
        regxPatten = in.readString();
        date = in.readString();
        clickUrl = in.readString();
        dicountLable = in.readString();
        offerDescription = in.readString();
        bannerType = in.readString();
        couponCode = in.readString();
        rating = in.readString();
        discountPrice = in.readString();
        orignalPrice = in.readString();
        cashback = in.readString();
        isDeliverd = in.readString();
        referImage = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getReferImage() {
        return referImage;
    }

    public void setReferImage(String referImage) {
        this.referImage = referImage;
    }

    public String getIsDeliverd() {
        return isDeliverd;
    }

    public void setIsDeliverd(String isDeliverd) {
        this.isDeliverd = isDeliverd;
    }

    public String getDicountLable() {
        return dicountLable;
    }

    public void setDicountLable(String dicountLable) {
        this.dicountLable = dicountLable;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(String orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public String getCashback() {
        return cashback;
    }

    public void setCashback(String cashback) {
        this.cashback = cashback;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getRegxPatten() {
        return regxPatten;
    }

    public void setRegxPatten(String regxPatten) {
        this.regxPatten = regxPatten;
    }

    public String getHintName() {
        return hintName;
    }

    public void setHintName(String hintName) {
        this.hintName = hintName;
    }

    public String getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(String minPoint) {
        this.minPoint = minPoint;
    }

    public String getIsMultipleUsed() {
        return isMultipleUsed;
    }

    public void setIsMultipleUsed(String isMultipleUsed) {
        this.isMultipleUsed = isMultipleUsed;
    }

    public String getTxtTypeID() {
        return txtTypeID;
    }

    public void setTxtTypeID(String txtTypeID) {
        this.txtTypeID = txtTypeID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCouponeCode() {
        return couponeCode;
    }

    public void setCouponeCode(String couponeCode) {
        this.couponeCode = couponeCode;
    }

    public String getTxnID() {
        return txnID;
    }

    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEarning_type() {
        return earning_type;
    }

    public void setEarning_type(String earning_type) {
        this.earning_type = earning_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount;
    }

    public int getEarningPoint() {
        return earningPoint;
    }

    public void setEarningPoint(int earningPoint) {
        this.earningPoint = earningPoint;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getBlock_points() {
        return block_points;
    }

    public void setBlock_points(String block_points) {
        this.block_points = block_points;
    }

    public String getBlock_bg() {
        return block_bg;
    }

    public void setBlock_bg(String block_bg) {
        this.block_bg = block_bg;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getStapes() {
        return stapes;
    }

    public void setStapes(String stapes) {
        this.stapes = stapes;
    }
    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getPayoutpoints() {
        return payoutpoints;
    }

    public void setPayoutpoints(String payoutpoints) {
        this.payoutpoints = payoutpoints;
    }

    public String getYoutubeImage() {
        return youtubeImage;
    }

    public void setYoutubeImage(String youtubeImage) {
        this.youtubeImage = youtubeImage;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getRefrealCode() {
        return refrealCode;
    }

    public void setRefrealCode(String refrealCode) {
        this.refrealCode = refrealCode;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public String getIsPayoutOffer() {
        return isPayoutOffer;
    }

    public void setIsPayoutOffer(String isPayoutOffer) {
        this.isPayoutOffer = isPayoutOffer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String gettIndex() {
        return tIndex;
    }

    public void settIndex(String tIndex) {
        this.tIndex = tIndex;
    }

    public String getIsTrending() {
        return isTrending;
    }

    public void setIsTrending(String isTrending) {
        this.isTrending = isTrending;
    }

    public String getIsHighPoint() {
        return isHighPoint;
    }

    public void setIsHighPoint(String isHighPoint) {
        this.isHighPoint = isHighPoint;
    }

    public String getIsProTask() {
        return isProTask;
    }

    public void setIsProTask(String isProTask) {
        this.isProTask = isProTask;
    }

    public String getIsDepositTask() {
        return isDepositTask;
    }

    public void setIsDepositTask(String isDepositTask) {
        this.isDepositTask = isDepositTask;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getIsReferPointEnable() {
        return isReferPointEnable;
    }

    public void setIsReferPointEnable(String isReferPointEnable) {
        this.isReferPointEnable = isReferPointEnable;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getImageUploadTitle() {
        return imageUploadTitle;
    }

    public void setImageUploadTitle(String imageUploadTitle) {
        this.imageUploadTitle = imageUploadTitle;
    }

    public String getIsImageUpload() {
        return isImageUpload;
    }

    public void setIsImageUpload(String isImageUpload) {
        this.isImageUpload = isImageUpload;
    }

    public String getTxtButtone1() {
        return txtButtone1;
    }

    public void setTxtButtone1(String txtButtone1) {
        this.txtButtone1 = txtButtone1;
    }

    public String getIsNewLable() {
        return isNewLable;
    }

    public void setIsNewLable(String isNewLable) {
        this.isNewLable = isNewLable;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public String getIsShowDetails() {
        return isShowDetails;
    }

    public void setIsShowDetails(String isShowDetails) {
        this.isShowDetails = isShowDetails;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getIsForce() {
        return isForce;
    }

    public void setIsForce(String isForce) {
        this.isForce = isForce;
    }

    public String getAppOpen() {
        return appOpen;
    }

    public void setAppOpen(String appOpen) {
        this.appOpen = appOpen;
    }

    public ArrayList<CategoryModel> getData() {
        return data;
    }

    public String getIsBorder() {
        return isBorder;
    }

    public String getImages() {
        return images;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setIsBorder(String isBorder) {
        this.isBorder = isBorder;
    }

    public void setData(ArrayList<CategoryModel> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsViewAll() {
        return isViewAll;
    }

    public void setIsViewAll(String isViewAll) {
        this.isViewAll = isViewAll;
    }

    public String getIsCardView() {
        return isCardView;
    }

    public void setIsCardView(String isCardView) {
        this.isCardView = isCardView;
    }

    public ArrayList<CategoryModel> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(ArrayList<CategoryModel> subMenu) {
        this.subMenu = subMenu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJsonImage() {
        return jsonImage;
    }

    public void setJsonImage(String jsonImage) {
        this.jsonImage = jsonImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(String screenNo) {
        this.screenNo = screenNo;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(String btnColor) {
        this.btnColor = btnColor;
    }

    public String getBtnTextColor() {
        return btnTextColor;
    }

    public void setBtnTextColor(String btnTextColor) {
        this.btnTextColor = btnTextColor;
    }

    public String getSliderIndex() {
        return sliderIndex;
    }

    public void setSliderIndex(String sliderIndex) {
        this.sliderIndex = sliderIndex;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getLableColor() {
        return lableColor;
    }

    public void setLableColor(String lableColor) {
        this.lableColor = lableColor;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsLoginNeed() {
        return isLoginNeed;
    }

    public void setIsLoginNeed(String isLoginNeed) {
        this.isLoginNeed = isLoginNeed;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getIsBlink() {
        return isBlink;
    }

    public void setIsBlink(String isBlink) {
        this.isBlink = isBlink;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(images);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(jsonImage);
        dest.writeString(url);
        dest.writeString(screenNo);
        dest.writeString(isBorder);
        dest.writeString(btnName);
        dest.writeString(btnColor);
        dest.writeString(btnTextColor);
        dest.writeString(sliderIndex);
        dest.writeString(bgColor);
        dest.writeString(lable);
        dest.writeString(lableColor);
        dest.writeString(offerId);
        dest.writeString(gameId);
        dest.writeString(taskId);
        dest.writeString(isActive);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(entryDate);
        dest.writeString(isLoginNeed);
        dest.writeString(titleColor);
        dest.writeString(isBlink);
        dest.writeString(icone);
        dest.writeString(type);
        dest.writeString(isViewAll);
        dest.writeString(isCardView);
        dest.writeString(icon);
        dest.writeString(points);
        dest.writeString(description);
        dest.writeString(displayImage);
        dest.writeString(packagename);
        dest.writeString(isForce);
        dest.writeString(appOpen);
        dest.writeString(txtButtone1);
        dest.writeString(isNewLable);
        dest.writeString(tagList);
        dest.writeString(isShowDetails);
        dest.writeString(hint);
        dest.writeString(hintName);
        dest.writeInt(earningPoint);
        dest.writeTypedList(subMenu);
        dest.writeTypedList(data);
        dest.writeString(stapes);
        dest.writeString(tnc);
        dest.writeString(status);
        dest.writeString(number);
        dest.writeString(indexId);
        dest.writeString(isNew);
        dest.writeString(bgImage);
        dest.writeString(isDeliverd);
        dest.writeString(payoutpoints);
        dest.writeString(youtubeImage);
        dest.writeString(youtubeLink);
        dest.writeString(refrealCode);
        dest.writeString(campaignId);
        dest.writeString(companyName);
        dest.writeString(payout);
        dest.writeString(isPayoutOffer);
        dest.writeString(note);
        dest.writeString(tIndex);
        dest.writeString(isTrending);
        dest.writeString(isHighPoint);
        dest.writeString(isProTask);
        dest.writeString(isDepositTask);
        dest.writeString(taskType);
        dest.writeString(groupNo);
        dest.writeString(isReferPointEnable);
        dest.writeString(updateDate);
        dest.writeString(imageUploadTitle);
        dest.writeString(isImageUpload);
        dest.writeString(block_id);
        dest.writeString(block_points);
        dest.writeString(block_bg);
        dest.writeString(campaignName);
        dest.writeString(earning_type);
        dest.writeString(settleAmount);
        dest.writeString(amount);
        dest.writeString(couponeCode);
        dest.writeString(txnID);
        dest.writeString(emailID);
        dest.writeString(mobileNo);
        dest.writeString(comment);
        dest.writeString(minPoint);
        dest.writeString(isMultipleUsed);
        dest.writeString(txtTypeID);
        dest.writeString(details);
        dest.writeString(regxPatten);
        dest.writeString(date);
        dest.writeString(clickUrl);
        dest.writeString(dicountLable);
        dest.writeString(offerDescription);
        dest.writeString(bannerType);
        dest.writeString(couponCode);
        dest.writeString(rating);
        dest.writeString(discountPrice);
        dest.writeString(orignalPrice);
        dest.writeString(cashback);
        dest.writeString(referImage);
    }
}
