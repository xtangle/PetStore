<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Add Pet</title>
  <jsp:include page="common/head.jsp"></jsp:include>
  <link rel="stylesheet" type="text/styles" href="${pageContext.request.contextPath}/assets/styles/add_pet.css">
  <script src="${pageContext.request.contextPath}/assets/js/petApp.js"></script>
</head>

<body>
  <div class="outer">
    <div class="middle">
      <div class="inner">
  
        <div class="main-box centered" data-ng-app="petApp" data-ng-controller="petController">
          <h1 class="main-header">Add a Pet</h1>
          
          <p class="success-msg" data-ng-show="successMsg">{{successMsg}}</p>
          <p class="error-msg" data-ng-show="errorMsg">{{errorMsg}}</p>

          <div id="pet-form">
            <p class="error-msg validation-error-msg" data-ng-show="petNameValidationErrorMsg">
              {{petNameValidationErrorMsg}}
            </p>
            <div>
              <label>Pet name: </label>
              <input type="text" class="text-input" data-ng-model="petName" />
            </div>

            <p class="error-msg validation-error-msg" data-ng-show="petCategoryNameValidationErrorMsg">
              {{petCategoryNameValidationErrorMsg}}
            </p>
            <div>
              <label>Category: </label>
              <select class="select-input" data-ng-model="petCategoryName">
                <c:forEach var="category" items="${categories}">
                  <option>${category.name}</option>
                </c:forEach>
              </select>
            </div>
            
            <p class="error-msg validation-error-msg" data-ng-show="petStatusValidationErrorMsg">
              {{petStatusValidationErrorMsg}}
            </p>
            <div>
              <label>Status: </label>
              <select class="select-input" data-ng-model="petStatus">
                <c:forEach var="statusType" items="${petStatusTypes}">
                  <option value="${statusType}">${statusType.name}</option>
                </c:forEach>
              </select>
            </div>
            
            <div>
              <label>Tags: </label>
              <textarea class="textarea-input" data-ng-model="petTagsString"
                rows="3"></textarea>
              <p class="input-hint">(Separate tags with ';')</p>
            </div>
            
            <div>
              <label>Photo URLs: </label>
              <textarea class="textarea-input" data-ng-model="petURLsString"
                rows="3" wrap="off"></textarea>
              <p class="input-hint">(Enter one URL per line)</p>
            </div>
          </div>
          
          <br/>
          <a href="index"><button type="button" class="btn btn-default btn-lg">Back</button></a>
          <a href="" data-ng-click="addPet();">
            <button type="button" class="btn btn-success btn-lg">Add Pet</button>
          </a>
          
        </div>

      </div>
    </div>
  </div>
</body>
</html>