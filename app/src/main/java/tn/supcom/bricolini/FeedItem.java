package tn.supcom.bricolini;

public class FeedItem {

    private String ProfilePhoto;
    private String Name;
    private String Adress;
    private String Rate;
    private String JobCount;
    private String jobId ;
    private String techDescription ;


    public FeedItem() {
    }

    public FeedItem(String profilePhoto, String name, String adress, String rate, String jobCount, String jobId, String techDescription) {
        ProfilePhoto = profilePhoto;
        Name = name;
        Adress = adress;
        Rate = rate;
        JobCount = jobCount;
        this.jobId = jobId;
        this.techDescription = techDescription;
    }

    public String getTechDescription() {
        return techDescription;
    }

    public void setTechDescription(String techDescription) {
        this.techDescription = techDescription;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getProfilePhoto() {
        return ProfilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        ProfilePhoto = profilePhoto;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getJobCount() {
        return JobCount;
    }

    public void setJobCount(String jobCount) {
        JobCount = jobCount;
    }
}