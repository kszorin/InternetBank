package kszorin.internetbank.models;

/**
 * Created by kszorin on 11.02.2017.
 */
public class Transaction {
    private int id;
    private int idBillSender;
    private int idBillRecipient;
    private float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBillSender() {
        return idBillSender;
    }

    public void setIdBillSender(int idBillSender) {
        this.idBillSender = idBillSender;
    }

    public int getIdBillRecipient() {
        return idBillRecipient;
    }

    public void setIdBillRecipient(int idBillRecipient) {
        this.idBillRecipient = idBillRecipient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
