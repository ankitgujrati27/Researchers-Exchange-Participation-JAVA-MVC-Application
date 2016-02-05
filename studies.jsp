<%@page import="java.util.List"%>
<%@page import="rep.model.StudyDB"%>
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
        <title>Studies</title>
        <c:set var="myurl" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}"/>
        <meta property="og:url"           content="${myurl}" />
        <meta property="og:type"          content="website" />
        <meta property="og:title"         content="Researchers Exchange Participation" />
        <meta property="og:description"   content="Assignment to use social plugins - Network Application Development  " />
        <meta property="og:image"         content="resources/Rep.png" />
        <link rel="stylesheet" href="styles/page_layout.css" />
        <link rel="stylesheet" href="styles/format.css" />
        <link rel="stylesheet" href="styles/padding.css" />
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="fb-root"></div>
        <script>
            (function(d, s, id) 
            {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) return;
                js = d.createElement(s); js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));            
        </script>
        
        <%@include file="header.jsp" %>
        
        <div id="main_section">
            <span class="font_bold title_col_pad"> My Studies </span>
            <div class="top_pad_25">
                <span class="back_pad font_bold"><a href="newstudy.jsp"> Add a new study </a></span>
            </div>
            <div class="top_pad_5">
                <span class="back_pad font_bold"><a href="main.jsp" class="back_home"> &Lt; Back to Main Page </a></span>
            </div>
            
            <div class="form_container">
                <div class="study_table">
                    <table class="center">
                        <tr>
                            <th> Study Name </th>
                            <th> Requested Participants </th>
                            <th> Participations </th>
                            <th> Status </th>
                            <th> Action </th>
                            <th> Facebook Share </th>
                        </tr>
                        <% 
                            List<Study> myStudy = (List) session.getAttribute("myStudy");
                            if(!myStudy.isEmpty())
                            {
                                for(Study study : myStudy){
                        
                        %>
                        
                                <tr>
                                    <td><%=study.getName()%></td>
                                    <td><%=study.getreqParticpants()%></td>
                                    <td><%=study.getNumParticpants()%></td>
                                    <% 
                                        String status;
                                        if(study.getStatus().equals("Start"))
                                            status = "Stop";
                                        else
                                            status = "Start";
                                    %>
                                    
                                    <td><input type="button" id="<%=study.getStudyCode()%>" name="<%=study.getStatus()%>_<%=study.getStudyCode()%>" value="<%=status %>" onclick="open_page('StudyController?action=<%=status %>&amp;StudyCode=<%=study.getStudyCode()%>')"> </td>
                                    <td><input type="button" name="edit_<%=study.getStudyCode()%>" value="Edit" onclick="open_page('StudyController?action=edit&amp;StudyCode=<%=study.getStudyCode()%>')"></td>
                                    
                                    <td><div class="fb-share-button" data-href="${myurl}" data-layout="button_count"></div></td>
                                </tr>
            
        
                            <%
                                }
                                
                            }
                            else
                            {
                            %>
                               <tr>
                                   <td colspan="5" style="text-align: center; font-weight: bold"> No Studies Available </td>
                               </tr> 
                            <%
                            }
                            %>
                        
                        
                        <tr>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                            <td> &nbsp; </td>
                        </tr>
                        
                        <tr>
                            <td colspan="6" style="color: red; font-weight: bold"> 
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
