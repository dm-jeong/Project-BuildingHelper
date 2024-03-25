function send_group_message(event, element) {
	alert("send_group_message.js 테스트");
	var msg_group_title = $("#msg_group_title").val(); // 메시지 제목을 가져옵니다.
	var msg_group_content = $("#msg_group_content").val(); // 메시지 내용을 가져옵니다.

	var current_url = window.location.href; // 현재 URL을 변수에 대입합니다.
	var destinaiton_url = current_url.replace('send_group_message', 'send_group_message_process');
	alert("현재 URL : " + current_url + "입니다.");
	alert("목표 URL : " + destinaiton_url + "입니다.");
	$.ajax({
		type: 'POST',
		url: destinaiton_url, // 컨트롤러 매핑 URL
		data: {
			"msg_group_title": msg_group_title,
			"msg_group_content": msg_group_content,
		},
		success: function(response) {
			// 성공시 처리 로직입니다.
			if (response == 'redirect:/message/send_group_message_process') {
				alert("단체 메시지가 전송되었습니다.");
				setTimeout(function () {window.location.reload();}, 100);
			} else {
				alert('<에러 발생가 발생했습니다.>');
			}
		},
		error: function(request, status, error) {
			// 에러 발생 시 사용자에게 알림을 줍니다.
			alert('<알 수 없는 에러가 발생했습니다.\n잠시 후 다시 실행해주세요.>');
		}
	});
}