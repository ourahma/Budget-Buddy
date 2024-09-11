<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="ISO-8859-1">
<%@ page import="modele.Utilisateur" %>
</head>
<body>
<%@ include file="components/navbar.jsp" %>
<%@ include file="components/alert.jsp" %>
  <main>
    <div class="container mt-5" style="margin-top:80px">
        <div class="row mt-5">
            <div class="col-12 col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h3 class="card-title">Votre Profile </h3>
                        <a href="Logout" class="btn btn-danger">Déconnexion</a>
                    </div>
                    <div class="card-body">
                       
                        <c:choose>
                        <c:when test="${! empty user }">
                        <form action="UpdateProfile" method="post">
							    <div class="mb-3">
							        <label for="prenom" class="form-label">Prénom</label>
							        <input type="text" class="form-control" id="prenom" name="prenom" value="${user.prenom }" required>
							    </div>
							    <div class="mb-3">
							        <label for="nom" class="form-label">Nom</label>
							        <input type="text" class="form-control" id="nom" name="nom" value="${user.nom }" required>
							    </div>
							    <div class="mb-3">
							        <label for="email" class="form-label">Email</label>
							        <input type="email" class="form-control" id="email" name="email" value="${user.email }" required disabled>
							    </div>
							      <div class="mb-3">
							            <div class="card mt-5">
							                <div class="card-body">
							                    <h5 class="card-title">Votre devise</h5>
							                    <div class="input-group mb-3">
							                        <input type="text" class="form-control" placeholder="Devise" aria-label="Devise" 
							                        value="${deviseuser.pays}-${deviseuser.annotation} ${deviseuser.symbole}"
							                        aria-describedby="change-devise" disabled>
							                        <div class="input-group-append">
							                            <a href="#" id="change-devise" class="input-group-text" data-bs-toggle="modal" data-bs-target="#changeDeviseModal">Changer</a>
							                        </div>
							                    </div>
							                </div>
							            </div>
							        </div>
							    <div class="mb-3">
							        <label for="mdps" class="form-label">Mot de passe</label>
							        <input type="password" class="form-control" id="mdps" name="mdps" required>
							    </div>
							    <div class="mb-3">
							        <label for="confirm-mdps" class="form-label">Confirmer le mot de passe</label>
							        <input type="password" class="form-control" id="confirm-mdps" name="confirm-mdps" required>
							    </div>
							    <button type="submit" class="btn btn-primary" id="updateBtn">Mettre à jour</button>
							</form>
                        </c:when>
                        <c:otherwise>
                      

                       
                        <div class="alert alert-danger" role="alert">
                            Aucune donnée utilisateur trouvée dans la session.
                        </div>
                        
                        </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </main>

  <!-- Modal for changing currency -->
  <div class="modal fade" id="changeDeviseModal" tabindex="-1" aria-labelledby="changeDeviseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="changeDeviseModalLabel">Changer votre devise</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="changeDeviseForm" action="ChangeDevise" method="post">
            <div class="mb-3">
              <label for="devise" class="form-label">Sélectionnez votre devise</label>
              <select class="form-select" id="devise" name="devise">
                <c:forEach var="devise" items="${requestScope.devises}">
                  <option value="${devise.id_devise}">${devise.pays} - ${devise.annotation}</option>
                </c:forEach>
              </select>
            </div>
            <button type="submit" class="btn btn-primary" id="saveDeviseBtn">Enregistrer</button>
          </form>
        </div>
      </div>
    </div>
  </div>

<script type="text/javascript" src="js/profile.js"></script>
<%@ include file="components/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
