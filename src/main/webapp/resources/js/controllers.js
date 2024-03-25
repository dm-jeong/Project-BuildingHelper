document.getElementById('img_name').addEventListener('change', function(event) {
	const preview = document.getElementById('preview');
	preview.innerHTML = ''; // 기존 미리보기 제거

	const files = event.target.files;

	for (const file of files) {
		if (file.type.startsWith('image/')) {
			const reader = new FileReader();

			reader.onload = function(e) {
				const img = document.createElement('img');
				img.src = e.target.result;
				img.style.maxWidth = '200px';
				img.style.maxHeight = '200px';
				img.style.margin = '3px';
				img.style.cursor = 'pointer';

				// 클릭 이벤트 추가
				img.addEventListener('click', function() {
					// 클릭된 이미지 삭제
					preview.removeChild(img);
					// 해당 이미지 파일도 삭제
					removeFileFromInput(file);
				});

				preview.appendChild(img);
			};

			reader.readAsDataURL(file);
		}
	}

	preview.style.display = 'flex';
	preview.style.flexWrap = 'wrap';
});

// 수정된 함수: input에서 특정 파일을 제거하는 함수
function removeFileFromInput(file) {
	const input = document.getElementById('img_name');

	// 현재 input에 선택된 파일 목록
	const currentFiles = Array.from(input.files);

	// 제거할 파일을 제외한 나머지 파일들을 새로운 파일 목록으로 설정
	const newFiles = currentFiles.filter((currentFile) => currentFile.name !== file.name);

	// input의 파일 목록을 갱신
	input.files = newFiles;
}








// 삭제         
function confirmDelete() {
	return confirm("삭제하시겠습니까?");
}


// 필드가 비어있는지 확인
function validateForm() {
    // Check if the required fields are not empty
    var buildingType = document.getElementById("building_type").value;
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    var imgName = document.getElementById("img_name").value;

    if (buildingType.trim() === "" || title.trim() === "" || description.trim() === "" || imgName.trim() === "") {
        alert("모든 필수 항목을 입력하세요.");
        return false;
    }
    return true;
}

window.onload = function(){
	
document.addEventListener('DOMContentLoaded', function () {
        var statusBadges = document.querySelectorAll('.statusBadge');

        statusBadges.forEach(function (statusBadge) {
            changeStatus(statusBadge);
        });

        function changeStatus(statusBadge) {
            var salesStatus = statusBadge.textContent.trim();

            switch (salesStatus) {
                case '판매중':
                    statusBadge.classList.remove("bg-secondary", "bg-primary");
                    statusBadge.classList.add("bg-success");
                    break;
                case '예약중':
                    statusBadge.classList.remove("bg-secondary", "bg-success");
                    statusBadge.classList.add("bg-primary");
                    break;
                case '판매완료':
                    statusBadge.classList.remove("bg-success", "bg-primary");
                    statusBadge.classList.add("bg-secondary");
                    break;
            }
        }
    });
}

