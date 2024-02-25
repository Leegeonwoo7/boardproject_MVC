package member.service;

import bean.MemberDTO;
import com.control.CommandProcess;
import dao.MemberDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateService implements CommandProcess {
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String email_addr = request.getParameter("email_addr");
        String phone = request.getParameter("phone");
        String address_code = request.getParameter("address_code");
        String address_address = request.getParameter("address_address");
        String address_address_detail = request.getParameter("address_address_detail");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setName(name);
        memberDTO.setPassword(password);
        memberDTO.setGender(gender);
        memberDTO.setEmail(email);
        memberDTO.setEmail_addr(email_addr);
        memberDTO.setPhone(phone);
        memberDTO.setAddress_code(address_code);
        memberDTO.setAddress_address(address_address);
        memberDTO.setAddress_address_detail(address_address_detail);

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.updateMember(memberDTO);

        HttpSession session = request.getSession();
        session.invalidate();
        return "/member/update.jsp";
    }
}
