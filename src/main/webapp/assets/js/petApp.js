var app = angular.module('petApp', []);

app.controller('petController', function($scope, $http) {
	
    $scope.fetchedPet = null;
    
    $scope.getPet = function() {
    	$scope.successMsg=null;
    	$scope.errorMsg=null;
    	if ($scope.petId === "") {
    		$scope.petId = null;
    	}
        $http.get('pet/' + $scope.petId)
        .success(function (data) {
        	$scope.fetchedPet=data;
        })
        .error(function (data, status) {
        	if (status == 400) {
        		$scope.errorMsg="Not a valid id";
        	} else {
        		$scope.errorMsg=data.message;
        	}
        });
    }
    
    $scope.deletePet = function() {
    	if (confirm("Are you sure you want to delete pet '" + $scope.fetchedPet.name + "' (id: " + $scope.fetchedPet.id + ")?")) {
        	$scope.errorMsg=null;
        	$scope.successMsg=null;
    		$http.delete('pet/' + $scope.fetchedPet.id)
            .success(function (data) {
            	$scope.successMsg="Successfully deleted pet '" + $scope.fetchedPet.name + "' (id: " + $scope.fetchedPet.id + ")";
            	$scope.fetchedPet=null;
            	$scope.petId=null;
            })
            .error(function (data, status) {
        		$scope.errorMsg=data.message;
            });
    	}
    }    
    
    $scope.addPet = function() {
    	$scope.successMsg = null;
    	$scope.errorMsg = null;
    	$scope.petNameValidationErrorMsg = null;
    	$scope.petCategoryNameValidationErrorMsg = null;
    	$scope.petStatusValidationErrorMsg = null;
    	
    	if (!$scope.petName) {
    		$scope.petNameValidationErrorMsg = "Enter the pet's name";
    		return;
    	}
    	if (!$scope.petCategoryName) {
    		$scope.petCategoryNameValidationErrorMsg = "Select a category";
    		return;
    	}
    	if (!$scope.petStatus) {
    		$scope.petStatusValidationErrorMsg = "Select a status"
			return;
    	}
    	
    	var petTagsArray = [];
    	var petURLsArray = [];
    	
    	if ($scope.petTagsString) {
    		var petTagNames = $scope.petTagsString.split(';');
    		for (i = 0; i < petTagNames.length; i++) {
    			petTagsArray.push({
    				"id": "0",
    				"name": petTagNames[i]
    			});
    		}
    	}
    	if ($scope.petURLsString) {
    		petURLsArray = $scope.petURLsString.split(/\r|\n/);
    	}
    	
    	var petJSON = {
    			"id": "0",
    			"name": $scope.petName,
    			"category": {
        			"id": "0",
        			"name": $scope.petCategoryName
    			},
    			"status": $scope.petStatus,
    			"tags": petTagsArray,
    			"photoURLs": petURLsArray
    	};
    	
    	$http.post('pet', petJSON)
    	.success(function (data) {
    		$scope.successMsg = "Successfully created pet '" + data.name + "' (id: " + data.id + ")";
    	})
    	.error(function (data, status) {
    		$scope.errorMsg = "Error: cannot create pet";
    	});
    	console.log(petJSON);
    }
});

app.filter('padZeros', function() {
	return function(number, numDigits) {
		if (!numDigits) {
			numDigits = 2;	// Default print 2 digits
		}
		if (number) {
			var str = "" + number;
			while (str.length < numDigits) str = "0" + str;
			return str;
		}
	};
});

app.filter('capitalizeFirstLetter', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
});
