<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/searchArchetypeBootStrapVersion.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap-theme.min.css">
<script src="/hibernarm-management/JS/jquery-2.0.3.min.js"></script>
<script src="/hibernarm-management/JS/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" id="wholeContainer">
		<div class="row">
			<div class="col-lg-12">
				<ul class="nav nav-tabs">
					<li><a
						href="/hibernarm-management/homeArchetype">Home</a></li>
					<li class="active"><a href="#">SearchDis</a></li>
					<li><a href="#">Messages</a></li>
				</ul>
			</div>
		</div>
		<div class="row" id="searchTopDiv">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2"
				id="searchDiv">
				<div class="input-group">
					<input type="text" class="form-control" id="searchConditionInput">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" id="searchButton">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2">

				<div class="panel-group" id="accordion">
					<s:if test="archetypeBean!=null">
						
					</s:if>
					<s:iterator value="archetypeBeanList" id="singleBean">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-toggle="collapse"
										data-parent="#accordion" href="#<s:property value="name" />"><s:property
											value="name" /> </a>

								</h4>
								<a
									href="/hibernarm-management/displayContent.action?keyName=<s:property value="name"/>">浏览</a>
							</div>
							<div id="<s:property value="name" />"
								class="panel-collapse collapse in">
								<div class="panel-body">Anim pariatur cliche
									reprehenderit, enim eiusmod high life accusamus terry
									richardson ad squid. 3 wolf moon officia aute, non cupidatat
									skateboard dolor brunch. Food truck quinoa nesciunt laborum
									eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on
									it squid single-origin coffee nulla assumenda shoreditch et.
									Nihil anim keffiyeh helvetica, craft beer labore wes anderson
									cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
									vice lomo. Leggings occaecat craft beer farm-to-table, raw
									denim aesthetic synth nesciunt you probably haven't heard of
									them accusamus labore sustainable VHS.</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
	</div>
</body>
</html>