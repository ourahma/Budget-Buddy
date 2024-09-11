<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Gestion des Dettes</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/dette.css">
</head>
<body>

<main>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h6 class="display-6" style="font-size:20px;">
            <img alt="dette" src="images/dette.png" height="30" width="30"> Vos Dettes
        </h6>
        <button type="button" class="btn btn-success rounded-circle" data-bs-toggle="modal" data-bs-target="#addDetteModal">+</button>
    </div>
    <table class="table table-striped depenses-table">
        <thead>
            <tr>
                <th scope="col">Nom Dette</th>
                <th scope="col">Description</th>
                <th scope="col">Montant</th>
                <th scope="col">Date de Limite</th>
                <th scope="col">Avancement</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="dette" items="${dettes}">
                <c:set var="initialAmount" value="${dette.intialAmount}" />
                <c:set var="montant" value="${dette.montant}" />
                <c:set var="percentage" value="${(initialAmount / montant) * 100}" />
                <tr>
                    <td>${dette.nom_dette}</td>
                    <td>${dette.description}</td>
                    <td>${dette.montant}</td>
                    <td>${dette.date}</td>
                    <td>
                        <div class="circular-progress" style="--progress: ${percentage}%;">
                            <span>${percentage}%</span>
                        </div>
                    </td>
                    <td>
                        <input type="hidden" name="id_dette" value="${dette.id_dette}" />
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifyDetteModal" 
                            data-id="${dette.id_dette}" data-nom="${dette.nom_dette}" 
                            data-initialamount="${dette.intialAmount}" 
                            <c:if test="${dette.completed}">disabled</c:if>
                            data-description="${dette.description}" data-montant="${dette.montant}"
                             data-date="${dette.date}">
                            Modifier
                        </button>
                        <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteDetteModal" data-id="${dette.id_dette}">
                            Supprimer
                        </button>
                        <button class="btn btn-success" data-bs-toggle="modal" 
                        data-bs-target="#addMontantModal" 
                        data-montant="${dette.montant}"
                        <c:if test="${dette.completed}">disabled</c:if>
                        data-id="${dette.id_dette}">
                            Ajouter d'argent
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>

<!-- Modal for adding debt -->
<div class="modal fade" id="addDetteModal" tabindex="-1" aria-labelledby="addDetteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addDetteModalLabel">Ajouter une Dette</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="AddDetteServlet" method="post">
                    <div class="form-group">
                        <label for="nom_dette">Nom Dette:</label>
                        <input type="text" class="form-control" id="nom_dette" name="nom_dette" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="montant">Montant:</label>
                        <input type="number" class="form-control" id="montant" name="montant" required>
                    </div>
                    <div class="form-group">
                        <label for="date_limite">Date de Limite:</label>
                        <input type="date" class="form-control" id="date_limite" name="date_limite" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for modifying debt -->
<div class="modal fade" id="modifyDetteModal" tabindex="-1" aria-labelledby="modifyDetteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyDetteModalLabel">Modifier la Dette</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ModifierDetteServlet" method="post">
                    <input type="hidden" id="modify_id_dette" name="id_dette">
                    <div class="form-group">
                        <label for="modify_nom_dette">Nom Dette:</label>
                        <input type="text" class="form-control" id="modify_nom_dette" name="nom_dette" required>
                    </div>
                    <div class="form-group">
                        <label for="modify_description">Description:</label>
                        <textarea class="form-control" id="modify_description" name="description" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="modify_montant">Montant:</label>
                        <input type="number" class="form-control" id="modify_montant" name="montant" required>
                        <input type="hidden" class="form-control" id="modify_initialAmount" name="modify_initialAmount" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="modify_date_limite">Date de Limite:</label>
                        <input type="date" class="form-control" id="modify_date_limite" name="date_limite" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Modifier</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for adding debt -->
<div class="modal fade" id="addDetteModal" tabindex="-1" aria-labelledby="addDetteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addDetteModalLabel">Ajouter une Dette</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="AddDetteServlet" method="post">
                    <div class="form-group">
                        <label for="nom_dette">Nom Dette:</label>
                        <input type="text" class="form-control" id="nom_dette" name="nom_dette" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="montant">Montant:</label>
                        <input type="number" class="form-control" id="montant" name="montant" required>
                    </div>
                    <div class="form-group">
                        <label for="date_limite">Date de Limite:</label>
                        <input type="date" class="form-control" id="date_limite" name="date_limite" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for modifying debt -->
<div class="modal fade" id="modifyDetteModal" tabindex="-1" aria-labelledby="modifyDetteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyDetteModalLabel">Modifier la Dette</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ModifierDetteServlet" method="post">
                    <input type="hidden" id="modify_id_dette" name="id_dette">
                    <div class="form-group">
                        <label for="modify_nom_dette">Nom Dette:</label>
                        <input type="text" class="form-control" id="modify_nom_dette" name="nom_dette" required>
                    </div>
                    <div class="form-group">
                        <label for="modify_description">Description:</label>
                        <textarea class="form-control" id="modify_description" name="description" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="modify_montant">Montant:</label>
                        <input type="number" class="form-control" id="modify_montant" name="montant" required>
                        <input type="hidden" class="form-control" id="modify_initialAmount" name="modify_initialAmount" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="modify_date_limite">Date de Limite:</label>
                        <input type="date" class="form-control" id="modify_date_limite" name="date_limite" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Modifier</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for deleting debt -->
<div class="modal fade" id="deleteDetteModal" tabindex="-1" aria-labelledby="deleteDetteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteDetteModalLabel">Confirmer la Suppression</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr de vouloir supprimer cette dette ?</p>
                <form action="SupprimerDetteServlet" method="post">
                    <input type="hidden" id="delete_id_dette" name="id_dette">
                    <button type="submit" class="btn btn-danger">Supprimer</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for adding amount to debt -->
<div class="modal fade" id="addMontantModal" tabindex="-1" aria-labelledby="addMontantModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addMontantModalLabel">Ajouter Montant à la Dette</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="AjouerMontant" method="post">
                    <input type="hidden" id="add_montant_id_dette" name="id_dette">
                    <div class="form-group">
                        <label for="add_montant">Montant à ajouter:</label>
                        <input type="number" class="form-control" id="add_montant" name="montant" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>
