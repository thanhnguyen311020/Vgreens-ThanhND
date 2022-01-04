


app.controller("consignment-ctrl", function ($http, $scope, $filter, dataFactory) {

	$scope.items = [];
	$scope.form = {};
	$scope.products = [];
	$scope.suppliers = [];

	$scope.isShow = false;

	// dirPaginate 
	$scope.pageSize = 10;

	$scope.sort = function (keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	}
	$scope.selectAll = function () {
		if ($scope.isShow === false) {
			angular.forEach($scope.items, function (item) {
				item.checked = true;
			});
			$scope.isShow = true;
		} else {
			angular.forEach($scope.items, function (item) {
				item.checked = false;
			});
			$scope.isShow = false;
		}
	};
	// $scope.$watch(
	//   "items",
	//   function (n, o) {
	//     var checked = $filter("filter")(n, { checked: true });
	//     if (checked.length) {
	//       $scope.selected = checked;
	//       $("#btnDelete").removeAttr("disabled");
	//     } else {
	//       $("#btnDelete").attr("disabled", "disabled");
	//     }
	//   },
	//   true
	// );

	$scope.initialize = function () {
		$http.get('/vgreens/rest/consignment/ExpiryGreaterDateNow').then(resp => {
			$scope.items = resp.data;
		})

		$http.get("/vgreens/rest/supplier").then(resp => {
			$scope.suppliers = resp.data;
			console.log($scope.suppliers);
		})

		$http.get("/vgreens/test1/product").then(resp => {
			$scope.products = resp.data;
			// console.log($scope.products);
		})
	}

	$scope.getConsignment = function () {
        let start = $('#startDateConsiments').val();
        let end = $('#endDateConsiments').val();
        var url = `/date?startDate=${start}&endDate=${end}`;
        dataFactory.statisticConsignment(url).then(resp => {
			$scope.showData(resp.data, start, end);
            $scope.items = resp.data;
            // console.log("consignment", $scope.statisticConsignment)
        })
    }

	$scope.findConsignmentExpiryLessDateNow = function () {
		$http.get('/vgreens/rest/consignment/ExpiryLessDateNow').then(resp => {
			$scope.items = resp.data;
		})
	}

	$scope.initialize();

	$scope.selectItem = function (item) {
		$scope.clickedItem = angular.copy(item);
		console.log($scope.clickedItem);
		$scope.message = $scope.clickedItem.name;
	}


	$scope.reset = function () {
		$scope.form = {
			created_time: new Date(),
			updated_time: new Date(),
			expiry_time: new Date(),
			numberOfSales: 0,
			numberOfReturns: 0,
			currentQuantity: 0
		}
		$('#currentQuantity').val(0);

		$scope.isShow = false;

	}

	$scope.reset();

	$scope.converDateTime = function (date_time) {
		if (date_time == null) {
			return null;
		} else {
			var todayDate = new Date(date_time);
			todayDate.setSeconds(0)
			todayDate.setMilliseconds(0)
			return todayDate;
		}
	}

	$scope.edit = function (item) {
		//console.log("0k")
		$scope.isShow = true;
		$scope.form = angular.copy(item);
		$('#currentQuantity').val(item.currentQuantity);
		$scope.form.updated_time = $scope.converDateTime($scope.form.updated_time);
		$scope.form.created_time = $scope.converDateTime($scope.form.created_time);
		$scope.form.expiry_time = $scope.converDateTime($scope.form.expiry_time);

		$(".nav-tabs a:eq(1)").tab("show");

		var now = item.created_time;
		// now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
		$scope.times = item.created_time;
		// $('#created_time').val(now.toISOString());

	}
	$scope.chooseDate = function () {
		var startDate = $('#created_time').val();
		var endDate = $('#expory_date').val();
		if (Date.parse(startDate) < Date.parse(endDate)) {
			$('#btnDelete').attr("disabled", false);
			$('#btnUpdate').attr("disabled", false);
		} else {
			$("#btnDelete").attr("disabled", "disabled");
			$("#btnUpdate").attr("disabled", "disabled");
			alert("Ngày hết hạn phải lớn hơn ngày nhập");
		}
	};

	$scope.create = function () {
		var item = angular.copy($scope.form);
		item.currentQuantity = $scope.form.numberOfInputs;
		$http.post("/vgreens/rest/consignment", item).then(resp => {
			$scope.items.push(resp.data);
			$scope.sweetalert2("Thêm mới thành công", '', 'success');
			$scope.initialize();
			$scope.reset()
		}).catch(error => {
			$scope.sweetalert2("Thêm mới thất bại", 'Kiểm tra lại các trường dữ liệu của bạn', 'error')
			console.log("Error", error);
		})

	}


	$scope.update = function () {
		var item = angular.copy($scope.form);
		item.updated_time = new Date();
		item.currentQuantity = $scope.form.numberOfInputs;
		$http.put(`/vgreens/rest/consignment/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.id == item.id);
			$scope.items[index] = item;
			// $scope.items;
			$scope.sweetalert2("Cập nhập thành công", '', 'success')
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại", '', 'error')
			console.log("Error Update" + error);
		})
	}


	$scope.delete = function (item) {

		$http.delete(`/vgreens/rest/consignment/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.sweetalert2("Xóa thành công", '', 'success')
			$scope.reset();

		}).catch(error => {
			console.log("Delete Error: ", error);
			$scope.sweetalert2("Xóa thất bại", '', 'error')


		})
	}
	$scope.getBySup = function (sup) {
		$http.get(`/vgreens/rest/consignment/${sup}`).then(resp => {
			$scope.items = resp.data;
		});
	}

	// $scope.imageChanged = function (files) {
	//     var data = new FormData();
	//     data.append('file', files[0]);
	//     $http.post('/vgreens/rest/upload/accounts', data, {
	//         transformRequest: angular.identity,
	//         headers: { 'Content-Type': undefined }

	//     }).then(resp => {
	//         $scope.form.photo = resp.data.name;

	//     }).catch(error => {
	// 		alert("Error")
	//         console.log("Error", error);
	//     })
	// }



	$scope.sweetalert2 = function (title, mess, type) {
		Swal.fire(
			title,
			mess,
			type
		)
	}





	// Tag thông báo ==> đang sửa đừng xóa
	// const Toast = Swal.mixin({
	// 	toast: true,
	// 	position: 'top-end',
	// 	showConfirmButton: false,
	// 	timer: 3000,
	// 	timerProgressBar: true,
	// 	didOpen: (toast) => {
	// 	  toast.addEventListener('mouseenter', Swal.stopTimer)
	// 	  toast.addEventListener('mouseleave', Swal.resumeTimer)
	// 	}
	//   })

	//   Toast.fire({
	// 	icon: 'success',
	// 	title: 'Signed in successfully'
	//   })


	$scope.myData = [];
    $scope.myHeader = [];
    $scope.myFooter = [];
    $scope.choseDate = "";
    $scope.showData = function(data, startDate, endDate) {

        $scope.myData = data.map((d) => {
            // const sign = Math.random() < 0.5 ? -1 : 1;
            return {
                id: d.id,
                firstName: d.name,
                created_time: new Date( d.created_time).toLocaleString(),
                expiry_time: new Date( d.expiry_time).toLocaleString(),
                numberOfInputs: d.numberOfInputs,
                currentQuantity: d.currentQuantity ,
                purchase_price: d.purchase_price,
                selling_price: d.selling_price,
                supplier: d.supplier.name,
                amount: (d.numberOfInputs * d.purchase_price),
                // income: +(Math.random() * 1000).toFixed(2) * sign,
            };
        });
        console.log('myData', $scope.myData);
      
        let total = 0;
        angular.forEach( $scope.myData, function(value, key) {
            // console.log(key + ': ' + value);
            total += +value?.amount;
          });
        $scope.myFooter = ['Tổng giá nhập hàng', '', '','','','','','', +total];
        $scope.choseDate ='Từ: '+ startDate + ' - Đến: ' + endDate;
        console.log("Total", total)
    }

    $scope.exportExcel = function(){
        exportToExcelConsignment('Users', 'Users', 'Báo cáo đơn hàng',
            $scope.choseDate,$scope.myFooter,$scope.myData)
    }



});

