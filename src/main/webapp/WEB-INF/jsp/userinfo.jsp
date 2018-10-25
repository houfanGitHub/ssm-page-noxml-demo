<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
		padding:0px;
		margin:0px;
	}
	table{
		background:#000;
	}
	table,div{
		margin:10px auto;
		width:1000px;
	}
	td,th{
		background:#FFF;
	}
	.numText{
		width:25px;
		text-align:center;
	}
	.submitBtn{
		width:80px;
	}
</style>
</head>
<body>
	<h2>Show User</h2>
	<hr>
	<form action="${pageContext.request.contextPath }/users/userinfo" method="post" id="userinfo">
		<div>
			name:<input type="text" name="name" id="name" value="${name }">&nbsp;&nbsp;
			username:<input type="text" name="username" id="username" value="${username }">&nbsp;&nbsp;
			<input type="submit" value="查询" class="submitBtn">&nbsp;&nbsp;
			<input type="button" onclick="resetBtn();" value="清空">
		</div>
		<table border="0" cellspacing="1" id="userinfoTable">
			<tr>
				<th>name</th>
				<th>username</th>
				<th>gender</th>
				<th>age</th>
				<th>createDate</th>
				<th>lastLoginDate</th>
				<th>locked</th>
				<th>操作</th>
			</tr>
			 <c:forEach items="${pageInfo.list }" var="user">
			<tr>
				<td>${user.name}</td>
				<td>
					<a href="javascript:void(0);" onclick="updateUser('${user.uid}');">${user.username}</a>
				</td>
				<td>${user.gender == 'M' ? '男':'女'}</td>
				<td>${user.age}</td>
				<td><fmt:formatDate value="${user.createdate}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${user.lastlogindate}" pattern="yyyy-MM-dd" /></td>
				<td>${user.locked}</td>
				<td>
					<a href="javascript:void(0);" onclick="deleteUser('${user.uid}','${user.username}');">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div>
			<a href="javascript:void(0);" onclick="userinfo('up')">上一页</a>&nbsp;&nbsp;
			当前页<input type="number" name="pageNum" class="numText" readonly value="${pageInfo.pageNum }" id="pageNum">&nbsp;&nbsp;
			总页数<input type="number" name="maxPage" class="numText" readonly value="${pageInfo.pages }" id="maxPage">&nbsp;&nbsp;
			<input type="hidden" id="firstPage" value="${pageInfo.isFirstPage }">
			<input type="hidden" id="lastPage" value="${pageInfo.isLastPage }">
			<a href="javascript:void(0);" onclick="userinfo('down')">下一页</a>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript">
		function updateUser(uid){
			window.location.href="${pageContext.request.contextPath}/users/toUpdateUser/"+uid;
		}
		function deleteUser(uid,username){
			if(confirm("确定要删除 "+username+" ?")){
				$.post("${pageContext.request.contextPath}/users/deleteUser/"+uid,function(data){
					if(data = 'success'){
						window.location.href="${pageContext.request.contextPath}/users/userinfo";
					}else{
						alert(data);
					}
				});
			}
		}
		function userinfo(msg){
			var pageNum = $("#pageNum").val();
			if(msg == 'up' && $("#firstPage").val() === 'false'){
				$("#pageNum").val(pageNum-1);
				$("#userinfo").submit();
			}else if(msg == 'down' && $("#lastPage").val() === 'false'){
				$("#pageNum").val($("#pageNum").val()+1);
				$("#userinfo").submit();
			}
		}
		function resetBtn(){
			$("#name").val("");
			$("#username").val("");
		}
	</script>
</body>
</html>