<%@ page import="Models.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Etudiant" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Enrollment</title>
</head>
<body>

<%
    Course course= (Course) request.getAttribute("course");
    List<Etudiant> etudiants=(List<Etudiant>) request.getAttribute("etudiants");
%>
Nom du cours : <%= course.getTitle() %>  <br>
description du cours : <%=  course.getDescription() %>  <br>

Liste des etudiants inscrit dans ce cours :


<% for(Etudiant etudiant: etudiants) { %>

<%= etudiant.getNom()%>  <br>
<%= etudiant.getPrenom()%> <br>
<%= etudiant.getEmail()%>  <br>

<% }%>



</body>
</html>