<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>원하는 구구단의 숫자?</h1>
    <input type="text" />
    <button onclick="sendServer();">계산하기</button>
    <div id="output"></div>
    <script type="text/javascript">
      const input = document.querySelector('input[type="text"]'); // 태그 타입으로 선택
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // Ajax request 객체 생성

      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let v = input.value; // input 창에 입력한 값
        let url = 'gugu.jsp?val=' + v; // 요청 할 jsp페이지 주소
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = function () {
          if (request.readyState == 4 && request.status == 200) {
            let val = request.responseText; // 요청한 결과 받기
            console.log(val);
            output.innerHTML = val;
          }
        };
      }
    </script>
  </body>
</html>
