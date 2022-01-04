app.controller("review-ctrl", function ($scope, $http) {

    $scope.items = [];
    $scope.reviewGroupProduct = [];

    $scope.pageSize = 10;
    // $scope.pageSize1 = 5;
    // $scope.sort = function (keyname) {
    //     $scope.sortKey = keyname; // set the sortKey to the param passed
    //     $scope.reverse = !$scope.reverse // if true make it fasle and vice versa
    // }

    $scope.initialize = function () {

        $http.get('/vgreens/rest/product-review').then(resp => {
            $scope.items = resp.data;
            console.log($scope.items)
        }).catch(error => {
            console.log("Error: ", error);
        })

        $http.get('/vgreens/rest/product-review/group-product').then(resp => {
            $scope.reviewGroupProduct = resp.data;

        }).catch(error => {
            console.log("Error: ", error);
        })

    }

    $scope.initialize();

    const starsTotal = 5;
    $scope.getRatingsProducts = function () {

        for (let rating in $scope.reviewGroupProduct) {

            //Lấy % rating
            const starPercentage = ($scope.reviewGroupProduct[rating].ratingCount / starsTotal) * 100;
            console.log("Get percent", starPercentage)
            //Làm tròn đến 10 gần nhất
            const starPercentageRounded = `${Math.round(starPercentage / 10) * 10}%`;
            console.log(starPercentageRounded)
            console.log($scope.reviewGroupProduct[rating].product.id)
            document.querySelector(`.p-${$scope.reviewGroupProduct[rating].product.id} .stars-inner`).style.width = starPercentageRounded;
        }

    }

    $scope.getRatingsProductByCustomer = function () {

        for (let xrating in $scope.items) {

            //Lấy % rating
            const starPercentage = ($scope.items[xrating].rating / starsTotal) * 100;
            console.log("Get percent", starPercentage)
            //Làm tròn đến 10 gần nhất
            const starPercentageRounded = `${Math.round(starPercentage / 10) * 10}%`;

            document.querySelector(`.r-${$scope.items[xrating].id} .stars-inner`).style.width = starPercentageRounded;
        }
    }

    $scope.selectItem = function (item) {
        $scope.clickedItem = angular.copy(item);
        console.log($scope.clickedItem);
        $scope.message = $scope.clickedItem.username;
    }

    $scope.delete = function (item) {
		$http.delete(`/vgreens/rest/product-review/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			$scope.sweetalert2("Xóa thành công",'','success')
			// $scope.reset();
		}).catch(error => {
			console.log("Delete Error: ", error);
			$scope.sweetalert2("Xóa thất bại",'','error')
		})
	}

    $scope.sweetalert2 = function (title, mess, type) {
        Swal.fire(
          title,
          mess,
          type
        )
      }

    // chạy khi tài liệu web và DOM được load
    angular.element(document).ready(function () {
        console.log('page loading completed');
        $scope.getRatingsProducts()
        $scope.getRatingsProductByCustomer()
    });

    

});

