package tn.supcom.bricolini;

public class Request {
    private String jobId ;
    private String clientId ;
    private String techID ;
    private String expirationDate ;
    private String details ;
    private String state ;
    private String requestId ;

    public Request(){

    }

    public Request(String jobId, String clientId, String techID, String expirationDate, String details, String state, String requestId) {
        this.jobId = jobId;
        this.clientId = clientId;
        this.techID = techID;
        this.expirationDate = expirationDate;
        this.details = details;
        this.state = state;
        this.requestId = requestId;
    }

    public String getTechID() {
        return techID;
    }

    public void setTechID(String techID) {
        this.techID = techID;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
