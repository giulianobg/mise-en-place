<!DOCTYPE html>
<html lang="pt-BR">
	<head>
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
		
			<div class="row">
			
				<div class="col-sm-12">
					<a href="/events/new" class="btn btn-primary btn-outline" data-toggle="modal" data-target="#modal_newEvent"><i class="fa fa-plus"></i> Novo evento</a>
					<a class="btn btn-default btn-outline"><i class="fa fa-plus"></i> </a>
					
					<hr>
				</div>
			
				<div class="col-sm-12">
				
					<div class="section">
						<h3>Eventos</h3>
						<div class="section-tools pull-right">
						</div>
					</div>
					<div class="list-group">
					<#if params.events??>
						<#list params.events as event>
						<a href="/events/${event.id}" class="list-group-item">
							<span class="badge badge-info">0 pratos </span>
							${event.name?html}
						</a>
						</#list>
					</#if>
					</div>
					
				</div>
				
			</div>
		
		</div>
		
		<div class="modal fade" id="modal_newEvent" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h3 class="modal-title" id="modalLabel">Novo evento</h3>
					</div>
					<div class="modal-body">
						<div class="progress progress-striped progress-thin active">
							<div class="progress-bar" style="width: 100%;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<#include "components/scripts.ftl">
	
	</body>
</html>
