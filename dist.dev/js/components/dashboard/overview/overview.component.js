"use strict";

;(function (angular) {
  "use strict";

  angular.module("core.dashboard").component("ghOverview", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('dashboard/overview');
    },
    controller: function controller(ApiService) {
      var $ctrl = this;

      Object.assign($ctrl, {
        $onInit: function $onInit() {
          ApiService.get("http://gh/api/hackathons").then(function (resp) {
            $ctrl.data = resp.data;
          });
        }
      });
    }
  });
})(angular);