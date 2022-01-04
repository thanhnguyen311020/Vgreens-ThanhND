app.controller("dashboard-ctrl", function ($http, $scope, $timeout,
    getStatisticRevenue, productByQuantityOrder, getOrderFailAndSuccess,getTopCloseCustomer
    , dataFactory) {

    $scope.data = [];
    $scope.datas = [];



    $scope.statisticProduct = [];
    $scope.statisticConsignment = [];
    $scope.products = [];
    $scope.productsExpiry_time = [];

    // ========== Thống kê lô hàng ===============


    $scope.pageSizes = 10;
    $scope.getConsignment = function () {
        const startDate = $('#startDateConsiment').val();
        const endDate = $('#endDateConsiments').val();
        let start = startDate.slice(0, 4) + '-' + startDate.slice(5, 7) + '-' + 1;
        let end = $scope.getMaxDayInMonth(endDate.slice(5, 7), endDate.slice(0, 4));
        var url = `/statisticProductByOrder?startDate=${start}&endDate=${end}`;
        dataFactory.getStatisticalProductByOrder(url).then(resp => {
            $scope.statisticProduct = resp.data;
            $scope.showData2(resp.data, startDate, endDate);
            console.log("Product Sta", $scope.statisticConsignment)
        })
    }
    $scope.myData2 = [];
    $scope.myHeader2 = [];
    $scope.myFooter2 = [];
    $scope.choseDate2 = "";
    $scope.showData2 = function(data, startDate, endDate) {

        $scope.myData2 = data.map((d) => {
            // const sign = Math.random() < 0.5 ? -1 : 1;
            return {
                id: d.product.id,
                name: d.product.name,
                countQuantity: d.countProduct,
                
                sumUnitPrice: d.sumUnitPrice,
                // income: +(Math.random() * 1000).toFixed(2) * sign,
            };
        });
        console.log('myData', $scope.myData2);
      
        let total = 0;
        angular.forEach( $scope.myData2, function(value, key) {
            // console.log(key + ': ' + value);
            total += +value?.sumUnitPrice;
          });
        $scope.myFooter2 = ['Tổng doanh thu', '', '', +total];
        $scope.choseDate2 ='Từ: '+ startDate + ' - Đến: ' + endDate;
        console.log("Total", total)
    }

    $scope.exportExcelProduct = function(){
        exportToExcelTurnoverProduct('Users', 'Users', 'Báo cáo đơn hàng',
            $scope.choseDate2,$scope.myFooter2,$scope.myData2)
    }



    $scope.porp = "consignment.currentQuantity"

    $scope.getProducts = function () {
        $http.get('/vgreens/rest/product/list').then(function (res) {
            $scope.products = res.data;
            $scope.productsExpiry_time = res.data;
            console.log($scope.products.length)


            $scope.pageCountQuantityProduct = Math.ceil($scope.products.length / $scope.pageSizeProduct);//==> so san pham tren cun 1 trang MAth.ceil== lam tron
        });
    }

    $scope.getProducts()

    $scope.beginQuantityProduct = 0;
    $scope.pageSizeProduct = 5;

    $scope.pageCountQuantityProduct = Math.ceil($scope.products.length / $scope.pageSizeProduct);
    $scope.showMore = function (n) {
        $scope.pageSizeProduct = $scope.pageSizeProduct + n;
        console.log($scope.pageSizeProduct);
    }
    $scope.sortBy = function (prop) {
        $scope.prop = prop;
    }

    $scope.productCount = 0;
    $scope.countProductByCurentQuantityLessThan10 = function(){
        $http.get("/vgreens/rest/product/countProductLessThan10").then(resp =>{
            $scope.productCount = resp.data;
        })
    }
    $scope.countProductByCurentQuantityLessThan10();

    // $scope.getConsignment();



    // ============Thống kê lô hàng===================

  
    $scope.getData = function () {
        $http.get('/vgreens/rest/orderAdmin/getStatusOrder?year=2021&month=10').then(resp => {
            $scope.data = resp.data;
            // console.log($scope.data)
            // console.log("Data 0",$scope.data[0])
            // console.log("Data 4",$scope.data[4])
            // for (var key in $scope.data) {
            //     if ($scope.data.hasOwnProperty(key)) {
            //         console.log("Data", $scope.data[key])

            //     }
            // }
            $scope.datas = [
                {
                    values: [$scope.data[4]],
                    text: "Đơn hàng thành công",
                    backgroundColor: "#3bc45b",
                }, {
                    values: [$scope.data[0]],
                    text: " Đơn hàng thất bại",
                }];


        })
    }
    $scope.getData();


    $scope.order = 0;
    $scope.totalSales = 0;
    $scope.totalProductOrder = 0;
    $scope.amountPaypal = 0;
    $scope.usd = [];
    $scope.amountPaypalUSD = 0;
    $scope.getTotalDashBoard = function () {
        var countOrderUrl = "/countOrderFinal"
        var amountOrderUrl = "/Amount"
        var amountOrderPaypalUrl = "/AmountPaypal"
        var amountOrderDetailsUrl = "/countProduct"
        dataFactory.getOrder(countOrderUrl).then(resp => {
            $scope.order = resp.data;
        })
        dataFactory.getOrder(amountOrderUrl).then(resp => {
            $scope.totalSales = resp.data;
        })
        dataFactory.getOrder(amountOrderPaypalUrl).then(resp => {
            $scope.amountPaypal = resp.data;
            $http.get('/vgreens/rest/orderAdmin/getUSD').then(resp =>{
                $scope.usd = angular.fromJson(resp.data);
                for(let i = 0; i < $scope.usd.items.length; i++){
                    if($scope.usd.items[i].type === 'USD'){
                        $scope.amountPaypalUSD = ($scope.amountPaypal / $scope.usd.items[i].bantienmat).toFixed(2)
                        console.log("USD Price",($scope.amountPaypal / $scope.usd.items[i].bantienmat).toFixed(2) )
                    }
                }
                
            })
            
        })
        dataFactory.getOrderDetail(amountOrderDetailsUrl).then(resp => {
            $scope.totalProductOrder = resp.data;
            
        })
    }

    $scope.getTotalDashBoard();

    // ====================== Get Total Month Now Dashboard ==============


    $scope.statisticProducts = function () {
        var url = "/group-product";
        dataFactory.statisticProduct(url).then(resp => {
            $scope.statisticProduct = resp.data;
        })
    }



    $scope.statisticProducts();
    const starsTotal = 5;
    $scope.getRatingsProducts = function () {

        for (let rating in $scope.statisticProduct) {
            console.log("Rating")
            //Lấy % rating
            const starPercentage = ($scope.statisticProduct[rating].ratingCount / starsTotal) * 100;
            console.log("Get percent", starPercentage)
            //Làm tròn đến 10 gần nhất
            const starPercentageRounded = `${Math.round(starPercentage / 10) * 10}%`;
            console.log(starPercentageRounded)
            console.log($scope.statisticProduct[rating].product.id)
            document.querySelector(`.p-${$scope.statisticProduct[rating].product.id} .stars-inner`).style.width = starPercentageRounded;
        }

    }




// =============== Biểu đồ JS ===================

    $scope.getChart = function () {
        const startDate = $('#startDateRevenue').val();
        const endDate = $('#endDateRevenue').val();
        let start = startDate.slice(0, 4) + '-' + startDate.slice(5, 7) + '-' + 1;
        let end = $scope.getMaxDayInMonth(endDate.slice(5, 7), endDate.slice(0, 4));
        console.log("Date:  ", start , end)
        getStatisticRevenue.loadChart(start, end);
        $http.get(`http://localhost:8080/vgreens/rest/orderAdmin/StatisticRevenue/${start}/${end}`).then(resp => {
            $scope.showData1(resp.data ,start, end);
        })

    };
    //============= Xuất báo cáo doanh số ===================
    $scope.myData1 = [];
    $scope.myHeader1 = [];
    $scope.myFooter1 = [];
    $scope.choseDate1 = "";
    $scope.showData1 = function(data, startDate, endDate) {
        $scope.myData1 = data.map((d) => {
            return {
               month: 'Tháng '+ d.mouth + ' năm ' + d.year,
               countOrder: d.countOrder,
               discount: d.discount != null ? d.discount : 0,
               revenue: d.revenue,
            };
        });
        console.log('myData Reven', $scope.myData1);
      
        let total = 0;
        angular.forEach( $scope.myData1, function(value, key) {
            // console.log(key + ': ' + value);
            total += +value?.revenue;
          });
        $scope.myFooter1 = ['Tổng doanh số', '','', +total];
        $scope.choseDate1 ='Từ: '+ new Date(startDate).getMonth()+1 + '/' + new Date(startDate).getFullYear() +
        ' - Đến: ' + (new Date(endDate).getMonth()+1) + '/' +new Date(endDate).getFullYear();
        console.log("Total", total)
    }

    $scope.exportExcelTurnover = function(){
        exportToExcelTurnover('Báo cáo doanh số', 'Báo cáo doanh số', 'Doanh số theo tháng',
            $scope.choseDate1,$scope.myFooter1,$scope.myData1)
    }




    $scope.getMaxDayInMonth = function (month, year) {
        var maxday = new Date(year, month, 0).getDate();

        return year + '-' + month + '-' + maxday;
    }


    $scope.getBarChart = function () {
        const startDate = $('#dateBarChart').val();
        // console.log("barchart",startDate.slice(5, 7),startDate.slice(0, 4))
        // console.log("chart",productByQuantityOrder.loadBarChart(startDate.slice(5, 7),startDate.slice(0, 4)))
        productByQuantityOrder.loadBarChart(startDate.slice(5, 7), startDate.slice(0, 4));
    }

    $scope.getPieChart = function () {
        const monthNow = $('#txtPieChart').val();
        // console.log("Pie chart", monthNow.slice(5, 7),monthNow.slice(0, 4))
        getOrderFailAndSuccess.loadPieChart(monthNow.slice(0, 4), monthNow.slice(5, 7));
    }

    $scope.closeCustomers = [];
    $scope.getCloseCustomerChart = function () {
        const monthNow = $('#topCloseCustomerDate').val();
        console.log("Pie chart", monthNow.slice(5, 7),monthNow.slice(0, 4))
        getTopCloseCustomer.loadBarChart( monthNow.slice(5, 7), monthNow.slice(0, 4));
        $http.get(`http://localhost:8080/vgreens/rest/orderAdmin/closeCustomer?month=${monthNow.slice(5, 7)}&year=${monthNow.slice(0, 4)}`).then(resp =>{
            $scope.closeCustomers = resp.data;
        })
       
    }



    $scope.getPieChart();
    $scope.getCloseCustomerChart();

    $scope.getChart()
    $scope.getBarChart();

    // angular.element(document).ready(function () {
    //     console.log('page loading completed');
    //     $scope.getRatingsProducts()
    //     // $scope.getRatingsProductByCustomer()
    // });

    // $scope.usd = [];
    // $scope.getUSD = function (){
    //     $http.get('/vgreens/rest/orderAdmin/getUSD').then(resp =>{
    //         $scope.usd = angular.fromJson(resp.data);
    //         for(let i = 0; i < $scope.usd.items.length; i++){
    //             if($scope.usd.items[i].type === 'USD'){
    //                 console.log("USD",$scope.usd.items[i])
    //             }
    //         }
            
    //     })
    // }

    // $scope.getUSD();

    // ============== Export Excel ==================
  //============ Thống kê lịch sử bán hàng & doanh số===================
    // var time = 1;
    // $timeout(function () { loadChart(time); }, 1000);
    $scope.orderDetails = [];
    $scope.sumAmount = 0;
    $scope.sumAmountPaypal = 0;
    $scope.myData = [];
    $scope.myHeader = [];
    $scope.myFooter = [];
    $scope.choseDate = "";
    $scope.getOrderDetails = function () {
        var txtStartDate = new Date($('#startDateOrderDetail').val());
        let startDate = txtStartDate.getFullYear() + '-' + (txtStartDate.getMonth() + 1) + '-' + txtStartDate.getDate() + ' '
            + txtStartDate.getHours() + ':' + txtStartDate.getMinutes() + ':' + txtStartDate.getSeconds();
        var txtEndDate = new Date($('#endDateOrderDetail').val())
        let endDate = txtEndDate.getFullYear() + '-' + (txtEndDate.getMonth() + 1) + '-' + txtEndDate.getDate() + ' '
            + txtEndDate.getHours() + ':' + txtEndDate.getMinutes() + ':' + txtEndDate.getSeconds();
        // console.log("FormatDate",);
        // console.log("http://localhost:8080/vgreens/rest/orderDetailAdmin/orderDetailBy-Date?startDate="+startDate + "&"+"endDate="+endDate)
        var url = `http://localhost:8080/vgreens/rest/orderDetailAdmin/orderDetailBy-Date?startDate=${startDate}&endDate=${endDate}`;
        console.log("url:" , url)
        var urlAmount = `/vgreens/rest/orderAdmin/sumAmountBetweenDate?startDate=${startDate}&endDate=${endDate}`;
        var urlAmountPaypal = `/vgreens/rest/orderAdmin/sumAmountPayPalBetweenDate?startDate=${startDate}&endDate=${endDate}`;
        dataFactory.getSumAmoutOrderBetweenDate(urlAmount).then(resp => {
            $scope.sumAmount = resp.data;
            // console.log("sum Amount", resp.data)
        }).catch(error => {
            console.log("loix", error)
        })
        dataFactory.getSumAmoutOrderBetweenDate(urlAmountPaypal).then(resp => {
            $scope.sumAmountPaypal = resp.data;
            // console.log("sum Amount", resp.data)
        }).catch(error => {
            console.log("loix", error)
        })


        dataFactory.getOrderDetailByDate(url).then(resp => {
            $scope.orderDetails = resp.data;
            $scope.showData(resp.data, startDate, endDate);
            $scope.pageCount = Math.ceil($scope.orderDetails.length / $scope.pageSize);
              console.log("Page count: ", $scope.pageCount)
        }).catch(error => {
            console.log("loix", error)
        })
    }
    $scope.getOrderDetails();

    $scope.begin = 0;
    $scope.pageSize = 10;

    // $scope.pageCount = Math.ceil($scope.orderDetails.length / $scope.pageSize);
  

    $scope.prop = 'unit_price';
    $scope.page =0;
    $scope.repaginate = function () {
        $scope.page =0;
        $scope.begin = 0;
        $scope.pageCount = Math.ceil($scope.orderDetails.length / $scope.pageSize);
    }

    $scope.sortBy = function (prop) {
        $scope.prop = prop;
        $scope.reverse = !$scope.reverse
    }

    // $scope.sort = function (keyname) {
	// 	$scope.sortKey = keyname; // set the sortKey to the param passed
	// 	$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	// }

    $scope.first = function () {
        $scope.begin = 0;
    }

    $scope.prev = function () {
        if ($scope.begin > 0) {
            $scope.begin -= $scope.pageSize;
            $scope.page = $scope.page - 1 <0 ? 0 : $scope.page - 1;
        }
    }

    $scope.next = function () {
        if ($scope.begin < ($scope.pageCount - 1) * $scope.pageSize) {
            $scope.begin += $scope.pageSize;

            $scope.page = $scope.page + 1 > $scope.pageCount ? $scope.pageCount : $scope.page + 1;
        }
    }

    $scope.last = function () {
        $scope.begin = ($scope.pageCount - 1) * $scope.pageSize;
        console.log("end:" , $scope.begin)
        console.log("end:" , $scope.pageCount)
    }



    // ===============================================================

   
    $scope.showData = function(data, startDate, endDate) {
        $scope.myData = data.map((d) => {
            // const sign = Math.random() < 0.5 ? -1 : 1;
            return {
                id: d.order.id,
                firstName: d.order.first_name,
                lastName: d.order.last_name,
                createDate: d.order.createDate,
                product: d.product.name,
                quantity: d.quantity,
                unitprice: d.unit_price,
                amount: d.unit_price * d.quantity,
                // income: +(Math.random() * 1000).toFixed(2) * sign,
            };
        });
        console.log('myData', $scope.myData);
      
        let total = 0;
        angular.forEach( $scope.myData, function(value, key) {
            // console.log(key + ': ' + value);
            total += +value?.amount;
          });
        $scope.myFooter = ['Tổng doanh thu', '', '', '','','','', +total];
        $scope.choseDate ='Từ: '+ startDate + ' - Đến: ' + endDate;
        console.log("Total", total)
    }

    $scope.exportExcel = function(){
        exportToExcel('Users', 'Users', 'Lịch sử giao dịch',
            $scope.choseDate,$scope.myFooter,$scope.myData)
    }
});


app.directive("convertToNumber", function(){
    return{
        require : 'ngModel',
        link: function(scope, elemnt, atrs, ngModel){
            ngModel.$parsers.push(function(val){
                return parseInt(val,10);
            });

            ngModel.$formatters.push(function(val){
                return '' + val;
            });
        }
    };
});

app.filter('percentage',function($filter){
    return function(input, decimals){
        return $filter('number')(input * 100, decimals)+'%';
    }
})

































// $scope.renderChart = function () {
//     $scope.myJson = {
//         globals: {
//             shadow: false,
//             fontFamily: "Verdana",
//             fontWeight: "500"
//         },
//         type: "pie",
//         backgroundColor: "#fff",

//         legend: {
//             layout: "x2",
//             position: "50%",
//             borderColor: "transparent",
//             marker: {
//                 borderRadius: 10,
//                 borderColor: "transparent"
//             }
//         },
//         tooltip: {
//             text: "%v Đơn hàng"
//         },
//         plot: {
//             refAngle: "-90",
//             borderWidth: "0px",
//             valueBox: {
//                 placement: "in",
//                 text: "%npv %",
//                 fontSize: "18px",
//                 fontWeight: "500",
//                 textAlpha: 1,
//             }
//         },
//         series: $scope.datas
//     }
// }