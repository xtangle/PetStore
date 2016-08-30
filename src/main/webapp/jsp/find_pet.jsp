<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Find Pet</title>
  <jsp:include page="common/head.jsp"></jsp:include>
  <link rel="stylesheet" type="text/styles" href="${pageContext.request.contextPath}/assets/styles/find_pet.css">
  <script src="${pageContext.request.contextPath}/assets/js/petApp.js"></script>
</head>

<body>
  <div class="outer">
    <div class="middle">
      <div class="inner">
  
        <div class="main-box centered" data-ng-app="petApp" data-ng-controller="petController">
          <h1 class="main-header">Find a Pet</h1>

          <p class="success-msg" data-ng-show="successMsg">{{successMsg}}</p>
          <p class="error-msg" data-ng-show="errorMsg">{{errorMsg}}</p>
          
          <div>
            <p>Enter the Pet Id: <input type="text" data-ng-model="petId"></p>
            
            <div id="pet-data" data-ng-show="fetchedPet">
              <div>
                <label>Id: </label>
                <span>{{fetchedPet.id}}</span>
              </div>
              
              <div>
                <label>Name: </label>
                <span>{{fetchedPet.name}}</span>
              </div>
              
              <div>
                <label>Category: </label>
                <span>{{fetchedPet.category.name}}</span>
              </div>
              
              <div>
                <label>Tags: </label>
                <span data-ng-repeat="tag in fetchedPet.tags">{{tag.name}}{{$last ? '' : ', '}}</span>
              </div>
              
              <div>
                <label>Status: </label>
                <span>{{fetchedPet.status | capitalizeFirstLetter}}</span>
              </div>
              
              <div>
                <label>Photos: </label>
                <span data-ng-repeat="url in fetchedPet.photoURLs">
                  <a href="{{url}}" target='_blank'>{{$index+1 | padZeros:2}}</a>{{$last ? '' : ', '}}
                </span>
              </div>
              
              <div>
                <a href="" data-ng-click="deletePet();">
                  <button id="delete-pet-button" type="button" class="btn btn-danger btn-xs">
                    Delete Pet</button>
                </a>
              </div>
            </div>
          </div>
          
          <br/>
          <a href="index"><button type="button" class="btn btn-default btn-lg">Back</button></a>
          <a href="" data-ng-click="getPet();">
            <button type="button" class="btn btn-info btn-lg">Search</button>
          </a>
        </div>

      </div>
    </div>
  </div>
</body>
</html>