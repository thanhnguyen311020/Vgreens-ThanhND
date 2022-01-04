app.controller("order-track-ctrl", function ($scope, $http,$interval, $filter,$window) {

    $scope.items =[];
    $scope.statusinfo =[];
    $scope.pageSize=8;

    $scope.findallOrderTrack = function(){
        $http.get("/vgreens/rest/ordertrackAdmin/findall").then(resp => {
            $scope.items = resp.data;

            console.log($scope.items)
          });
    }

    $scope.selectItem = function (item) {
        $scope.clickedItem = angular.copy(item);
        $scope.message = $scope.clickedItem.id;
        console.log("Status:" , $scope.clickedItem.status)
        $scope.checkstatus($scope.clickedItem.status);
        // $scope.name = item.first_name + " " + item.last_name;
        // $scope.phone = item.phone_number
        $scope.status = item.status
    }
    $scope.updateStatus = function(orderId,status){
        $http.post(`/vgreens/rest/ordertrackAdmin/order_shipper/update/${$scope.clickedItem.id}/${status}`).then(resp => {
            $scope.statusinfo = resp.data;

            console.log($scope.items)
            $window.location.reload();
          }).catch(error => {
            console.log("Error", error);
          });
    }
    $scope.findallOrderTrack();

    $scope.sort = function(keyname){
        $scope.sortKey = keyname; // set the sortKey to the param passed
        $scope.reverse = !$scope.reverse // if true make it fasle and vice versa
    }

    $scope.getDateTimeLocal = function(idElement){ 
        let datetime = new Date($(idElement).val());  
        
        return  datetime.getFullYear() + '-' + (datetime.getMonth()+1) + '-' + datetime.getDate() + ' '
        + datetime.getHours() + ':' + datetime.getMinutes() + ':' + datetime.getSeconds();
    }

    $scope.ordernew =0;
    $scope.countOrder = function(status){
        let url = `/vgreens/rest/orderAdmin/countOrder?status=${status}`
        
        $http.get(url).then(resp =>{
            $scope.ordernew = resp.data;
      
        })
        
    }
    $scope.countOrder(2);
    $interval( function(){$scope.countOrder(2)}, 5000);

    $scope.edit = function (item) {
        $scope.checkstatus(item.status);
        $scope.clickedItem = angular.copy(item);
        $scope.message = $scope.clickedItem.id;
        console.log("Status:" , $scope.clickedItem.status)
      
        // $scope.name = item.first_name + " " + item.last_name;
        // $scope.phone = item.phone_number
        // $scope.status = item.status
       
        $('#updatestatus2').modal('show');
    
    }


    $scope.baseUrl =  '/vgreens/rest/orderAdmin'
    $scope.orders =[];
    $scope.getOrderByStatus = function (status) {
        $('#search').val('');
        $scope.search = ''
      $http.get(`/vgreens/rest/orderAdmin?status=${status}`).then(resp =>{
          $scope.orders = resp.data;
      })
    }

    $scope.getOrderByStatus(2);
    $scope.orderAll =[]
    $scope.findAllOrderBetweenDate = function(){
        $('#search').val('');
        $scope.search = ''
        let startDate = $scope.getDateTimeLocal('#txtStartDateTimeOrder');
        let endtDate = $scope.getDateTimeLocal('#txtEndDateTimeOrder');
        console.log("Start-End", startDate + '-' + endtDate);
        let url = $scope.baseUrl+`/findAll?startDate=${startDate}&endDate=${endtDate}`;
        $http.get(url).then(resp =>{
            $scope.orderAll = resp.data;
            $scope.pageCount = Math.ceil($scope.orderAll.length / $scope.pageSize);
            console.log("Page Count",$scope.pageCount)
        })
    }


    $scope.findAllOrderBetweenDateFillterStatus = function(status){
        document.querySelector('.load-form-container').classList.add('fade-in-form');
        $('#search').val('');
        $scope.search = ''
        let startDate = $scope.getDateTimeLocal('#txtStartDateTimeOrder');
        let endtDate = $scope.getDateTimeLocal('#txtEndDateTimeOrder');
        console.log("Start-End", startDate + '-' + endtDate);
        let url =$scope.baseUrl+ `/findByDateAndStatus?startDate=${startDate}&endDate=${endtDate}&status=${status}`;
        $http.get(url).then(resp =>{
            $scope.orderAll = resp.data;
            $scope.pageCount = Math.ceil($scope.orderAll.length / $scope.pageSize);
            console.log("Page Count",$scope.pageCount)
        }).finally(function () {
			document.querySelector('.load-form-container').classList.remove('fade-in-form');
		});
    }
    



    // Phân trang

    $scope.page =0;
    $scope.begin = 0;
    $scope.pageSize = 10;

    // $scope.pageCount = Math.ceil($scope.orderDetails.length / $scope.pageSize);
  

   

    $scope.repaginate = function () {
        $scope.page =0;
        $scope.begin = 0;
        $scope.pageCount = Math.ceil($scope.orderAll.length / $scope.pageSize);
    }

    // $scope.sortBy = function (prop) {
    //     $scope.prop = prop;
    //     $scope.reverse = !$scope.reverse
    // }

    $scope.sort = function (keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	}

    $scope.first = function () {
        $scope.begin = 0;
    }

    $scope.prev = function () {
        if ($scope.begin > 0) {
            $scope.begin -= $scope.pageSize;
            // $scope.page = $scope.page - 1 <0 ? 0 : $scope.page - 1;
        }
    }

    $scope.next = function () {
        console.log("Next", $scope.begin < ($scope.pageCount - 1) * $scope.pageSize)
        if ($scope.begin < ($scope.pageCount - 1) * $scope.pageSize) {
            $scope.begin += $scope.pageSize;

            // $scope.page = $scope.page + 1 > $scope.pageCount ? $scope.pageCount : $scope.page + 1;
        }
    }

    $scope.last = function () {
        $scope.begin = ($scope.pageCount - 1) * $scope.pageSize;
        console.log("end:" , $scope.begin)
        console.log("end:" , $scope.pageCount)
    }


    $scope.checkstatus = function (status) {
        let text = ""
        let title =""
        if( status == 0){
            text = "Xác nhận đơn hàng"
            $("#btnUpdate").show()
        }
        else if (status == 1) {
            text = "Tiến hành đóng gói"
            $("#btnUpdate").show()
        } else if (status == 2) {
            text = "Xác nhận vận chuyển"
            console.log("true")
            $("#btnUpdate").show()
        } else if (status == 3) {
            text = "Giao hàng thành công"
            $('#btnFail').text("Giao hàng thất bại")
            $("#btnUpdate").show()
        } else if (status == 4) {
            // text = "Đang giao hàng"
            $("#btnUpdate").hide()
        }else{
            $("#btnUpdate").hide()
        }
        $("#btnUpdate").text(text)

    }


});