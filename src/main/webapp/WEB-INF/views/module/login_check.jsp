<%@ page contentType="text/html; charset=utf-8"%>
<!-- 2024-02-18-현재 미사용 -->
<section id="login_check">
	<div class="container">
		<h1>로그인 체크용 모듈</h1>
			<p><%= session.getAttribute("user_id") %> </p>
	</div>
</section>