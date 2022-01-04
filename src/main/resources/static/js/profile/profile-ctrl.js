app.controller('profile-ctrl', function ($scope, $http, $rootScope) {
    $rootScope.user = {};
    $scope.country = [];
    $scope.items = [];
    $scope.max = new Date();
    $scope.histories = [];
    $scope.pageSize = 10;
    $scope.detailHistories = [];
    $scope.countryall = [];
    $scope.states = [];
    $scope.getUserNow = function () {
        $http.get(`/vgreens/account/rest/profile`).then(resp => {
            $scope.user = resp.data;
            $scope.user.register_date = new Date(resp.data.register_date);
            // console.log($scope.user);
        });
        $http.get(`/vgreens/account/rest/country`).then(resp => {
            $scope.country = resp.data;
            // console.log($scope.country);
        })

        // order history
        $http.get("/vgreens/rest/orders/findall").then(resp => {
            $scope.histories = resp.data;

        });


    }

    $scope.getUserNow();
    // cap nhat user
    $scope.update = function () {
        var item = angular.copy($scope.user);
        item.password = "";
        $http.put(`/vgreens/account/rest/profile/${item.username}`, item).then(resp => {
            var index = $scope.items.findIndex(a => a.username == item.username);
            $scope.items[index] = item;
            console.log($scope.items[index]);
            $scope.sweetalert2("Cập nhật thông tin người dùng thành công !", '', 'success');
        }).catch(error => {
            $scope.sweetalert2("Không thể cập nhật thông tin !!", '', 'error');
            console.log("Error:", error);
        })
    }
    // load image
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]); //lấy file bỏ vào data
        $http.post('/vgreens/rest/upload/imageTemp', data, { //post lên server
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(resp => {
            // $scope.user.photo = resp.data.name; //upload xong lấy tên bỏ vào image của form
            $scope.user.img = resp.data.img; //upload xong lấy tên bỏ vào image của form
            $scope.imageTemp = resp.data.name; //upload xong lấy tên bỏ vào image của form

        }).catch(error => {
            alert("Không thể cập nhật hình ảnh ! ");
            $scope.sweetalert2("Không thể cập nhật hình ảnh !", '', 'error');
            console.log("Error", error);
        });
    }
    $scope.sweetalert2 = function (title, mess, type) {
            Swal.fire(
                title,
                mess,
                type
            )
        },

        $scope.getAllCountry = function () {
            $http.get("/vgreens/rest/countrysite").then(resp => {

                $scope.countryall = resp.data;

            });

        },
        $scope.getAllCountry();
    $scope.getAllState = function () {
            $http.get("/vgreens/rest/statesite").then(resp => {

                $scope.states = resp.data;

            });

        },
        $scope.getAllState();
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
        // hien thi state khi chon country
        var id = $scope.user.country.id; // cái root nay dung ko e,à k dối thành scope đi cx dc
        //lay id country tu form
        if (id != null) {
            $http.get(`/vgreens/rest/statesite/${id}`).then(resp => {
                $scope.states = resp.data;
                console.log("state", $scope.states)
            })
        } else {
            $scope.states = [];
        }
    }
    // action collapse

    $scope.print = function ($event, id) {
        angular.element('.panel-collapse').collapse('hide');
        // order detail histories
        $http.get(`/vgreens/rest/orders/orderdetail/findall/${id}`).then(resp => {
            $scope.detailHistories = resp.data;
            console.log($scope.detailHistories);
        });
    }
    // phân trang cho order histories
    $scope.pager = {
        page: 0,
        size: 10,
        get histories() {
            var start = this.page * this.size;
            return $scope.histories.slice(start, start + this.size); //trích sp de xem
        },
        get count() {
            return Math.ceil(1.0 * $scope.histories.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        },
    }
});