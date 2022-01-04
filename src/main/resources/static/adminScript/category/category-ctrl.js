app.controller("category-ctrl", function ($scope, $http, $filter, $timeout, $interval) {
  $scope.items = [];
  $scope.form = {};
  $scope.isShow = false;
  // $scope.page = 0;
  $scope.pageSize = 10;

  $scope.sort = function (keyname) {
    $scope.sortKey = keyname; // set the sortKey to the param passed
    $scope.reverse = !$scope.reverse; // if true make it fasle and vice versa
  };

  // $scope.isAll = false;
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

  $scope.initialize = function () {
    $http.get("/vgreens/rest/category/list").then((resp) => {
      $scope.items = resp.data;
      console.log($scope.items);
    });
  };

  $scope.selectItem = function (item) {
    $scope.clickedItem = angular.copy(item);
    console.log($scope.clickedItem);
    $scope.message = $scope.clickedItem.name;
  };
  $scope.initialize();
  $scope.reset = function () {
    $scope.form = {
      image: null,
      img: null
    };
    $('#image').val('');
    $scope.imageTemp = null;
    $scope.isShow = false;
  };

  $scope.edit = function (item) {
    console.log(item);
    $scope.isShow = true;
    $scope.form = angular.copy(item);
    console.log($scope.form.img);
    console.log($scope.form.image);
    console.log("DK: ", ($scope.form.image == null) && ($scope.form.img == null))

    $(".nav-tabs a:eq(1)").tab("show");
  };

  $scope.create = function () {
    document.querySelector('.load-form-container').classList.add('fade-in-form');
    var item = angular.copy($scope.form);
    console.log("item: ", item)
    $http.post("/vgreens/rest/category/create", item).then((resp) => {
        $scope.items.push(resp.data);
        $scope.sweetalert2("Thêm mới thành công", "", "success");
        $scope.initialize();
        $scope.reset();
      })
      .catch((error) => {
        $scope.sweetalert2(
          "Thêm mới thất bại",
          "Kiểm tra lại các trường dữ liệu của bạn",
          "error"
        );
        console.log("Error", error);
      }).finally(function() {
        document.querySelector('.load-form-container').classList.remove('fade-in-form');
    });
  };

  $scope.update = function () {
    document.querySelector('.load-form-container').classList.add('fade-in-form');
    var item = angular.copy($scope.form);
    
    $http.put(`/vgreens/rest/category/${item.id}`, item).then((resp) => {
        
        var index = $scope.items.findIndex((a) => (a.id == item.id));
        $scope.items[index] = item;
        $scope.items;
        $scope.sweetalert2("Cập nhập thành công", "", "success");
      }).catch((error) => {
        $scope.sweetalert2("Cập nhập thất bại", "", "error");
        console.log("Error Update" + error);
      }).finally(function() {
        document.querySelector('.load-form-container').classList.remove('fade-in-form');
    });
  };

  $scope.loading = function () {
    // $timeout(function () {
    //   document.querySelector('.load-form-container').classList.add('fade-in-form');
    // }, 2000);
  }
  $scope.delete = function (item) {
    $http
      .delete(`/vgreens/rest/category/${item.id}`)
      .then((resp) => {
        var index = $scope.items.findIndex((p) => p.id == item.id);
        $scope.items.splice(index, 1);
        $scope.sweetalert2("Xóa thành công", "", "success");
        $scope.reset();
      })
      .catch((error) => {
        console.log("Delete Error: ", error);
        $scope.sweetalert2("Xóa thất bại", "", "error");
      });
  };

  $scope.deleteAll = function () {
    var itemList = [];
    $.each($scope.checked, function (value, item) {
      itemList.push(item.id);
      return parseString(itemList);

    });
    $http
      .delete(`/vgreens/rest/category/${itemList.id}`)
      .then((resp) => {
        var index = $scope.items.findIndex((p) => p.id == itemList.id);
        $scope.items.splice(index, 1);
        $scope.sweetalert2("Xóa thành công", "", "success");
        $scope.reset();
      })
      .catch((error) => {
        console.log("Delete Error: ", error);
        $scope.sweetalert2("Xóa thất bại", "", "error");
      });
  };

  $scope.imageChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/vgreens/rest/upload/imageTemp", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        // $scope.form.image = resp.data.name;
        $scope.imageTemp = resp.data.name
        console.log("Img temp:" ,$scope.imageTemp);
        $scope.form.img = resp.data.img;
        console.log("Img", $scope.form.img);
      })
      .catch((error) => {
        alert("Error");
        console.log("Error", error);
      });
  };

  // phan trang


  $scope.sweetalert2 = function (title, mess, type) {
    Swal.fire(title, mess, type);
  };
});
