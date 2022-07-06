/*const $menuList = document.getElementsByClassName("infoTitle");
const menuList	= Array.from(HTMLCollection);
console.log($menuList);
console.log(menuList);
console.log(menuList[1]);*/



/* 사이드 리스트 클릭시 컬러 바꾸기 */
window.onload = function () {
	let ul =  document.querySelectorAll('.infoMenuList');
	let menu = document.querySelectorAll('.menu-txt');
	
	document.addEventListener('click', (e) => {
		if(e.target.classList.contains("menu-txt") == true) {
			for(let i=0; i < menu.length; i++){
				if(e.target === menu[i]) {
					console.log(e.target);
					console.log(i + menu[i]);
					menu[i].classList.add('clicked');
					console.log('a = ' + menu[i].parentElement);
					console.log(menu[i].parentElement.href);
					console.log( menu[i].children[0]);
					menu[i].parentElement.href += 
					'?myCondition=' + menu[i].innerText; 
				} else {
					console.log(i + menu[i]);
					if(menu[i].classList.contains('clicked') == true) {
						menu[i].classList.remove('clicked');
					} else {
						
					}
				}
			}
		}
	});
	
	/*document.addEventListener('mouseover', (e) => {
		if(e.target.classList.contains("menuList") == true) {
			for(let i=0; i<menu.length;i++){
				if(e.target === menu[i]) {
					console.log(i + menu[i]);
				}
			}
			
			e.target.classList.add('clicked');
		}
	});
	
	document.addEventListener('mouseout', (e) => {
		if(e.target.classList.contains("menuList") == true) {
			e.target.classList.remove('clicked');
		}
	});*/
	
}



