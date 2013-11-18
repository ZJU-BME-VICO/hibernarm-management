$(document).ready(function() {
	var strArchetype = $("#archetypeDiv").html();
	var strArchetypeReplaced = strArchetype.trim().replace(/\n/g, "<br/>").replace(/\s/g, "&nbsp;");
	$("#archetypeDiv").html(strArchetypeReplaced);
	
	var strArm = $("#armDiv").html();
	var strArmReplaced = strArm.trim().replace(/\n/g, "<br/>").replace(/\s/g, "&nbsp;");
	$("#armDiv").html(strArmReplaced);
});