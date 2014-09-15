//App
var app = angular.module('app', ['ngRoute', 'ngResource']);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {templateUrl: '/assets/pages/partials/welcome.html'}).
	when('/event', {
		templateUrl: '/assets/pages/partials/event.html'
	}).
	when('/event/:id', {
		templateUrl: '/assets/pages/partials/event-edit.html',
		controller: 'EventGetCtrl'
	}).
	when('/event/:id/dish/:id_dish', {
		templateUrl: '/assets/pages/partials/preparations.html'
	}).
	when('/event/:id/dish/:id_dish/preps/:idPrep/edit', {
		templateUrl: '/assets/pages/partials/preparation-edit.html'
	}).
	otherwise({
		redirectTo: '/'
	});
}]);

app.run(function($rootScope, $location, $anchorScroll, $routeParams) {
	//when the route is changed scroll to the proper element.
	$rootScope.$on('$routeChangeSuccess', function(newRoute, oldRoute) {
		$location.hash($routeParams.scrollTo);
		$anchorScroll();  
	});
});

// Controllers
app.controller('EventCtrl', function ($scope, $http, $route, EventResource, $window) {
	//console.log(EventResource.query());
	
	EventResource.query(function(data) {$scope.events = data.result;});
	$scope.$on('reload', function (event, args) {
		$route.reload();
		$scope.$emit('$routeChangeSuccess');
	});
	
	$scope.save = function() {
		EventResource.save($scope.data, function(data) {
			$scope.event = data.result;
			//$scope.events.push({id: $scope.event.id, name: data.name});
			$scope.data = {};
			$scope.$emit('reload', {});
		});
	}
	
	$scope.update = function(data) {
		EventResource.update({id: event.id}, $scope.data);
	}
	
	$scope.remove = function(event) {
		EventResource.remove({id: event.id}, function() {
			$scope.$emit('reload', {});
		})
	}
});

app.controller('EventGetCtrl', function ($scope, $http, $route, EventResource) {
	EventResource.get({id: $route.id}, function(data) {
		$scope.event = data.result;
		console.log($scope)
	});
});

app.controller('DishCtrl', function ($scope, $http, $route, DishResource, $window) {
	//console.log(EventResource.query());
	
	DishResource.query(function(data) {$scope.events = data.result;});
	$scope.$on('reload', function (event, args) {
		$route.reload();
	});
	
	$scope.save = function() {
		DishResource.save($scope.data, function(data) {
			$scope.event = data.result;
			$scope.data = {};
			$scope.$emit('reload', {});
		});
	}
	
	$scope.update = function(data) {
		DishResource.update({id: event.id}, $scope.data);
	}
	
	$scope.remove = function(event) {
		DishResource.remove({id: event.id}, function() {
			$scope.$emit('reload', {});
		})
	}
});

app.controller('DishGetCtrl', function ($scope, $http, $route, DishResource) {
	DishResource.get({id: $route.id}, function(data) {
		$scope.event = data.result;
		console.log($scope)
	});
});

// Services
app.factory('EventResource', ['$resource', function($resource) {
	return $resource('/api/event/:id', {}, {
		query: {method:'GET',isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.factory('DishResource', ['$resource', function($resource) {
	return $resource('/api/dish/:id', {}, {
		query: {method:'GET', isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.factory('PrepsResource', ['$resource', function($resource) {
	return $resource('/api/preparation/:id', {}, {
		query: {method:'GET', isArray:false},
		update: {method: 'PUT'}
	});
}]);
