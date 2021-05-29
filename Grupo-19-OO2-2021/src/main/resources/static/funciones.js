function eliminar(idUsuario) {
	console.log(idUsuario);
	swal({
		  title: "Esta seguro de Eliminar?",
		  text: "Una vez eliminado no se prodra restablecer!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/usuario/eliminar/"+idUsuario,
				 success: function(res) {
					console.log(res);
				},
			  });
			swal("LISTO! Registro eliminado!", {
			  icon: "success",
			}).then((ok)=>{
				if(ok){
					location.href="/usuario/list";
				}
			});
		  }
		});
}
