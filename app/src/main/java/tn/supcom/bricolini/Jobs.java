package tn.supcom.bricolini;

public class Jobs {
    String jobId ;
    String technicianId ;
    String service ;
    String description ;
    float rate ;
    int jobCount ;

    public Jobs() {
    }

    public Jobs(String jobId, String technicianId, String service, String description, float rate, int jobCount) {
        this.jobId = jobId;
        this.technicianId = technicianId;
        this.service = service;
        this.description = description;
        this.rate = rate;
        this.jobCount = jobCount;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }
}
