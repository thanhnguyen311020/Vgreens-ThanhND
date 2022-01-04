app.controller("discount-ctrl", function ($http, $scope) {

	$scope.items = [];
	$scope.form = {};
	$scope.consignments = [];

	$scope.isShow = false;

	// dirPaginate 
	$scope.pageSize = 10;
	$scope.sort = function (keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	}

	$scope.initialize = function () {
		$http.get("/vgreens/rest/discount").then(resp => {
			$scope.items = resp.data;
			console.log($scope.items);
		})

		$http.get("/vgreens/rest/consignment").then(resp => {
			$scope.consignments = resp.data;
			console.log($scope.consignments);
		})
	}

	$scope.initialize();

	$scope.selectItem = function (item) {
		$scope.clickedItem = angular.copy(item);
		console.log("Click-Item: ",$scope.clickedItem);
		$scope.message = $scope.clickedItem.name_discount;
	}


	$scope.reset = function () {
		$scope.form = {
			startDate: new Date(),
			endDate: new Date(),
		}

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

	$scope.chooseDate = function () {
		var startDate = $('#startDate').val();
        var endDate = $('#endDate').val();
		if (Date.parse(startDate) < Date.parse(endDate)) {
			$('#btnDelete').attr("disabled", false);
			$('#btnUpdate').attr("disabled", false);
		} else{
			$("#btnDelete").attr("disabled", "disabled");
			$("#btnUpdate").attr("disabled", "disabled");
			alert("Ngày hết hạn phải lớn hơn ngày nhập");
		}
	};

	$scope.edit = function (item) {
		//console.log("0k")
		$scope.isShow = true;
		$scope.form = angular.copy(item);
		$scope.form.startDate = $scope.converDateTime($scope.form.startDate);
		$scope.form.endDate = $scope.converDateTime($scope.form.endDate);
		$(".nav-tabs a:eq(1)").tab("show");

		var now = item.created_time;
		// now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
		$scope.times = item.created_time;
		// $('#created_time').val(now.toISOString());

	}

	$scope.create = function () {
		document.querySelector('.load-form-container').classList.add('fade-in-form');
		var item = angular.copy($scope.form);

		$http.post("/vgreens/rest/discount", item).then(resp => {

			$scope.items.push(resp.data);
			$scope.sweetalert2("Thêm mới thành công", '', 'success')
			$scope.reset()
		}).catch(error => {
			$scope.sweetalert2("Thêm mới thất bại", 'Kiểm tra lại các trường dữ liệu của bạn', 'error')
			console.log("Error", error);
		}).finally(function () {
			document.querySelector('.load-form-container').classList.remove('fade-in-form');
		  });
	}


	$scope.update = function () {
		document.querySelector('.load-form-container').classList.add('fade-in-form');
		var item = angular.copy($scope.form);

		$http.put(`/vgreens/rest/discount/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.id = item.id);
			$scope.items[index] = item;
			$scope.items;
			$scope.sweetalert2("Cập nhập thành công", '', 'success')
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại", '', 'error')
			console.log("Error Update" + error);
		}).finally(function () {
			document.querySelector('.load-form-container').classList.remove('fade-in-form');
		  });
	}

	$scope.updateStatus = function (item) {

		$http.put(`/vgreens/rest/discount/updateStatus/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.id = item.id);
			$scope.items[index] = item;
			$scope.items;
			$scope.initialize();
			$scope.sweetalert2("Cập nhập thành công", '', 'success')
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại", '', 'error')
			console.log("Error Update" + error);
		})
	}

	$scope.delete = function (item) {

		$http.delete(`/vgreens/rest/discount/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.sweetalert2("Xóa thành công", '', 'success')
			$scope.reset();

		}).catch(error => {
			console.log("Delete Error: ", error);
			$scope.sweetalert2("Xóa thất bại", '', 'error')


		})
	}


	$scope.imageChanged = function (files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/vgreens/rest/upload/imageTemp', data, {
		  transformRequest: angular.identity,
		  headers: { 'Content-Type': undefined }
		}).then(resp => {
		  $scope.imageTemp = resp.data.name
		  $scope.form.img = resp.data.img;
		  console.log("Img", $scope.form.img);
		}).catch(error => {
		  alert("Lỗi dung lượng ảnh quá lớn")
		  console.log("Error", error);
		})
	  }



	$scope.sweetalert2 = function (title, mess, type) {
		Swal.fire(
			title,
			mess,
			type
		)
	}

	// Phân trang

	// $scope.pager = {
	//     page: 0,
	//     size: 10,
	//     get items() {
	//         var start = this.page * this.size;
	//         return $scope.items.slice(start, start + this.size);
	//     },
	//     get count(){// tong so item chia cho phần tử moi trang va lam tron
	//         return Math.ceil(1.0 *$scope.items.length/this.size);
	//     },
	//     first(){
	//         this.page=0;
	//     },
	//     prev(){
	//         this.page--;
	//         if(this.page<0){
	//             this.last();
	//         }
	//     },
	//     next(){
	//         this.page++;
	//         if(this.page >=this.count){
	//             this.first();
	//         }
	//     },
	//     last(){
	//         this.page = this.count-1;
	//     }

	// }



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



})

