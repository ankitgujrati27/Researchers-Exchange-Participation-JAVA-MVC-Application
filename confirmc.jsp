<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


                        <c:if test="${user == null}">
                            <c:redirect url="login.jsp"></c:redirect>
                        </c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

                       
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Message Sent</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            <span class="back_pad font_bold"><a href="main.jsp" class="back_home"> &Lt; Back to Main Page </a></span>
            
            <div class="form_container">
                <div class="align_mid top_pad_100">
                
                    <p class="caption"> Message Sent.. </p>
                
                
                
                
                </div>
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>
