/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rep.beans;

import java.io.Serializable;
import java.util.List;
import rep.model.StudyDB;

/**
 *
 * @author Krunal
 */
public class Study implements Serializable {
    public String name;
    public String code;
    public String date_created;
    public String email_creator;
    public String question;
    public String image_url;
    public int req_participants;
    public int num_participants;
    public String desc;
    public String status;
    public int answer_col;
    
    public Study() {
        name= "";
        code = "";
        date_created= "";
        email_creator= "";
        question= "";
        image_url= "";
        req_participants= 0;
        num_participants= 0;
        desc= "";
        status= "";
        answer_col= 0;
    }

    public Study(String study_code, String study_name, String date_created, String email_creator, String question, String image_url, int req_participants, int num_participants, String desc, String Status, int ans_choice) 
    {
        this.code = study_code;
        this.name= study_name;
        this.date_created= date_created;
        this.email_creator= email_creator;
        this.question= question;
        this.image_url= image_url;
        this.req_participants= req_participants;
        this.num_participants= num_participants;
        this.desc= desc;
        this.status= Status;
        this.answer_col= ans_choice;
    }
    
    public void setEmail(String email)
    {
        this.email_creator = email;
    }
    public String getEmail()
    {
        return this.email_creator;
    }   
    
    public void setStudyCode(String code)
    {
        this.code = code;
    }
    public String getStudyCode()
    {
        return this.code;
    }
    
    public void setdateCreate(String create_date)
    {
        this.date_created = create_date;
    }
    public String getdateCreate()
    {
        return this.date_created;
    }

    public void setName(String sname) {
        this.name = sname;
    }
    
    public String getName() {
        return this.name;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }
    public String getImageUrl() {
        return image_url;
    }

    public void setreqParticpants(int req_participants) {
        this.req_participants = req_participants;
    }
    public int getreqParticpants() {
        return req_participants;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    
    public void setNumParticpants(int num_participants) {
        this.num_participants = num_participants;
    }
    public int getNumParticpants() {
        return num_participants;
    }
    

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
    
    
    
    
    
    
    
}
