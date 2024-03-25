function del_register_link(register_url_link) {
	if(confirm("링크를 삭제하시겠습니까?") == true) {
	// 현재 URL 획득
    var url = window.location.href; 
    
	// 목적 URL 설정과 문자열 가공
    var destination_url = url + "/delete_register_link/" + register_url_link;

    // XMLHttpRequest 객체 생성
    var xhr = new XMLHttpRequest();
    
	// 요청 준비
   	xhr.open("DELETE", destination_url);
   	// 요청 전송
    xhr.send();
    // 페이지 새로 고침
    setTimeout(function () {window.location.reload();}, 100);		
		
	} else {
		alert("삭제가 취소되었습니다.");
	}

    
}