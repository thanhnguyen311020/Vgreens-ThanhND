

app.controller("shopping-cart-ctrl", function ($scope, $http,$window, $interval, $timeout) {

    $scope.shipping_rate = [];
    $scope.shipping_rateByState = [];
    $scope.countryall = [];
    $scope.states = [];
    $scope.confirmOrderInfo = {};
    $scope.formOrder = {};
    $scope.codSupport = {};
    $scope.couponsform = {};
    $scope.coupons = [];
    $scope.getcoupon2 =[];
    var json = localStorage.getItem("Coupon");
    $scope.getcoupon2 = json ? JSON.parse(json) : [];
   

    // ngay+'/'+thang+'/'+nam;





    $scope.getAllCountry = function () {
        $http.get("/vgreens/rest/countrysite").then(resp => {

            $scope.countryall = resp.data;

        });

    },
        $scope.getAllState = function () {
            $http.get("/vgreens/rest/statesite").then(resp => {

                $scope.states = resp.data;

            });

        },
        // $('#state').attr("disabled", true);
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
        var id = $scope.formOrder.country.id;
        //lay id country tu form
        if (id != null) {
            $http.get(`/vgreens/rest/statesite/${id}`).then(resp => {
                $scope.states = resp.data;
                console.log("state", $scope.states)
            })
        } else {
            $scope.states = [];
        }
    },

        $scope.showShippingRateWhenChooseSate = function () {
            var state = $scope.formOrder.state;

            if (state != null) {
                $http.get(`/vgreens/rest/shippingrates/findbystate/${state}`).then(resp => {
                    $scope.codSupport = resp.data;
                    $scope.shipping_rate = resp.data.rate;
                    sessionStorage.setItem("ship-rate", JSON.stringify($scope.shipping_rate));


                    console.log("shipping_rateByState: ", $scope.codSupport)
                })
            } else {
                $scope.getCheckoutCodSupport();
            }
        }



    // $scope.getShippingRates = function () {
    //         $http.get("/vgreens/rest/checkout/shippingrates").then(resp => {
    //             $scope.shipping_rate = resp.data.rate;
    //             sessionStorage.setItem("ship-rate", JSON.stringify($scope.shipping_rate));
    //             console.log("shipping rates", $scope.shipping_rate);
    //         });
    //     },

    $scope.getInforOrderByAccount = function () {
        $http.get("/vgreens/rest/checkout/account").then(resp => {

            $scope.formOrder = angular.copy(resp.data);
            console.log("infoOrderToAccount:", $scope.formOrder);
        });

    },


        $scope.getCheckoutCodSupport = function () {
            $http.get("/vgreens/rest/checkout/checkoutinfo").then(resp => {

                $scope.codSupport = resp.data;
                $scope.shipping_rate = resp.data.rate;
                sessionStorage.setItem("ship-rate", JSON.stringify($scope.shipping_rate));
                console.log("codSupport:", $scope.codSupport);
            });

        },



        $scope.getInforOrderByAccount();

    // $scope.getShippingRates();

    $scope.getAllCountry();
    $scope.getAllState();
    $scope.showShippingRateWhenChooseSate();
    $scope.getCheckoutCodSupport();



    $scope.cart = {

        items: [],



        //them vao gio hang
        addtocart(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {

                item.qty++;
                this.saveToLocalStorage();
                let add = {
                    title: "Thành công",
                    message: "Đã thêm sản phẩm " + item.name + " vào giỏ hàng ",
                    status: TOAST_STATUS.SUCCESS,
                    timeout: 1000
                }
                Toast.create(add);

            } else {
                $http.get(`/vgreens/site/rest/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                    let add = {
                        title: "Thành công",
                        message: "Đã thêm sản phẩm " + resp.data.name + " vào giỏ hàng ",
                        status: TOAST_STATUS.SUCCESS,
                        timeout: 1000
                    }
                    Toast.create(add);
                })
            }

        },
        //xoa khoi gio hang
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
            let remove = {
                title: "Thành công",
                message: "Đã xóa sản phẩm ra khỏi giỏ hàng",
                status: TOAST_STATUS.SUCCESS,
                timeout: 1000
            }
            Toast.create(remove);


        },

        //xoa sach gio hang
        clear() {
            this.items = [];
            this.saveToLocalStorage();
            let clear = {
                title: "Thành công",
                message: "Đã xóa tất cả sản phẩm",
                status: TOAST_STATUS.SUCCESS,
                timeout: 3000
            }
            Toast.create(clear);
        },

        //tinh tien cua 1 san pham
        amt_of(item) { },

        //tinh tong so luong cac mat hang trong gio
        get count() {
            this.loadFromLocalStorage();
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);

        },
        //tinh tong thanh tien cac mat hang trong gio

        get sub_total() {

            return this.items
                .map(item => item.qty * item.consignment.selling_price)
                .reduce((total, qty) => total += qty, 0);
        },
        
        // tinh tien cac san pham da giam gia
        get amountdiscount() {
            //map toi san pham co discount va luu vao array
            $scope.itmesdiscount = this.items.filter((item) => item.discount != null)
            // console.log('array discount',$scope.itmesdiscount);
            return $scope.itmesdiscount.map(item => item.qty *
                (item.consignment.selling_price * item.discount.percent_discount / 100))
                .reduce((total, qty) => total += qty, 0);


        },

        // Tong tien tat ca
        get total() {

            if (Object.keys($scope.getcoupon2).length === 0) {
                return $scope.cart.sub_total - $scope.cart.amountdiscount
            } else{
                return $scope.cart.sub_total - $scope.cart.amountdiscount- 
            
                ($scope.cart.sub_total - $scope.cart.amountdiscount)* $scope.cart.coupon/100;
            }
           

        },

        get shipping() {
            let rate = sessionStorage.getItem("ship-rate");
            // if ($scope.cart.total>100000000) {
            //     return 1000000;
            // } else {

            // return $scope.shipping_rate.map((item => item.rate)*$scope.cart.total)
            // .reduce((total)=> total,0);
            return rate * $scope.cart.total / 100;


            // }


        },


        get TotalAll() {
            return $scope.cart.total + $scope.cart.shipping;
            // return $scope.cart.total;

        },
        get discount() {
            return $scope.cart.total + $scope.cart.shipping;
            // return $scope.cart.total;

        },
        
        get coupon(){
          
          
           
           
            return $scope.getcoupon2.coupon_percent;
        },




        //luu go hang vao local storage
        saveToLocalStorage() {

            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        //doc gio hang tu local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    },
        $scope.cart.loadFromLocalStorage();
    $scope.wishlist = {
        itemswishlist: [],


        //them vao gio hang
        addtowishlist(id) {
            var item = this.itemswishlist.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveWishListToLocalStorage();
                let addwishlist = {
                    title: "Thành công",
                    message: "Đã thêm sản phẩm " + item.name + " vào yêu thích ",
                    status: TOAST_STATUS.SUCCESS,
                    timeout: 3000
                }
                Toast.create(addwishlist);
            } else {
                $http.get(`/vgreens/site/rest/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.itemswishlist.push(resp.data);
                    this.saveWishListToLocalStorage();
                    let addwishlist = {
                        title: "Thành công",
                        message: "Đã thêm sản phẩm " + resp.data.name + " vào yêu thích ",
                        status: TOAST_STATUS.SUCCESS,
                        timeout: 3000
                    }
                    Toast.create(addwishlist);
                })
            }

        },

        //xoa khoi gio hang
        removewishlist(id) {
            var index = this.itemswishlist.findIndex(item => item.id == id);
            this.itemswishlist.splice(index, 1);
            this.saveWishListToLocalStorage();
            let remove = {
                title: "Thành công",
                message: "Đã xóa sản phẩm ra danh sách yêu thích",
                status: TOAST_STATUS.SUCCESS,
                timeout: 3000
            }
            Toast.create(remove);

        },

        //xoa sach gio hang
        clearwishlist() {
            this.itemswishlist = [];
            this.saveWishListToLocalStorage();
            let clear = {
                title: "Thành công",
                message: "Đã xóa tất cả sản phẩm",
                status: TOAST_STATUS.SUCCESS,
                timeout: 3000
            }
            Toast.create(clear);
        },

        //tinh tien cua 1 san pham
        amt_of(item) { },

        //tinh tong so luong cac mat hang trong gio
        get countwishlist() {
            this.loadWishListFromLocalStorage();
            return this.itemswishlist
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);

        },

        //tinh tong thanh tien cac mat hang trong gio
        get amountwishlist() {
            return this.itemswishlist
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },


        //luu go hang vao local storage
        saveWishListToLocalStorage() {

            var json = JSON.stringify(angular.copy(this.itemswishlist));
            localStorage.setItem("wishlist", json);
        },
        //doc gio hang tu local storage
        loadWishListFromLocalStorage() {
            var json = localStorage.getItem("wishlist");
            this.itemswishlist = json ? JSON.parse(json) : [];
        }

    }
    $scope.wishlist.loadWishListFromLocalStorage();
    $scope.selectMe = function (event) {


        location.href = "/vgreens/cart/checkout/confirmorder";
        $scope.cornfirmOrder();

    };
    $scope.cornfirmOrder = function () {

        $scope.confirmOrderInfo = angular.copy($scope.formOrder);
        var json = JSON.stringify($scope.confirmOrderInfo);
        localStorage.setItem("confirmOrderInfo", json);
        localStorage.setItem("sub_total", $scope.cart.sub_total);
        localStorage.setItem("amountdiscount", $scope.cart.amountdiscount);
        localStorage.setItem("total", $scope.cart.total);
        localStorage.setItem("shipping", $scope.cart.shipping);
        localStorage.setItem("TotalAll", $scope.cart.TotalAll);
        localStorage.setItem("discount", $scope.cart.discount);
        localStorage.setItem("deliverDays", $scope.codSupport.deliverDays);
        localStorage.setItem("deliverDate", $scope.codSupport.deliverDate);
        console.log("confirmOrderInfo", $scope.confirmOrderInfo);

    }

   
    // lay coupon
    $scope.getCoupon = function () {
        $scope.getCP = angular.copy($scope.couponsform);
        $http.get(`/vgreens/rest/site/coupon/${$scope.getCP.name}`).then(resp => {

            $scope.coupons =resp.data;
            if (Object.keys($scope.coupons).length === 0) {
                let addCoupon = {
                    title: "Thất bại",
                    message: "Mã giảm giá không tồn tại",
                    status: TOAST_STATUS.DANGER,
                    timeout: 2000
                }
                Toast.create(addCoupon);
                
               
            } else {
                
                $scope.coupons = resp.data;
                var json = JSON.stringify($scope.coupons);
                localStorage.setItem("Coupon", json);
                let addCoupon = {
                    title: "Thành công",
                    message: "Thêm mã giảm giá thành công",
                    status: TOAST_STATUS.SUCCESS,
                    timeout: 2000
                }
                Toast.create(addCoupon);
                $window.location.reload();
                console.log("Coupon", $scope.coupons);
            }


        }).catch(error => {
            console.log("Error", error);
        })


    }




    // Ad=============
    $scope.header = {};
    $scope.discount = {};
    $scope.getAdvertisements = function(){
        $http.get("/vgreens/rest/advertisement/get/ad-header").then(resp =>{
            $scope.header = resp.data;
            console.log("Header", $scope.header);
        })

        $http.get("/vgreens/rest/advertisement/get/ad-discount").then(resp =>{
            $scope.discount = resp.data;
            console.log("Header", $scope.discount);
            $interval(CountDown, 1000, 0, true, new Date($scope.discount.endDate).getTime());
            
        })
    }
    $scope.getAdvertisements()
    let countDate = new Date('2021-12-20 21:58:10').getTime();
   
})