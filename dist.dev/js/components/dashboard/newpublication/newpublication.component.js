"use strict";

;(function (angular) {
  "use strict";

  angular.module("core.dashboard").component("ghNewpublication", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('dashboard/newpublication');
    },
    controller: function controller(ApiService, $stateParams) {
      var $ctrl = this;

      Object.assign($ctrl, {
        $onInit: function $onInit() {}
      });
    }
  });
})(angular);