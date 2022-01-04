// Set Date 
// $('#endDateRevenue').val('2020-02')
var nowDashboard = new Date();
var dayNow = ("0" + nowDashboard.getDate()).slice(-2);
var monthNow = ("0" + (nowDashboard.getMonth() + 1)).slice(-2);

var today = nowDashboard.getFullYear() + "-" + (monthNow) + "-" + (dayNow);


function formatDate(date) {

    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}




function setDate() {
    // console.log("check dashcbord: ", $('#endDateRevenue').is(':empty'))
    if ($('#endDateRevenue').is(':empty') && $('#startDateRevenue').is(':empty')) {
        var start = nowDashboard.getFullYear().toString() + '-' + '01';
        var end = nowDashboard.getFullYear().toString() + '-' + (nowDashboard.getMonth() + 1).toString();
        var endDate = nowDashboard.getFullYear().toString() + '-' + (nowDashboard.getMonth() + 1).toString() + '-' + nowDashboard.getDate();
        var startDate = nowDashboard.getFullYear().toString() + '-' + (nowDashboard.getMonth() + 1).toString() + '-' + '01';
        $('#endDateRevenue').val(end)
        $('#startDateRevenue').val(start)
        $('#dateBarChart').val(end);
        $('#txtPieChart').val(end);
        $('#topCloseCustomerDate').val(end);
        $('#startDateConsiment').val(start)

        $('#endDateConsiments').val(end)

        let startToday = nowDashboard.getFullYear() + '-' + (nowDashboard.getMonth() + 1) + '-' + (nowDashboard.getDate());
        console.log("Today: ", startToday + "T01:00:00")
        document.getElementById('startDateOrderDetail').value = formatDate(startToday) + "T02:00:00";
        // document.getElementById('txtDateTimeOrder').value = startToday+"T01:00";

        let endToday = new Date();
        endToday.setMilliseconds(0);
        endToday.setMinutes(endToday.getMinutes() - endToday.getTimezoneOffset());
        console.log(endToday.toISOString().slice(0, -1))
        document.getElementById('endDateOrderDetail').value = endToday.toISOString().slice(0, -1);
    }
}
function setDateInput() {

    if ($('#startDateConsiments').is(':empty') && $('#endDateConsiments').is(':empty')) {
        var endDate = nowDashboard.getFullYear().toString() + '-' + (nowDashboard.getMonth() + 1).toString() + '-' + nowDashboard.getDate();
        var startDate = nowDashboard.getFullYear().toString() + '-' + (nowDashboard.getMonth() + 1).toString() + '-' + '01';
        $('#startDateConsiments').val(startDate)

        $('#endDateConsiments').val(formatDate(endDate))
    }
}

setDateInput();
setDate();
// formatDate(nowDashboard)
app.factory('productByQuantityOrder', function () {
    return {

        loadBarChart: function (month, year) {
            var dataPoints = [];


            var chart = new CanvasJS.Chart("barChartContainer", {

                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Thống kê các sản phẩm bán chạy"
                },
                axisX: {
                    interval: 1
                },
                axisY: {
                    title: "Số lượng bán",
                    includeZero: true,
                    interval: 1,
                    scaleBreaks: {
                        type: "wavy",
                        customBreaks: [{
                            startValue: 80,
                            endValue: 210
                        },
                        {
                            startValue: 230,
                            endValue: 600
                        }
                        ]
                    }
                },
                data: [{
                    type: "bar",
                    // toolTipContent: "<img src=\"https://canvasjs.com/wp-content/uploads/images/gallery/javascript-column-bar-charts/\"{url}\"\" style=\"width:40px; height:20px;\"> <b>{label}</b><br>Budget: ${y}bn<br>{gdp}% of GDP",
                    yValueFormatString: "Đã bán #,### sản phẩm",
                    dataPoints: dataPoints
                }]
            });


            function addData(data) {
                Object.keys(data)
                    .forEach(function eachKey(key) {
                        // console.log(key); // alerts key 
                        // console.log(data[key]); // alerts value

                        dataPoints.push({
                            label: key,
                            y: data[key]
                        });
                    });
                chart.render();
            }
            $.getJSON(`http://localhost:8080/vgreens/rest/orderDetailAdmin/getSumQuantityProductInOrDerDetail?month=${month}&year=${year}`, addData);
        }
    }
})

app.factory('getStatisticRevenue', function () {
    return {

        loadChart: function (startDate, endDate) {
            var dataPoints = [];

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Doanh số theo tháng"
                },
                axisX: {
                    interval: 1,
                    intervalType: "month"

                },
                axisY: {
                    title: "Doanh số",
                    titleFontSize: 24,
                    includeZero: true
                },
                data: [{
                    type: "line",
                    yValueFormatString: "#,### VND",
                    dataPoints: dataPoints
                }]
            });

            function addData(data) {

                for (var i = 0; i < data.length; i++) {
                    dataPoints.push({
                        x: new Date(data[i].year, data[i].mouth - 1, 01),
                        y: data[i].revenue
                    });
                }
                chart.render();

            }
            // console.log(new Date(2010, 6, 26).getTime() / 1000)
            $.getJSON(`http://localhost:8080/vgreens/rest/orderAdmin/StatisticRevenue/${startDate}/${endDate}`, addData);
        }
    }
})
app.factory('getOrderFailAndSuccess', function () {
    return {

        loadPieChart: function (year, month) {
            var dataPoints = [];

            var chart = new CanvasJS.Chart("pieChartContainer", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Biểu đồ tỷ lệ thành công các đơn hàng",
                    fontSize: 20,
                },
                axisY: {
                    labelFontSize: 40,
                },
                height: 300,
                data: [{
                    type: "pie",
                    startAngle: 440,

                    // yValueFormatString: "##0.00\"%\"",
                    indexLabel: "{label} {percent} %",
                    indexLabelFontSize: 14,
                    dataPoints: dataPoints
                }]
            });

            function addData(data) {

                var sum = 0;
                Object.keys(data)
                    .forEach(function eachKey(key) {

                        sum = sum + data[key];
                    });
                // console.log("sum", sum);
                Object.keys(data)
                    .forEach(function eachKey(key) {
                        // console.log(key); // alerts key 
                        // console.log(data[key]); // alerts value
                        var keys = key == 4 ? 'Đơn hàng thành công' : 'Đơn hàng thất bại';
                        dataPoints.push({
                            label: keys,
                            y: data[key],
                            percent: (data[key] / sum * 100).toFixed(2)
                        });
                    });


                chart.render();

            }

            $.getJSON(`http://localhost:8080/vgreens/rest/orderAdmin/getStatusOrder?year=${year}&month=${month}`, addData);


        }

    }
})

// get bar chart
app.factory('getTopCloseCustomer', function () {
    return {

        loadBarChart: function (month, year) {
            var dataPoints = [];

            var chart = new CanvasJS.Chart("chartTopCloseCustomer", {
                animationEnabled: true,
                theme: "light2", // "light1", "light2", "dark1", "dark2"
                title: {
                    text: "Mức độ chi tiêu của khách hàng trong " + month + '/' + year
                },
                axisY: {
                    title: "Doanh số",
                    // suffix: " .%"
                    // includeZero: true,
                    // interval: 1,
                },
                axisX: {
                    title: "Khách hàng"
                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,##0.0#\" .đ\"",
                    dataPoints: dataPoints
                }]
            });

            function addData(data) {

                if (data.length === 0) {
                    dataPoints.push({
                        label: 'Chưa có dữ liệu',
                        y: 0
                    });
                } else {
                    for (var i = 0; i < data.length; i++) {
                        console.log("data: ", data[i])
                        dataPoints.push({
                            label: data[i].username,
                            y: data[i].sumAmount
                        });
                        if (i === 10) {
                            break;
                        }
                    }
                }



                chart.render();

            }

            $.getJSON(`http://localhost:8080/vgreens/rest/orderAdmin/closeCustomer?month=${month}&year=${year}`, addData);


        }

    }
})

app.factory('dataFactory', function ($http) {
    var dataFactory = {};
    var baseUrlOrder = "/vgreens/rest/orderAdmin"
    var baseUrlOrderDetail = "/vgreens/rest/orderDetailAdmin"
    var baseUrlProductReview = "/vgreens/rest/product-review"
    var baseUrlConsignment = "/vgreens/rest/consignment"
    var baseUrlProduct = "/vgreens/rest/product"

    dataFactory.getOrderDetailByDate = function (url) {
        return $http.get(url);
    }

    dataFactory.getSumAmoutOrderBetweenDate = function (url) {
        return $http.get(url);
    }

    dataFactory.getOrder = function (url) {
        return $http.get(baseUrlOrder + url);
    }
    dataFactory.getOrderDetail = function (url) {
        return $http.get(baseUrlOrderDetail + url);
    }

    dataFactory.statisticProduct = function (url) {
        return $http.get(baseUrlProductReview + url);
    }

    dataFactory.statisticConsignment = function (url) {

        return $http.get(baseUrlConsignment + url);
    }
    dataFactory.getProduct = function (url) {
        return $http.get(baseUrlProduct + url);
    }
    dataFactory.getStatisticalProductByOrder = function (url) {
        return $http.get(baseUrlOrderDetail + url);
    }

    return dataFactory;
})