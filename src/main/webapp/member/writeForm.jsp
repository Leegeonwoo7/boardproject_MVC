<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/member/write.do" id="create_account_form" method="post">
    <table>
        <tr>
            <th><label for="create_account_name">이름</label></th>
            <td>
                <input type="text" name="name" id="create_account_name" placeholder="이름입력">
                <div id="validate_name_message"></div>
            </td>
        </tr>

        <tr>
            <th><label for="create_account_id">아이디</label></th>
            <td>
                <input type="text" name="id" id="create_account_id" placeholder="ID입력">
                <input type="button" onclick="validate_duplicate_id()" value="중복검사">
                <div id="validate_id_message"></div>
            </td>
        </tr>


        <tr>
            <th><label for="create_account_password">비밀번호</label></th>
            <td>
                <input type="password" name="password" id="create_account_password" placeholder="비밀번호 입력">
                <div id="validate_password_message"></div>
            </td>
        </tr>

        <tr>
            <th><label for="create_account_re_password">재확인</label></th>
            <td><input type="password" name="re_password" id="create_account_re_password" placeholder="비밀번호 입력"></td>
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
            <th><label for="create_account_email">이메일</label></th>
            <td>
                <input type="text" name="email" id="create_account_email" placeholder="이메일 입력">
                <label for="create_account_email_addr">@</label>
                <input type="text" name="email_addr" id="create_account_email_addr" placeholder="주소입력">
                <select name="email_menu" id="create_account_email_menu" oninput="email_choice_input()">
                    <option value="직접입력">직접입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="gmail.com">gamile.com</option>
                    <option value="hanmail.com">hanmail.com</option>
                </select>
            </td>
        </tr>

        <tr>
            <th><label for="create_account_phone">휴대폰</label></th>
            <td><input type="text" name="phone" id="create_account_phone" placeholder="-는 제외하고 입력"></td>
        </tr>

        <tr>
            <th><label for="create_account_address">주소</label></th>
            <td>
                <input type="text" name="address_code" id="create_account_address_code">
                <!-- 카카오 API 추가-->
                <input type="button" onclick="checkPost()" value="우편번호검색">
                <br>
                <input type="text" name="address_address" id="create_account_address" placeholder="주소"><br>
                <input type="text" name="address_address_detail" id="create_account_address_detail" placeholder="상세주소"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" onclick="create_account()" value="회원가입">
                <input type="reset" value="다시작성">
            </td>
        </tr>
    </table>
</form>
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function create_account(){
        document.getElementById('validate_name_message').innerText = "";
        document.getElementById('validate_id_message').innerText = "";
        document.getElementById('validate_password_message').innerText = "";

        if (document.getElementById('create_account_name').value === ""){
            document.getElementById('create_account_name').focus();
            document.getElementById('validate_name_message').innerText = "이름을 입력해주세요";
        }else if(document.getElementById('create_account_id').value === ""){
            document.getElementById('create_account_id').focus();
            document.getElementById('validate_id_message').innerText = "아이디를 입력해주세요";
        }else if(document.getElementById('create_account_password').value === "" ||
            document.getElementById('create_account_password').value !==
            document.getElementById('create_account_re_password').value){
            document.getElementById('create_account_password').focus();
            document.getElementById('validate_password_message').innerText = "비밀번호를 확인해주세요";
        }else{
            document.getElementById('create_account_form').submit();
        }
    }

    function email_choice_input(){
        document.getElementById('create_account_email_addr').value =
            document.getElementById('create_account_email_menu').value;
    }

    //ID중복검사
    function validate_duplicate_id(){
        let isDuplicateChecked = false;
        let id = document.getElementById('create_account_id').value;
        if(id === ""){
            document.getElementById('validate_id_message').innerText = "아이디를 먼저 입력하세요";
        }else{
            window.open("http://localhost:8080/member/duplicateId.jsp?id="+ id
                ,"validate_duplicate_id"
                ,"width=450 height=150 left=300 top=300");
            isDuplicateChecked = true;
        }
    }

    function checkPost(){
        new daum.Postcode({
            oncomplete: function (data) {
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
                document.getElementById('create_account_address_code').value = data.zonecode;
                document.getElementById("create_account_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("create_account_address_detail").focus();
            }
        }).open();
    }
</script>
</body>
</html>