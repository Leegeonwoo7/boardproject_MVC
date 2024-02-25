<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${pageContext.request.contextPath}/member/login.do" id="login_form" method="post">
<table>
    <tr>
        <th><label for="login_id">아이디</label></th>
        <td>
            <input type="text" name="login_id" id="login_id">
            <div id="id_check_message"></div>
        </td>
    </tr>
    <tr>
        <th><label for="login_password">비밀번호</label></th>
        <td>
            <input type="password" name="login_password" id="login_password">
            <div id="password_check_message"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" onclick="login()" value="로그인">
            <input type="button" onclick="location.href='${pageContext.request.contextPath}/member/writeForm.do'" value="회원가입">
        </td>
    </tr>
    <script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="member_js/member.js">
    </script>
</table>
</form>
