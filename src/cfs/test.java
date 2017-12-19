package cfs;



/**
 * Created by Fang Yi on 17-10-25.
 */

public class test {

    public static void main(String args[]){

        Ofs_rq ofs_rq = new Ofs_rq();

        Sche_entity sche_entity1 = new Sche_entity("进程1", 1, 3);
        Sche_entity sche_entity2 = new Sche_entity("进程2", 2, 5);
        Sche_entity sche_entity3 = new Sche_entity("进程3", 3, 1);
        Sche_entity sche_entity4 = new Sche_entity("进程4", 4, 0);

        ofs_rq.queue_in(sche_entity1);
        ofs_rq.queue_in(sche_entity2);
        ofs_rq.queue_in(sche_entity3);
        ofs_rq.queue_in(sche_entity4);

        ofs_rq.initial();
        while(true){
            ofs_rq.tick();
        }
//        System.out.println(queue);
//        System.out.println(ofs_rq);

    }
}
