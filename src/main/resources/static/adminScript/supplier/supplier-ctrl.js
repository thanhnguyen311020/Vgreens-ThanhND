app.controller("supplier-ctrl", function ($http, $scope, $filter,$rootScope) {

	$scope.items = [];
	$scope.form = {};
	// $scope.form.create_time = new Date();

	$scope.isShow = false;

	$scope.pageSize = 10;

    $scope.sort = function(keyname){
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
    $scope.$watch(
      "items",
      function (n, o) {
        var checked = $filter("filter")(n, { checked: true });
        if (checked.length) {
          $scope.selected = checked;
          $("#btnDelete").removeAttr("disabled");
        } else {
          $("#btnDelete").attr("disabled", "disabled");
        }
      },
      true
    );

	// $scope.setDateNow = function () {

	// 	var now = new Date();

	// 	var day = ("0" + now.getDate()).slice(-2);
	// 	var month = ("0" + (now.getMonth() + 1)).slice(-2);

	// 	var today = now.getFullYear() + "-" + (month) + "-" + (day);

	// 	$('#createDate').val(today);

	// }
	// $scope.setDateNow();
	// dirPaginate 
	$scope.pageSize = 10;
	$scope.sort = function (keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse // if true make it fasle and vice versa
	}

	$scope.initialize = function () {
		$http.get("/vgreens/rest/supplier").then(resp => {
			$scope.items = resp.data;
		})
	}

	$scope.initialize();

	$scope.selectItem = function (item) {
		$scope.clickedItem = angular.copy(item);
		$scope.message = $scope.clickedItem.name;
	}


	$scope.reset = function () {
		$scope.form = {
			created_time: new Date()
		}
		// $scope.setDateNow();
		$scope.isShow = false;

	}

	$scope.reset();

	$scope.edit = function (item) {
		$scope.isShow = true;
		$scope.form = angular.copy(item);
		$scope.form.created_time = new Date(item.created_time)
		$(".nav-tabs a:eq(1)").tab("show");
	}

	$scope.create = function () {

		var item = angular.copy($scope.form);
		$http.post("/vgreens/rest/supplier", item).then(resp => {

			$scope.items.push(resp.data);
			$scope.sweetalert2("Thêm mới thành công", '', 'success')
			$scope.reset()
		}).catch(error => {
			$scope.sweetalert2("Thêm mới thất bại", 'Kiểm tra lại các trường dữ liệu của bạn', 'error')
			console.log("Error", error);
		})
	}


	$scope.update = function () {
		var item = angular.copy($scope.form);
		item.update_time = new Date()
		$http.put(`/vgreens/rest/supplier/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.id = item.id);
			$scope.items[index] = item;
			$scope.items;
			$scope.sweetalert2("Cập nhập thành công", '', 'success')
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại", '', 'error')
			console.log("Error Update" + error);
		})
	}


	$scope.delete = function (item) {

		$http.delete(`/vgreens/rest/supplier/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.sweetalert2("Xóa thành công", '', 'success')
			$scope.reset();

		}).catch(error => {
			console.log("Delete Error: ", error);
			$scope.sweetalert2("Xóa thất bại", '', 'error')


		})
	}




	$scope.sweetalert2 = function (title, mess, type) {
		Swal.fire(
			title,
			mess,
			type
		)
	}

	$scope.convertDate = function (date) {
		var createdTime=new Date(date);
		var day1 = ("0" + createdTime.getDate()).slice(-2);
		var month1 = ("0" + (createdTime.getMonth() + 1)).slice(-2);

		var dates = createdTime.getFullYear() + "-" + (month1) + "-" + (day1);
		return dates;
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

