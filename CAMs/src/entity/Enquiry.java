package entity;

import java.util.UUID;

public class Enquiry {
    private String campID;
    private String enquiryText;
    private String enquiryBy;
    private String replyText;
    private String replyBy;
    private boolean status;
    private String enquiryID;

    public Enquiry(String enquiryText, String campID,String enquiryBy,String replyText, String replyBy,
                   boolean status, String enquiryID) {
        this.enquiryText = enquiryText;
        this.campID = campID;
        this.enquiryBy = enquiryBy;
        this.replyText = replyText;
        this.replyBy = replyBy;
        this.status = status;
        if (enquiryID.equals("")) {
            this.enquiryID =  UUID.randomUUID().toString();
        } else {
            this.enquiryID = enquiryID;
        }
    }

    public String getEnquiryText() {
        return enquiryText;
    }

    public String getCampID() { return this.campID; }


    public String getEnquiryBy() {
        return enquiryBy;
    }

    public String getReplyText() {
        return replyText;
    }

    public String getReplyBy() {
        return replyBy;
    }

    public String getEnquiryID() {
        return this.enquiryID;
    }

    public boolean getStatus() {
        return status;
    }
    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }

    public void setCampID(String campID) {
        this.campID = campID;
    }
    public void setEnquiryBy(String enquiryBy) {
        this.enquiryBy = enquiryBy;
    }
    public void setReplyText(String replyText, String replyBy) {
        this.replyText = replyText;
        this.replyBy = replyBy;
        this.status = true;
    }
    public void setReplyBy(String replyBy) {
        this.replyBy = replyBy;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}

