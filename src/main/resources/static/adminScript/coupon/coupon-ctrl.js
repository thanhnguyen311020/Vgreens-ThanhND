app.controller("coupon-ctrl", function ($scope, $http, $filter) {
  $scope.items = [];
  $scope.form = {};
  $scope.isShow = false;
  // $scope.page = 0;
  $scope.pageSize = 8;

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
    $http.get("/vgreens/rest/coupon/list").then((resp) => {
      $scope.items = resp.data;
      $scope.items.forEach(item => {
        item.created_time = new Date(item.created_time)
      })
      console.log($scope.items)
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
      created_time: new Date(),
      updated_time: new Date()
    };
    $scope.isShow = false;
  };

  $scope.reset();

  $scope.edit = function (item) {
    console.log(item);
    $scope.isShow = true;
    $scope.form = angular.copy(item);
    $scope.form.created_time = $scope.converDateTime($scope.form.created_time);
		$scope.form.updated_time = $scope.converDateTime($scope.form.updated_time);
    $(".nav-tabs a:eq(1)").tab("show");
  };

  $scope.create = function () {
    var item = angular.copy($scope.form);
    $http
      .post("/vgreens/rest/coupon/create", item)
      .then((resp) => {
        $scope.items.push(resp.data);
        $scope.sweetalert2("Thêm mới thành công", "", "success");
        $scope.reset();
      })
      .catch((error) => {
        $scope.sweetalert2(
          "Thêm mới thất bại",
          "Kiểm tra lại các trường dữ liệu của bạn",
          "error"
        );
        console.log("Error", error);
      });
  };

  $scope.update = function () {
    var item = angular.copy($scope.form);
    item.updated_time = new Date();
    $http
      .put(`/vgreens/rest/coupon/${item.coupon_id}`, item)
      .then((resp) => {
        var index = $scope.items.findIndex((a) => (a.coupon_id == item.coupon_id));
        $scope.items[index] = item;
        $scope.items;
        $scope.sweetalert2("Cập nhập thành công", "", "success");
      })
      .catch((error) => {
        $scope.sweetalert2("Cập nhập thất bại", "", "error");
        console.log("Error Update" + error);
      });
  };

  $scope.delete = function (item) {
    $http
      .delete(`/vgreens/rest/coupon/${item.coupon_id}`)
      .then((resp) => {
        var index = $scope.items.findIndex((p) => p.id == item.coupon_id);
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
      .delete(`/vgreens/rest/coupon/${itemList.id}`)
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

  // phan trang
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

  $scope.sweetalert2 = function (title, mess, type) {
    Swal.fire(title, mess, type);
  };
});

