    <div id="left_nav">
        <nav>
            <ul>
                <li><a href="#"> Coins (<%=session.getAttribute("coin") %>) </a></li>
                <li><a href="#"> Participants (<%=session.getAttribute("participants") %>) </a></li>
                <li><a href="#"> Participation (<%=session.getAttribute("participation") %>) </a></li>
                <li> &nbsp; </li>
                <li><a href="UserController?action=main"> Home </a></li>
                <li><a href="StudyController?action=participate_page"> Participate </a></li>
                <li><a href="StudyController?action=myStudy"> My Studies </a></li>
                <li><a href="recommend.jsp"> Recommend </a></li>
                <li><a href="contact.jsp"> Contact </a></li>
                
            </ul>
        </nav>
    </div>