<%@page import="java.util.List"%>
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
        <title>Participate</title>
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
            <div class="font_bold title_col_pad"> Studies </div>
            
            
            <div class="form_container">
                <div class="par_table">
                    
                    <table class="left">
                        <tr>
                            <th> Study Name </th>
                            <th> Image </th>
                            <th> Question </th>
                            <th> Action </th>
                        </tr>
                        <% 
                            List<Study> pStudy = (List) session.getAttribute("parStudy");
                            for(Study study : pStudy){
                        
                        %>
                        
                            <tr>
                                <td><%=study.getName()%></td>
                                <td><img src="resources/blue_tree.png" alt="gui_logo"></td>
                                <td><%=study.getQuestion()%></td>
                                <td><input type="button" name="participate" value="Participate" onclick="open_page('StudyController?action=participate&amp;StudyCode=<%=study.getStudyCode()%>')"></td>
                            </tr>
                            
                            <%}%>
                                                
                        <tr>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                        </tr>
                        
                        <tr>
                            <td colspan="4" style="color: red; font-weight: bold"> 
                            <%
                                if (request.getAttribute("message") != null)
                                {
                                    out.print(request.getAttribute("message"));
                                }
                            %>
                            </td>
                        </tr>
                        
                        
                        
                    </table>
                
                
                
                
                </div>
            
            </div>
        </div>
        
        <%@include file="footer.jsp" %>
        
    </body>
</html>
