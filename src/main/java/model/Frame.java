package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    List<Integer> shots;
    Integer total;
    boolean hasFail;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setHasFail(boolean hasFail) {
        this.hasFail = hasFail;
    }

    public List<Integer> getShots() {
        return shots;
    }

    public void setShots(List<Integer> shots) {
        this.shots = shots;
    }

    public String toStringTotal(){
        return this.getTotal().toString();
    }

    public Frame() {
        this.shots = new ArrayList<>();
        this.total = 0;
        this.hasFail = false;
    }

    @Override
    public String toString(){
        String result;
        Integer sumTotal = this.shots.stream().collect(Collectors.summingInt(Integer::intValue));
        if(sumTotal == 10 && this.shots.size() == 1){
//            Strike case
            result = "\t" + "X";
        }else if(sumTotal == 10){
//            Spare case
            result = this.shots.get(0) + "\t" +"/";
        }else if (this.hasFail){
            result = this.getShots().stream().map(o-> o==0 ? "F":o ).collect(Collectors.toList()).stream().map(String :: valueOf).collect(Collectors.joining("\t"));
        }else{
            result = this.getShots().stream().map(o-> o==10 ? "X":o ).collect(Collectors.toList()).stream().map(String :: valueOf).collect(Collectors.joining("\t"));
        }
        return result;
    }

}
