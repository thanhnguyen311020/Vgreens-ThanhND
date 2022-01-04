app.controller("order-ctrl", function ($scope, $http, $window) {
    $scope.items = [];

    $scope.orderstatus5 = [];
    $scope.orderstatus1 = [];
    $scope.orderstatus2 = [];
    $scope.orderstatus3 = [];
    $scope.orderstatus4 = [];
    $scope.cancelOrder =[];

    $scope.countorderstatus5
    $scope.countorderstatus1
    $scope.countorderstatus2
    $scope.countorderstatus3
    $scope.countorderstatus4

    $scope.countallorders
    $scope.OrderDetailist = [];
    $scope.pageSize =10;

    $scope.findOrderDetailByOrderId = function (id) {
        $http.get(`/vgreens/rest/orders/orderdetail/findall/${id}`).then(resp => {
            $scope.OrderDetailist = resp.data;
            localStorage.setItem("OrderDetails", $scope.OrderDetailist);


        });
    }


    $scope.CountAllOrders = function () {
        $http.get("/vgreens/rest/orders/countallorders").then(resp => {
            $scope.countallorders = resp.data;


        });
    }


    $scope.findallorders = function () {
        $http.get("/vgreens/rest/orders/findall").then(resp => {
            $scope.items = resp.data;


        });
    }

    $scope.orderstatus5 = function () {
        $http.get("/vgreens/rest/orders/findorderstatus5").then(resp => {
            $scope.orderstatus5 = resp.data;


        });
    }
    $scope.orderstatus1 = function () {
        $http.get("/vgreens/rest/orders/findorderstatus1").then(resp => {
            $scope.orderstatus1 = resp.data;


        });
    }
    $scope.orderstatus2 = function () {
        $http.get("/vgreens/rest/orders/findorderstatus2").then(resp => {
            $scope.orderstatus2 = resp.data;


        });
    }
    $scope.orderstatus3 = function () {
        $http.get("/vgreens/rest/orders/findorderstatus3").then(resp => {
            $scope.orderstatus3 = resp.data;


        });
    }
    $scope.orderstatus4 = function () {
        $http.get("/vgreens/rest/orders/findorderstatus4").then(resp => {
            $scope.orderstatus4 = resp.data;


        });
    }

    $scope.countorderstatus5 = function () {
        $http.get("/vgreens/rest/orders/countstatus5").then(resp => {
            $scope.countorderstatus5 = resp.data;


        });
    }

    $scope.countorderstatus1 = function () {
        $http.get("/vgreens/rest/orders/countstatus1").then(resp => {
            $scope.countorderstatus1 = resp.data;


        });
    }

    $scope.countorderstatus2 = function () {
        $http.get("/vgreens/rest/orders/countstatus2").then(resp => {
            $scope.countorderstatus2 = resp.data;


        });
    }

    $scope.countorderstatus3 = function () {
        $http.get("/vgreens/rest/orders/countstatus3").then(resp => {
            $scope.countorderstatus3 = resp.data;


        });
    }

    $scope.countorderstatus4 = function () {
        $http.get("/vgreens/rest/orders/countstatus4").then(resp => {
            $scope.countorderstatus4 = resp.data;
            console.log("countorderstatus4", $scope.countorderstatus4);

        });
    },

    $scope.selectItem = function (orders) {
        $scope.clickedItem = angular.copy(orders);
        $scope.message = $scope.clickedItem.id;
    },

    $scope.cancelorder = function(id){
        $http.post(`/vgreens/rest/order/cancel/${$scope.clickedItem.id}`).then(resp => {
            $scope.cancelOrder = resp.data;
            $window.location.reload();
          

        });

    },
    // $scope.currency =[];
    // $scope.getTyGia = function(){
    //     var url="http://www.dongabank.com.vn/exchange/export";

    //     $http.get(url).then( resp =>{
    //         $scope.currency = JSON.stringify(resp.data);
         
           
    //     });
    // },

    // $scope.getTyGia();



    $scope.orderstatus5();
    $scope.orderstatus1();
    $scope.orderstatus2();
    $scope.orderstatus3();
    $scope.orderstatus4();

    $scope.countorderstatus5();
    $scope.countorderstatus1();
    $scope.countorderstatus2();
    $scope.countorderstatus3();
    $scope.countorderstatus4();
    $scope.CountAllOrders();

    $scope.findallorders();
});