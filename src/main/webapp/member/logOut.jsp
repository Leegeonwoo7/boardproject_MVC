<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>logOut</title>
</head>
<body>
<script>
    window.onload=function (){
        alert("로그아웃")
        location.href="${pageContext.request.contextPath}/member/loginForm.do";
    }
</script>
</body>
</html>
