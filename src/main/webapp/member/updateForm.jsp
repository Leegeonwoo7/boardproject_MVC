<%@ page import="dao.MemberDAO" %>
<%@ page import="bean.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, th, td{
            border: 1px solid black;
            border-collapse: collapse;
        }
        div{
            color: red;
        }
    </style>
    <title>회원정보 수정</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/member/update.do" id="update_form" method="post">
    <table>
        <tr>
            <th><label for="update_name">이름</label></th>
            <td>
                <input type="text" name="name" id="update_name" value="${requestScope.memberDTO.getName()}">
                <div id="validate_name_message"></div>
            </td>
        </tr>

        <tr>
            <th><label for="update_id">아이디</label></th>
            <td>
                <input type="text" name="id" id="update_id" value="${memberDTO.id}" readonly> <!-- 메소드명 , 변수명 아님-->
            </td>
        </tr>

        <tr>
            <th><label for="update_password">비밀번호</label></th>
            <td>
                <input type="password" name="password" id="update_password" placeholder="비밀번호 입력">
                <div id="validate_password_message"></div>
            </td>
        </tr>

        <tr>
            <th><label for="update_re_password">재확인</label></th>
            <td><input type="password" name="re_password" id="update_re_password" placeholder="비밀번호 입력"></td>
        </tr>

        <tr>
            <th>성별</th>
            <td>
                <input type="radio" name="gender" id="male" value="0" checked="checked">
                <label for="male">남자</label>
                <input type="radio" name="gender" id="female" value="1">
                <label for="female">여자</label>
            </td>
        </tr>

        <tr>
            <th><label for="update_email">이메일</label></th>
            <td>
                <input type="text" name="email" id="update_email" value="${memberDTO.email}">
                <label for="update_email_addr">@</label>
                <input type="text" name="email_addr" id="update_email_addr" value="${memberDTO.email_addr}">
                <select name="email_menu" id="update_email_menu" oninput="email_choice_input()">
                    <option value="직접입력">직접입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="gmail.com">gamile.com</option>
                    <option value="hanmail.com">hanmail.com</option>
                </select>
                <!-- 이메일3부분 체크-->
            </td>
        </tr>

        <tr>
            <th><label for="update_phone">휴대폰</label></th>
            <td><input type="text" name="phone" id="update_phone" value="${memberDTO.phone}"></td>
        </tr>

        <tr>
            <th><label for="update_address_code">주소</label></th>
            <td>
                <input type="text" name="address_code" id="update_address_code" value="${memberDTO.address_code}">
                <input type="button" onclick="checkPost()" value="우편번호검색">
                <br>
                <input type="text" name="address_address" id="update_address" value="${memberDTO.address_address}"><br>
                <input type="text" name="address_address_detail" id="update_address_detail" value="${memberDTO.address_address_detail}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" onclick="update_info()" value="수정완료">
                <input type="reset" value="다시작성" onclick="location.reload()">
            </td>
        </tr>
    </table>
</form>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    window.onload=function (){
        <%--document.updateForm.gender['${memberDTO.gender}'].checked = true;--%>
        <%--document.updateForm.tell.value = ${memberDTO.tel1};--%>
    }

    function update_info(){
        document.getElementById('validate_name_message').innerText = "";
        document.getElementById('validate_password_message').innerText = "";
        if (document.getElementById('update_name').value === ""){
            document.getElementById('update_name').focus();
            document.getElementById('validate_name_message').innerText = "이름을 입력해주세요";
        }else if(document.getElementById('update_password').value === "" || document.getElementById('update_re_password').value === ""){
            document.getElementById('update_password').focus();
            document.getElementById('validate_password_message').innerText = "비밀번호를 확인해주세요";
        }else{
            document.getElementById('update_form').submit();
        }
    }


    function checkPost(){
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('update_address_code').value = data.zonecode;
                document.getElementById("update_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("update_address_detail").focus();
            }
        }).open();
    }
</script>
</body>
</html>
