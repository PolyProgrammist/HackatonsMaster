'use strict';

;(function (angular) {
  'use strict';

  angular.module('core').factory('ApiService', function ($http, $httpParamSerializer, $window, ResourcesService, $q) {

    var ROOT_URL = '';
    var BASE_URL = '';
    var VERSION_URL = '';

    var PATHS = {};

    var getApiUrl = function getApiUrl() {
      return ROOT_URL + BASE_URL + VERSION_URL;
    };

    // let getLanguageParam = function () {
    //   let lang = $window.localStorage.lang || 'en';
    //   return '?lang=' + lang;
    // };

    var _request = function _request(options) {
      return $http(options).then(function (resp) {
        return resp;
      }, function (err) {
        console.log(err);
      });
    };

    var _get = function _get(url) {
      return _request({
        method: "GET",
        url: url
      });
    };

    var _post = function _post(url, data) {
      return _request({
        method: "POST",
        url: url,
        data: data || {}
      });
    };

    var _put = function _put(url, data) {
      return _request({
        method: "PUT",
        url: url,
        data: data || {}
      });
    };

    var _delete = function _delete(url) {
      return _request({
        method: "DELETE",
        url: url
      });
    };

    return {
      get: _get,
      post: _post,
      put: _put,
      delete: _delete,

      auth: {
        login: function login() {
          return $q.when({}).then(function () {
            return {
              data: {
                accessToken: "12312312312"
              }
            };
          });
        },
        currentUser: function currentUser() {
          return $q.when({}).then(function () {
            return JSON.parse(window.localStorage.getItem('user'));
          });
        },
        logout: function logout() {
          return $q.when({}).then(function () {
            return null;
          });
        }
      }

    };
  });
})(angular);