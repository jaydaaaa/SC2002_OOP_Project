public class Enquiry {

    private String enquiryText;
    private String enquiryBy;
    private String replyText;
    private String replyBy;
    private boolean status;

    public Enquiry(String enquiryText, String enquiryBy, String replyText, String replyBy, boolean status) {
        this.enquiryText = enquiryText;
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

}
