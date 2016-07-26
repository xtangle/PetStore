<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Pet Store</title>
  <jsp:include page="common/head.jsp"></jsp:include>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
</head>

<body>
  <div class="outer">
    <div class="middle">
      <div class="inner">
  
        <div id="welcome-box" class="centered">
          <h1 id="welcome-header">Welcome to Pet Store!</h1>
          
          <img id="welcome-image" src="${pageContext.request.contextPath}/static/images/welcome_image.jpg" 
            alt="Home Image">
          
          <a href="addPet"><button type="button" class="btn btn-success btn-lg">Add a Pet</button></a>
          <a href="findPet"><button type="button" class="btn btn-info btn-lg">Find a Pet</button></a>
        </div>

      </div>
    </div>
  </div>
</body>
</html>