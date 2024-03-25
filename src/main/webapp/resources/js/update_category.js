// 버튼을 클릭할 경우 r_category를 수정합니다.
function update_category(r_login_id, r_category) {
	// 현재 URL 획득
    var url = window.location.href; 
    // URL 문자열 가공
    var lastIndex = url.lastIndexOf('/');
    var result = url.substring(0, lastIndex);
    
	// 목적 URL 설정
    var destination_url = result + "/update/" + r_login_id;
    // XMLHttpRequest 객체 생성
    var xhr = new XMLHttpRequest();
    
	if(r_category === "세대원")
	{
		if(confirm("세대원을 동대표로 변경하겠습니까?")) {
			alert("세대원->동대표")
			// 요청 준비
    		xhr.open("PUT", destination_url);
    		// 요청 전송
    		xhr.send();
    		setTimeout(function () {window.location.reload();}, 100);
		};
	} else if(r_category === "동대표"){
		if(confirm("동대표를 세대원으로 변경하겠습니까?")) {
			alert("동대표->세대원");
			// 요청 준비
    		xhr.open("PUT", destination_url);
    		// 요청 전송
    		xhr.send();
    		// window.location.reload()로 할 경우 동작이 안되서 딜레이를 주었습니다.
    		setTimeout(function () {window.location.reload();}, 100);
		}
	}
}

