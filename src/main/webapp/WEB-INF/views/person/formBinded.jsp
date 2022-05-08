<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="person">
    <div>
        <label for="login">Login</label>
        <form:input path="login" id="login" type="text"/>
    </div>
    <div>
        <label for="password">Password</label>
        <form:input path="password" id="password" type="password"/>
    </div>
    <div>
        <label for="email">Email</label>
        <form:input path="email" id="email" type="email"/>
    </div>
    <div>
        <input type="submit">
    </div>
</form:form>
</body>
</html>
