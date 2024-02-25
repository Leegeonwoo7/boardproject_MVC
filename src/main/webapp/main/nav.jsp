<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="button" value="로그인" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
<input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/member/writeForm.do'">

<%--<c:if test="${sessionScope.memId == null}">--%>
<%--    <input type="button" id="login_btn" value="로그인"--%>
<%--           onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'"><br><br>--%>
<%--    <input type="button" value="회원가입"--%>
<%--           onclick="location.href='${pageContext.request.contextPath}/member/writeForm.do'"><br>--%>
<%--</c:if>--%>

<%--<c:if test="${sessionScope.memId != null}">--%>
<%--    <h3><a href="#" onclick="location.href='${pageContext.request.contextPath}/member/updateForm.do'">${memName}</a>님 로그인</h3>--%>
<%--    <input type="button" value="로그아웃" id="logoutBtn">--%>
<%--    <input type="button" value="회원정보수정"--%>
<%--            onclick="location.href='${pageContext.request.contextPath}/member/updateForm.do'">--%>
<%--</c:if>--%>

<%--<script>--%>
<%--    $('#logout_btn').click(function (){--%>
<%--       $.ajax({--%>
<%--           type: 'post',--%>
<%--           url: '${pageContext.request.contextPath}/member/logout.do',--%>
<%--           dataType: 'text',--%>
<%--           success: function (data){--%>
<%--               alert(data);--%>
<%--               location.href='${pageContext.request.contextPath}/main/index.jsp'--%>
<%--           },--%>
<%--           error: function (e){--%>
<%--               console.log(e);--%>
<%--           }--%>
<%--       });--%>
<%--    });--%>
<%--</script>--%>