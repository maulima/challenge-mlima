public class Frame {

    public Frame(Integer firstShot, Integer secondShot) {
        this.firstShot = firstShot;
        this.secondShot = secondShot;
    }

    public Integer getFirstShot() {
        return firstShot;
    }

    public void setFirstShot(Integer firstShot) {
        this.firstShot = firstShot;
    }

    public Integer getSecondShot() {
        return secondShot;
    }

    public void setSecondShot(Integer secondShot) {
        this.secondShot = secondShot;
    }



    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    Integer firstShot;
    Integer secondShot;
    Integer total;


}
