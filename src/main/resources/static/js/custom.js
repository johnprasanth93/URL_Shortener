// Angularjs controller code goes here
var demo = angular.module('demo', []);

demo.controller("EmployeeCtrl", [
		'$rootScope',
		'$scope',
		'$compile',
		'$element',
		'$filter',
		'$http',
		'$location',
		'$interval',
		function($rootScope, $scope, $compile, $element, $filter, $http,
				$location, $interval) {
			var srcURL = $location.absUrl();
			// To get the number of hits. It will check every sec
			$interval(function getHitCount() {
				$http({
					method : 'GET',
					url : srcURL + "getSiteHitCount"
				}).then(function success(res) {
					$scope.hitCount = res.data;
				}, function failure() {
					console.log("Failed");
					alert(resperr.message);
				});
			}, 10000);
			$scope.OnSave = function(srcform) {

				if ($scope.fullURL !== '' && $scope.fullURL !== undefined) {
					srcform.fullURLs = [];
					var uploadUrl = srcURL + "shortenurl";
					srcform.fullURLs.push({
						full_url : $scope.fullURL
					});

					var jsonInput = JSON.stringify(srcform.fullURLs[0]);

					$http.post(uploadUrl, jsonInput, {
						transformRequest : angular.identity,
						headers : {
							'Content-Type' : 'application/json'
						}
					}).success(function(result) {
						console.log("Success");
						$scope.short_url = result.short_url;
						alert("Short URL has been created" + result.short_url);
						$http({
							method : 'GET',
							url : srcURL + "getURLs"
						}).then(function success(res) {
							$scope.allURLS = res.data;
						}, function failure() {
							console.log("Failed");
							alert(resperr.message);
						});
					}).error(function(response) {
						console.log("Failed");
						alert(response.message);
					});
				} else {
					alert("Please add a complete URL");
				}
			}

		} ]);