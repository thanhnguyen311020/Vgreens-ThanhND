app.controller('review-ctrl', function ($scope, $http) {
    $scope.reviews = [];
    $scope.form = {};
    

   
    // create review

   
    const orderid = document.getElementById('orderDetailId').value;
    const type = document.getElementById('id-product').value;

    $scope.selectItem = function (id) {
        $scope.clickedItem = angular.copy(id);
        $scope.product_id =  $scope.clickedItem
       

        console.log($scope.product_id);
    },

    $scope.create = function () {
        var item = angular.copy($scope.form);
        
        
        $http.post(`/vgreens/rest/review/${orderid}/${$scope.product_id}`, item).then(resp => {
            $scope.reviews.push(resp.data);
            $scope.reset();
            console.log(resp.data);
            
            let add = {
                title: "Thành công",
                message: "Đánh giá thành công",
                status: TOAST_STATUS.SUCCESS,
                timeout: 1000
            }
            Toast.create(add);

        });
      
    }
    $scope.reset = function (item) {
        $scope.form = {
            rating: 0,

        };
    }

   

})