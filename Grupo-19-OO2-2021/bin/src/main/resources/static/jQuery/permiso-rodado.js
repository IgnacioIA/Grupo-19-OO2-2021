$(document).ready(function() {
	var CONTEXT_PATH = $('#contextPathHolder').attr('href');
	var contador=CONTEXT_PATH.split("/");
	var uurle="rodad";
	var i=0;
	for(i=0;i<contador.length-2;i++){
		uurle="../"+uurle;
	}
	$.get(uurle, function(dataE) {
		var objE = JSON.parse(dataE);
		var datossE = $("#idRodado")[0];
		var perfil = $("#idRodad").val();
		for (a in objE.rodados) { 
			datossE.innerHTML += `<option value='${objE.rodados[a].idRodado}'>${objE.rodados[a].dominio}</option>`;
		}
	});
});