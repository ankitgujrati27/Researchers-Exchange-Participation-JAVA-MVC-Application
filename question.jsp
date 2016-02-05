<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



                        <c:if test="${user == null}">
                            <c:redirect url="login.jsp"></c:redirect>
                        </c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Question</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            <%@include file="left_nav.jsp" %>
            <div class="font_bold title_col_pad"> Question </div>
            
            
            <div class="form_container">
                <div class="quest_table">
                    <form method="post" action="StudyController?action=qSubmit">
                    <table class="left">
                        <tr>
                            <td colspan="2" rowspan="3"> <img src="resources/outdoor.gif" alt="outdoor"> </td>
                            <td> &nbsp; </td>
                        </tr>
                        <tr>
                            <td class="font_bold left_pad_10"> <c:out value="${question_page}"></c:out> </td>
                            <td>
                                <select name="choice_ans">
                                        <option value="">Select the value</option>
                                        <c:forEach var="i" begin="1" end="10">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                        
                                        
                                </select>
                            </td>   
                        </tr>
                        <tr>
                            <td> <input type="hidden" value="${question_page}" name="qtext"><td>
                            <td> <input type="hidden" value="${StudyCode}" name="StudyCode"><td>
                        </tr>
                        
                        <tr>
                            <td> &nbsp; </td>
                            <td> <input type="submit" value="Submit"><td>
                        </tr>
                        
                        
                        
                    </table>
                    </form>    
                
                
                
                
                </div>
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>
