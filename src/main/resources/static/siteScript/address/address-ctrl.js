app.controller("address-ctrl", function ($http, $scope,$window){
    $scope.items = [];
	$scope.itemscreate = [];
	$scope.form ={};
	$scope.countries = [];

	$scope.initialize = function () {
        $http.get("/vgreens/rest/country/list").then(resp => {
          $scope.countries = resp.data;
          console.log($scope.countries);
        });
      }
	$scope.initialize();
    $scope.getAddressById = function (id) {
		
		$http.get(`/vgreens/rest/address/${id}`).then(resp => {
			$scope.items = resp.data;
			$scope.form =angular.copy($scope.items);
			console.log("Success: ", $scope.items);
			
		}).catch(error => {
			console.log("Error: ", error);
	
		

		})
	}
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/vgreens/rest/address/${item.id}`, item).then(resp => {
			
		
			$scope.reset();
			console.log("Success: ", $scope.items);
			$window.location.href = "http://localhost:8080/vgreens/account/address";
		}).catch(error => {
			
			console.log("Error Update" + error);
		})
	}
	$scope.create = function () {	
		var item = angular.copy($scope.form);
		$http.post("/vgreens/rest/address", item).then(resp => {
			$scope.itemscreate.push(resp.data);
			$scope.reset();
			$window.location.href = "http://localhost:8080/vgreens/account/address";
			// $scope.sweetalert2("Thêm mới thành công",'','success')
			// $scope.reset()
		}).catch(error => {
		
			// $scope.sweetalert2("Thêm mới thất bại",'Kiểm tra lại các trường dữ liệu của bạn','error')
			console.log("Error", error);
		})
	}
	$scope.reset = function () {
        $scope.form = {
        
        }
        
      }

	
});