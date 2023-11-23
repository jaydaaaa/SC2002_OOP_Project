package entity;
public class Enquiry {
    private String campName;
    private String enquiryText;
    private String enquiryBy;
    private String replyText;
    private String replyBy;
    private boolean status;

    public Enquiry(String enquiryText, String campName,String enquiryBy,String replyText, String replyBy, boolean status) {
        this.enquiryText = enquiryText;
        this.campName = campName;
        this.enquiryBy = enquiryBy;
        this.replyText = replyText;
        this.replyBy = replyBy;
        this.status = status;
    }

    public String getEnquiryText() {
        return enquiryText;
    }

    public String getEnquiryBy() {
        return enquiryBy;
    }

    public String getReplyText() {
        return replyText;
    }

    public String getReplyBy() {
        return replyBy;
    }

    public boolean getStatus() {
        return status;
    }
    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }
    public void setEnquiryBy(String enquiryBy) {
        this.enquiryBy = enquiryBy;
    }
    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    public void setReplyBy(String replyBy) {
        this.replyBy = replyBy;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}

