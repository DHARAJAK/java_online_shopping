package org.users;

public interface UsersDao {
	public boolean RegisterUser(RegUser r) throws UsersException;

	public boolean UserExists(String username) throws UsersException;

	public boolean VerifyPassword(String username, String password) throws UsersException;

}
