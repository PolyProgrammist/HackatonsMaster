'use strict';

;(function (angular) {
    'use strict';

    angular.module('core').provider('ResourcesService', function ResourcesServiceProvider() {

        var siteId = 'game';

        this.getSiteId = function () {
            return siteId;
        };

        this.$get = function ResourcesServiceFactory() {

            /**
             * @param template - имя html шаблона
             * @param uniqueTemplates - массив с идентификаторами сайта для которых необходим уникальный template
             * @returns {string}
             */
            var getTemplate = function getTemplate(template) {
                var uniqueTemplates = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : [];


                var pathArray = template.split('/');
                var file = pathArray.slice(-1)[0];
                var path = "js/components/" + pathArray.join('/') + '/';

                if (uniqueTemplates.indexOf(siteId) != -1) {
                    file = file + '-' + siteId;
                }

                return path + file + '.html';
            };

            var getSiteId = function getSiteId() {
                return siteId;
            };

            var setSiteId = function setSiteId(id) {
                siteId = id;
            };

            return {
                getTemplate: getTemplate,
                getSiteId: getSiteId,
                setSiteId: setSiteId
            };
        };
    });
})(angular);