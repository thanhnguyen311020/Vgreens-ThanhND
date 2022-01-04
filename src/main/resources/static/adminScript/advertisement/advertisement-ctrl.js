app.controller("advertisement-ctrl", function ($http, $scope, $timeout) {
	$scope.items = [];
	$scope.form = {};

	$scope.isShow = false;

	// dirPaginate 
	$scope.pageSize = 10;
	$scope.sort = function (keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	}

	$scope.initialize = function () {
		$http.get("/vgreens/rest/advertisement").then(resp => {
			$scope.items = resp.data;
		})		
	}


	$scope.initialize();

	$scope.selectItem = function (item) {
		$scope.clickedItem = angular.copy(item);
		$scope.message = $scope.clickedItem.id;
	}


	$scope.reset = function () {
		$scope.form = {
			startDate: new Date(),
			endDate: new Date()
		}

		$scope.isShow = false;

		$('#password').val('');
	}

	$scope.reset();

	$scope.edit = function (item) {
		//console.log("0k")
		$scope.isShow = true;
		$scope.form = angular.copy(item);
        // $scope.form.startDate = new Date(item.startDate);
        $scope.form.endDate = new Date(item.endDate);
		// $scope.chooseCountry();
		$(".nav-tabs a:eq(1)").tab("show");
	}

	$scope.create = function () {
		document.querySelector('.load-form-container').classList.add('fade-in-form');
		var item = angular.copy($scope.form);
		$http.post("/vgreens/rest/advertisement/create", item).then(resp => {

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
		$http.put(`/vgreens/rest/advertisement/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.id == item.id);
			$scope.items[index] = item;
			// $scope.items;
			$scope.sweetalert2("Cập nhập thành công", '', 'success')
		
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại", '', 'error')
			console.log("Error Update" + error);
		}).finally(function () {
			document.querySelector('.load-form-container').classList.remove('fade-in-form');
		});
	}

	$scope.delete = function (item) {

		$http.delete(`/vgreens/rest/account/${item.username}`).then(resp => {
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


	$scope.sweetalert2 = function (title, mess, type) {
		Swal.fire(
			title,
			mess,
			type
		)
	}

})

