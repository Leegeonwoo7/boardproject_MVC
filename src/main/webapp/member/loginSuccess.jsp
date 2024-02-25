<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${sessionScope.memName}님 로그인
<input type="button" value="회원정보수정" id="update_btn"
       onclick=location.href="${pageContext.request.contextPath}/member/updateForm.do">
</body>
</html>
