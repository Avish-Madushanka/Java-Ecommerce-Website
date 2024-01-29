package com.entity;

import com.DAO.FeedbackDAO;

public class FeedbackDtls{

    public FeedbackDtls() {
        super();
    }

    int fid;
    String feedname;
    String feedemail;
    String feedmessage;

    public FeedbackDtls(String feedname, String feedemail, String feedmessage) {
        this.feedname = feedname;
        this.feedemail = feedemail;
        this.feedmessage = feedmessage;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    public String getFeedemail() {
        return feedemail;
    }

    public void setFeedemail(String feedemail) {
        this.feedemail = feedemail;
    }

    public String getFeedmessage() {
        return feedmessage;
    }

    public void setFeedmessage(String feedmessage) {
        this.feedmessage = feedmessage;
    }
}