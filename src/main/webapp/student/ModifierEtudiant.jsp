<%@ page import="Models.Etudiant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier Étudiant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2 class="text-center mb-4">Modifier un Étudiant</h2>
        <% Etudiant etudiant = (Etudiant) request.getAttribute("etudiant"); %>
        <form action="etudiants" method="POST">
            <input type="hidden" name="page" value="modifier">

            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" name="id" class="form-control" value="<%= etudiant.getId() %>" readonly>
            </div>

            <div class="mb-3">
                <label class="form-label">Nom</label>
                <input type="text" name="name" class="form-control" value="<%= etudiant.getNom() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Prénom</label>
                <input type="text" name="prenom" class="form-control" value="<%= etudiant.getPrenom() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" value="<%= etudiant.getEmail() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Date de naissance</label>
                <input type="date" name="datenaissance" class="form-control" value="<%= etudiant.getDatenaissance() %>" required>
            </div>

            <button type="submit" class="btn btn-success w-100">Modifier</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
