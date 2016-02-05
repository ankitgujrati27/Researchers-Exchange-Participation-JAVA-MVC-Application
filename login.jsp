<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                        <c:if test="${user != null}">
                            <c:redirect url="main.jsp"></c:redirect>
                        </c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            
            <div class="form_container">
                <div class="transparent_box form_head"> Login Form </div>
                <div class="transparent_box loginform_pad">
                
                    <form method="post" action="UserController?action=login">
                        <table>
                            <tr>
                                <td> Email Address<span>* </span> </td>
                                <td> <input type="email" name="email_id" placeholder="Enter your username" size="30" required> </td>
                            </tr>
                            
                            <tr>
                                <td> Password<span>* </span> </td>
                                <td> <input type="password" name="user_pass" placeholder="Enter your password"  size="30" required> </td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <input type="submit" value="Log In"> 
                                     <a href="forgotPass.jsp" style="padding-left: 10px"> Forgot Password </a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <a href="signup.jsp"><i> Sign Up for a new account  </i></a></td>
                            </tr>
                            
                            <tr>
                                <td class="fred" colspan="2">
                                    <%
                                        
                                        if(null == session.getAttribute("user") && request.getAttribute("msg") != null)
                                            out.println(request.getAttribute("msg"));
                                                                               
                                    %>
                                </td>
                            </tr>
                            
                            
                            
                            
                        </table>
                        
                    </form>
                
                
                
                
                </div>
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>
