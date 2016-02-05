/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import rep.beans.Study;
import java.util.Date;

/**
 *
 * @author Krunal
 */
public class StudyDB {

    

    

   
    /*ArrayList<Study> study = new ArrayList<Study>();*/
    UserDB udb = new UserDB();
    
    public StudyDB()
    {
        //study.add(new Study("1", "GUI", "10/15/2015", "admin@uncc.edu", "How much you know about GUI?", "image1", 10, 3, "Graphical User Interface", "Start", 7));
        //study.add(new Study("2", "Sec", "10/16/2015", "ankit@uncc.edu", "On an average how many hours you spend on Computer?", "image2", 5, 5, "Sectional", "Stop", 10));
    }
    
    private static String maxSCode() 
    {
        //System.out.println("Max scode method called");
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "Select max(SCode) as SCode from Study";
        try 
        {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                String maxScode = String.valueOf(rs.getInt("SCode") + 1);
                //System.out.println("Max is "+maxScode);
                return maxScode;
            }
            else
                return "";
            //return maxScode;           
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return "";
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public int addStudy(String study_name, String u_email, String quest_text, int num_party, String img_url, String desc, String Status)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String Scode = maxSCode();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
        Date date = new Date();
        
        String creation_date = ft.format(date);
        String query
                = "INSERT INTO Study (SName, SCode, Description, Email, DateCreated, Question, ImageURL, ActParticipants,SStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, study_name);
            ps.setString(2, Scode);
            ps.setString(3, desc);
            ps.setString(4, u_email);
            ps.setString(5, creation_date);
            ps.setString(6, quest_text);
            ps.setString(7, img_url);
            ps.setInt(8, num_party);
            ps.setString(9, Status);
            int i = ps.executeUpdate();
            
            if(i == 1)
            {
                udb.UpdateCoins(u_email, -2);
                udb.UpdateStudies(u_email, 1);
            }
            return 1;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return 0;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    
    public Study getStudy(String study_code)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
               
        String query
                = "select * from Study where SCode=?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, study_code);
            ResultSet rs = ps.executeQuery();
            Study s1 = new Study();
            if (rs.next())
            {
                
                s1.setStudyCode(rs.getString("SCode"));
                s1.setName(rs.getString("SName"));
                s1.setDesc(rs.getString("Description"));
                s1.setEmail(rs.getString("Email"));
                s1.setdateCreate(rs.getString("DateCreated"));
                s1.setQuestion(rs.getString("Question"));
                s1.setImageUrl(rs.getString("ImageURL"));
                s1.setreqParticpants(rs.getInt("ReqParticipants"));
                s1.setNumParticpants(rs.getInt("ActParticipants"));                
                s1.setStatus(rs.getString("SStatus"));                
            }
            return s1;
            
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
    }
    
    public List<Study> getmyStudies(String email)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Study> studies = new ArrayList<>();
               
        String query
                = "select * from Study where Email=?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                Study study = new Study();
                study.setStudyCode(rs.getString("SCode"));
                study.setName(rs.getString("SName"));
                study.setDesc(rs.getString("Description"));
                study.setEmail(rs.getString("Email"));
                study.setdateCreate(rs.getString("DateCreated"));
                study.setQuestion(rs.getString("Question"));
                study.setImageUrl(rs.getString("ImageURL"));
                study.setreqParticpants(rs.getInt("ReqParticipants"));
                study.setNumParticpants(rs.getInt("ActParticipants"));                
                study.setStatus(rs.getString("SStatus"));
                
                studies.add(study);
                
            }
            return studies;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return studies;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public List<Study> getStudies()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Study> studies = new ArrayList<>();
               
        String query
                = "select * from Study";
        try 
        {
            ps = connection.prepareStatement(query);          
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                Study study = new Study();
                study.setStudyCode(rs.getString("SCode"));
                study.setName(rs.getString("SName"));
                study.setDesc(rs.getString("Description"));
                study.setEmail(rs.getString("Email"));
                study.setdateCreate(rs.getString("DateCreated"));
                study.setQuestion(rs.getString("Question"));
                study.setImageUrl(rs.getString("ImageURL"));
                study.setreqParticpants(rs.getInt("ReqParticipants"));
                study.setNumParticpants(rs.getInt("ActParticipants"));                
                study.setStatus(rs.getString("SStatus"));
                
                studies.add(study);
                
            }
            return studies;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return studies;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    /*public ArrayList<Study> getStudies(String email)
    {
        ArrayList study1 = new ArrayList();
        
        for(int i=0; i<study.size(); i++)
        {
            study1.add((Collection) study.get(study.indexOf(email)));
        }
        return study1;
    }*/
      

    public int update(String Study_code, String study_name, String img_url, String quest_text, int num_party, String Desc) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
               
        String query = "update Study set SName = ?, Question= ?, ImageURL=?, ActParticipants=?, Description=? where SCode=?";
      
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, study_name);
            ps.setString(2, quest_text);
            ps.setString(3, img_url);
            ps.setInt(4, num_party);
            ps.setString(5, Desc);     
            ps.setString(6, Study_code);
            return ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return 0;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    

    public List<Study> getParStudies(String u_email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        List<Study> studies = new ArrayList<>();
               
        String query = "select * from Study where not Email =? and SStatus=? and not Scode in (select SCode from Answer where Email = ?)";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, u_email);
            ps.setString(2, "Start");
            ps.setString(3, u_email);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                Study study = new Study();
                study.setStudyCode(rs.getString("SCode"));
                study.setName(rs.getString("SName"));
                study.setDesc(rs.getString("Description"));
                study.setEmail(rs.getString("Email"));
                study.setdateCreate(rs.getString("DateCreated"));
                study.setQuestion(rs.getString("Question"));
                study.setImageUrl(rs.getString("ImageURL"));
                study.setreqParticpants(rs.getInt("ReqParticipants"));
                study.setNumParticpants(rs.getInt("ActParticipants"));                
                study.setStatus(rs.getString("SStatus"));
                
                studies.add(study);
                
            }
            return studies;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return studies;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int parSubmit(String scode, String uemail, String choice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
        Date date = new Date();
        
        String par_date = ft.format(date);
        String query = "INSERT INTO Answer (Email, Choice, SCode,DateSubmitted)"
                + "VALUES (?, ?, ?, ?)";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, uemail);
            ps.setString(2, choice);
            ps.setString(3, scode);
            ps.setString(4, par_date);         
            int i = ps.executeUpdate();
            //System.out.println(i);
            if(i == 1)
            {
                updateStudyReqPar(scode);
                udb.updateUserPar(uemail);
                udb.UpdateCoins(uemail, 1);
                //udb.UpdateCoins(scode);
                return 1;
            }
            else
            {
                return 11;
            }          
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return 0;
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    
    
    public int updateStudyReqPar(String sCode)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE Study SET ReqParticipants = ReqParticipants + 1 "
                + "WHERE SCode = ?";
        
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, sCode);           
            
            return ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return 0;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public void updateStatus(String study_code, String status, String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
               
        String query = "update Study set SStatus = ? where SCode=?";
      
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, study_code);
            ps.executeUpdate();
                    
        }
        catch (SQLException e)
        {
            System.out.println(e);
           
        }
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
            
    

    

    
    
    
    
    
    
    
    
}
