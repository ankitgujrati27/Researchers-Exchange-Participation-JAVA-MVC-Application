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
import java.util.*;
import java.sql.*;
import javax.servlet.http.HttpSession;
import rep.beans.User;

/**
 *
 * @author Krunal
 */
public class UserDB {

   
    ArrayList<String> userEmail = new ArrayList<String>();
    ArrayList<String> password = new ArrayList<String>();
    ArrayList<String> uname = new ArrayList<String>();
    User user = new User();
    
    public UserDB()
    {
        
        
        userEmail.add("admin@uncc.edu");
        userEmail.add("krunal@uncc.edu");
        userEmail.add("ankit@uncc.edu");
        
        password.add("admin");
        password.add("krunal");
        password.add("ankit");
        
        uname.add("Admin");
        uname.add("Krunal");
        uname.add("Ankit");
        
    }
    public static User selectUser(String email)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        //System.out.println(email);
        PreparedStatement ps = null;
        ResultSet rs = null;
        //System.out.println(email);
        String query = "SELECT * FROM User "
                + "WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("UName"));
                user.setPass(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setParticipation(rs.getInt("Participation"));
                user.setCoins(rs.getInt("Coins"));
                user.setStudies(rs.getInt("Studies"));
                
                //System.out.println(rs.getInt("Coins"));
                
            }
            return user;
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            User user = new User();
            return user;
        }
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int register_user(User user) 
    {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO User (UName, Email , Password, Coins)"
                + "VALUES (?, ?, ?, ?)";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPass());
            ps.setInt(4, 10);
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
    
    
    public static int delete(User user) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM User "
                + "WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
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
    
    public static boolean emailExists(String uemail)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User "
                + "WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, uemail);
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
    
    public String getPassword(int pos)
    {
        return password.get(pos);
    }
    public String getUserEmail(int pos)
    {
        return userEmail.get(pos);

    }
    public String getUsername(int pos)
    {
        return uname.get(pos);

    }
    
    public int updateUserPar(String email)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE User SET Participation = Participation + 1 WHERE Email = ?";
        
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);           
            ps.executeUpdate();
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

    

    public int checkCoins(String uemail) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Coins FROM User WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, uemail);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                //System.out.println(rs.getInt("Coins"));
                return rs.getInt("Coins");
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
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }

    public int UpdateCoins(String u_email, int i) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Update User Set Coins = Coins+(?) WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, i);
            ps.setString(2, u_email);
            return ps.executeUpdate();
                     
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            return 0;
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    

    int UpdateStudies(String u_email, int i) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Update User Set Studies = Studies +(?) WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, i);
            ps.setString(2, u_email);
            return ps.executeUpdate();      
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
            return 0;
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public void updatePass(String email_id, String reset_pass) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Update User Set Password = ? WHERE Email = ?";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, reset_pass);
            ps.setString(2, email_id);
            ps.executeUpdate();  
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public void userLogout(HttpSession session)
    {
        session.invalidate();
    }

       
    
    
}
