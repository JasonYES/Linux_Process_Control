package cfs;

/**
 * Created by Fang Yi on 17-10-25.
 */

import java.util.Map;
import java.util.TreeMap;

public class Ofs_rq implements Ofs_rq_interface{

    //nice值的转换
    int[] nice_table = {300,};
    //等待队列
    private TreeMap<Double,Sche_entity> queue = new TreeMap<>();

    private Sche_entity present = null;
    private int nice_total = 0;
    private int time_round = 100;
    private int solid = 1;
    private int gran = 10;

    @Override
    public void queue_in(Sche_entity sche_entity) {
        //nice totatl, queue更新
        try {
            queue.put(sche_entity.getVruntime(),sche_entity);
        } catch (Exception e) {
            System.out.println("错误：新进程的vruntime与已有重复！");
        }
        nice_total += sche_entity.getNice();
    }

    @Override
    public void initial() {
        try {
            if(present == null) {
                Map.Entry<Double, Sche_entity> firstEntry = queue.pollFirstEntry();
                present = firstEntry.getValue();
                run();
            }
        } catch (Exception e){
            System.out.println("进程初始错误！");
        }
    }

    @Override
    public void run() {
        try {
            Double runtime = count_time(present);
            Long threattime = runtime.longValue();
            Thread.sleep(threattime*20);
            present.setVruntime(present.getVruntime()+count_vtime(runtime));

            System.out.println("进程名："+present.getName()+"  本次运行："+threattime*20*1.0/1000+"s  总Vtime："+present.getVruntime());
            return;
        } catch (Exception e){
            System.out.println("进程运行中错误！");
        }
    }

    @Override
    public void tick() {
        try {
            if(insert_check()){
                insert_safe();
                run();
            }else {
                run();
            }
        } catch (Exception e){
        }
    }

    @Override
    public boolean insert_check() {
        try{
            Map.Entry<Double, Sche_entity> firstEntry = queue.firstEntry();
            if(present.getVruntime() - firstEntry.getKey() > gran){
                return true;
            }else return false;
        } catch (Exception e){
            System.out.println("插入判定出错");
        }
        return false;
    }

    @Override
    public boolean insert_safe() {
        try {
            while(queue.containsKey(present.getVruntime())){
                present.setVruntime(present.getVruntime()+0.1);
            }
            queue.put(present.getVruntime(),present);
            Map.Entry<Double, Sche_entity> firstEntry = queue.pollFirstEntry();
            present = firstEntry.getValue();
        } catch (Exception e){
            System.out.println("插入判定出错");
        }

        return false;
    }

    @Override
    public void reflesh_v() {
        //刷新防止溢出函数
        try {
            if(present.getVruntime()>(Double.MAX_VALUE/2)){

            }
        } catch (Exception e){

        }
    }

    @Override
    public double count_time(Sche_entity sche_entity) {
        try {
            double runtime = time_round * (sche_entity.getNice() * 1.0 / nice_total);
            return runtime;
        }catch (Exception e){
            System.out.println("时间计算出错！");
        }
        return 0.0;
    }

    @Override
    public double count_vtime(double time) {
        try {
            double vtime = time * (solid *1.0 / present.getNice());
            return vtime;
        }catch (Exception e){
            System.out.println("时间计算出错！");
        }
        return 0.0;
    }

    @Override
    public String toString() {
        String output = "";
        for(Map.Entry<Double, Sche_entity> entry : queue.entrySet()){
            output+=entry.getKey() +"  "+ entry.getValue().getName() + '\n';
        }
        return output;
    }

}
