<%@ page import="Models.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Étudiants</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .table-container {
            max-width: 900px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="table-container">
        <h2 class="text-center mb-4">Liste des Étudiants</h2>

        <div class="mb-3">
            <a href="etudiants?action=add" class="btn btn-primary">Ajouter</a>
        </div>

        <% List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants"); %>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>Id</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Date de naissance</th>
                <th>Modifier</th>
                <th>Supprimer</th>
            </tr>
            </thead>
            <tbody>
            <% for (Etudiant etudiant : etudiants) { %>
            <tr>
                <td><%= etudiant.getId() %></td>
                <td><%= etudiant.getNom() %></td>
                <td><%= etudiant.getPrenom() %></td>
                <td><%= etudiant.getEmail() %></td>
                <td><%= etudiant.getDatenaissance() %></td>
                <td>
                    <a href="etudiants?action=update&&id=<%= etudiant.getId() %>" class="btn btn-warning btn-sm">Modifier</a>
                </td>
                <td>
                    <a href="etudiants?action=delete&&id=<%= etudiant.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
