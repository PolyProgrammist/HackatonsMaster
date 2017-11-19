"use strict";

;(function (angular) {
  "use strict";

  angular.module("core.dashboard").component("ghEvent", {
    templateUrl: function templateUrl(ResourcesService) {
      return ResourcesService.getTemplate('dashboard/event');
    },
    controller: function controller(ApiService, $stateParams) {
      var $ctrl = this;

      Object.assign($ctrl, {
        $onInit: function $onInit() {
          var id = $stateParams.id;
          ApiService.get("http://gh/api/hackathons/" + id).then(function (resp) {
            $ctrl.data = resp.data;
          });
          ApiService.get("http://gh/api/projects/" + id).then(function (resp) {
            $ctrl.projectsList = resp.data;
          });
          // ApiService.get("http://gh/api/orgs/all/" + id).then(function(resp){
          //   $ctrl.orgsList = resp.data;
          // })
          $ctrl.participant = [{
            name: 'Sergey',
            project: 'Project 1',
            lang: 'javascript',
            level: 5,
            avatar: 'avatar.jpg'
          }, {
            name: 'Sergey 2',
            project: 'Project 2',
            lang: 'C#',
            level: 5,
            avatar: 'avatar.jpg'
          }, {
            name: 'Sergey 3',
            project: 'Project 3',
            lang: 'java',
            level: 5,
            avatar: 'avatar.jpg'
          }, {
            name: 'Sergey 4',
            project: 'Project 4',
            lang: 'C#',
            level: 5,
            avatar: 'avatar.jpg'
          }];
        }
      });
    }
  });
})(angular);