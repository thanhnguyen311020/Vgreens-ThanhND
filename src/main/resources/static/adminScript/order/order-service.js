let startTodays= nowDashboard.getFullYear()+'-'+(nowDashboard.getMonth() + 1)+'-'+(nowDashboard.getDate());
document.getElementById('txtStartDateTimeOrder').value = formatDate(startTodays)+"T01:00:00";

let endToday = new Date();
endToday.setMilliseconds(0);
endToday.setMinutes(endToday.getMinutes() - endToday.getTimezoneOffset());
document.getElementById('txtEndDateTimeOrder').value = endToday.toISOString().slice(0, -1);

app.factory("orderService", function($http){

    var orderService = {}
    var baseUrl = "http://localhost:8080/vgreens/rest/orderAdmin"
    orderService.findOrderByStatus = function(url) {
       
        return $http.get(baseUrl+url)
    }

    orderService.updateStatusOrderToDay = function(url,data){
        return $http.put(baseUrl+url,data);
    }
    orderService.findOrderDetailsByOrderId = function(url){
        return $http.get("/vgreens/rest/orderDetailAdmin"+url);
    }

    orderService.findAllByBetweenDate = function(url){
        console.log("url", baseUrl+url);
        return $http.get(baseUrl+url);
    }

    orderService.countOrder = function(url){
        return $http.get(baseUrl+url);
    }

    return orderService;

})