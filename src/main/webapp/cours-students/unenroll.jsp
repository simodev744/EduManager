<%--
  Created by IntelliJ IDEA.
  User: Med
  Date: 2/21/2025
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Formulaire unrolllement d un cours d'un cours

<form action="enrollement" method="post">
    <input type="text" name="action" value="unenroll">
    Etudiant: <input type="text" name="etudiantId"> <br>
    Cours:    <input type="text" name="coursId">

    <button type="submit">unroll</button>

</form>
</body>
</html>
