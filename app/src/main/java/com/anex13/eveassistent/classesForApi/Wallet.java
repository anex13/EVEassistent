package com.anex13.eveassistent.classesForApi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wallet {

    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("wallet_id")
    @Expose
    private Integer walletId;

    /**
     *
     * @return
     * The balance
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     * The balance
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     *
     * @return
     * The walletId
     */
    public Integer getWalletId() {
        return walletId;
    }

    /**
     *
     * @param walletId
     * The wallet_id
     */
    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

}
