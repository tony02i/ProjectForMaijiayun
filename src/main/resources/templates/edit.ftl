<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="fyunli">

    <base id="base" href="${base}">
    <title>编辑账户</title>

    <!-- Bootstrap core CSS -->



    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="//cdn.jsdelivr.net/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    



	

<link href="dataTables/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="select2-4.0.0/dist/css/select2.min.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="select2-4.0.0/dist/js/select2.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$('select').select2();
	});
</script>
<script type="text/javascript">
	$(function(){
		$("input[type=radio]").each(function(){
			if($(this).val()=="${state}"){
				$(this).attr("checked","checked");
			}
		});
		
		
		$("#save").click(function(){
			$("#addform").submit();
		});
		$("#addform").ajaxForm({
			beforeSubmit:function(){
				$("#message").text("正在保存许可请稍后...");
				$.ajaxSetup({
					async:false
				});

				$("#requiretext").blur();
				var flag = true;
				
				if($("#requirespan").text()!=""){
					flag = false;
				}
			
				if(!flag){
					$("#message").text("数据非法请检查!");
				}
				return flag;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("保存成功！");
				
				}else{
					$("#message").text("保存失败");												
				}
				
				$.ajaxSetup({
					async:true
				});

			}
		});
		$("#requiretext").blur(function(){
			$("#message").text("");
			var name = this.value;
			name = $.trim(name);
			if(name==""){
				$("#requirespan").text("编码不能为空！");				
			}else{
				$.get("editgetbyid",
						{"id":name,"oldid":"${oldid}","_":new Date().getTime()},function(jsonObject){
							if(jsonObject.success){
								$("#requirespan").text("");
							}else{
								$("#requirespan").text("该编码已经存在");
							}
						});
			}
		});
	});
</script>	
	
</head>

<body>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h3>新建账户</h3>
    </div>

    <div>
         ${message}&nbsp&nbsp&nbsp&nbspDate: ${time?date}&nbsp&nbspTime: ${time?time}   
        <br/><br/>

    </div>	
				<form action="update" method="post" id="addform">
					
					<div >
						<label>&nbsp&nbsp编&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码:<input value="${id}" name="id" id="requiretext" type="text" class="form-control" id="name" placeholder="请输入编码"><span id="requirespan" style="color: red;font-size: 16px"></span></label>
					</div>
					<div>
						<label>&nbsp&nbsp名&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp称:<input  value="${name}" name="name" type="text" class="form-control" id="name" placeholder="请输入名称"></label>
					</div>
					<div >
						<label>&nbsp&nbsp账户类型:&nbsp<select id="type" name="type" class="select_gallery" style="width:220px;">
							
							<optgroup  label=请选择账号类型！">
								<option id="option" value="1">普通用户</option>
								<option id="option" value="2">会员</option>
								<option id="option" value="3">超级用户</option>
							</optgroup></select>
							 <script type="text/javascript">  
                                document.getElementById("type").value="${type}";  
                                document.getElementById("type")[${type}-1].selected= true;  
                            </script> 
							</label>
							
					</div>
						
					<div>
						<label>&nbsp&nbsp备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注:<input value="${note}" name="note" type="text" class="form-control" id="name" placeholder="请填写备注"></label>
					</div>
					<div >
						<label>&nbsp&nbsp该账号是否启用:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<br/>
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="state" value="1" checked="checked" />&nbsp&nbsp是
						<input  type="radio" name="state" value="0" />&nbsp&nbsp否</label>
					</div>
						
					<br/>
					<button id="save" type="button" class="btn btn-default">提交</button><span id="message" style="color: red;font-size: 16px"></span>
				
				</form>
		</div>
	</div>
<script type="text/javascript">
		$(".select_gallery-multiple").select2();
		$(".select_gallery").select2();
	</script>
</body>
</html>