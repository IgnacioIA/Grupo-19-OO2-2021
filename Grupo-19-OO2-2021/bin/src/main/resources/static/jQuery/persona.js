$(document).ready(function() {
	var CONTEXT_PATH = $('#contextPathHolder').attr('href');
	var contador=CONTEXT_PATH.split("/");
	var uurle="usuar";
	var i=0;
	for(i=0;i<contador.length-2;i++){
		uurle="../"+uurle;
	}
	$.get(uurle, function(dataE) {
		var objE = JSON.parse(dataE);
		var datossE = $("#idNombreUsuario")[0];
		var perfil = $("#idNombreUsuar").val();
		for (a in objE.usuarios) { 
			datossE.innerHTML += `<option value='${objE.usuarios[a].nombreUsuario}'>${objE.usuarios[a].nombreUsuario}</option>`;
		}
	});
});