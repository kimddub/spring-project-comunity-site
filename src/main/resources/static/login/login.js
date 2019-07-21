function formCheck(form) {
	
	form.loginId.value = form.loginId.value.trim();
	
	if ( form.loginId.value.length == 0 ) {
		alert('ID를 입력해주세요');
		form.loginId.focus();
		
		return false;
	}
	
	form.loginPw.value = form.loginPw.value.trim();
	
	if ( form.loginPw.value.length == 0 ) {
		alert('PW를 입력해주세요');
		form.loginPw.focus();
		
		return false;
	}
	
	form.submit();
}