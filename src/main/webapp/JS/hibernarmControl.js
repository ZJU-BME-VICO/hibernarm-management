$(document).ready(function() {
	$('#searchDiv').on('click', '#searchButton', function() {
		$.ajax({
			type: "post",
			url: "/hibernarm-management/hibernarmReconfigure.action",
			dataType: "json",
			processData: false,  // tell jQuery not to process the data
			contentType: false,  // tell jQuery not to set contentType
			success: function() {
				window.location.href="/hibernarm-management/hibernarmControl.action";
			}
		});
	});
});
