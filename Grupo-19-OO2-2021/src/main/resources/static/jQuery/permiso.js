$(document).ready(function() {
	var CONTEXT_PATH = $('#contextPathHolder').attr('href');
	var contador=CONTEXT_PATH.split("/");
	var uurle="person";
	var i=0;
	for(i=0;i<contador.length-2;i++){
		uurle="../"+uurle;
	}
	$.get(uurle, function(dataE) {
		var objE = JSON.parse(dataE);
		var datossE = $("#idNombrePersona")[0];
		var perfil = $("#idNombrePerson").val();
		for (a in objE.personas) { 
			datossE.innerHTML += `<option value='${objE.personas[a].idPersona}'>${objE.personas[a].nombrePersona}</option>`;
		}
	});
});