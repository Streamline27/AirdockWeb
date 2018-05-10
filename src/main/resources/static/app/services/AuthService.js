
app.factory('AuthService', AuthService);

function AuthService($http,  $window, jwtHelper) {
    AuthService.$inject = ['$http'];

    var service = {};

    service.Login = Login;
    service.logout = logout;
    service.isLoggedIn = isLoggedIn;
    service.getToken = getToken;

    return service;

    function Login(username, password, onSuccess, onError) {

        var successCallback = function (response) {

            var token = response.headers("Authorization");

            if (token) {
                $http.defaults.headers.common['Authorization'] = token; // headers for every
                saveToken(token);
                onSuccess && onSuccess()
            }
            else {
                logout();
                onError && onError();
            }
        };

        var errorCallback = function (data) {
            logout();
            onError && onError();
        };

        $http({
            url : '/login',
            method : 'POST',
            skipAuthorization : true,
            data: { login : username, password : password }
        }).then(successCallback, errorCallback);
    }

    function logout(onSuccess) {
        $window.sessionStorage.removeItem('id_token');
        onSuccess && onSuccess();
    }

    function saveToken(token) {
        $window.sessionStorage.setItem('id_token', token);
    }

    function isLoggedIn(){
        var token = getToken();
        return token && !jwtHelper.isTokenExpired(token);
    }

    function getToken() {
        var token = $window.sessionStorage.getItem('id_token');
        if (token && jwtHelper.isTokenExpired(token)) {
            logout();
            return null;
        }
        else {
            return token;
        }
    }
}