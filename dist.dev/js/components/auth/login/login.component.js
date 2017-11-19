"use strict";

;(function (angular) {
    "use strict";

    angular.module("core.auth").component("ghLogin", {
        templateUrl: function templateUrl(ResourcesService) {
            return ResourcesService.getTemplate('auth/login');
        },
        controller: function controller(AuthService) {
            var $ctrl = this;

            Object.assign($ctrl, {
                $onInit: function $onInit() {},

                login: function login() {
                    AuthService.login($ctrl.form);
                }
            });
        }
    });
})(angular);