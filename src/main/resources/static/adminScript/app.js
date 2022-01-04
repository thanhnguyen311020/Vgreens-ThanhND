var app = angular.module("vgreens-app", ['angularUtils.directives.dirPagination', 'zingchart-angularjs', 'textAngular']);


// $scope.sweetalert2 = function(title, mess, type){
//     Swal.fire(
//         title,
//         mess,
//        type
//       )
// }
app.controller("navbar-ctrl", function ($scope, $http, $filter, $timeout) {
    
    $scope.productCount = 0;
    $scope.countProductByCurentQuantityLessThan10 = function(){
        $http.get("/vgreens/rest/product/countProductLessThan10").then(resp =>{
            $scope.productCount = resp.data;
        })
    }
    $scope.countProductByCurentQuantityLessThan10();
})



