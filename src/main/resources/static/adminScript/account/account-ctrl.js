app.controller("account-ctrl", function ($http, $scope, $timeout) {

	$scope.items = [];
	$scope.form = {};
	$scope.countries = [];
	$scope.states = [];
	// $scope.states = [];

	$scope.isShow = false;

	// dirPaginate 
	$scope.pageSize = 10;
	$scope.sort = function (keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	}

	$scope.initialize = function () {
		$http.get("/vgreens/rest/account/list").then(resp => {
			$scope.items = resp.data;
			// console.log(angular.element('#checkAccount'));
			// var x = angular.element('.checkAccount'  );
			// console.log("Checkbox", x.length)
		})

		$http.get("/vgreens/rest/countryAdmin").then(resp => {
			$scope.countries = resp.data;
			// console.log($scope.countries)
		})

		
	}

	$scope.getCheckbox = function () {
		var checkbox = document.getElementsByClassName("checkAccount");
		console.log("checkBox", checkbox.length)
	}
	// $timeout(
	// 	function(){ $scope.getCheckbox()},1000);
	$scope.getCheckbox();


	$scope.initialize();

	$scope.selectItem = function (item) {
		$scope.clickedItem = angular.copy(item);
		$scope.message = $scope.clickedItem.username;
	}


	$scope.reset = function () {
		$scope.form = {
			register_date: new Date()
		}

		$scope.isShow = false;

		$('#password').val('');
	}

	$scope.reset();

	$scope.edit = function (item) {
		//console.log("0k")
		$scope.isShow = true;
		$scope.form = angular.copy(item);
		$scope.chooseCountry();
		$(".nav-tabs a:eq(1)").tab("show");
	}

	$scope.create = function () {
		document.querySelector('.load-form-container').classList.add('fade-in-form');
		var item = angular.copy($scope.form);
		item.password = $('#password').val();
		$http.post("/vgreens/rest/account/create", item).then(resp => {

			$scope.items.push(resp.data);
			$scope.sweetalert2("Thêm mới thành công", '', 'success')
			$scope.initialize();
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
		item.password = $('#password').val();
		$http.put(`/vgreens/rest/account/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.username == item.username);
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
		$http.put(`/vgreens/rest/account/updateStatus/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.username = item.username);
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

		$http.delete(`/vgreens/rest/account/${item.username}`).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
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
			// $scope.form.photo = resp.data.name;    
			// $scope.form.image = resp.data.img;
			$scope.imageTemp = resp.data.name
			$scope.form.img = resp.data.img;
			// console.log("Img", $scope.form.img);
		}).catch(error => {
			alert("Lỗi dung lượng ảnh quá lớn")
			console.log("Error", error);
		})
	}

	// $('#country').on('change', function (e) {
	// 	// var optionSelected = $("option:selected", this);
	// 	// console.log(optionSelected)
	// 	var valueSelected = this.value;
	// 	console.log(valueSelected)

	// });
	// $('#country')
	$('#state').attr("disabled", true);
	$scope.chooseCountry = function () {
		if ($('#country').val() != null) {
			$('#state').attr("disabled", false);
			$scope.showStateWhenChooseCountry();
		} else if ($('#country').val() === "") {
			$('#state').attr("disabled", true);
		}
	}

	$scope.showStateWhenChooseCountry = function () {
		// var valueSeleted = this.value;
		var id = $scope.form.country.id;
		if (id != null) {
			$http.get(`/vgreens/rest/stateAdmin/${id}`).then(resp => {
				$scope.states = resp.data;
			})
		} else {
			$scope.states = [];
		}
	}

	

	$scope.filterAccountByStatus = function(status) {
		if(status == ""){
			$http.get("/vgreens/rest/account/list").then(resp => {
				$scope.items = resp.data;
			})
		}else{
			$http.get(`/vgreens/rest/account/status?status=${status}`).then(resp => {
				$scope.items = resp.data;
			})
		}
	}

	$scope.orderDetails = [];
	$scope.getOrderDetailByUsername = function(item){
		$http.get(`/vgreens/rest/orderDetailAdmin/getBy?username=${item.username}`).then(resp =>{
			$scope.orderDetails = resp.data;
			console.log("Detail Order: ", $scope.orderDetail);
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

