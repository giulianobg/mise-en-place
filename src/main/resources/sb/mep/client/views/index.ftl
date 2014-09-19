<!DOCTYPE html>
<html ng-app="app" lang="pt-BR">
	<head>
		<base href="/">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Mise en place</title>

		<#include "components/head.ftl">

	</head>

	<body>
	
		<#include "components/nav.ftl">
	
		<div class="container">
		
			<div class="col-sm-12 hidden">
				<a href="/#!/event" class="btn btn-primary btn-outline" ><i class="fa fa-plus"></i> Novo evento</a>
				<a href="/#!/blank" class="btn btn-default btn-outline"><i class="fa fa-plus"></i> Blank</a>
				<a href="/#!/dish/kinds/new" class="btn btn-default btn-outline"><i class="fa fa-plus"></i> Tipos de pratos</a>
				<hr>
			</div>
			
			<div class="col-sm-12" ng-view class="view-animate"></div>
		</div>
	
	</body>
	<#include "components/modal.ftl">
	
	<#include "components/scripts.ftl">
</html>
