<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                        <c:if test="${user != null}">
                            <c:redirect url="main.jsp"></c:redirect>
                        </c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            
            <div class="form_container">
                <div class="transparent_box form_head"> Find your account </div>
                <div class="transparent_box loginform_pad">
                
                    <form method="post" action="UserController?action=forgotPass">
                        <table>
                            <tr>
                                <td> Email Address<span>* </span> </td>
                                <td> <input type="email" name="email_id" placeholder="Enter your email" size="30" required> </td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <input type="submit" value="Confirm Your Account"> 
                                     
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
