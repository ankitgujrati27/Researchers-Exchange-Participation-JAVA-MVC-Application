package rep.beans;

import java.io.Serializable;

public class TempUser implements Serializable {

    private String name;
    private String email;
    private String pass;
    private String token ;
    
    public TempUser() {
        name = "";
        email = "";
        pass = "";
        token = "";
        
    }

    public TempUser(String uname, String uemail, String upass, String token) {
        this.name = uname;
        this.email = uemail;
        this.pass = upass;
        this.token = token;
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
    
    public String getToken()
    {
       return token;
    }
    public void setToken(String token)
    {
       this.token = token;
    }   
}
