package org.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.smartcardio.CardException;

public class CardsDaoImpl implements CardsDao {
	Connection connection = null;

	public CardsDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Boolean verifyCard(Integer cardNo, Integer cardCVV, Integer cardPassword) throws CardException {

		if (connection != null) {

			try (PreparedStatement psCardVer = connection
					.prepareStatement("select cardNo, cardCVV, cardPassword from cards where cardNo= ?")) {
				psCardVer.setInt(1, cardNo);

				ResultSet result = psCardVer.executeQuery();

				if (result.next()) {
					Integer cardNoO = result.getInt(1);
					Integer cardCVVO = result.getInt(2);
					Integer cardPasswordO = result.getInt(3);

					System.out.println(cardNoO);
					System.out.println(cardCVVO);
					System.out.println(cardPasswordO);
					System.out.println(cardNo);
					System.out.println(cardCVV);
					System.out.println(cardPassword);

					if (cardNo.equals(cardNoO) && cardCVV.equals(cardCVVO) && cardPassword.equals(cardPasswordO)) {
						return true;
					}

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	public Float getBalance(Integer cardNo) throws CardException {
		if (connection != null) {

			try (PreparedStatement psGetBal = connection
					.prepareStatement("select cardBalance from cards where cardNo = ?")) {
				psGetBal.setInt(1, cardNo);

				ResultSet result = psGetBal.executeQuery();

				if (result.next()) {
					return result.getFloat(1);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;

	}

	@Override
	public Boolean deductBalance(Integer cardNo, Float billAmount) throws CardException {

		if (connection != null) {

			try {
				connection.setAutoCommit(false);

				try (PreparedStatement psCardVer = connection
						.prepareStatement("update cards set cardBalance = (cardBalance - ?) where cardNo= ?")) {
					psCardVer.setFloat(1, billAmount);
					psCardVer.setInt(2, cardNo);

					psCardVer.executeUpdate();
				}

				Float Balance = getBalance(cardNo);

				if (Balance == null || Balance < 0) {
					System.out.println("Transaction Rejected: Overdraft or missing card data. Rolling back");
					connection.rollback();
				} else {
					connection.commit();
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return false;
				}
			} finally {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return false;
	}

}
