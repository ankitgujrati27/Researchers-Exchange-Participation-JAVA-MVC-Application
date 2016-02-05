/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rep.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rep.beans.EmailUtil;
import rep.beans.Study;
import rep.beans.User;
import rep.model.StudyDB;
import rep.model.AnswerDB;
import rep.model.TempUserDB;
import rep.model.UserDB;
import rep.process.SessionIdentifierGenerator;

/**
 *
 * @author Krunal
 */
@MultipartConfig
public class StudyController extends HttpServlet {

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

        StudyDB sdb = new StudyDB();
        UserDB udb = new UserDB();
        AnswerDB ansdb = new AnswerDB();
        TempUserDB tpdb = new TempUserDB();
        User user = new User();
        Study s1 = new Study();
        String stud_code1 = request.getParameter("StudyCode");
        int stud_code;
        stud_code = stud_code1 == null ? 0 : Integer.parseInt(stud_code1);
        String action = request.getParameter("action");

        HttpSession sess = request.getSession();
        
        String username = (String) sess.getAttribute("user");
        String u_email = (String) sess.getAttribute("user_email");
        user = UserDB.selectUser(u_email);
        sess.setAttribute("coin", user.getCoins());
        sess.setAttribute("participation", user.getParticipation());
        sess.setAttribute("participants", ansdb.getCount(u_email));

        if (("myStudy").equals(action))
        {
            if (username != null) 
            {
                
                List<Study> s2 = sdb.getmyStudies(u_email);
                sess.setAttribute("myStudy", s2);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/studies.jsp");
                dispatcher.forward(request, response);

            }
        }

        if (("participate_page").equals(action)) {
            if (username != null) 
            {
                //String u_email = (String) sess.getAttribute("user_email");
                System.out.println(u_email);
                List<Study> s2 = sdb.getParStudies(u_email);
                sess.setAttribute("parStudy", s2);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/participate.jsp");
                dispatcher.forward(request, response);

            } 
            else
            {
                response.sendRedirect("login.jsp");
            }
        }

        if (("participate").equals(action)) {
            //System.out.println("Called Participate");
            if (username != null) {
                String scode = request.getParameter("StudyCode");
                s1 = sdb.getStudy(scode);

                request.setAttribute("question_page", s1.question);
                //request.setAttribute("choice", s1.answer_col);
                request.setAttribute("StudyCode", scode);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/question.jsp");
                dispatcher.forward(request, response);

            } 
            else
            {
                response.sendRedirect("login.jsp");
            }
        }

        if (("add").equals(action)) 
        {
            if (username != null) 
            {
                user = UserDB.selectUser(u_email);
                if(user.getCoins() > 1)
                {
                    String study_name = request.getParameter("stud_name");
                    System.out.println("Stud name " + study_name);
                    s1.setName(study_name);
                    String quest_text = request.getParameter("qtext");
                    s1.setQuestion(quest_text);
                    String num_party1 = request.getParameter("num_participants");
                    int num_party = num_party1 == null ? 0 : Integer.parseInt(num_party1);
                    s1.setNumParticpants(num_party);
                    String img_url = request.getParameter("image_url");
                    s1.setImageUrl(img_url);
                    String desc = request.getParameter("desc");
                    s1.setDesc(desc);
                    sess = request.getSession();
                    //String u_email = (String) sess.getAttribute("user_email");
                    int addStudy = sdb.addStudy(study_name, u_email, quest_text, num_party, img_url, desc, "Stop");
                    user = UserDB.selectUser(u_email);
                    sess.setAttribute("coin", user.getCoins());
                    List<Study> s3 = sdb.getmyStudies(u_email);
                    sess.setAttribute("myStudy", s3);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/studies.jsp");
                    dispatcher.forward(request, response);
                }
                else
                {
                    request.setAttribute("message", "You do not have enough coins");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/studies.jsp");
                    dispatcher.forward(request, response);
                }
                
            } 
            else 
            {
                response.sendRedirect("login.jsp");
            }
        }

        if (("edit").equals(action))
        {
            if (username != null)
            {
                s1 = sdb.getStudy(stud_code1);
                sess.setAttribute("myStudy", s1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/editstudy.jsp");
                dispatcher.forward(request, response);
            } 
            else
            {
                response.sendRedirect("login.jsp");
            }
        }

        if (("update").equals(action))
        {
            if (username != null) 
            {

                String study_name = request.getParameter("stud_name");

                String scode = request.getParameter("scode");
                System.out.println(scode);
                String quest_text = request.getParameter("qtext");
                int num_party = Integer.parseInt(request.getParameter("num_participants"));
                String Desc = request.getParameter("desc");
                String img_url = request.getParameter("image_url");

                int b = sdb.update(scode, study_name, img_url, quest_text, num_party, Desc);
                System.out.println(b);
                //String u_email = (String) sess.getAttribute("user_email");
                List<Study> s3 = sdb.getmyStudies(u_email);
                sess.setAttribute("myStudy", s3);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/studies.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                response.sendRedirect("login.jsp");
            }
        }

        if (action.equals("Start") || action.equals("Stop")) 
        {
            if (username == null)
            {
                response.sendRedirect("login.jsp");
            } 
            else 
            {
                String status = action;
                sdb.updateStatus(stud_code1, status, u_email);
                               
                        
                List<Study> s4 = sdb.getmyStudies(u_email);
                sess.setAttribute("myStudy", s4);

                RequestDispatcher rd = request.getRequestDispatcher("/studies.jsp");
                rd.forward(request, response);

            }
            
        } 
        

        if (("qSubmit").equals(action)) 
        {
            if (username != null) 
            {
                user = UserDB.selectUser(u_email);
                if(user.getCoins() >= 1)
                {
                    String scode = request.getParameter("StudyCode");
                    //System.out.println("Value of Scode is " + scode);
                    s1.setStudyCode(scode);
                    String uemail = (String) sess.getAttribute("user_email");
                    s1.setEmail(uemail);
                    String choice = request.getParameter("choice_ans");

                    int res = sdb.parSubmit(scode, uemail, choice);
                    user = UserDB.selectUser(uemail);
                    sess.setAttribute("coin", user.getCoins());
                    sess.setAttribute("participation", user.getParticipation());
                    sess.setAttribute("participants", ansdb.getCount(uemail));

                    if(res == 1)
                    {
                        List<Study> s3 = sdb.getmyStudies(uemail);
                        sess.setAttribute("myStudy", s3);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/studies.jsp");
                        dispatcher.forward(request, response);
                    }
                    else
                    {
                        request.setAttribute("message", "You have already participated");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/participate.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                else
                {
                    request.setAttribute("message", "You do not have enough coins");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/participate.jsp");
                    dispatcher.forward(request, response);
                }
                
            } 
            else 
            {
                response.sendRedirect("login.jsp");
            }
        }
        /*
        if (("edit").equals(action)) 
        {
            if (username != null)
            {
                s1 = sdb.getStudy(stud_code1);
                sess.setAttribute("myStudy", s1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/editstudy.jsp");
                dispatcher.forward(request, response);
                
            }
            else 
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                
                
            }
        }
        */
        

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
