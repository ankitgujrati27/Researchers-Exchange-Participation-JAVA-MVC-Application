        <footer>
        <%
            String hostname = request.getServerName();
            String portnum = Integer.toString(request.getServerPort());
            Cookie c1 = new Cookie("host", hostname);
            Cookie c2 = new Cookie("port", portnum);
            c1.setPath("/");
            c2.setPath("/");
            response.addCookie(c1);
            response.addCookie(c2);
            
            
        %>
            <div class="footer_wrapper"> 
                
                <div class="footer_text"><div class="footer_text_left"> <%=hostname+":"+portnum %> </div>Copyright © Researchers Exchange Participations <img src="resources/small_logo.png" alt="small_logo" class="footer_logo"/></div>
                 
            </div>
        </footer>