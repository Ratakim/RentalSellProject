<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/goods/registerPage.css?after">
	<script type="text/javascript" src="${contextPath}/resources/js/main/goods/registerMain.js?after"></script>
	 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<%--<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=njxbfdulto"></script>--%>
</head>
<body>
	<div class="registerMain">
		<form action="${contextPath}/goods/registeredGoodsArr.do"  id="formRegist" name="formRegist" method="post" enctype="multipart/form-data">
			<main class="rm-main">
				<section class="rm-section">
					<ul class="rm-ul">
						<li class="rm-listname">
							<div class="rm-ln">
								<label>제목</label>
							</div>
							<div class="rm-ln-it">
								<div class="rm-ln-ita">
									<input type="text" placeholder="상품 제목을 입력해주세요." class="rm-ln-itt" name="goods_title" value="" >
								</div>
								<div class="error-name" id="error-name">
									상품명을 두 글자 이상 입력해주세요.
								</div>
							</div>
						</li>
						
						<li class="rm-listname">
							<div class="rm-cn">
								<label>카테고리</label>
							</div>
							<div class="rm-cn-it">
								<div class="rm-cn-itt">
									<div class="rm-cn-itt-large">
										<ul class="rm-cn-itt-large-a " >
										<c:forEach var="category" items="${ctgList}" >
											<li class="large-a">
												<button type="button" class="large-a-s"  id="${category.category_ID}" onclick="bringCategory(this);">${category.category_NM}</button>
											</li>
										</c:forEach>
										</ul>
									</div>
									<div class="rm-cn-itt-middle">
										<ul class="rm-cn-itt-middle-a">
											
										</ul>
									</div>
									<div class="rm-cn-itt-small">
										<ul class="rm-cn-itt-small-a">
											
										</ul>
									</div>
								</div>
								<input type="hidden" id="middle-ctg" name="category_ID" value=""/>
								<h3 class="rm-cn-if" >
								선택한 카테고리 : 
								<b id="rm-cn-iff"></b>
								<b id="rm-cn-iffS" ></b>
								</h3>
								<div class="error-ctg" >
									<label>세부 카테고리를 선택해주세요.</label>
								</div>
							</div>
						</li>
						<li class="rm-listname">
							<div class="rm-in">
								상품 이미지
								<div id="fileCount">(<span></span>/12)</div>
							</div>
							<div class="rm-ir">
								<!--<h3>업로드된 파일1</h3>
									<input id="browse" type="file" onchange="previewFiles()" multiple>
									<div id="preview"></div> -->
    								<h3>업로드된 파일</h3>
								<ul class="rm-ir-m">
									<li class="imageRegist">
										이미지 등록
										<input type="file" class="chooseFile" id="imageInput" name="imageRegist" accept="image/jpg, image/jpeg, image/png" multiple/>
									</li>
								</ul>
								<ul class="rm-ir-m preview">
								</ul>
								<div class="eeeee-e" >
									<label>상품 사진을 한 장 이상 등록해주세요.</label>
								</div>
								<div class="rm-ir-caution">
									<b>* 상품 이미지는 640x640에 최적화 되어 있습니다.</b>
									<br>- 상품 이미지는 PC에서는 1:1, 모바일에서는 1:1.23 비율로 보여집니다. 
									<br>- 이미지는 상품 등록 시 정사각형으로 잘려서 등록됩니다.
									<br>- 이미지를 클릭할 경우 원본 이미지를 확인할 수 있습니다.
									<br>- 이미지를 클릭 후 이동하여 등록순서를 변경할 수 있습니다.
									<br>- 큰 이미지일 경우 이미지가 깨지는 경우가 발생할 수 있습니다.
									<br>최대 지원 사이즈인 640 X 640으로 리사이즈 해서 올려주세요.(개당 이미지 최대 10M)
								</div>
							</div>
						</li>
						<li class="rm-listname">
							<div class="rm-region-n">
								거래지역
							</div>
							<div class="rm-region-r">
								<div class="region-menu">
									<button type="button" class="region-mb">내 위치</button>
									<button type="button" class="region-mb" id="address_kakao" >주소 검색</button>
								</div>
								<input readonly placeholder="거래 지역을 검색해주세요." class="region-input" name="deal_region" value=""/>
								<div class="error-region">
									지역을 입력해주세요.
								</div>
							</div>
						</li>
						<li class="rm-listname">
							<div class="rm-gi-n">
								상품설명
							</div>
							
							<div class="rm-ln-it">
								<textarea placeholder="상품 설명을 입력해주세요. (10글자 이상)" rows="6" class="rm-gi-it" name="goods_desc"></textarea>
								<div class=error-explain>
								내용을 10글자 이상 입력해주세요.
								</div>
							</div>
							
						</li>
						<li class="rm-listname">
							<div class="rm-price-n">
								금액
							</div>
							<div class="rm-ln-it">
								<div class="rm-price-hour">
									<input type="text" placeholder="숫자만 입력해주세요." class="price_hour" name="goods_price"value="">	
									원/시간
								</div>
								<div class="error-price" >
								시간당 금액을 입력해주세요.
								</div>
								<div class="rm-price-day">
									<input type="text" placeholder="숫자만 입력해주세요." class="price_day" value="" readonly>	
									원/ 일
								</div>
								
							</div>
							
						</li>
					</ul>
				</section>
			</main>
			<footer class="rm-registButton">
				<div class="rb-panel">
					<button type="button" class="rb-main" onclick="checkForm(this.form)">
						
					</button>
				</div>
			</footer>
		</form>
	</div>
</body>
</html>