<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sign Up</title>
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
                <div class="transparent_box form_head"> Sign Up Form </div>
                <div class="transparent_box loginform_pad">
                
                    <c:if test="${user_email != null}">
                        <form method="post" action="UserController?action=r_create">
                    </c:if>
                    <c:if test="${user_email == null}">
                        <form method="post" action="UserController?action=create">
                    </c:if>
                        <table>
                            
                            <tr>
                                <td> Name<span>* </span> </td>
                                <td> <input type="text" name="user_name" size="27" required> </td>
                            </tr>
                            <c:if test="${user_email != null}">
                            <tr>
                                <td> Email <span>* </span> </td>
                                <td> <input type="email" name="email_id" value="<%= request.getAttribute("user_email") %>" size="27" readonly> </td>
                            </tr>
                            </c:if>
                            <c:if test="${user_email == null}">
                            <tr>
                                <td> Email <span>* </span> </td>
                                <td> <input type="email" name="email_id" size="27" required> </td>
                            </tr>
                            </c:if>
                            
                            <tr>
                                <td> Password<span>* </span> </td>
                                <td> <input type="password" name="user_pass" id="user_pass" size="27" required> </td>
                            </tr>
                            
                            <tr>
                                <td> Confirm Password<span>* </span> </td>
                                <td> <input type="password" name="conf_pass" id="conf_pass" size="27" required> </td>
                            </tr>
                            
                            
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <input type="submit" id="reg_submit" value="Create Account"> </td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <a href="login.jsp"><i> Go back to Login page  </i></a></td>
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
