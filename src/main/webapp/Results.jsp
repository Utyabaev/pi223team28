<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Результаты вычисления</title>
</head> 
<body>
<h1>Результаты вычисления (мм)</h1>
<h2>Ваши введеные данные (мм):</h2>
<p><strong>Радиус (мм):</strong> ${first_result}</p>
<p><strong>Образующая (мм):</strong> ${second_result}</p>
<p><strong>Результат (мм):</strong> ${result}</p>

<form action="${pageContext.request.contextPath}/Form.jsp">
    <input type="submit" name="sign" value="Назад">
    </form>

</body>
</html
>