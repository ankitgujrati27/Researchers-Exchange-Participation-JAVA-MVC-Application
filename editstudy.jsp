<%@page import="rep.beans.Study"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


                        <c:if test="${user == null}">
                            <c:redirect url="login.jsp"></c:redirect>
                        </c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editing Study</title>
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            <span class="font_bold title_col_pad"> Editing a study </span>
            
            <div class="top_pad_5">
                <span class="back_pad font_bold"><a href="main.jsp" class="back_home"> &Lt; Back to Main Page </a></span>
            </div>
            
            <div class="form_container">
                <div class="transparent_box loginform_pad">
                    <%
                        Study study = (Study) session.getAttribute("myStudy");
                    %>
                    <form method="post" action="StudyController?action=update">
                        <table>
                            
                            <tr>
                                <td> Study Name<span>* </span> </td>
                                <td> <input type="text" name="stud_name" size="35" value="<%=study.getName()%>" required> </td>
                            </tr>
                            
                            <tr>
                                <td> Question Text<span>* </span> </td>
                                <td> <input type="text" name="qtext" size="35" value="<%=study.getQuestion()%>" required> </td>
                            </tr>
                            
                            <tr>
                                <td> Image<span>* </span> </td>
                                <td> <img src="#" /><input type="file" name="image_url"> </td>
                            </tr>
                            
                            <tr>
                                <td> # Participants<span>* </span> </td>
                                <td> <input type="text" name="num_participants"  value="<%=study.getNumParticpants()%>" size="35" required> </td>
                            </tr>
                                                      
                            <tr>
                                <td> Description<span>* </span> </td>
                                <td> <textarea name="desc" cols="27" required><%=study.getDesc()%></textarea> </td>
                            </tr>
                            
                            <tr>
                                <td colspan="2"> <input type="hidden" name="scode" value="<%=study.getStudyCode()%>"></td>
                            </tr>
                            
                            <tr>
                                <td> &nbsp; </td>
                                <td> <input type="submit" value="Update"> </td>
                            </tr>
                            
                        </table>
                        
                    </form>
                
                
                
                
                </div>
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>
