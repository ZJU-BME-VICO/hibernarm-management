<%@page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/searchArchetype.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap-theme.min.css">
<script src="/hibernarm-management/JS/jquery-2.0.3.min.js"></script>
<script src="/hibernarm-management/JS/bootstrap.min.js"></script>
<script src="/hibernarm-management/JS/hibernarmControl.js"></script>
</head>
<body>
	<div class="container" id="wholeContainer">
		<div class="row" id="searchTopDiv">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2"
				id="searchDiv">
				<div class="input-group">
					<button class="btn btn-primary" type="button" id="searchButton">Restart
					</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2">
				<div class="panel-group" id="accordion">
					<s:iterator value="archetypeIdList" id="singleId">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-toggle="collapse"
										data-parent="#accordion" href="#<s:property />">
										<s:property /></a>
								</h4>
								<a href="/hibernarm-management/displayContent.action?keyName=<s:property />">浏览</a>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
	</div>
</body>
</html>