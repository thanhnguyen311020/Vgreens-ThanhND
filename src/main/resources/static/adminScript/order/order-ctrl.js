

app.controller("order-ctrl", function ($filter, $scope, $http, orderService, $timeout, $interval) {

    $scope.orders = [];
    $scope.details = [];
    $scope.orderAll = [];

    $scope.getDateTimeLocal = function (idElement) {
        let datetime = new Date($(idElement).val());

        return datetime.getFullYear() + '-' + (datetime.getMonth() + 1) + '-' + datetime.getDate() + ' '
            + datetime.getHours() + ':' + datetime.getMinutes() + ':' + datetime.getSeconds();
    }
    console.log($scope.getDateTimeLocal('#txtStartDateTimeOrder'))



    $scope.getOrderByStatus = function (status) {
        $('#search').val('');
        $scope.search = ''
        let url = `?status=${status}`
        orderService.findOrderByStatus(url).then(resp => {
            $scope.orders = resp.data;
            console.log($scope.orders)
        })
    }

    $scope.selectItem = function (item) {
        $scope.clickedItem = angular.copy(item);
        console.log("item", $scope.clickedItem);
        $scope.message = $scope.clickedItem.id;
    }




    $scope.edit = function (item) {
        $scope.checkstatus(item.status);
        // $scope.name = item.first_name + " " + item.last_name;
        // $scope.phone = item.phone_number
        $scope.status = item.status
        $scope.findOrderDetailsByOrderId(item.id);


        $scope.isShow = true;
        $scope.form = angular.copy(item);
        $('#formCreateDate').val($filter('date')(item.createDate, "hh:mm dd/MM/yyyy "));
        console.log("Form:", $scope.form)
        $('#updateStatusModal').modal('show');
        // $(".nav-tabs a:eq(1)").tab("show");
    }

    $scope.updateStatusOrder = function () {
        var item = angular.copy($scope.form)
        console.log("item:" , item);
        let url = `/${item.id}`;
        orderService.updateStatusOrderToDay(url, item).then(resp => {
            var index = $scope.orders.findIndex(p => p.id == item.id);
            $scope.orders.splice(index, 1);
            item.status = item.status +1;
            var indexAll = $scope.orderAll.findIndex(a => a.id == item.id);
            $scope.orderAll[indexAll] = item;
            console.log("Order All", $scope.orderAll[indexAll]);
            $('#updateStatusModal').modal('hide');
            $scope.sweetalert2("Cập nhập thành công", '', 'success')

        }).catch(error => {
            $scope.sweetalert2("Cập nhập thất bại", '', 'error')
            console.log(error)
        })
    }

    $scope.findOrderDetailsByOrderId = function (id) {
        let url = `?orderId=${id}`;
        orderService.findOrderDetailsByOrderId(url).then(resp => {
            $scope.details = resp.data;
            console.log("Details", $scope.details);
        })
    }
    $scope.getOrderByStatus(0);
    $scope.ordernew = 0;
    $scope.countOrder = function (status) {
        let url = `/countOrder?status=${status}`

        orderService.countOrder(url).then(resp => {
            $scope.ordernew = resp.data;

        })

    }
    $scope.countOrder(0);
    $interval(function () { $scope.countOrder(0) }, 3000);

    $scope.findAllOrderBetweenDate = function () {
        $('#search').val('');
        $scope.search = ''
        let startDate = $scope.getDateTimeLocal('#txtStartDateTimeOrder');
        let endtDate = $scope.getDateTimeLocal('#txtEndDateTimeOrder');
        console.log("Start-End", startDate + '-' + endtDate);
        let url = `/findAll?startDate=${startDate}&endDate=${endtDate}`;
        orderService.findAllByBetweenDate(url).then(resp => {
            $scope.showData(resp.data, startDate, endtDate);
            $scope.orderAll = resp.data;
            $scope.pageCount = Math.ceil($scope.orderAll.length / $scope.pageSize);
            console.log("Page Count", $scope.pageCount)
        })
    }
    $scope.findAllOrderBetweenDateFillterStatus = function (status) {
        document.querySelector('.load-form-container').classList.add('fade-in-form');
        $('#search').val('');
        $scope.search = ''
        let startDate = $scope.getDateTimeLocal('#txtStartDateTimeOrder');
        let endtDate = $scope.getDateTimeLocal('#txtEndDateTimeOrder');
        console.log("Start-End", startDate + '-' + endtDate);
        let url = `/findByDateAndStatus?startDate=${startDate}&endDate=${endtDate}&status=${status}`;
        orderService.findAllByBetweenDate(url).then(resp => {
            $scope.showData(resp.data, startDate, endtDate);
            $scope.orderAll = resp.data;
            $scope.page = 0;
            $scope.begin = 0;
            $scope.pageCount = Math.ceil($scope.orderAll.length / $scope.pageSize);
            console.log("Page Count", $scope.pageCount)
        }).finally(function () {
            document.querySelector('.load-form-container').classList.remove('fade-in-form');
        });
    }

    $scope.findAllOrderBetweenDate();
    // $scope.search="createDate";
    // Phân trang
    $scope.page = 0;
    $scope.begin = 0;
    $scope.pageSize = 3;

    // $scope.pageCount = Math.ceil($scope.orderDetails.length / $scope.pageSize);




    $scope.repaginate = function () {
        $scope.page = 0;
        $scope.begin = 0;
        $scope.pageCount = Math.ceil($scope.orderAll.length / $scope.pageSize);
    }

    $scope.sort = function (keyname) {
        $scope.sortKey = keyname; // set the sortKey to the param passed
        $scope.reverse = !$scope.reverse // if true make it fasle and vice versa
    }

    $scope.first = function () {
        $scope.begin = 0;
    }

    $scope.prev = function () {
        if ($scope.begin > 0) {
            // console.log("Begin:", $scope.begin - $scope.pageSize)
            $scope.begin -= $scope.pageSize;
           
            $scope.page = $scope.page - 1 < 0 ? 0 : $scope.page - 1;
        }
    }

    $scope.next = function () {
        // console.log("Next", $scope.begin < ($scope.pageCount - 1) * $scope.pageSize)
        if ($scope.begin < ($scope.pageCount - 1) * $scope.pageSize) {
            $scope.begin += $scope.pageSize;
            
            $scope.page = $scope.page + 1 > $scope.pageCount ? $scope.pageCount : $scope.page + 1;
        }
    }

    $scope.last = function () {
        $scope.begin = ($scope.pageCount - 1) * $scope.pageSize;
        console.log("end:", $scope.begin)
        console.log("end:", $scope.pageCount)
    }





    $scope.checkstatus = function (status) {
        let text = ""
        let title = ""
        if (status == 0) {
            text = "Xác nhận đơn hàng"
            $("#btnUpdate").show()
            $("#btnUpdateStaff").show()
        }
        else if (status == 1) {
            text = "Tiến hành đóng gói"
            $("#btnUpdate").show()
            $("#btnUpdateStaff").show()
        } else if (status == 2) {
            text = "Xác nhận vận chuyển"
            $("#btnUpdate").show()
            $("#btnUpdateStaff").hide()
            
        } else if (status == 3) {
            text = "Giao hàng thành công"
            $("#btnUpdate").show()
            $("#btnUpdateStaff").hide()
        } else if (status == 4) {
            // text = "Đang giao hàng"
            $("#btnUpdate").hide()
        } else {
            $("#btnUpdate").hide()
        }
        $("#btnUpdate").text(text)
        $("#btnUpdateStaff").text(text)

    }

    $scope.sweetalert2 = function (title, mess, type) {
        Swal.fire(
            title,
            mess,
            type
        )
    }

    // $scope.pager = {
    //     // begin: 0,
    //     // size: 2,
    //     // search:'createDate',
    //     // repaginate() {
    //     //     $scope.begin = 0;
    //     // $scope.pageCount = Math.ceil($scope.orderAll.length / this.size);
    //     // },

    //     // sortBy(search){
    //     //     this.search = search
    //     //     $scope.reverse = !$scope.reverse
    //     // },

    //     // first(){
    //     //     this.begin =0;
    //     // },

    //     // prev() {
    //     //     if(this.begin >0){
    //     //         this.begin -=this.size;
    //     //     }
    //     // },

    //     // next(){
    //     //     if(this.begin < ($scope.pageCount -1) * this.size){
    //     //         this.begin += this.size
    //     //     }
    //     // },

    //     // last(){
    //     //     this.begin = ($scope.pageCount -1) * this.size;
    //     // }

    //     // get items() {
    //     //     var start = this.page * this.size;
    //     //     return $scope.orders.slice(start, start + this.size);
    //     // },
    //     // get count(){// tong so item chia cho phần tử moi trang va lam tron
    //     //     return Math.ceil(1.0 *$scope.orders.length/this.size);
    //     // },
    //     // first(){
    //     //     this.page=0;
    //     // },
    //     // prev(){
    //     //     this.page--;
    //     //     if(this.page<0){
    //     //         this.last();
    //     //     }
    //     // },
    //     // next(){
    //     //     this.page++;
    //     //     if(this.page >=this.count){
    //     //         this.first();
    //     //     }
    //     // },
    //     // last(){
    //     //     this.page = this.count-1;
    //     // }

    // }

    $scope.myData = [];
    $scope.myHeader = [];
    $scope.myFooter = [];
    $scope.choseDate = "";
    $scope.showData = function (data, startDate, endDate) {

        $scope.myData = data.map((d) => {
            // const sign = Math.random() < 0.5 ? -1 : 1;
            return {
                id: d.id,
                firstName: d.first_name,
                lastName: d.last_name,
                createDate: new Date(d.createDate).toLocaleString(),
                phone: d.phone_number,
                status: getStatusExcel(d.status),
                amount: d.amount,
                address: d.address_line,
                note: d.description,
                // income: +(Math.random() * 1000).toFixed(2) * sign,
            };
        });
        console.log('myData', $scope.myData);

        let total = 0;
        angular.forEach($scope.myData, function (value, key) {
            // console.log(key + ': ' + value);
            total += +value?.amount;
        });
        $scope.myFooter = ['Tổng doanh thu', '', '', '', '', '', '', '', +total];
        $scope.choseDate = 'Từ: ' + startDate + ' - Đến: ' + endDate;
        console.log("Total", total)
    }

    $scope.exportExcel = function () {
        exportToExcelOrder('Users', 'Users', 'Báo cáo đơn hàng',
            $scope.choseDate, $scope.myFooter, $scope.myData)
    }
})
