package cfs;

/**
 * Created by Fang Yi on 17-10-25.
 */
public interface Ofs_rq_interface {

    public void queue_in(Sche_entity sche_entity);

    public void tick();

    public void run();

    public boolean insert_check();

    public boolean insert_safe();

    public void reflesh_v();

    public double count_time(Sche_entity sche_entity);

    public double count_vtime(double time);

    public void initial();

}
