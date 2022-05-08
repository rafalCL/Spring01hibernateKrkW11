<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>
<form:form method="post" modelAttribute="book">
    <div>
        <label for="title">Title</label>
        <form:input path="title" id="title" type="text"/>
    </div>
    <div>
        <label for="rating">rating</label>
        <form:input path="rating" id="rating" type="number"/>
    </div>
    <div>
        <label for="description">description</label>
        <form:textarea path="description" id="description"/>
    </div>
<%--    <div>--%>
<%--        <label for="publisher">publisher</label>--%>
<%--        <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id" id="publisher"/>--%>
<%--    </div>--%>
    <div>
        <input type="submit">
    </div>
</form:form>
</body>
</html>
