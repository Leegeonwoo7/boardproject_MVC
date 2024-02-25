function login() {
    let id_check_message = $('#id_check_message');
    let password_check_message = $('#password_check_message');
    let login_id = $('#login_id');
    let login_password = $('#login_password');
    let login_form = $('#login_form');

    id_check_message.empty();
    password_check_message.empty();

    if (login_id.val() === "") {
        login_id.focus();
        id_check_message.text('아이디를 입력해주세요');
    } else if (login_password.val() === "") {
        login_password.focus();
        password_check_message.text('비밀번호를 입력해주세요');
    } else {
        login_form.submit();
    }
}


    $(function (){
    $('#create_account_id').focusout(function () {
        if ($('#create_account_id').val() === '') {
            $('#validate_id_message').html('아이디를 입력하세요')
        } else {
            $.ajax({
                type: 'post',
                url: '/memberMVC_war/member/checkId.do',
                data: 'create_account_id=' + $('#id').val(), //서버로 보낼데이터
                dataType: 'text',
                success: function (data) {
                    alert(data.trim());
                    if (data.trim() === 'validate') {
                        $('#idDiv').html('사용 불가능').css('color', 'red');
                    } else if (data.trim() === 'non_validate') {
                        $('#validate_id_message').html('사용 가능').css('color', 'blue');
                    }
                },
                error: function (e) {
                    console.log(e);
                }
            });
        }
    })
})


/*
function login() {
    let id_check_message = $('#id_check_message');
    let password_check_message = $('#password_check_message');
    let login_id = $('#login_id');
    let login_password = $('#login_password');

    id_check_message.text("");
    password_check_message.text("");

    if(login_id.val() === ""){
        login_id.focus();
        id_check_message.text('아이디를 입력해주세요');
    }else if(login_password.val() === ""){
        login_password.focus();
        password_check_message.text('비밀번호를 입력해주세요');
    }else{
        $.ajax({
            url: '/login', // 요청을 보낼 URL
            type: 'POST',  // HTTP 메소드
            data: {  // 서버에 전송할 데이터
                id: login_id.val(),
                password: login_password.val()
            },
            success: function(response) {
                // 요청이 성공적으로 완료됐을 때 실행할 함수
                // response는 서버에서 보낸 데이터입니다.
                // 요기서 로그인이 성공적으로 처리되었는지, 실패했는지에 따라 다른 동작을 수행하면 됩니다.
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // 요청이 실패했을 때 실행할 함수
                // jqXHR는 jQuery XMLHttpRequest 객체, textStatus는 상태 정보 문자열, errorThrown은 예외 정보입니다.
            }
        });
    }
}

 */