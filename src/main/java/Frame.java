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

    public void addShot(Integer move){
        this.shots.add(move);
    }

    @Override
    public String toString(){
        String result = "";
        Integer sumTotal = this.shots.stream().collect(Collectors.summingInt(Integer::intValue));
        if(sumTotal == 10 && this.shots.size() == 1){
            result = "\t" + "X";
        }else if(sumTotal == 10){
            result = this.shots.get(0) + "\t" +"/";
        }else if (this.hasFail){
            for(int i :this.shots){
                result += (i==0)? "F":i;
                result += "\t";
            }
        }else{
            for(int i :this.shots){
                result += (i==10)? "X":i;
                result += "\t";
            }

        }
        return (result.endsWith("\t"))?result.replaceFirst(".$",""):result;

    }
    public String toStringTotal(){
        return this.getTotal().toString();
    }

    public Frame() {
        this.shots = new ArrayList<>();
        this.total = 0;
        this.hasFail = false;
    }

}
