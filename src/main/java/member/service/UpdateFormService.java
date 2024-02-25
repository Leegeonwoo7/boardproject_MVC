package member.service;

import bean.MemberDTO;
import com.control.CommandProcess;
import dao.MemberDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateFormService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("memId");

        MemberDAO memberDAO = MemberDAO.getInstance();
        MemberDTO memberDTO = memberDAO.getMemberById(id);

        request.setAttribute("memberDTO", memberDTO);

        //STUDY updateForm에서 로그인 되어있는 멤버의 value를 미리 설정하기위해 세션을 통해
        //STUDY memId에속하는 멤버의 정보를 DB에서 가져오고, 그 값을 memberDTO객체에 넣어
        //STUDY updateForm으로 객체를 전송
        return "/member/updateForm.jsp";
    }
}
