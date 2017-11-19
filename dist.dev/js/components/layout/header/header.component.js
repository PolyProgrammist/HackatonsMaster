"use strict";

;(function (angular) {
  "use strict";

  angular.module("core").component("ghHeader", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('layout/header');
    },
    controller: function controller($translate, $state, $window, ApiService, AuthService, $http, LANGUAGES) {
      var $ctrl = this;

      Object.assign($ctrl, {
        $onInit: function $onInit() {
          $ctrl.currentLang = $window.localStorage.lang ? $window.localStorage.lang : null;
          $http.defaults.headers.common['Accept-Language'] = $ctrl.currentLang;
          $ctrl.languages = [];
        },
        switchLang: function switchLang(key) {
          $translate.use(key);
          $window.localStorage.lang = $ctrl.currentLang = key;
          $http.defaults.headers.common['Accept-Language'] = $window.localStorage.lang;
        },

        LANGUAGES: LANGUAGES,
        logout: function logout() {
          AuthService.logout();
        },

        currentLang: null
      });
    }
  });
})(angular);