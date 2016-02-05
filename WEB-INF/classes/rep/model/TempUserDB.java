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
//import java.util.*;
import java.sql.*;
import rep.beans.TempUser;
import rep.beans.User;
/**
 *
 * @author Krunal
 */
public class TempUserDB 
{
    TempUser tp = new TempUser();
    
    public User getUser(String token)
    {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Select Email, UName, Password from TempUser Where Token = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if(rs.next())
            {
                user.setEmail(rs.getString("Email"));
                user.setName(rs.getString("UName"));
                user.setPass(rs.getString("Password"));
            }
            deleteTempUser(token);
            return user;
              
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            return user;
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
    }
    
    public User getUEmail(String token)
    {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Select Email from TempUser Where Token = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if(rs.next())
            {
                user.setEmail(rs.getString("Email"));
            }
            return user;
              
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            return user;
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        } 
    }
    
    public boolean checkUser(String token)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Select Token from TempUser Where Token = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            return rs.next();       
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            return false;
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    
    
    public int addTempUser(String UName, String Email, String pass, String token)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "insert into TempUser (UName, Email , Password, Token) VALUES (?, ?, ?, ?)";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, UName);
            ps.setString(2, Email);
            ps.setString(3, pass);
            ps.setString(4, token);
            
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

    public void deleteTempUser(String token) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "delete from TempUser Where Token = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
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
