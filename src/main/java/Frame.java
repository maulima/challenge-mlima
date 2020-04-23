import java.util.ArrayList;
import java.util.List;

public class Frame {
    List<Integer> shots;
    Integer total;

    public void setHasFail(boolean hasFail) {
        this.hasFail = hasFail;
    }

    boolean hasFail;

    public List<Integer> getShots() {
        return shots;
    }

    public void setShots(List<Integer> shots) {
        this.shots = shots;
    }

    public void addShot(Integer move){
        this.shots.add(move);
    }

    public Frame() {
        this.shots = new ArrayList<>();
        this.total = 0;
        this.hasFail = false;
    }
    @Override
    public String toString(){
        System.out.print("Frames: ");
        System.out.print(this.shots.toString());
        System.out.print(" Has Fail: ");
        System.out.print(this.hasFail +" ");
        System.out.println();
        return null;
    }
}
