
app.factory('settings', ['$rootScope', '$http', '$q', function($rootScope, $http, $q) {
    var deferred = $q.defer();

    $http.get('/vgreens/rest/countryAdmin').success(function(data) {
        deferred.resolve(data);
    });

    return deferred.promise;
}]);



app.controller("country-ctrl", function ($scope, $http, $timeout) {
    $('#addState').prop('disabled', true);
    $scope.countries = [];
    $scope.states = [];
    $scope.pageSize = 10;
    $scope.pageSize1 = 10;
    $scope.isShow = false;
    $scope.sort = function (keyname) {
        $scope.sortKey = keyname; // set the sortKey to the param passed
        $scope.reverse = !$scope.reverse // if true make it fasle and vice versa
    }
    // settings.then(function(data){
    //     $scope.settings=data;
    //  })
    $scope.initialize = function () {

        $http.get("/vgreens/rest/countryAdmin").then(resp => {
            $scope.countries = resp.data;
            console.log($scope.countries)
        })

        
    }
    $scope.initialize();

    $scope.selectItem = function (item) {
        $scope.clickedItem = angular.copy(item);
        console.log($scope.clickedItem);
        $scope.message = $scope.clickedItem.name;
    }

    $scope.edit = function (item) {
        //console.log("0k")
        $scope.isShow = true;
        $scope.form = angular.copy(item);

        // $(".nav-tabs a:eq(1)").tab("show");
    }

    $scope.reset = function () {
        $scope.form = {

        }
        $scope.formState = {}
        $scope.isShow = false;
    }

    $scope.create = function () {
        var item = angular.copy($scope.form)
        $http.post("/vgreens/rest/countryAdmin", item).then(resp => {
            $scope.countries.push(resp.data);
            $scope.sweetalert2("Thêm mới thành công", "", "success")
            $scope.reset()
            $scope.initialize();
        }).catch(error => {
            console.log(error)
            $scope.sweetalert2("Thêm mới thất bại", "", "error")
        })

    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/vgreens/rest/countryAdmin/${item.id}`, item).then(resp => {
            var index = $scope.countries.findIndex(a => a.id = item.id);
            $scope.countries[index] = item;
            $scope.sweetalert2("Cập nhập thành công", '', 'success')
            $scope.initialize();
        }).catch(error => {
            console.log(error);
            $scope.sweetalert2("Cập nhập thất bại", '', 'error')
        })
    }

    $scope.delete = function (item) {
        $http.delete(`/vgreens/rest/countryAdmin/${item.id}`).then(resp => {
            var index = $scope.countries.findIndex(p => p.id == item.id);
            $scope.countries.splice(index, 1);
            $scope.sweetalert2("Xóa thành công", '', 'success')
            $scope.reset();
        }).catch(error => {
            console.log("Delete Error: ", error);
            $scope.sweetalert2("Xóa thất bại", '', 'error')
        })
    }

        ;
    $scope.showStatesWhenCheckedRadio = function (item) {

        // console.log($scope.country_id);
        $('#addState').prop('disabled', false);
        $scope.country_name = item.name;
        $scope.formState = {
            country: { id: item.id }
        }
        $http.get(`/vgreens/rest/stateAdmin/${item.id}`).then(resp => {
            $scope.states = resp.data;
            console.log($scope.states)

        })

        var chekbox = $timeout(
            $scope.getChecked = function () {
                console.log(document.getElementsByName("checkState").length);
                return document.getElementsByName("checkState");

            }, 1000);

        console.log("checkbox", chekbox)

    }





    $scope.editState = function (item) {
        //console.log("0k")
        console.log($scope.states)
        $scope.isShow = true;
        $scope.formState = angular.copy(item);
        // console.log("Edit: ", $scope.formState)


        // $(".nav-tabs a:eq(1)").tab("show");
    }

    $scope.createState = function () {

        var item = angular.copy($scope.formState);
        console.log(item)

        $http.post("/vgreens/rest/stateAdmin/", item).then(resp => {
            $scope.states.push(resp.data);
            $scope.reset();
            $scope.sweetalert2("Thêm mới thành công", "", "success")
        }).catch(error => {
            console.log(error)
            $scope.sweetalert2("Thêm mới thất bại", "", "error")
        })

    }

    $scope.updateState = function () {
        var item = angular.copy($scope.formState);
        $http.put(`/vgreens/rest/stateAdmin/${item.id}`, item).then(resp => {

            var index = $scope.states.findIndex(a => a.id = item.id);
            $scope.states[index] = item;

            $scope.sweetalert2("Cập nhập thành công", '', 'success')
            // $scope.showStatesWhenCheckedRadio(item.country.id)
        }).catch(error => {
            console.log(error);
            $scope.sweetalert2("Cập nhập thất bại", '', 'error')
        })


    }

    $scope.deleteState = function (item) {
        $http.delete(`/vgreens/rest/stateAdmin/${item.id}`).then(resp => {
            var index = $scope.states.findIndex(p => p.id == item.id);
            $scope.states.splice(index, 1);
            $scope.sweetalert2("Xóa thành công", '', 'success')
            $scope.reset();
        }).catch(error => {
            $scope.sweetalert2("Xóa thất bại", '', 'error')
            console.log("Delete Error: ", error);

        })
    }

    $scope.selectItemState = function (item) {
        $scope.clickedItemState = angular.copy(item);
        console.log($scope.clickedItemState);
        $scope.message = $scope.clickedItemState.name;
    }

    $scope.newValue = function (value) {
        console.log(value);
    }

    $scope.sweetalert2 = function (title, mess, type) {
        Swal.fire(
            title,
            mess,
            type
        )
    }
});