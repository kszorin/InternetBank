package kszorin.internetbank.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by kszorin on 11.02.2017.
 */
public class Bill {
    private int id;
    //@NotNull
    private int idClient;
    //@NotNull(message = "The initial sum should not be null!")
    //@Min(0)
    private float sum=0f;

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
