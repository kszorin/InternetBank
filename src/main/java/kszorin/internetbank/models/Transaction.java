package kszorin.internetbank.models;

/**
 * Created by kszorin on 11.02.2017.
 */
public class Transaction {
    private int id;
    private int idBillSend;
    private int idBillReceive;
    private float sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBillSend() {
        return idBillSend;
    }

    public void setIdBillSend(int idBillSend) {
        this.idBillSend = idBillSend;
    }

    public int getIdBillReceive() {
        return idBillReceive;
    }

    public void setIdBillReceive(int idBillReceive) {
        this.idBillReceive = idBillReceive;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
