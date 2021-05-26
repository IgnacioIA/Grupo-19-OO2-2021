$(document).ready(function() {
	$("#backLink").click(function(event) {
		event.preventDefault();
		history.back(1);
	});
	$("#backLink2").click(function(event) {
		event.preventDefault();
		history.go(-2)
	});
});