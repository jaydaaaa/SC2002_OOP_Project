package entity;

import java.util.UUID;

/**
 * The Enquiry class represents an enquiry by a student.
 * It contains information such as enquiry text, camp ID, enquired by, reply text,
 * replied by, status, and enquiry ID.
 * @author Group 2
 * @since 2023-11-20
 */
public class Enquiry {

    /**
     * Enquiry of that specific campID.
     */
    private String campID;
    /**
     * The enquiry text.
     */
    private String enquiryText;
    /**
     * Name of student
     */
    private String enquiryBy;
    /**
     * Enquiry reply text.
     */
    private String replyText;
    /**
     * Name of staff.
     */
    private String replyBy;
    /**
     * Whether enquiry is pending or replied
     */
    private boolean status;
    /**
     * EnquiryID
     */
    private String enquiryID;

    /**
     * Constructs a new Enquiry object with the specified details.
     *
     * @param enquiryText The text of the enquiry.
     * @param campID      The camp ID associated with the enquiry.
     * @param enquiryBy   The student who made the enquiry.
     * @param replyText   The text of the reply.
     * @param replyBy     The staff who provided the reply.
     * @param status      The status of the enquiry.
     * @param enquiryID   The enquiryID.
     */
    public Enquiry(String enquiryText, String campID, String enquiryBy, String replyText, String replyBy,
                   boolean status, String enquiryID) {
        this.enquiryText = enquiryText;
        this.campID = campID;
        this.enquiryBy = enquiryBy;
        this.replyText = replyText;
        this.replyBy = replyBy;
        this.status = status;
        if (enquiryID.equals("")) {
            this.enquiryID = UUID.randomUUID().toString();
        } else {
            this.enquiryID = enquiryID;
        }
    }

    /**
     * Gets the text of the enquiry.
     * @return The text of the enquiry.
     */
    public String getEnquiryText() {
        return enquiryText;
    }

    /**
     * Gets the campaign ID associated with the enquiry.
     * @return The camp ID.
     */
    public String getCampID() {
        return this.campID;
    }

    /**
     * Gets the student who made the enquiry.
     * @return The student who made the enquiry.
     */
    public String getEnquiryBy() {
        return enquiryBy;
    }

    /**
     * Gets the text of the reply.
     * @return The text of the reply.
     */
    public String getReplyText() {
        return replyText;
    }

    /**
     * Gets the staff who provided the reply.
     * @return The staff who provided the reply.
     */
    public String getReplyBy() {
        return replyBy;
    }

    /**
     * Gets the enquiryID
     * @return The enquiry ID.
     */
    public String getEnquiryID() {
        return this.enquiryID;
    }

    /**
     * Gets the status of the enquiry.
     * @return The status of the enquiry.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the text of the enquiry.
     * @param enquiryText The text of the enquiry.
     */
    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }

    /**
     * Sets the camp ID associated with the enquiry.
     * @param campID The new camp ID to set.
     */
    public void setCampID(String campID) {
        this.campID = campID;
    }

    /**
     * Sets the student who made the enquiry.
     * @param enquiryBy The student who made the enquiry.
     */
    public void setEnquiryBy(String enquiryBy) {
        this.enquiryBy = enquiryBy;
    }

    /**
     * Sets the text of the reply and the staff who provided the reply.
     * Also sets the status to true, indicating a replied status.
     * @param replyText The new text of the reply.
     * @param replyBy   The new user who provided the reply.
     */
    public void setReplyText(String replyText, String replyBy) {
        this.replyText = replyText;
        this.replyBy = replyBy;
        this.status = true;
    }

    /**
     * Sets the staff who provided the reply.
     * @param replyBy The new user who provided the reply.
     */
    public void setReplyBy(String replyBy) {
        this.replyBy = replyBy;
    }

    /**
     * Sets the status of the enquiry.
     * @param status The new status to set.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
