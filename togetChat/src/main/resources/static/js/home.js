
	const mainImageList = document.querySelectorAll('.titleimage > li');
	const mainImageArray = Array.from(mainImageList);

	// 메인 이미지 좌우버튼 클릭 이벤트
	const mainImageBtn = document.querySelectorAll('.arrow > div')
	mainImageBtn.forEach(Btn => Btn.addEventListener('click', function(event) {
		const plus = +event.target.getAttribute('order');
		let order = 0;

		mainImageArray.forEach(li => {
			if(!li.classList.contains('hidden')) {
				li.classList.add('hidden');
				order = +li.getAttribute('order') + plus;
				if(order<1) order = 4;
				if(order>4) order = 1;
			}
		})
		mainImageArray.forEach(li => {
			if(+li.getAttribute('order') == order) {
				li.classList.remove('hidden');
			}
		})

	}))

	const programTop5 = document.getElementById('programTOP5');

	const allProgram = document.getElementById('allProgram');

