"use strict";

;(function (angular) {
  "use strict";

  angular.module("core").component("ghLayoutAuth", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('layout/layout-auth');
    },
    controller: function controller() {
      var $ctrl = this;

      Object.assign($ctrl, {});
    }
  });
})(angular);