package member.service;

import bean.MemberDTO;
import com.control.CommandProcess;
import dao.MemberDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteService implements CommandProcess {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(request.getParameter("id"));
        memberDTO.setName(request.getParameter("name"));
        memberDTO.setPassword(request.getParameter("password"));
        memberDTO.setGender(request.getParameter("gender"));
        memberDTO.setPhone(request.getParameter("phone"));
        memberDTO.setEmail(request.getParameter("email"));
        memberDTO.setEmail_addr(request.getParameter("email_addr"));
        memberDTO.setAddress_code(request.getParameter("address_code"));
        memberDTO.setAddress_address(request.getParameter("address_address"));
        memberDTO.setAddress_address_detail(request.getParameter("address_address_detail"));

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.makeAccount(memberDTO);
        return "/member/loginForm.jsp";
    }
}
