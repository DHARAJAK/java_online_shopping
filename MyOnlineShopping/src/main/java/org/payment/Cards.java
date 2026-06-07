package org.payment;

public class Cards {
	Integer cardNo;
	Integer cardCVV;
	Integer cardPassword;
	Float cardBalance;
	public Cards(Integer cardNo, Integer cardCVV, Integer cardPassword, Float cardBalance) {
		super();
		this.cardNo = cardNo;
		this.cardCVV = cardCVV;
		this.cardPassword = cardPassword;
		this.cardBalance = cardBalance;
	}
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getCardCVV() {
		return cardCVV;
	}
	public void setCardCVV(Integer cardCVV) {
		this.cardCVV = cardCVV;
	}
	public Integer getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(Integer cardPassword) {
		this.cardPassword = cardPassword;
	}
	public Float getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Float cardBalance) {
		this.cardBalance = cardBalance;
	}

}
