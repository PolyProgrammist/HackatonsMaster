;(function (angular) {
  "use strict";

  angular.module("core.dashboard")
      .component("ghNewpublication", {
        templateUrl: function(ResourcesService) {
          return ResourcesService.getTemplate('dashboard/newpublication');
        },
        controller(ApiService, $stateParams){
          let $ctrl = this;

          Object.assign($ctrl, {
            $onInit(){
              
            }
          })
        }
      })
})(angular);
