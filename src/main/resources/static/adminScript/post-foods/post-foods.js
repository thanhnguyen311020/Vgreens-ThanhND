
app.controller("post-foods-ctrl", function ($scope, $http, $filter) {
    $scope.items = [];
    $scope.form = {};

    $scope.isShow = false;
    $scope.pageSize = 10;
    $scope.form.created_time = new Date();
    $scope.searchCon= '';
  
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
  
      $http.get("/vgreens/rest/post_foods/list").then(resp => {
        $scope.items = resp.data;
        $scope.items.forEach(item => {
          item.created_time = new Date(item.created_time)
        })
        console.log($scope.items)
      });
    
  
     
  
     
    }
    $scope.initialize();
    $scope.selectItem = function (item) {
      $scope.clickedItem = angular.copy(item);
      $scope.message = $scope.clickedItem.postfoods_Id;
    }
    $scope.reset = function () {
      $scope.form = {
        created_time: new Date(),
        image: 'no_image.png'
      }
  
     
      $scope.isShow = false;
    }
  
    $scope.edit = function (item) {
      //console.log("0k")
      $scope.isShow = true;
      $scope.form = angular.copy(item);
    
      $(".nav-tabs a:eq(1)").tab("show");
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
  
    $scope.update = function () {
      document.querySelector('.load-form-container').classList.add('fade-in-form');
      var item = angular.copy($scope.form);
      item.created_time = new Date();
      // item.consignment.id = $('#consignmentId').val();
      $http.put(`/vgreens/rest/post_foods/${item.postfoods_Id}`, item).then(resp => {
        var index = $scope.items.findIndex(a => a.postfoods_Id = item.postfoods_Id);
        $scope.items[index] = item;
        // $scope.items = {};
        $scope.sweetalert2("Cập nhập thành công", '', 'success')
      }).catch(error => {
        $scope.sweetalert2("Cập nhập thất bại", '', 'error')
        console.log("Error Update" + error);
      }).finally(function () {
        document.querySelector('.load-form-container').classList.remove('fade-in-form');
      });
    }
  
   
  
    $scope.create = function () {
      document.querySelector('.load-form-container').classList.add('fade-in-form');
      var item = angular.copy($scope.form);
     
      // item.consignment.id = $('#consignmentId').val();
      $http.post("/vgreens/rest/post_foods/create", item).then(resp => {
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
    $scope.delete = function (item) {
      $http.delete(`/vgreens/rest/post_foods/${item.postfoods_Id}`).then(resp => {
        var index = $scope.items.findIndex(p => p.postfoods_Id == item.postfoods_Id);
        $scope.items.splice(index, 1);
        $scope.sweetalert2("Xóa thành công", '', 'success')
        $scope.reset();
      }).catch(error => {
        console.log("Delete Error: ", error);
        $scope.sweetalert2("Xóa thất bại", '', 'error')
      })
    }
    
   
  

  
    $scope.temp = {};
    $scope.selectConsignment = function (item) {
      $scope.temp = item;
  
      $('#consignmentId').val($scope.temp.id)
      $('#consignmentName').val($scope.temp.name);
      Object.assign($scope.form, {consignment: {id: $scope.temp.id} });
    
      console.log("Consignment Id: ", $('#consignmentId').val())
      console.log("Form:", $scope.form);
    }
  
    // $scope.getConsignmentByModal = function(){
    //   $scope.form.consignment.id = $scope.temp.id;
    //   console.log($scope.form.consignment.id);
    // }
  
  
    $scope.sweetalert2 = function (title, mess, type) {
      Swal.fire(
        title,
        mess,
        type
      )
    }
  
    // $scope.check = function(){
    //   if($('#consignmentName').val() == '' && $('#consignmentId') == ''){
    //     $('#btnCreate').prop('disabled', false)
    //   }else{
    //     $('#btnCreate').prop('disabled', true)
    //   }
    // }
  
    // $scope.check();
  
  });
  
  