<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>账户管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="datatables is jquery-plugin">

<!-- Le styles -->

<link href="dataTables/bootstrap.min.css"
	rel="stylesheet">
<link
	href="dataTables/bootstrap-responsive.min.css"
	rel="stylesheet">
<link
	href="dataTables/jquery.dataTables.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="all" href="daterangepicker-bs3.css"
		/>
<link rel="stylesheet" href="select2-4.0.0/dist/css/select2.min.css" type="text/css" />
<script type="text/javascript" src="jquery.min.js">
</script>
<script type="text/javascript" src="bootstrap.min.js">
</script>
<script type="text/javascript" src="moment.js">
</script>
	



<script type="text/javascript" async=""
	src="dt/ga.js"></script>
<script type="text/javascript"
	src="dt/site.js"></script>
<script type="text/javascript"
	src="dt/dynamic.php"
	async=""></script>
<script type="text/javascript" language="javascript"
	src="dt/dataTables.responsive.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#find").click(function(){
			$("#search2").submit();
		});
		
		$("#search2").ajaxForm({
			beforeSubmit:function(){
				
				return true;
			},
			success:function(data){
			
				var ss=new Array;
			    var temp=new Array;
			    table = $("#example").dataTable();
			  	oSettings = table.fnSettings();
			    table.fnClearTable(this);
			   	var jsonLength=0;
                for (var i in data) {
                    jsonLength++;
                }
		        for(var j=0;j<jsonLength;j++)
		        {
		       
		        table.oApi._fnAddData(oSettings, data[j]);
		        
		      	}
			     oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
    			table.fnDraw();
			    
			  
				
			}
		});
		$('#example').DataTable({
			"ajax" : '/list'
		});
		$('#new').click(function(){
			 window.location.href="/new"; 
		});
		$("#checkOrCancelAll").click(function(){
			
			$(":checkbox[name='id']").prop("checked",this.checked);
		});
	});
	$("#checkOrCancelAll").click(function(){
			
			$(":checkbox[name='id']").prop("checked",this.checked);
		});
	function controlTHeadCheckbox(){
		
		$("#checkOrCancelAll").prop("checked",$(":checkbox[name='id']").length==$(":checkbox[name='id']:checked").length);
	}
	
	function del(){
		//获取要被删除的账户
		var checkedElts = $(":checkbox[name='id']:checked");
		if(checkedElts.length==0){
			$("#message").text("请选择要删除的账户");
		}else{
			if(confirm("您确定要删除选中的账户吗?")){
				//ids=			111&ids=222&ids=333
				/* 
				var sendData = "";
				$.each(checkedElts,function(i,n){
					sendData +="&ids=" +n.value;
				});
				//sendData = sendData.substr(1);
				sendData = sendData.substring(1);
				alert(sendData);
				*/ 
				//拼接要删除的账户id方式2
				var sendData = "ids=";
				var idArray = [];
				$.each(checkedElts,function(i,n){
					idArray.push(n.value);	
				});
				sendData += idArray.join("&ids=");
				//alert(sendData);
				//发送ajax请求,完成账户的删除
				$.ajax({
					url:"/delete",
					type:"post",
					data:sendData,
					beforeSend:function(){
						$("#message").text("正在删除账户请稍后...");
						return true;
					},
					success:function(jsonObject){
						//{"success":true}成功  {"success":false}失败
						if(jsonObject.success){
							$("#message").text("删除成功");		
							//重新加载页面  displayData(0);
							window.location.reload();
						}else{
							$("#message").text("删除失败");														
						}
					}
				});
			}
		}
	}
	
	function on(){
		//获取要被启封的账户
		var checkedElts = $(":checkbox[name='id']:checked");
		if(checkedElts.length==0){
			alert("请选择要启封的账户！");
		}else{
			if(confirm("您确定要启封选中的账户吗?")){
				//ids=			111&ids=222&ids=333
				/* 
				var sendData = "";
				$.each(checkedElts,function(i,n){
					sendData +="&ids=" +n.value;
				});
				//sendData = sendData.substr(1);
				sendData = sendData.substring(1);
				alert(sendData);
				*/ 
				//拼接要启封的账户id方式2
				var sendData = "ids=";
				var idArray = [];
				$.each(checkedElts,function(i,n){
					idArray.push(n.value);	
				});
				sendData += idArray.join("&ids=");
				//alert(sendData);
				//发送ajax请求,完成账户的启封
				$.ajax({
					url:"/on",
					type:"post",
					data:sendData,
					beforeSend:function(){
						$("#message").text("正在启封账户请稍后...");
						return true;
					},
					success:function(jsonObject){
						//{"success":true}成功  {"success":false}失败
						if(jsonObject.success){
							$("#message").text("启封成功");		
							//重新加载页面  displayData(0);
							window.location.reload();
						}else{
							$("#message").text("启封失败");														
						}
					}
				});
			}
		}
	}
	function off(){
		//获取要被封存的账户
		var checkedElts = $(":checkbox[name='id']:checked");
		if(checkedElts.length==0){
			alert("请选择要封存的账户！");
		}else{
			if(confirm("您确定要封存选中的账户吗?")){
				//ids=			111&ids=222&ids=333
				/* 
				var sendData = "";
				$.each(checkedElts,function(i,n){
					sendData +="&ids=" +n.value;
				});
				//sendData = sendData.substr(1);
				sendData = sendData.substring(1);
				alert(sendData);
				*/ 
				//拼接要封存的账户id方式2
				var sendData = "ids=";
				var idArray = [];
				$.each(checkedElts,function(i,n){
					idArray.push(n.value);	
				});
				sendData += idArray.join("&ids=");
				//alert(sendData);
				//发送ajax请求,完成账户的封存
				$.ajax({
					url:"/off",
					type:"post",
					data:sendData,
					beforeSend:function(){
						$("#message").text("正在封存账户请稍后...");
						return true;
					},
					success:function(jsonObject){
						//{"success":true}成功  {"success":false}失败
						if(jsonObject.success){
							$("#message").text("封存成功");		
							//重新加载页面  displayData(0);
							window.location.reload();
						}else{
							$("#message").text("封存失败");														
						}
					}
				});
			}
		}
	}
	function edit(){
		//获取要被修改的账户
		var checkedElts = $(":checkbox[name='id']:checked");
		if(checkedElts.length==0){
			alert("请选择要修改的账户！");
		}else if(checkedElts.length>1){
			alert("只能选择一个账户进行修改！");
		}else{
			$.each(checkedElts,function(i,n){
					id =n.value;
				});
				
			window.location.href="/edit?id="+id; 	
			
		}
	}
</script>
<script type="text/javascript" src="daterangepicker.js">
		</script>
		
		

</head>
<body>
	<style>
	.hot {
	height: 12px;
	width: 21px;
	position: absolute;
}
</style>

<style type="text/css">
	.table>tbody>tr>td{  
        text-align:center;  
	}  
  
/* dataTables表头居中 */  
	.table>thead:first-child>tr:first-child>th{  
        text-align:center;  
        }  
</style>



	<div class="container">
		<div class="row-fluid" style="margin-top: 20px">

			<!-- 表格开始 -->
			<div id="example_wrapper" class="dataTables_wrapper">
				<div class="toolbar">
				
					<b style="font-size: 14px">欢迎进入账户管理系统!</b>
				</div>
				<div id="example_filter" class="dataTables_filter">
					<label>日期: ${time?date}&nbsp&nbsp&nbsp&nbsp时间: ${time?time}<br></label>
				</div>
				
				<br/>
				<div class="toolbar">
					<b style="font-size: 24px">账户列表</b>
				</div>
				<br/>
				<div id="example_filter" class="dataTables_filter">
					<form action="/search" method="post" id="search2">
						<label>&nbsp&nbsp编码:<input name="id"  type="text" class="form-control" id="name" placeholder="请输入编码">&nbsp&nbsp名称:<input name="name" type="text" class="form-control"  id="name" placeholder="请输入名称">&nbsp&nbsp注册时段:<input type="text" style="width:180px" name="time" id="reservation"
										class="form-control"  value="01/01/2010 - ${formaTime}" />&nbsp&nbsp账户类型:&nbsp&nbsp<select name="type" class="select_gallery" style="width:200px;">
										<script type="text/javascript">
											$(document).ready(function() {
												$('#reservation').daterangepicker(null,
												function(start, end, label) {
													console.log(start.toISOString(), end.toISOString(), label);
												});
											});
										</script>
							<optgroup label=请选择账号类型>
								<OPtion value="0">不限</option>
								<option value="1">普通用户</option>
								<option value="2">会员</option>
								<option value="3">超级用户</option>
							</optgroup></select>&nbsp&nbsp&nbsp&nbsp<button id="find" type="button" class="btn btn-primary btn-lg">查&nbsp&nbsp&nbsp&nbsp询</button></label>
						<label><button id="new"  type="button" class="btn btn-primary btn-lg"  >新增账户</button>&nbsp&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary btn-lg" onclick="edit()">修改账户</button>&nbsp&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary btn-lg" onclick="del();">删除账户</button>&nbsp&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary btn-lg" onclick="off();">封&nbsp&nbsp&nbsp&nbsp存</button>&nbsp&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary btn-lg" onclick="on();">启&nbsp&nbsp&nbsp&nbsp封</button></label>
					</form>
					
				</div>
				
				<table id="example" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="10px"><input type="checkbox" id="checkOrCancelAll" />全选</th>
								<th  width="70px">账户编号</th>
								<th>名称</th>
								<th>类型</th>
								<th>状态</th>
								<th>创建时间</th>
								<th>最后登录时间</th>
								<th>备注</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Check</th>
								<th>AccountId</th>
								<th>Name</th>
								<th>Type</th>
								<th>StateMent.</th>
								<th>CreatTime</th>
								<th>LastLoginTime</th>
								<th>Note</th>
							</tr>
						</tfoot>
					</table>
				
		</div>
	</div>

</body>
</html>