;(function (angular) {
  "use strict";

  angular.module("core.dashboard")
      .component("ghOverview", {
        templateUrl: function(ResourcesService) {
          return ResourcesService.getTemplate('dashboard/overview');
        },
        controller(ApiService){
          let $ctrl = this;

          Object.assign($ctrl, {
            $onInit(){
              ApiService.get("http://gh/api/hackathons").then(function(resp){
                $ctrl.data = resp.data;
              })
            }
          })
        }
      })
})(angular);
