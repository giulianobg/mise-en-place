//App
var app = angular.module('app', ['ngRoute', 'ngResource']);

app.constant('baseHref', '/');

app.config(['$routeProvider', '$locationProvider', '$anchorScrollProvider', function($routeProvider, $locationProvider, $anchorScrollProvider) {
	$routeProvider.
	when('/', {templateUrl: '/assets/pages/partials/welcome.html'}).
	
	// Events
	when('/event', {
		templateUrl: '/assets/pages/partials/event.html'
	}).
	when('/event/new', {
		templateUrl: '/assets/pages/partials/event-new.html'
	}).
	when('/event/:id', {
		templateUrl: '/assets/pages/partials/event-edit.html',
		controller: 'EventGetCtrl'
	}).
	
	
	// Dishes
	when('/event/:id/dish/new', {
		templateUrl: '/assets/pages/partials/dish-new.html'
	}).
	when('/event/:id/dish/:idDish', {
		templateUrl: '/assets/pages/partials/dish-edit.html'
	}).
	
	// Preparations
	when('/event/:id/dish/:idDish/prep/new', {
		templateUrl: '/assets/pages/partials/preparation-new.html'
	}).
	when('/event/:id/dish/:idDish/prep/:idPrep/edit', {
		templateUrl: '/assets/pages/partials/preparation-edit.html',
		controller: 'PreparationGetCtrl'
	}).
	
	
	// Dish kinds
	when('/dish/kinds', {
		templateUrl: '/assets/pages/partials/dish-kinds.html'
	}).
	when('/dish/kinds/:id/edit', {
		templateUrl: '/assets/pages/partials/dish-kinds-edit.html',
		controller: 'DishKindGetCtrl'
	}).
	when('/dish/kinds/new', {
		templateUrl: '/assets/pages/partials/dish-kinds-new.html'
	}).
	
	// Intervals
	when('/intervals', {
		templateUrl: '/assets/pages/partials/intervals.html'
	}).
	when('/intervals/:minute/edit', {
		templateUrl: '/assets/pages/partials/intervals-edit.html',
		controller: 'IntervalGetCtrl'
	}).
	when('/intervals/new', {
		templateUrl: '/assets/pages/partials/intervals-new.html'
	}).
	
	otherwise({
		redirectTo: '/'
	});
	
	//$locationProvider.html5Mode(true);
	$locationProvider.hashPrefix('!');
	
	$anchorScrollProvider.disableAutoScrolling();
}]);

// Controllers
app.controller('EventCtrl', function ($scope, $http, $route, Event, $window, $location) {
	Event.query(function(data) {$scope.events = data.result;}); // works fine
	
	$scope.save = function() { // works fine
		Event.save($scope.data, function(data) {
			$scope.event = data.result;
			$scope.data = {};
			//$scope.$emit('reload', {});
			$location.path('/event')
		});
	}
	
	$scope.update = function(data) {
		Event.update({id: event.id}, $scope.data);
	}
	
	$scope.remove = function(event) { // works fine
		Event.remove({id: event.id}, function() {
			$location.path('/event')
		});
	}
});

app.controller('EventGetCtrl', function ($scope, $http, $route, Event) { // works fine
	Event.get({id: $route.current.params.id}, function(data) {
		$scope.event = data.result;
	});
});

app.controller('DishKindCtrl', function ($scope, $http, $route, DishKind, $window, $location) {
	
	DishKind.query(function(data) {$scope.kinds = data.result;});
	
	$scope.save = function() { // works fine
		DishKind.save($scope.kind, function(data) {
			$scope.kind = {};
			$location.path('/dish/kinds')
		});
	}
	
	$scope.update = function() { // works fine
		DishKind.update({id: $scope.kind.id}, $scope.kind);
		$location.path('/dish/kinds');
	}
	
	$scope.remove = function(kind) { // works fine
		DishKind.remove({id: kind.id}, function() {
		});
	}
});

app.controller('DishKindGetCtrl', function ($scope, $http, $route, DishKind) {
	DishKind.get({id: $route.current.params.id}, function(data) {
		$scope.kind = data.result;
	});
});

app.controller('DishCtrl', function ($scope, $http, $route, Dish, $window, $location) {
	
	$scope.event = {id: $route.current.params.id}
	
	$scope.save = function() {
		$scope.dish.event = {};
		$scope.dish.event.id = $route.current.params.id;
		Dish.save($scope.dish, function(data) {
			var path = 'event/' + $route.current.params.id;
			$scope.dish = {};
			$location.path(path);
		});
	}
	
	$scope.update = function(data) {
		Dish.update({id: event.id}, $scope.data);
	}
	
	$scope.remove = function(event) {
		Dish.remove({id: event.id}, function() {
		});
	}
});

app.controller('DishGetCtrl', function ($scope, $http, $route, Event, $filter) {
	Event.get({id: $route.current.params.id}, function(data) {
		$scope.event = data.result;
		$scope.dish = $filter('filter')($scope.event.dishes, {id: $route.current.params.idDish})[0];
	});
});

app.controller('IntervalCtrl', function ($scope, $http, $route, Interval, $window, $location) {
	
	Interval.query(function(data) {$scope.intervals = data.result;});
	
	$scope.save = function() {
		Interval.save($scope.interval, function(data) {
			$scope.interval = {};
			$location.path('/intervals');
		});
	}
	
	$scope.update = function() {
		Interval.update({minute: $scope.interval.minute}, $scope.interval);
		$location.path('/intervals');
	}
	
	$scope.remove = function(interval) {
		Interval.remove({minute: interval.minute}, function() {
		});
	}
});

app.controller('IntervalGetCtrl', function ($scope, $http, $route, Interval) {
	console.log($route.current.params);
	Interval.get({minute: $route.current.params.minute}, function(data) {
		$scope.interval = data.result;
	});
});

app.controller('PreparationCtrl', function ($scope, $http, $route, Prep, Interval, $window, $location) {
	
	$scope.event = {id: $route.current.params.id, dish:{id:$route.current.params.idDish}}
	Interval.query(function(data) {$scope.intervals = data.result;});
	
	$scope.save = function() {
		$scope.prep.dish = {};//id:$scope.event.dish.id};
		$scope.prep.dish.id = $scope.event.dish.id;
		Prep.save($scope.prep, function(data) {
			$scope.prep = {};
			$location.path('/event/'+$scope.event.id+'/dish/'+$scope.event.dish.id);
		});
	}
	
	$scope.update = function() {
		$scope.prep.dish = {};//id:$scope.event.dish.id};
		$scope.prep.dish.id = $scope.event.dish.id;
		Prep.update({id: $scope.prep.id}, $scope.prep);
		$location.path('/event/'+$scope.event.id+'/dish/'+$scope.event.dish.id);
	}
});

app.controller('PreparationGetCtrl', function ($scope, $http, $route, Prep) {
	Prep.get({id: $route.current.params.idPrep}, function(data) {
		$scope.prep = data.result;
	});
});

// Services
app.factory('Event', ['$resource', function($resource) {
	return $resource('/api/event/:id', { id: '@_id' }, {
		query: {method:'GET',isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.factory('Dish', ['$resource', function($resource) {
	return $resource('/api/dish/:id', { id: '@_id' }, {
		query: {method:'GET', isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.factory('DishKind', ['$resource', function($resource) {
	return $resource('/api/dish-kind/:id', { id: '@_id' }, {
		query: {method:'GET', isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.factory('Prep', ['$resource', function($resource) {
	return $resource('/api/preparation/:id', { id: '@_id' }, {
		query: {method:'GET', isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.factory('Interval', ['$resource', function($resource) {
	return $resource('/api/interval/:minute', { id: '@_minute' }, {
		query: {method:'GET', isArray:false},
		update: {method: 'PUT'}
	});
}]);

app.run(function($rootElement) {
	$rootElement.on('click', function(e) {
		e.stopPropagation();
	});
});
