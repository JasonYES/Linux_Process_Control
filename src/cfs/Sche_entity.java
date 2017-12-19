package cfs;

/**
 * Created by Fang Yi on 17-10-24.
 */

public class Sche_entity {

    private String name;
    private int nice;
    private double vruntime;

    public Sche_entity(String name, int nice, double vruntime) {
        this.name = name;
        this.nice = nice;
        this.vruntime = vruntime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNice() {
        return nice;
    }

    public void setNice(int nice) {
        this.nice = nice;
    }

    public double getVruntime() {
        return vruntime;
    }

    public void setVruntime(double vruntime) {
        this.vruntime = vruntime;
    }

}
