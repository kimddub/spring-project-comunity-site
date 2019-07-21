function Member__chagePw() {
	var $tr = $('.password-form');
	
	$tr.addClass('password-change-mode');
	$tr.find('input[name="passwordChange"]').val("yes");
}

function Member__notChagePw() {
	var $tr = $('.password-form');
	
	$tr.removeClass('password-change-mode');
	$tr.find('input[name="passwordChange"]').val("no");
}

function doRetirement() {
	if ( confirm('정말로 탈퇴하시겠습니까?') == false ) {
		return false;
	}
	
	var loginPw = $('#modify-form input[name="loginPw"]').val();
	var $retirementForm = $('#retirement-form');
	$retirementForm.find('input[name="loginPw"]').val(loginPw);
	$retirementForm.submit();
	
	return true;
}