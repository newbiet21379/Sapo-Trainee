export default function headerService() {
	const users =JSON.parse(localStorage.getItem("users")||'{}');
		if (users) return { Authorization: 'Bearer ' + users.accessToken };
	else return {};
}
