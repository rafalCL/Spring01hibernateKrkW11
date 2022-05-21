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
        <form:errors path="title"/>
    </div>
    <div>
        <label for="rating">rating</label>
        <form:input path="rating" id="rating" type="number"/>
        <form:errors path="rating"/>
    </div>
    <div>
        <label for="description">description</label>
        <form:textarea path="description" id="description"/>
        <form:errors path="description"/>
    </div>
    <div>
        <label for="publisher">publisher</label>
        <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id" id="publisher"/>
        <form:errors path="publisher"/>
    </div>
    <div>
        <label for="authors">authors</label>
        <form:select path="authors" items="${authors}" itemLabel="name" itemValue="id" id="authors" multiple="true"/>
        <form:errors path="authors"/>
    </div>
    <div>
        <label for="pages">pages</label>
        <form:input path="pages" id="pages" type="number"/>
        <form:errors path="pages"/>
    </div>
    <div>
        <input type="submit">
    </div>
    <form:errors path="*"/>
</form:form>
</body>
</html>
