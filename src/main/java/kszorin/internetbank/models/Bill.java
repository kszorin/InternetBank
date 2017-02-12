package kszorin.internetbank.models;

/**
 * Created by kszorin on 11.02.2017.
 */
public class Bill {
    private int id;
    private int idClient;
    private float sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
