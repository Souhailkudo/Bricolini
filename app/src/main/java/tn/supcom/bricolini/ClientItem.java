package tn.supcom.bricolini;

public class ClientItem {
    private String Name ;
    private String ProfilePic ;
    private String adress ;
    private String phone ;
    private String date ;
    private String state ;
    private String service ;

    //for popup
    private String ClientID ;
    private String Description ;

    public ClientItem(String name, String profilePic, String adress, String phone, String date, String state, String service, String clientID, String description) {
        Name = name;
        ProfilePic = profilePic;
        this.adress = adress;
        this.phone = phone;
        this.date = date;
        this.state = state;
        this.service = service;
        ClientID = clientID;
        Description = description;
    }


    // public ClientItem() {
    //}


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
