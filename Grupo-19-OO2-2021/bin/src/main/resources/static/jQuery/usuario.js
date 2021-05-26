$(document).ready(function() {
	var CONTEXT_PATH = $('#contextPathHolder').attr('href');
	var contador=CONTEXT_PATH.split("/");
	var uurle="perfi";
	var i=0;
	for(i=0;i<contador.length-2;i++){
		uurle="../"+uurle;
	}
	$.get(uurle, function(dataE) {
		var objE = JSON.parse(dataE);
		var datossE = $("#idPerfil")[0];
		var perfil = $("#idPerfi").val();
		for (a in objE.perfiles) { 
			if(objE.perfiles[a].idPerfil == perfil){
					datossE.innerHTML += `<option selected value='${objE.perfiles[a].idPerfil}'>${objE.perfiles[a].nombrePerfil}</option>`;
			
			}else{
			datossE.innerHTML += `<option value='${objE.perfiles[a].idPerfil}'>${objE.perfiles[a].nombrePerfil}</option>`;
		}}
	});
});