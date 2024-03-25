function move_resident(r_login_id) {
	var user_input = prompt('주의하세요! \n미거주 상태로 변경 시 세대원 목록에서 사라집니다.\n 진행하시려면 "미거주 변경"이라고 입력해주세요', '');
	
	if(user_input === '미거주 변경'){
		alert("미거주로 변경합니다.");
		// 현재 URL 획득
	    var url = window.location.href; 
		// 목적 URL 설정
	    var destination_url = url + "/update_resident/" + r_login_id;
	    // XMLHttpRequest 객체 생성
	    var xhr = new XMLHttpRequest();
	    // 요청 준비
    	xhr.open("PUT", destination_url);
    	// 요청 전송
    	xhr.send();
    	
    	var last_index = url.lastIndexOf("/");
    	var last_index2 = url.substring(0, last_index).lastIndexOf('/');
    	var replace_page = url.substring(0, last_index2);
		
    	setTimeout(function () {window.location.replace(replace_page+"/home");}, 100);
	} else {
		alert("변경이 취소되었습니다.");
	}
}

function move_resident_all_in_house(r_house_id) {
	alert("자바스크립트 연결 확인 : " + r_house_id);
	var user_input = prompt('주의하세요! \n미거주 상태로 변경 시 전체 세대원이 목록에서 사라집니다.\n 진행하시려면 "전체 미거주 변경"이라고 입력해주세요', '');
	if(user_input === '전체 미거주 변경') {
		alert("전체 미거주 변경 테스트");
		// 현재 URL 획득
	    var url = window.location.href; 
		// 목적 URL 설정
	    var destination_url = url + "/update_resident_all/" + r_house_id;
	    // XMLHttpRequest 객체 생성
	    var xhr = new XMLHttpRequest();
	    // 요청 준비
    	xhr.open("PUT", destination_url);
    	// 요청 전송
    	xhr.send();
    	
    	var last_index = url.lastIndexOf("/");
    	var welcome_page = url.substring(0, last_index + 1);
		
    	setTimeout(function () {window.location.replace(welcome_page);}, 100);
	} else {
		alert("변경이 취소되었습니다.");
	}
}

function move_resident_by_office(r_login_id) {
	var user_input = prompt('주의하세요! \n미거주 상태로 변경 시 세대원 목록에서 사라집니다.\n 진행하시려면 "미거주 변경"이라고 입력해주세요', '');
	
	if(user_input === '미거주 변경'){
		alert("미거주로 변경합니다.");
		
		// 현재 URL 획득
	    var url = window.location.href; 
	    // URL 문자열 가공
	    var lastIndex = url.lastIndexOf('/');
	    var result = url.substring(0, lastIndex);
	    
		// 목적 URL 설정
	    var destination_url = result + "/update_move_resident/" + r_login_id;
	    
	    // XMLHttpRequest 객체 생성
	    var xhr = new XMLHttpRequest();
	    // 요청 준비
    	xhr.open("PUT", destination_url);
    	// 요청 전송
    	
    	xhr.send();
		
    	setTimeout(function () {window.location.reload();}, 100);
	} else {
		alert("변경이 취소되었습니다.");
	}
}