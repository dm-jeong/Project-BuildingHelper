function send_message(event, element) {
	var msg_sender_house_dong = $("#msg_sender_house_dong").val(); // 보내는 동을 가져옵니다.
	var msg_sender_house_hosu = $("#msg_sender_house_hosu").val(); // 보내는 호수를 가져옵니다.
	var msg_title = $("#msg_title").val(); // 메시지 제목을 가져옵니다.
	var msg_content = $("#msg_content").val(); // 메시지 내용을 가져옵니다.
	var msg_receiver_house_dong = $("#msg_receiver_house_dong").val(); // 받는 동을 가져옵니다.
	var msg_receiver_house_hosu = $("#msg_receiver_house_hosu").val(); // 받는 호수를 가져옵니다.

	var current_url = window.location.href; // 현재 URL을 변수에 대입합니다.
	var destinaiton_url = current_url.replace('send', 'send_message');
	
	$.ajax({
		type: 'POST',
		url: destinaiton_url, // 컨트롤러 매핑 URL
		data: { // 전달할 데이터입니다.
			"msg_sender_house_dong": msg_sender_house_dong,
			"msg_sender_house_hosu": msg_sender_house_hosu,
			"msg_title": msg_title,
			"msg_content": msg_content,
			"msg_receiver_house_dong": msg_receiver_house_dong,
			"msg_receiver_house_hosu": msg_receiver_house_hosu,
		},
		success: function(response) {
			// 성공 처리 로직이 필요합니다.
			if (response == 'redirect:/message/send') {
				alert(msg_receiver_house_dong + "동" + msg_receiver_house_hosu + "호로 메시지가 전송되었습니다.");
				setTimeout(function () {window.location.reload();}, 100);
			} else {
				alert('<에러 발생가 발생했습니다.>빌딩 헬퍼를 사용하지 않는 집입니다. 직접 찾아가서 말씀 나누시는건 어떨까요?');
			}
		},
		error: function(request, status, error) {
			// 에러 발생 시 사용자에게 알림을 줍니다.
			alert('<알 수 없는 에러가 발생했습니다.\n잠시 후 다시 실행해주세요.>');
		}
	});
}