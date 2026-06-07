package org.payment;

import javax.smartcardio.CardException;

public interface CardsDao {

	public Boolean verifyCard(Integer cardNo, Integer cardCVV, Integer cardPassword) throws CardException;

	public Boolean deductBalance(Integer cardNo, Float billAmount) throws CardException;

}
