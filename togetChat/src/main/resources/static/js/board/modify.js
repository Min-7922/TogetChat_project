
 const ImageList = document.querySelector('.boardImages');
 ImageList.addEventListener('click', function(event) {
	 console.log('클릭이벤트시작')
	 if(event.target.tagName === 'BUTTON') {
		 console.log('클릭이벤트시작->버튼누른거 확인')
		 event.preventDefault();
		 
		 const imageOne = event.target.closest('.boardImageOne');
		 imageDelete(imageOne);
	 }
 });
 
 async function imageDelete(imageOne) {
	 console.log('imageOne메서드 시작')
	 const imageSrc = imageOne.querySelector('img').src;
	 const boardIdx = imageOne.querySelector('button').getAttribute('boardIdx');
	 
	 const url = '/board/deleteImage';
	 const ob = {
			 boardIdx: boardIdx,
			 imagePath: imageSrc
	 };
	 const opt = {
			 method: 'DELETE',
			 body: JSON.stringify(ob),
			 headers: {
				 'Content-Type': 'application/json; charset=utf-8;'
			 }
	 };
	 
	 const data = await fetch(url, opt).then(resp => resp.json());
	 if(data == 1) {
		 imageOne.remove();
	 }
	 else {alert('이미지 삭제에 실패하였습니다');}
 }
