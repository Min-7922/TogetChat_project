
	const searchInput = document.querySelector('input[type="search"]');
	const searchBtn = document.querySelector('.search-form > button');

	const resultUl = document.getElementById('result-program');
	
	// 검색 결과 받아오기
	searchBtn.addEventListener('click', async function(event) {
		event.preventDefault();
		// 기존 검색 결과는 삭제
		resultUl.innerHTML = '';
		
		const search = searchInput.value;
		console.log('검색:' + search);
		const url = '/search';
		const ob = {
			'search': search
		};
		const opt = {
			headers: {
				'Content-Type':'application/json; charset=utf-8;'
			},
			body: JSON.stringify(ob),
			method: 'POST'
		};

		const data = await fetch(url, opt).then(resp => resp.json());
		console.log(data);
		searchResult(data);
	});
	

	// 검색 결과 화면에 출력하기
	function searchResult(list) {
		list.forEach(dto => {
			console.log('검색결과 제목:' + dto.title);
			let li = '';
			
			li += `<li><a href="#">`;
			if(dto.image != null) {
				li += `	<img src="/images/default.png" width="150px">`;
				li += ` <div class="program-title">${dto.title}</div>`;
			}
			if(dto.image == null) {
				li += `	<div class="no-image">${dto.title}</div>`;
			}
			li += `</a></li>`;
	
			resultUl.innerHTML += li;
		});
	}

	
