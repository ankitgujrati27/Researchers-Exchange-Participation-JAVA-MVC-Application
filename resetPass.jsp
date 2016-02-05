<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                        <c:if test="${user != null}">
                            <c:redirect url="main.jsp"></c:redirect>
                        </c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reset Password</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            
            <div class="form_container">
                <div class="transparent_box form_head"> Reset Your Password </div>
                <div class="transparent_box loginform_pad">
                
                    <form method="post" action="UserController?action=Submit_ResetPassword">
                        <table>
                            <tr>
                                <td> New Password<span>* </span> </td>
                                <td> <input type="password" name="reset_pass" id="reset_pass" placeholder="Your new Password" size="30" required> </td>
                            </tr>
                            <tr>
                                <td> Confirm Password<span>* </span> </td>
                                <td> <input type="password" name="conf_reset_pass" id="conf_reset_pass" placeholder="Confirm new Password" size="30" required> </td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <input type="submit" id="reset_password_submit" value="Reset Password"> 
                                     
                                </td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <a href="login.jsp" style="padding-left: 10px">Back to Login Page </a></td>
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
