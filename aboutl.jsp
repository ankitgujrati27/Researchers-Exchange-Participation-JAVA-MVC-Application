<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

                        <c:if test="${user == null}">
                            <c:redirect url="login.jsp"></c:redirect>
                        </c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>About Us</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            <%@include file="left_nav.jsp" %>
            <div class="form_container">
                <div class="transparent_box how_pad_1">
                    
                    <p class="caption">About Us</p>
                    <div class="p_section">
                        <p> Researchers Exchange Participations is a platform for researchers to exchange participations.</p>
                        <p> The aim of this platform is to encourage researchers participate in each other’s user studies. Moreover, recruiting serious participants has been always a burden on a researcher's shoulder, thus, this platform gives researchers the opportunity to do that effectively and in a suitable time.</p>
                    </div>  
                       
                    
                    
                    
                </div>
                
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>

