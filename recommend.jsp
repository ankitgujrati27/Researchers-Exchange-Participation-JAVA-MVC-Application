<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


                        <c:if test="${user == null}">
                            <c:redirect url="login.jsp"></c:redirect>
                        </c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Recommend to Friends</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            <span class="back_pad font_bold"><a href="main.jsp" class="back_home"> &Lt; Back to Main Page </a></span>
            
            <div class="form_container">
                <div class="transparent_box form_head"> Recommend to a friend </div>
                <div class="transparent_box loginform_pad">
                
                    <form method="post" action="UserController?action=recommend">
                        <table>
                            
                            <tr>
                                <td>Friend's Name<span>* </span> </td>
                                <td> <input type="text" name="user_name" size="35" required> </td>
                            </tr>
                            
                            <tr>
                                <td> Friend's Email<span>* </span> </td>
                                <td> <input type="email" name="femail_id" size="35" required> </td>
                            </tr>
                            
                            <tr>
                                <td> Message<span>* </span> </td>
                                <td> <textarea name="msg" cols="27" ></textarea> </td>
                            </tr>
                                                      
                           <tr>
                                <td> &nbsp; </td>
                                <td> <input type="submit" value="Submit"> </td>
                            </tr>
                            
                                            
                  
                        </table>
                        
                    </form>
                
                
                
                
                </div>
                <p class='align_mid font_bold top_pad_10'> When your friend logs in and participate in one user study , you'll get 2 coin bonus </p>
            </div>
            
            
            
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>