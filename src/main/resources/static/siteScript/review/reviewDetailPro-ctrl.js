app.controller("product-review-ctrl", function ($scope, $http) {
    // one product review
    $scope.values = [];
    $scope.comments = [];
    $scope.getView = function () {
        const id = document.getElementById('product_id').value;
        $http.get(`/vgreens/site/rest/product/review/${id}`).then(resp => {
            $scope.values = resp.data;
            console.log($scope.values);
        })
        $http.get(`/vgreens/rest/comment/${id}`).then(resp => {
            $scope.comments = resp.data;
            console.log($scope.comments);
        })
    };
    $scope.getView();
    // phân trang cho detail product review
    $scope.pager = {
        page: 0,
        size: 10,
        get values() {
            var start = this.page * this.size;
            return $scope.values.slice(start, start + this.size); //trích sp de xem
        },
        get count() {
            return Math.ceil(1.0 * $scope.values.length / this.size);
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


    // comment product
    $scope.items = [];
    $scope.formCmt = {};
    $scope.create = function (id) {
        var item = angular.copy($scope.formCmt);
        $http.post(`/vgreens/rest/comment/${id}`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            console.log(resp.data);
            let add = {
                title: "Thành công",
                message: "Cảm ơn bạn đã đánh giá sản phẩm " + +" của chúng tôi ",
                status: TOAST_STATUS.SUCCESS,
                timeout: 3000
            }
            Toast.create(add);
            $scope.getView();
        })
    }
    $scope.reset = function () {
        $scope.formCmt = {

        };
    }
})