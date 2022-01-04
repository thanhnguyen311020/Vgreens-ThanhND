app.controller('authority-ctrl', function ($scope, $http, $location) {

    $scope.roles = [];
    $scope.authorities = [];
    $scope.admins = [];
    $scope.accounts = [];
    $scope.search = "";
    $scope.form = {};


    $scope.initialize = function () {

        // Load all roles
        $http.get('/vgreens/rest/roles').then(resp => {
            $scope.roles = resp.data;
        })

        // load staffs and directors (administrtor)
        $http.get('/vgreens/rest/account?admin=true').then(resp => {
            $scope.admins = resp.data;
        })
        $http.get('/vgreens/rest/account/list').then(resp => {
            $scope.accounts = resp.data;
        })

        //load authorities of staffs and directors
        $http.get('/vgreens/rest/authorities?admin=true').then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path('/unauthorized');
        })
        // $scope.form.role.id= 'STAF'

    }
    $scope.initialize();

    $scope.authority_of = function (acc, role) {

        if ($scope.authorities) {
            return $scope.authorities.find(ur => ur.account.username == acc.username
                && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function (acc, role) {
        var authority = $scope.authority_of(acc, role);

        if (authority) { //đã cấp quyền => thu hổi quyền
            $scope.revoke_authority(authority);
        } else {
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    }

    // Thêm mới authority
    $scope.grant_authority = function (authority) {
        Swal.fire({
            title: 'Bạn muốn cấp quyền cho tài khoảng này?',
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: `Cấp quyền`,
            denyButtonText: `Không cấp quyền`,
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                $http.post("/vgreens/rest/authorities", authority).then(resp => {
                    $scope.authorities.push(resp.data);
                    Swal.fire('Cấp quyền thành công!', '', 'success')
                }).catch(error => {
                    Swal.fire('Cấp quyền thất bại', '', 'error')
                    console.log("Error: ", error);
                })

            } else if (result.isDenied) {
                Swal.fire('Hủy quá trình cấp quyền', '', 'info')
            }
        })


    }

    // Xoa authority
    $scope.revoke_authority = function (authority) {
        Swal.fire({
            title: 'Bạn muốn hủy vai trò của người dùng?',
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: `Hủy vai trò`,
            denyButtonText: `Hủy thao tác`,
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                $http.delete(`/vgreens/rest/authorities/${authority.id}`).then(resp => {
                    var index = $scope.authorities.findIndex(a => a.id == authority.id);
                    $scope.authorities.splice(index, 1);
                    Swal.fire('Xóa quyền thành công', '', 'success')
                }).catch(error => {
                    console.log("Error", error);
                    Swal.fire('Lỗi!', '', 'error')
                })


            } else if (result.isDenied) {
                Swal.fire('Changes are not saved', '', 'info')
            }
        })


    }

    $scope.addStaff = function () {
        // $scope.form.account.username = $('#username').val()
        var item = angular.copy($scope.form);
        console.log("item",item);
        // item.account.username =  $('#username').val()
        console.log(item)
        $http.post("/vgreens/rest/authorities", item).then(resp => {
            $scope.authorities.push(resp.data);
            $scope.initialize()
            Swal.fire('Thêm mới nhân viên thành công', '', 'success')
        }).catch(error => {
            Swal.fire('Thêm mới thất bại', '', 'error')
            console.log("Error: ", error);
        })
    }

    $scope.pushUsername = function (username) {
       
        $scope.form = {
            account:{
                username: username
            },
            role:{
                id:$scope.form.role.id
            }
        }
        $('#username').val(username)
        // console.log($scope.form)
    }

})