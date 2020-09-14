export const login = function(UsrEmail, UsrPwd) {
	if (UsrEmail === UsrPwd)
		return true;
	else
		return false;
};