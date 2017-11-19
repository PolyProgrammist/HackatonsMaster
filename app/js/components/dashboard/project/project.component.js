;(function (angular) {
  "use strict";

  angular.module("core.dashboard")
      .component("ghProject", {
        templateUrl: function(ResourcesService) {
          return ResourcesService.getTemplate('dashboard/project');
        },
        controller(ApiService, $stateParams){
          let $ctrl = this;

          Object.assign($ctrl, {
            $onInit(){
              let id = $stateParams.id;
              ApiService.get("http://gh/api/project/" + id).then(function(resp){
                $ctrl.data = resp.data;
              })
              ApiService.get("http://gh/api/content/all/" + id).then(function(resp){
                $ctrl.posts = resp.data;
              })
              // ApiService.get("http://gh/api/users/all/" + id).then(function(resp){
              //   $ctrl.userlist = resp.data;
              // })
              $ctrl.formPost = {};
              ApiService.get("http://gh/api/users/all/" + id).then(function(resp){
                $ctrl.users = resp.data;
              })
              $ctrl.participant = [
                {
                  name:'Sergey',
                  project:'Project 1',
                  lang:'javascript',
                  level:5,
                  avatar:'avatar.jpg'
                },
                {
                  name:'Sergey 2',
                  project:'Project 2',
                  lang:'C#',
                  level:5,
                  avatar:'avatar.jpg'
                },
                {
                  name:'Sergey 3',
                  project:'Project 3',
                  lang:'java',
                  level:5,
                  avatar:'avatar.jpg'
                },
                {
                  name:'Sergey 4',
                  project:'Project 4',
                  lang:'C#',
                  level:5,
                  avatar:'avatar.jpg'
                }
              ]
            },
            addPost(){
              var currentdate = new Date();
                $ctrl.posts.push({
                  git : "",
                    id : $ctrl.posts.length + 1 ,
                    photo : "",
                    project_id : 1,
                    text : $ctrl.formPost.comment,
                    time : currentdate.getFullYear() + "-" + (currentdate.getMonth()+1) + "-" +currentdate.getDate()
                    + " " + currentdate.getHours() + ":"
                    + currentdate.getMinutes() + ":"
                    + currentdate.getSeconds(),
                    video : ""
                });
            }
          })
        }
      })
})(angular);
