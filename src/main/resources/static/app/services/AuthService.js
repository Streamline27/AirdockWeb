
app.factory('AuthService', AuthService);

function AuthService($http, $cookieStore, $rootScope) {
    AuthService.$inject = ['$http', '$cookieStore', '$rootScope'];
    $rootScope.userCredentials = {};
    var service = {};

    service.GetUserCredentials = GetUserCredentials;
    service.Login = Login;
    service.Logout = logout;
    service.isLoggedIn = isLoggedIn;
    service.OnAuthenticationStatusChanged = OnAuthenticationStatusChanged;
    service.loginFromCookies = loginFromCookies;
    service.ClearCookies = ClearCookies;

    return service;

    function Login(username, password, onSuccess, onError) {

        var successCallback = function (data) {
            user = {};
            user.username= username;
            user.password = password;
            user.firstName = data.firstName;
            user.lastName = data.lastName;

            $rootScope.userCredentials = user;

            $cookieStore.put('userCredentials', $rootScope.userCredentials); // save credentials in cookies
            $http.defaults.headers.common['Authorization'] = "Basic "+ btoa(username + ":" + password); // headers for every

            onSuccess && onSuccess()
        };

        var errorCallback = function (data) {
            if (isLoggedIn()) ClearCookies();
            onError && onError();
        };

        $http({
            method: 'POST',
            url: 'api/users/login',
            headers: { authorization : "Basic "+ btoa(username + ":" + password) }

        }).then(successCallback, errorCallback);

    }

    function logout() {

        ClearCookies();

        $http({
            method: 'POST',
            url: 'api/user/logout'
        }).then(function(){}, function(){});
    }

    function ClearCookies(){
        $rootScope.userCredentials = undefined; // Clear globals
        $cookieStore.remove('userCredentials'); // Clear cookies
        delete $http.defaults.headers.common['Authorization']; //Clear headers
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

    /**
     * @return {boolean}
     */
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