package org.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dbConfig.DBConfig;

public class UsersDaoImpl implements UsersDao {

	Connection connection;
	PreparedStatement psRegisterUser;
	PreparedStatement psUserExists;
	PreparedStatement psPasswordCheck;

	public UsersDaoImpl() {
	}

	public UsersDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean RegisterUser(RegUser r) throws UsersException {

		try {

			psRegisterUser = connection
					.prepareStatement("insert into  users( email, name , password, usename) values (?,?,?,?) ");

			psRegisterUser.setString(1, r.getEmail());
			psRegisterUser.setString(2, r.getName());
			psRegisterUser.setString(3, r.getPassword());
			psRegisterUser.setString(4, r.getUsername());

			return psRegisterUser.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean UserExists(String username) throws UsersException {
		try {

			psUserExists = connection.prepareStatement("select 1 from users where usename = ? ");
			psUserExists.setString(1, username);

			try (ResultSet result = psUserExists.executeQuery()) {
				return result.next();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean VerifyPassword(String username, String password) throws UsersException {
		try {
			psPasswordCheck = connection.prepareStatement("select password from users where usename = ?");
			psPasswordCheck.setString(1, username);

			ResultSet result = psPasswordCheck.executeQuery();

			if (result.next()) {
				return result.getString("password").trim().equals(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
