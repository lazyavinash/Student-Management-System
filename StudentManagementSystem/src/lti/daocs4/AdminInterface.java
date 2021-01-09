package lti.daocs4;

interface AdminInterface {
	int register(String username, String password, String name, String contact);
	int signin(String username,String password);
}
