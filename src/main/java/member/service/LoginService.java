package member.service;

import bean.MemberDTO;
import com.control.CommandProcess;
import dao.MemberDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String id = request.getParameter("login_id");
        String password = request.getParameter("login_password");

        MemberDAO memberDAO = MemberDAO.getInstance();
        MemberDTO memberDTO = memberDAO.login(id, password);

        if(memberDTO == null){
            return "loginFail.jsp";
        }else {
            String email1 = memberDTO.getEmail();
            String email2 = memberDTO.getEmail_addr();
            String email = email1 + "@" + email2;

            HttpSession session = request.getSession();
            session.setAttribute("memName", memberDTO.getName());
            session.setAttribute("memId", id);
            session.setAttribute("email",email);
            return "loginSuccess.jsp";
        }
    }
}
