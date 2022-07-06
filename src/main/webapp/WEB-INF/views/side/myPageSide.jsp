<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" src="${contextPath}/resources/js/main/myPage/side.js?after"></script>
<title>마이페이지</title>
</head>
<style>
a {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}


section {
	position: relative;
	display: 	flex;
}

.myPageMain {
	min-height: 1000px;
	float: 		left;
	width:		300px;
	height: 	100%;
	
}

.side {
	background-color: white;
	width: 200px;
	text-align: left;
	padding: 50px 20px 25px 50px ;
	
}

.main {
	
	width: 800px;
}

.myPageMain div {
	
}

.myPageMain ol {
	border: 1px solid #000;
	margin: 5px 5px 5px 5px;
	padding: 5px 5px 5px 5px;
	text-align: center;
}

.side2 div {
	padding: 0px 20px 0px 0px;
}

.flex-column1 {
	display: 		flex;
	flex-direction: column;

}
.flex-row1 {
	width:			100%;
	display: 		flex;
	flex-direction: row;
}
.flex-row1 div {
	width: 			50%;
	height: 		150px;
}

.myPageSide {
	width:			100%;
	padding:		25px 0px;
}
.myPage {
	font-weight : 	800;
	font-size :		26px;
	color : 		black;
}
.myPage a:link	{
	color:			black;
}

.myPage a:visited {
	color:			black;
}
.myPageSideList{
	padding-top:	20px;
}
.billyInfoSide{
	padding-bottom:	20px;
}
.infoTitle{
	font-weight:	700;
	font-size:		20px;
}
.infoMenu{
	font-size:		15px;
	padding-top:	12px;
}
.infoMenuList{
	display:		flex;
	flex-direction: column;
}
.menuList{
	margin-top:	8px;
	color: rgba(34,34,34,.5);
}
.clicked {
	color : black;
	font-weight: 700;
}
.menuList .menu-txt {
	color: rgba(34,34,34,.5);
}

.memnuList.clicked .menu-txt {
	color: black;
}

li {
list-style : none;
}

.menu-txt {
	display:	inline-block;
	padding : 0px;
	width: 		200px;
	height: 	20px;
}

.menuList .menuLink {
	display:	inline-block;
	width: 		200px;
	height: 	20px;
}
</style>
<body>
	<div class="myPageMain">
		<div class="side">



			<div>

			<div class="myPageSide">
				<div class="myPage">
					<a href="/user/myPageMain.go">마이페이지</a>
				</div>
			</div>
			<div class="myPageSideList">
				<div class="billyInfoSide">
					<div class="infoTitle">
						거래 정보
					</div>
					<div class="infoMenu">
						<ul class="infoMenuList" >
							<li class="menuList ${param.myCondition eq '빌리기 내역' ? 'clicked' : ''}" >
								<a class="menuLink" href="/user/myPageBorrowList.go" ><span class="menu-txt">빌리기 내역</span></a>
							</li>
							<li class="menuList ${param.myCondition eq '빌려주기 내역' ? 'clicked' : ''}">
								<a class="menuLink" href="/user/myPageLendingList.go" ><span class="menu-txt">빌려주기 내역</span></a>
							</li>
<%-- 							<li class="menuList ${param.myCondition eq '관심 상품' ? 'clicked' : ''}">
								<a class="menuLink" href="/user/myPageDisinterestList.go" ><span class="menu-txt">관심 상품</span></a>
							</li> --%>
						</ul>
					</div>
				</div>
				<div class="myInfoSide">
					<div class="infoTitle">
						내 정보
					</div>
					<div class="infoMenu">
						<ul class="infoMenuList" >
							<li class="menuList ${param.myCondition eq '내정보 수정' ? 'clicked' : ''}">
								<a class="menuLink" href="/user/myPageInfo.go" class="menuList"><span class="menu-txt">내정보 수정</span></a>
							</li>
<%-- 							<li class="menuList ${param.myCondition eq '내 거래' ? 'clicked' : ''}">
								<a class="menuLink" href="/user/myPageTransaction.go" ><span class="menu-txt">내 거래</span></a>
							</li> --%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>