$(document).ready(function() {
	var CONTEXT_PATH = $('#contextPathHolder').attr('href');
	var contador=CONTEXT_PATH.split("/");
	var uurle="lug";
	var i=0;
	for(i=0;i<contador.length-2;i++){
		uurle="../"+uurle;
	}
	$.get(uurle, function(dataE) {
		var objE = JSON.parse(dataE);
		var datossE = $("#idLugar")[0];
		var datossL = $("#idLugar1")[0];
		var perfil = $("#idLug").val();
		for (a in objE.lugares) { 
			datossE.innerHTML += `<option value='${objE.lugares[a].idLugar}'>${objE.lugares[a].nombreLugar}</option>`;
			datossL.innerHTML += `<option value='${objE.lugares[a].idLugar}'>${objE.lugares[a].nombreLugar}</option>`;
		
		}
	});
});