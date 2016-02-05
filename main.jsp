<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


                        <c:if test="${user == null}">
                            <c:redirect url="login.jsp"></c:redirect>
                        </c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>How it works</title>
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
                    
                    <p class="caption">How it works</p>
                    <div class="p_section">
                        <p> This site was built to help researchers conduct their user studies </p>
                        <p> 1 participation = 1 coin
                        <p> <b>To participate</b>, go to "Participate" section and choose a study to complete </p>
                        <p> <b>To get participants</b>, submit your user study here to start getting Participations. In order to do so, you must have enough coins in your account.</p>  
                    </div>   
                       
                    
                    
                    
                </div>
                
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>

