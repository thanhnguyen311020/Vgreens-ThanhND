app.controller("confirm-info-order", function ($scope, $http){

    $scope.confirmOrderInfoSession ={};
        $scope.deliverDays = localStorage.getItem("deliverDays");
        $scope.deliverDate = localStorage.getItem("deliverDate");
        $scope.total = localStorage.getItem("total");
        $scope.shipping = localStorage.getItem("shipping");
        $scope.TotalAll = localStorage.getItem("TotalAll");
        $scope.amountdiscount = localStorage.getItem("amountdiscount");

        var json = localStorage.getItem("Coupon");
        $scope.Coupon= json ? JSON.parse(json) : [];
        // $scope.coupon_percent= $scope.Coupon.coupon_percent;
      

    $scope.ShowcornfirmOrder= function(){
        var json = localStorage.getItem("confirmOrderInfo");
        $scope.confirmOrderInfoSession = json ? JSON.parse(json) : [];
       
    },
    $scope.ShowcornfirmOrder();
    $scope.clearLocalstorage = function(){
        localStorage.setItem("confirmOrderInfo", []);
        localStorage.setItem("sub_total",[]);
        localStorage.setItem("amountdiscount",[]);
        localStorage.setItem("total",[]);
        localStorage.setItem("shipping",[]);
        localStorage.setItem("TotalAll",[]);
        localStorage.setItem("discount",[]);
        localStorage.setItem("deliverDays",[]);
        localStorage.setItem("deliverDate",[]);
        localStorage.setItem("Coupon",[]);
    
    }
    $scope.cart = {

        items: [],

        clear() {
            this.items = [];
            this.saveToLocalStorage();
           
        },
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

    $scope.order = {
        first_name: $scope.confirmOrderInfoSession.first_name ,
        last_name: $scope.confirmOrderInfoSession.last_name ,
        phone_number: $scope.confirmOrderInfoSession.phone_number,
        address_line: $scope.confirmOrderInfoSession.address_line,
        state: $scope.confirmOrderInfoSession.state,
        country: $scope.confirmOrderInfoSession.country.name,
        postal_code: $scope.confirmOrderInfoSession.postal_code,
        description: $scope.confirmOrderInfoSession.description,
        createDate: new Date(),
        account: {

            username: $scope.confirmOrderInfoSession.username,



        },
        
        
           
            coupon_id:  $scope.Coupon.coupon_id,
      
        
        deliver_days: $scope.deliverDays,
        deliver_date: $scope.deliverDate, /// format sang dinh dang ngay
      
        payment_method: "COD",
        sub_total: $scope.total,
        shipping_cost: $scope.shipping,
        amount: $scope.TotalAll,
        discount: $scope.amountdiscount,
        payment_status: "Ch??a Thanh To??n",



        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {
                        id: item.id
                    },
                    unit_price: item.consignment.selling_price,
                    quantity: item.qty
                }
            });
        },

       
       

        purchase() {


            var orders = angular.copy(this);
            // alert(orders.discount);
            $http.post("/vgreens/rest/orders",orders).then(resp =>{
                location.href="/vgreens/order/receipt/"+resp.data.id;
                 $scope.clearLocalstorage()
                 $scope.cart.clear();
                // alert("?????t h??ng th??nh c??ng"); 

            }).catch(error =>{
                alert("?????t h??ng l???i"); 
                console.log(error)
            })

        }
    }

    $scope.usd = [];
    $scope.amountPaypalUSD = 0;
    $scope.getTyGia = function(){
        $http.get('/vgreens/rest/orderAdmin/getUSD').then(resp =>{
            $scope.usd = angular.fromJson(resp.data);
            for(let i = 0; i < $scope.usd.items.length; i++){
                if($scope.usd.items[i].type === 'USD'){
                    $scope.amountPaypalUSD = ($scope.TotalAll / $scope.usd.items[i].bantienmat).toFixed(2)
                    console.log("USD Price",($scope.TotalAll / $scope.usd.items[i].bantienmat).toFixed(2) )
                }
            }
            
        })
    }
    $scope.getTyGia();
 
  

    // var amountToVnd = $scope.TotalAll / 22500;
    // amountTotal = amountToVnd.toFixed(2);
    $scope.paypal = {
        createOrder: function (data, actions) {
            return actions.order.create({
                intent: 'CAPTURE',

                payer: {
                    name: {
                        given_name: $scope.confirmOrderInfoSession.first_name,
                        surname: $scope.confirmOrderInfoSession.last_name,
                    },
                    address: {
                        address_line_1: $scope.confirmOrderInfoSession.address_line,
                        address_line_2: $scope.confirmOrderInfoSession.address_line,
                        admin_area_2: $scope.confirmOrderInfoSession.state,
                        admin_area_1: $scope.confirmOrderInfoSession.state,
                        postol_code: $scope.confirmOrderInfoSession.postal_code,
                        country_code: "US"


                    },
                    email_address: $scope.confirmOrderInfoSession.email,
                    phone: {
                        phone_type: "MOBILE",
                        phone_number: {
                            national_number: $scope.confirmOrderInfoSession.phone_number,
                        }
                    }
                },

                purchase_units: [{

                    amount: {
                        value:  $scope.amountPaypalUSD,
                        currency_code: "USD"
                    }
                }]
            });
        },

        onApprove: function (data, actions) {
            
            return actions.order.capture().then(function(details){
                $scope.orderpaypal = {
                    first_name: $scope.confirmOrderInfoSession.first_name ,
                    last_name: $scope.confirmOrderInfoSession.last_name ,
                    phone_number: $scope.confirmOrderInfoSession.phone_number,
                    address_line: $scope.confirmOrderInfoSession.address_line,
                    state: $scope.confirmOrderInfoSession.state,
                    country: $scope.confirmOrderInfoSession.country.name,
                    postal_code: $scope.confirmOrderInfoSession.postal_code,
                    description: $scope.confirmOrderInfoSession.description,
                    createDate: new Date(),
                    account: {
            
                        username: $scope.confirmOrderInfoSession.username,
            
            
            
                    },
                  
                    coupon_id:  $scope.Coupon.coupon_id,
                  
                    deliver_days: $scope.deliverDays,
                    deliver_date: $scope.deliverDate, /// format sang dinh dang ngay
                  
                    payment_method: "PAYPAL",
                    sub_total: $scope.total,
                    shipping_cost: $scope.shipping,
                    amount: $scope.TotalAll,
                    discount: $scope.amountdiscount,
                    payment_Id: details.purchase_units[0].payments.captures[0].id,
                    payment_status: "???? Thanh To??n",
            
            
            
                    get orderDetails() {
                        return $scope.cart.items.map(item => {
                            return {
                                product: {
                                    id: item.id
                                },
                                unit_price: item.consignment.selling_price,
                                quantity: item.qty
                            }
                        });
                    },
            
                   
                   
            
                    purchase() {
            
            
                        var orders = angular.copy(this);
                        // alert(orders.discount);
                        $http.post("/vgreens/rest/orders",orders).then(resp =>{
                            location.href="/vgreens/order/receipt/"+resp.data.id;
                             $scope.clearLocalstorage()
                             $scope.cart.clear();
                            // alert("?????t h??ng th??nh c??ng"); 
            
                        }).catch(error =>{
                            alert("?????t h??ng l???i"); 
                            console.log(error)
                        })
            
                    }
                }

                console.log(details);
                // orderId = details.id;
                // totalAmount = details.purchase_units[0].amount.value;
                // captureId =details.purchase_units[0].payments.captures[0].id;
                ///g???i thanh to??n t??? paypal
                $scope.orderpaypal.purchase();
                // alert("C???m ??n b???n ???? thanh to??n. OrderID: " + orderId+ " AMount: "+totalAmount+ " CaptureID: "+captureId);
                
               
             });
        },
        onCancel: function(data){
            let cancel = {
                title: "Th??nh c??ng",
                message: "Thanh to??n ???? h???y",
                status: TOAST_STATUS.DANGER,
                timeout: 3000
            }
            Toast.create(cancel);
        }, 
        onError: function(error){
            let notierror = {
                title: "L???i",
                message: "C?? l???i x???y ra trong qu?? tr??nh thanh to??n",
                status: TOAST_STATUS.DANGER,
                timeout: 3000
            }
            Toast.create(notierror);
        }
    };
});

