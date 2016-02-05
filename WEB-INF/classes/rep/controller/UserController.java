/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rep.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rep.beans.EmailUtil;
import rep.beans.TempUser;
import java.util.TimerTask;
import rep.beans.User;
import rep.model.AnswerDB;
import rep.model.TempUserDB;
import rep.process.SessionIdentifierGenerator;

import rep.model.UserDB;
import rep.process.JavaMD5Hash;

/**
 *
 * @author Krunal
 */
public class UserController extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath();
        System.out.println(uri);
        String action = request.getParameter("action");
        
        UserDB udb = new UserDB();
        TempUserDB tpdb = new TempUserDB();
        AnswerDB ansdb = new AnswerDB();

        
        if(action.equals("login"))
        {
            String user_email = request.getParameter("email_id");
            String password = request.getParameter("user_pass");
            String md5_pass = JavaMD5Hash.md5(password);
            User user = new User();
            if(UserDB.emailExists(user_email))
            {
                user = UserDB.selectUser(user_email);
                
                if(user_email.equals(user.getEmail()) && md5_pass.equals(user.getPass()))
                {
                    String user_name = user.getName();
                    user_email = user.getEmail();
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user_name);                   
                    session.setAttribute("user_email", user_email);
                    Cookie cuser_email = new Cookie("user_email", user_email);
                     Cookie cuser_name = new Cookie("user", user_name); 
                    response.addCookie(cuser_name);
                    response.addCookie(cuser_email);
                    UserDB.selectUser(user_email);
                    session.setAttribute("coin", user.getCoins());
                    session.setAttribute("participation", user.getParticipation());
                    session.setAttribute("participants", ansdb.getCount(user_email));
                      //setting session to expiry in 5 mins
                    session.setMaxInactiveInterval(50*60);
                    response.sendRedirect("main.jsp");
                }
                else
                {
                    request.setAttribute("msg","Username & Password combination is incorrect");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward( request, response ); 
                }
            }
            else
            {
                request.setAttribute("msg","Username is not registered");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward( request, response ); 

            }
            
        }
        
        else if(action.equals("forgotPass"))
        {
            String user_email = request.getParameter("email_id");
        
            if(UserDB.emailExists(user_email))
            {
                SessionIdentifierGenerator sid = new SessionIdentifierGenerator();
                String nextSessionId = sid.nextSessionId();
                
                tpdb.addTempUser("", user_email, "", nextSessionId);

                String message = "Hi User,"+"\n \n Click the link to reset your password:"+request.getRequestURL().toString()+"?action=resetPassword&token="+nextSessionId+"\n \n Regards,\n Researchers Exchange Participation";
                EmailUtil.sendEmail(user_email, "Reset Your Account Password", message);
                
                request.setAttribute("msg","Reset Password Link is sent to your mail");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward( request, response );
            }
            else
            {
                
                request.setAttribute("msg","Email id you enetered has not been registered");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/forgotPass.jsp");
                dispatcher.forward( request, response ); 
            }
        }
        
        else if(action.equals("create"))
        {
            String reg_email = request.getParameter("email_id");
            String reg_uname = request.getParameter("user_name");
            String reg_pass = request.getParameter("user_pass");
            String md5_pass = JavaMD5Hash.md5(reg_pass);
            
            if(UserDB.emailExists(reg_email))
            {
                request.setAttribute("msg","Email has been already registered");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
                dispatcher.forward( request, response );
                
                
            }
            else
            {
                SessionIdentifierGenerator sid = new SessionIdentifierGenerator();
                String nextSessionId = sid.nextSessionId();
                
                int i = tpdb.addTempUser(reg_uname, reg_email, md5_pass, nextSessionId);
                if(i==1)
                {
                    String message = "Hi "+reg_uname+",\n \n Confirm your account by clicking the link: "+request.getRequestURL().toString()+"?action=activate&token="+nextSessionId+"\n \n Regards,\n Researchers Exchange Participation";
                    EmailUtil.sendEmail(reg_email, "Activate Your REP Account", message);

                    request.setAttribute("msg","Activation Link sent to your mail");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward( request, response ); 
                }
                else
                {
                    request.setAttribute("msg","Something Went Wrong");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward( request, response ); 
                }
            }
        
        }
        
        
        else if(action.equals("resetPassword"))
        {
            String token = request.getParameter("token");
            
            if(tpdb.checkUser(token))
            {
                User user = tpdb.getUser(token);
                user = UserDB.selectUser(user.getEmail());
                HttpSession session = request.getSession();
                
                session.setAttribute("user_email", user.getEmail());
                session.setMaxInactiveInterval(500*60);
                response.sendRedirect("resetPass.jsp");             
            }
            else
            {
                request.setAttribute("msg","Invalid Token");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward( request, response); 
            }
        }
        
        
        
        
        else if(action.equals("Submit_ResetPassword"))
        {
            HttpSession session = request.getSession();
            String email_id = (String) session.getAttribute("user_email");
            String reset_pass = request.getParameter("reset_pass");
            String md5_reset_pass = JavaMD5Hash.md5(reset_pass);
            udb.updatePass(email_id, md5_reset_pass);
            udb.userLogout(session);
            request.setAttribute("msg","Password has been reset Successfully.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward( request, response); 
            
        }
            
        else if(action.equals("activate"))
        {
            
            String token = request.getParameter("token");
            
            if(tpdb.checkUser(token))
            {
                User user = tpdb.getUser(token);
                UserDB.register_user(user);
                request.setAttribute("msg","Your account has been activated.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward( request, response);            
            }
            else
            {
                request.setAttribute("msg","Invalid Token");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward( request, response); 
            }
        }
            
            
        
        else if(action.equals("how"))
        {
            HttpSession sess = request.getSession();
            String username = (String) sess.getAttribute("user");
            
            if(username != null)
                response.sendRedirect("main.jsp");
            else
                response.sendRedirect("how.jsp");
        }
        
        else if(action.equals("about"))
        {
            HttpSession sess = request.getSession();
            String username = (String) sess.getAttribute("user");
            
            if(username != null)
                response.sendRedirect("aboutl.jsp");
            else
                response.sendRedirect("about.jsp");
        }
        
        else if(action.equals("home") || action.equals("main"))
        {
            HttpSession sess = request.getSession();
            String username = (String) sess.getAttribute("user");
            
            if(username != null)
                response.sendRedirect("main.jsp");
            else
                response.sendRedirect("home.jsp");
        }
        
        else if(action.equals("logout"))
        {
            HttpSession sess = request.getSession();
            udb.userLogout(sess);            
            request.setAttribute("msg","You have successfully logged out");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward( request, response); 
        }
        
        else if (("recommend").equals(action)) {
            HttpSession sess = request.getSession();
            String u_email = (String) sess.getAttribute("user_email");
            if (u_email != null) {
                try 
                {
                    SessionIdentifierGenerator sid = new SessionIdentifierGenerator();
                    String nextSessionId = sid.nextSessionId();
                    
                    String fname = request.getParameter("user_name");
                    String femail = request.getParameter("femail_id");
                    
                    tpdb.addTempUser("", femail, "", nextSessionId);
                    
                    

                    String message = "Hi "+fname+",\n \n Your invitation link: "+request.getRequestURL().toString()+"?action=invite&token="+nextSessionId+"&recommender="+u_email+"\n \n Regards,\n Researchers";
                    EmailUtil.sendEmail(femail, "Recommend to Join REP", message);
                
                    request.setAttribute("msg", "Recommendation sent successfully");
                    /*UserDB.UpdateCoins(u_email, 2);
                    user = UserDB.selectUser(u_email);
                    sess.setAttribute("coin", user.getCoins());*/
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmr.jsp");
                    dispatcher.forward(request, response);
                } 
                catch (ServletException | IOException ex) {
                    request.setAttribute("msg", "Unable to send recommendation. Please try again after some time");

                }
            }

        }
        else if(action.equals("invite"))
        {
            
            HttpSession sess = request.getSession();
            String username = (String) sess.getAttribute("user");
            
            if(username != null)
                response.sendRedirect("main.jsp");         
            else
            {
                String token = request.getParameter("token");
                if(tpdb.checkUser(token))
                {

                    String recommender_email_id = request.getParameter("recommender");
                    User user = tpdb.getUEmail(token);

                    sess.setAttribute("recommender_email_id", recommender_email_id);
                    sess.setAttribute("token", token);
                    request.setAttribute("user_email", user.getEmail());                

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
                    dispatcher.forward( request, response);           
                }
                else
                {
                    request.setAttribute("msg","Invalid Token");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward( request, response); 
                }
            }
            
            
        }
        
        else if(("r_create").equals(action))
        {
            String reg_email = request.getParameter("email_id");
            HttpSession session = request.getSession();
            String recommender_email_id = (String) session.getAttribute("recommender_email_id");
            String token = (String) session.getAttribute("token");
            if(!UserDB.emailExists(reg_email))
            {
                
                String reg_uname = request.getParameter("user_name");
                String reg_pass = request.getParameter("user_pass");
                String md5_pass = JavaMD5Hash.md5(reg_pass);
                User user = new User();
                user.setEmail(reg_email);
                user.setName(reg_uname);
                user.setPass(md5_pass);
                int i = UserDB.register_user(user);
                if(i==1)
                {
                    udb.userLogout(session);
                    tpdb.deleteTempUser(token);
                    udb.UpdateCoins(recommender_email_id, 2);

                    request.setAttribute("msg","Your account has been created.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward( request, response);
                }
                                
            }
            else
            {
                tpdb.deleteTempUser(token);
                request.setAttribute("msg","Email id has been already registered");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward( request, response);
            }
            
            
            
            
            
            
        }
        
        else
        {
            HttpSession sess = request.getSession();
            String username = (String) sess.getAttribute("user");
            
            if(username != null)
                response.sendRedirect("main.jsp");
            else
                response.sendRedirect("home.jsp");
        }
           
        
        
        
        
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
