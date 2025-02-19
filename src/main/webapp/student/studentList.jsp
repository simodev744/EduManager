<%@ page import="Models.Etudiant" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Med
  Date: 2/18/2025
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudentList</title>


    <style>

        table,tr,td{
            border: 1px solid black;
        }
    </style>
</head>


<body>

liste des etudiants

<% List<Etudiant> etudiants= (List<Etudiant>) request.getAttribute("etudiants");   %>

<button type="button">
    <a href="etudiants?action=add">Ajouter</a>
</button>

<table>
     <thead>
         <tr >
             <td>Id</td>
             <td>nom</td>
             <td>prenom</td>
             <td>email</td>
             <td>date de naissance</td>
             <td>Modifier</td>
             <td>Supprimer</td>
         </tr>

       </thead>

    <% for(Etudiant etudiant : etudiants){ %>
      <tr>
          <td><%= etudiant.getId() %></td>
          <td><%= etudiant.getNom() %></td>
          <td><%= etudiant.getPrenom() %></td>
          <td><%= etudiant.getEmail() %></td>
          <td><%= etudiant.getDatenaissance() %></td>


           <td>

               <button type="button">
                   <a href="etudiants?action=update&&id=<%= etudiant.getId() %>">Modifier</a>
               </button>

           </td>

          <td>

              <button type="button">
                  <a href="etudiants?action=delete&&id=<%= etudiant.getId() %>">Supprimer</a>
              </button>

          </td>

      </tr>
    <% } %>

</table>

</body>
</html>
