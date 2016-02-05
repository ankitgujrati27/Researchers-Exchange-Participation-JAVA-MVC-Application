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
import rep.beans.User;

/**
 *
 * @author Krunal
 */
public class AnswerDB {

   
    
    User user = new User();
    
    public AnswerDB()
    {
        
        
        
        
    }
    public int getCount(String email)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "Select count(*) as count from Answer a where a.Email != ? and Scode in (select SCode from Study where Email = ?); ";
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {               
                return rs.getInt("count");
            }
            else
                return 0;
                   
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

       
    
    
}
