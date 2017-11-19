"use strict";

;(function (angular) {
  "use strict";

  angular.module("core").component("ghLayoutNonAuth", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('layout/layout-non-auth');
    },
    controller: function controller() {
      var $ctrl = this;

      Object.assign($ctrl, {});
    }
  });
})(angular);