<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
<title>로그인 페이지</title>
</head>
<body>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="main.jsp">
				<img src="img/premium-icon-letter-h-5540593.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
				<span class="navbar-brand mb-0 h1">Hotels 4TEAM</span>
			</a>
			<div class="d-flex justify-content-end">
				<div class="dropdown mr-2">
					<a class="btn btn-outline-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-expanded="false">
						도움말
					</a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<a class="dropdown-item" href="#">공지 사항</a>
						<a class="dropdown-item" href="#">문의 사항</a>
						<a class="dropdown-item" href="#">FAQ</a>
					</div>
				</div>
				<a class="btn btn-primary mr-2" href="join.jsp">회원가입</a>
				<a class="btn btn-primary mr-2" href="login.jsp">로그인</a>
			</div>
		</div>
	</nav>
</header>

<main>
	<div class="container">
		<div class="jumbotron mt-5">
			<h2>여행지 검색</h2>
			
			<div class="row">
				<div class="col-lg-5 col-md-7 mx-auto">
					<div class="bg-light p-5 mt-5">
						<form action="<%=request.getContextPath() %>/login" method="post">
							<h3 class="text-center mb-3">로그인</h3>
							<input type="text" class="form-control mb-3" name="customerID" placeholder="아이디" maxlength="20" required>
			          		<input type="password" class="form-control mb-3" name="customerPassword" placeholder="비밀번호" maxlength="20" required>
			          		<input type="submit" class="btn btn-primary form-control mb-3" value="로그인">
						</form>
					</div>
				</div>
			</div>
			
		</div>
	</div>	
</main>

<footer>

</footer>

</body>
</html>