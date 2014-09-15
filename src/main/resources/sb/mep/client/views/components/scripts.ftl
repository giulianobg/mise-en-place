<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-rc.1/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-rc.1/angular-route.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-rc.1/angular-resource.min.js"></script>
<script src="../assets/js/mep.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		/* clear modals in close */
		$('body').on('hidden.bs.modal', '.modal', function () {
			$(this).removeData('bs.modal');
		});
	});
</script>
