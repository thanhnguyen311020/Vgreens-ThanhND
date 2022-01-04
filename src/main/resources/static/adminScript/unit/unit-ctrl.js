app.controller("unit-ctrl", function ($http, $scope) {

	$scope.items = [];
	$scope.form ={};


	$scope.isShow=false;

    // dirPaginate 
    $scope.pageSize=5;
    $scope.sort = function(keyname){
        $scope.sortKey = keyname; // set the sortKey to the param passed
        $scope.reverse = !$scope.reverse // if true make it fasle and vice versa
    }

	$scope.initialize = function () {
		$http.get("/vgreens/rest/unit").then(resp => {
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
			
		}

		$scope.isShow = false;

	}

	$scope.edit = function (item){
		//console.log("0k")
		$scope.isShow = true;
			$scope.form = angular.copy(item);
			$(".nav-tabs a:eq(1)").tab("show");
			
	}

	$scope.create = function () {
		
		var item = angular.copy($scope.form);

		$http.post("/vgreens/rest/unit", item).then(resp => {

			$scope.items.push(resp.data);
			$scope.sweetalert2("Thêm mới thành công",'','success')
			$scope.reset()
		}).catch(error => {
			$scope.sweetalert2("Thêm mới thất bại",'Kiểm tra lại các trường dữ liệu của bạn','error')
			console.log("Error", error);
		})
	}


	$scope.update = function () {
		var item = angular.copy($scope.form);

		$http.put(`/vgreens/rest/unit/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(a => a.id = item.id);
			$scope.items[index] = item;
			$scope.items;
			$scope.sweetalert2("Cập nhập thành công",'','success')
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại",'','error')
			console.log("Error Update" + error);
		})
	}


	$scope.delete = function (item) {
		
		$http.delete(`/vgreens/rest/unit/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			$scope.sweetalert2("Xóa thành công",'','success')
			$scope.reset();
			
		}).catch(error => {
			console.log("Delete Error: ", error);
			$scope.sweetalert2("Xóa thất bại",'','error')
		

		})
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


	
	$scope.sweetalert2 = function(title, mess, type){
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

