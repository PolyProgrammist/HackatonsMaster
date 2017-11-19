"use strict";

;(function (angular) {
  "use strict";

  angular.module("core").component("ghFooter", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('layout/footer');
    },
    controller: function controller() {
      var $ctrl = this;

      Object.assign($ctrl, {});
    }
  });
})(angular);