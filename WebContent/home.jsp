<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>

<div class="container">

	<div class="row">
	
		<div class="col-sm-3">
			<table class="table table-hover">
				<thead>
					<tr align=center>
						<th>번호</th>
						<th>구단</th>
					</tr>
				</thead>
				<tbody id="teamlist" align="center">
					<c:forEach var="team" items="${teams}">
						<tr style="cursor: pointer;">
							<td>${team.no}</td>
							<td onclick="player(${team.no})">${team.team}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="col-sm-4">
			<table class="table table-hover">
				<thead>
					<tr align=center>
						<th>선수 번호</th>
						<th>소속 선수</th>					
					</tr>
				</thead>
				<tbody id="playerList" align="center">			
				</tbody>
			</table>
		</div>
		
		<div class="col-sm-4">
			<table class="table table-hover">
				<thead>
					<tr align=center>
						<th>선수 번호</th>
						<th>소속 선수</th>
						<th> 포지션 </th>
					</tr>
				</thead>
				<tbody id="playerinfo" align="center">
							
				</tbody>
			</table>
		</div>		
	</div>
</div>

<script>
	function player(tno){
		alert('팀번호 : '+tno);
		// ajax로 호출해서 팀번호 select * from info where tno = 팀번호
		$.ajax({
			type: "get",
			url: "/project/ball?cmd=info&tno="+tno,
		   dataType: "json"
		}).done(function(result){
			$("#playerList").empty();
			alert(result);
			
			     for(let player of result) {  
			var string = 
			            	"          <tr>\r\n"+
			            	"          <td>" +player.dno+"</td>\r\n"+
			                "	        <td onclick=\"getDesc('"+player.dno+"','"+player.name+"')\" style=\"cursor: pointer;\">"+player.name+"</td>\r\n" +
                            "           </tr>";
			            		
			            		$("#playerList").append(string);
			     }
			     $("#playerinfo").empty();
			alert("정상이야");
		}).fail(function(error){
			alert("에러야");
		});
	}	
</script>

<script>
	function getDesc(dno, name){
		alert('등번호 : ' + dno + ' 이름 : ' + name);
		
		// ajax로 호출해서 팀번호 select * from info where tno = 팀번호
		$.ajax({
			type: "get",
			url: "/project/ball?cmd=playerinfo&dno="+dno+"&name="+name,
		   dataType: "json"
		}).done(function(result){
			 $("#playerinfo").empty();
			alert(result);	
			
			alert("정상이야");
	
			var str = "          <tr>\r\n"+
								"          <td>" + result.dno+"</td>\r\n"+
								"          <td>" + result.name+"</td>\r\n" +
								"          <td>" + result.posistion+"</td>\r\n"+
								"        </tr>";
		
			$("#playerinfo").append(str);
		}).fail(function(error){
			alert("에러야");
		});
	}	
</script>


 



</body>
</html>