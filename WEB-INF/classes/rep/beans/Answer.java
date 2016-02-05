/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rep.beans;

import java.io.Serializable;

/**
 *
 * @author Krunal
 */
public class Answer implements Serializable{
    private String pemail;
    private int choice;
    
    private String date_created;
    private String email_creator;
    private String question;
    private String image_url;
    
    
    public String getParticipantEmail() 
    {
        return pemail;
    }
    
    public void setParticipantEmail(String pemail) 
    {
        this.pemail = pemail;
    }
    
    public int getchoice() 
    {
        return choice;
    }
    
    public void setchoice(int choice) 
    {
        this.choice = choice;
    }
    
    public long getAverage()
    {
        return 0;
    }
    
    public long getMin()
    {
        return 0;
    }
    
    public long getMax()
    {
        return 0;
    }
    
    
    
}




