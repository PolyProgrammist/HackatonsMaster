'use strict';

;(function (angular) {
  'use strict';

  angular.module('core').factory('AuthService', function (ApiService, $window, $http, $q, $state) {
    var CurrentUser = null;
    var authentication = false;

    var setHeaderToken = function setHeaderToken(token) {
      if (token) {
        $http.defaults.headers.common['Access-Token'] = token;
      }
    };

    var saveToken = function saveToken(token) {
      $window.localStorage['token'] = token;
    };

    var getToken = function getToken() {
      return $window.localStorage['token'];
    };

    var removeToken = function removeToken() {
      $window.localStorage.removeItem('token');
    };

    var isAuthentication = function isAuthentication() {
      return authentication;
    };

    var clearSession = function clearSession() {
      authentication = false;
      CurrentUser = null;
      removeToken();
      setHeaderToken(null);
      window.localStorage.clear();
      $state.go('app.non_auth.login');
    };

    var login = function login(form, errorCallback) {
      return ApiService.auth.login({
        username: form.login,
        password: form.password
      }).then(function (resp) {
        authentication = true;
        if (resp.data && resp.data.accessToken) {
          saveToken(resp.data.accessToken);
          setHeaderToken(resp.data.accessToken);
          window.localStorage.setItem('user', JSON.stringify({
            avatar: '/images/avatar.png',
            user: form
          }));
        }
        $state.go('app.auth.overview');
        return resp;
      }, errorCallback);
    };

    var logout = function logout() {
      return ApiService.auth.logout().then(function () {
        clearSession();
      }, function (err) {
        console.error('error logout', err);
        return err;
      });
    };

    var checkUser = function checkUser() {
      var token = getToken();
      var promise = $q.when({});
      authentication = token ? true : false;
      if (CurrentUser) {
        promise = $q.when(CurrentUser);
      } else {
        if (token) {
          setHeaderToken(token);
          promise = ApiService.auth.currentUser().then(function (data) {
            CurrentUser = data;
            return data;
          }, function () {
            clearSession();
            $state.go('app.non_auth.login');
          });
        }
      }

      return promise;
    };

    var getUser = function getUser() {
      return ApiService.auth.currentUser();
    };

    return {
      isAuthentication: isAuthentication,
      clearSession: clearSession,
      setHeaderToken: setHeaderToken,
      login: login,
      logout: logout,
      getUser: getUser,
      setAvatar: function setAvatar(avatar) {
        CurrentUser.avatar = avatar;
        console.log(CurrentUser);
      },

      checkUser: checkUser
    };
  });
})(angular);