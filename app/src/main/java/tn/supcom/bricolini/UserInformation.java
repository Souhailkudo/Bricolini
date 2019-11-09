package tn.supcom.bricolini;

public class UserInformation {

    String userID;
    String nom;
    String prenom;
    String tel;
    String gouvernorat;
    String ville;
    String codepostal;

    public UserInformation(String userID, String nom, String prenom, String tel, String gouvernorat, String ville, String codepostal) {
        this.userID = userID;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.codepostal = codepostal;
    }

    public UserInformation() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


   public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }
}