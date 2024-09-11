
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        #alert-container {
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1050; /* Ensure it appears above other content */
        }
    </style>
</head>
<body>
    <div id="alert-container">
        <c:if test="${sessionScope.success != null}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Success!</strong> ${sessionScope.success}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:remove var="success" scope="session"/>
        </c:if>
        <c:if test="${sessionScope.error != null}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Error!</strong> ${sessionScope.error}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:remove var="error" scope="session"/>
        </c:if>
    </div>

   

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
