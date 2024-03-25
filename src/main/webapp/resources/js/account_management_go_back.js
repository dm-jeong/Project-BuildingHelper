// 계정 정보 변경 페이지에서 뒤로 가는 함수입니다.
function account_management_go_back() {
    // 현재 주소
    var currentUrl = window.location.href;
    
    // 원하는 페이지로 바꿀 주소
    var newUrl = currentUrl.replace("/resident/account_management/home", "");

    // 페이지 이동
    window.location.href = newUrl;
}