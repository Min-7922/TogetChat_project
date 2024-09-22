
   	const posts = document.querySelectorAll('.post');
   	
   	posts.forEach(post => post.addEventListener('click', function() {
   		const boardIdx = post.querySelector('td:first-child').innerText;
   		
   		window.location.href='/board/view/' + boardIdx;
   	}));
