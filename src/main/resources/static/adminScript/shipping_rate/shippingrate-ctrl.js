app.controller("shippingrate-ctrl", function ($scope, $http, $filter, $timeout) {
  $scope.items = [];
  $scope.form = {};
  $scope.countries = [];
  $scope.states = [];
  $scope.isShow = false;
  $scope.regex = "/^-?[0-9][^\.]*$/";
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
    $http.get("/vgreens/rest/shippingRate/list").then((resp) => {
      $scope.items = resp.data;
    });
    $http.get("/vgreens/rest/countryAdmin").then(resp => {
      $scope.countries = resp.data;
    });
    $http.get("/vgreens/rest/stateAdmin").then(resp => {
      $scope.states = resp.data;
    });
  };

  $scope.selectItem = function (item) {
    $scope.clickedItem = angular.copy(item);
    console.log($scope.clickedItem);
    $scope.message = $scope.clickedItem.state;
  };
  $scope.initialize();
  $scope.reset = function () {
    $scope.form = {
      // image: 'product-1.png'
    };
    $scope.isShow = false;
  };

  $scope.edit = function (item) {
    console.log(item);
    $scope.isShow = true;
    $scope.form = angular.copy(item);
    $(".nav-tabs a:eq(1)").tab("show");
  };

  $scope.create = function () {
    var item = angular.copy($scope.form);
    $http.post("/vgreens/rest/shippingRate/create", item)
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
    $http.put(`/vgreens/rest/shippingRate/${item.id}`, item)
      .then((resp) => {
        var index = $scope.items.findIndex((a) => (a.id = item.id));
        $scope.items[index] = item;
        $scope.items;
        $scope.sweetalert2("Cập nhập thành công", "", "success");
      })
      .catch((error) => {
        $scope.sweetalert2("Cập nhập thất bại", "", "error");
        console.log("Error Update" + error);
      });
  };

  $scope.updateCodSupported = function(item){
    $http.put(`/vgreens/rest/shippingRate/updateCodSupported/${item.id}`,item).then(resp => {
			var index = $scope.items.findIndex(a => a.id = item.id);
			$scope.items[index] = item;
			$scope.items;
      $scope.initialize();
			$scope.sweetalert2("Cập nhập thành công", '', 'success')
		}).catch(error => {
			$scope.sweetalert2("Cập nhập thất bại",'','error')
			console.log("Error Update" + error);
		})
  }

  $scope.delete = function (item) {
    $http
      .delete(`/vgreens/rest/shippingRate/${item.id}`)
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
 
  $scope.chooseCountry = function () {
    if ($('#country').val() != null) {
        $('#state').attr("disabled", false);
        $scope.showStateWhenChooseCountry();
    } else if ($('#country').val() === "") {
        $('#state').attr("disabled", true);
    }
};

$scope.showStateWhenChooseCountry = function () {
    // var valueSeleted = this.value;
    // hien thi state khi chon country
    var id = $scope.form.country.id;
    //lay id country tu form
    if (id != null) { 
        $http.get(`/vgreens/rest/stateAdmin/${id}`).then(resp => {
            $scope.states = resp.data;
            console.log("state", $scope.states)
        })
        var chekbox = $timeout(
          $scope.getChecked = function () {
              console.log(document.getElementsByName("checkState").length);
              return document.getElementsByName("checkState");
  
          }, 1000);
      console.log("checkbox", chekbox);
    } else {
        $scope.states = [];
    }
};


  $scope.sweetalert2 = function (title, mess, type) {
    Swal.fire(title, mess, type);
  };
});
// 
