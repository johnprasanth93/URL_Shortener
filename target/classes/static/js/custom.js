// Angularjs controller code goes here
var demo = angular.module('demo', []);

demo.controller("EmployeeCtrl", [ '$rootScope', '$scope', '$compile',
		'$element', '$filter', '$http','$location',
		function($rootScope, $scope, $compile, $element, $filter, $http,$location) {

			$scope.OnSave = function(srcform) {

				if ($scope.fullURL !== '' && $scope.fullURL !== undefined) {
					srcform.fullURLs = [];
					var srcURL = $location.absUrl();
					var uploadUrl = srcURL+"shortenurl";
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
							url : srcURL+"getURLs"
						}).then(function success(res) {
							console.log(res);
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