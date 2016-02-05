
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <header>
            <a href="UserController?action=home"> <img src="resources/logo.png" alt="logo" class="logo"/> </a>
            <div class="menu_section">
                <ul>
                    
                      
                            <li><a href="UserController?action=about"> About Us </a></li>
                            <li><a href="UserController?action=how">  How it Works </a></li>
                            
                            <c:if test="${user != null}">
                                <li> Hello, &nbsp;<c:out value="${user}" /></li>
                                <li> <a href="UserController?action=logout">  Logout </a></li>
                            </c:if>
                            <c:if test="${user == null}">
                                <li> <a href="${pageContext.request.contextPath}/login.jsp"> Log In </a></li>
                            </c:if>
                        
                </ul>
            </div>
        </header>   