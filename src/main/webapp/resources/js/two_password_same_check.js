function compare_pw() {
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    
    if (password1 !== password2) {
        alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
        
        return false;
    }
    return true;
}