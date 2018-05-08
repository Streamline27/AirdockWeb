
app.factory('AuthService', AuthService);

function AuthService($http, $cookieStore, $rootScope, jwtHelper) {
    AuthService.$inject = ['$http', '$cookieStore', '$rootScope'];
    $rootScope.userCredentials = {};
    var service = {};

    service.GetUserCredentials = GetUserCredentials;
    service.Login = Login;
    service.logout = logout;
    service.isLoggedIn = isLoggedIn;
    service.OnAuthenticationStatusChanged = OnAuthenticationStatusChanged;
    service.loginFromCookies = loginFromCookies;
    service.clearCookies = logout;
    service.getToken = getToken;
    service.tokenIsExpired = tokenIsExpired;

    return service;

    function Login(username, password, onSuccess, onError) {

        var successCallback = function (response) {

            var token = response.headers("Authorization");

            if (token) {
                $http.defaults.headers.common['Authorization'] = token; // headers for every

                var tokenPayload = jwtHelper.decodeToken(token);

                user = {};
                user.username = tokenPayload.username;
                user.role = tokenPayload.role;
                user.token = token;

                $rootScope.userCredentials = user;
                $cookieStore.put('userCredentials', $rootScope.userCredentials); // save credentials in cookies

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

        $http.post('/login', { login : username, password : password })
            .then(successCallback, errorCallback);
    }

    function getToken() {
        if ($rootScope.userCredentials && $rootScope.userCredentials.token) {
            return $rootScope.userCredentials.token
        }
        return null
    }

    function tokenIsExpired() {
        return jwtHelper.isTokenExpired(getToken())
    }

    function logout(onSuccess) {

        $rootScope.userCredentials = undefined; // Clear globals
        $cookieStore.remove('userCredentials'); // Clear cookies
        delete $http.defaults.headers.common['Authorization']; //Clear headers

        onSuccess && onSuccess()
    }

    function GetUserCredentials(){
        return $rootScope.userCredentials;
    }

    function isLoggedIn(){
        if ($rootScope.userCredentials){
            var size = Object.keys($rootScope.userCredentials).length;
            if(size!=0) return true;
        }
        else return false;
    }

    function OnAuthenticationStatusChanged(callback){
        $rootScope.$watch('userCredentials', callback);
    }

    function loginFromCookies(){
        userCredentials = $cookieStore.get('userCredentials');

        if(undefined == userCredentials) return false;

        var hasFields = Object.keys(userCredentials).length !=0;

        if (hasFields) {
            var username = userCredentials.username;
            var password = userCredentials.password;
            Login(username, password);

            return true
        }
        return false
    }

}