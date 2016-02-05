package rep.beans;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String pass;
    private int coins;
    private int participation;
    private int participant;
    private int uStudies;

    public User() {
        name = "";
        email = "";
        coins = 0;
        participation = 0;
        participant = 0;
    }

    public User(String uname, String uemail, String upass) {
        this.name = uname;
        this.email = uemail;
        this.pass = upass;
    }
    
    public User(String uname, String uemail) {
        this.name = uname;
        this.email = uemail;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String uname) {
        this.name = uname;
    }
    
    public String getPass() {
        return pass;
    }

    public void setPass(String upass) {
        this.pass = upass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String uemail) {
        this.email = uemail;
    }
    
    public int getParticipation()
    {
       return participation;
    }
    public void setParticipation(int count)
    {
       this.participation = count;
    }
    
    
    public int getParticipant()
    {
       return participant;
    }
    public void setParticipant(int count)
    {
       this.participant = count;
    }
    
    
    
    public int getCoins()
    {
       return coins;
    }
    public void setCoins(int count)
    {
       this.coins = count;
    }
    
    
    
    public int getStudies() {
        
        return uStudies;
    }

   

    public void setStudies(int studies) {
        this.uStudies = studies;
    }
    
}
