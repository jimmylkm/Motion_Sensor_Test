package fyp.motionsensortest;

/**
 * Created by Jimmy on 26/10/2015.
 */
public class PathSegment {
    private double displacement;
    private double direction;
    private long startTime;
    private long endTime;
    private Coordinates startCord;
    private Coordinates endCord;

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Coordinates getStartCord() {
        return startCord;
    }

    public void setStartCord(Coordinates startCord) {
        this.startCord = startCord;
    }

    public Coordinates getEndCord() {
        return endCord;
    }

    public void setEndCord(Coordinates endCord) {
        this.endCord = endCord;
    }

    public PathSegment(double _displacement, double _direction,  long _startTime, long _endTime,
                       Coordinates _startCord) {
        this.displacement = _displacement;
        this.direction = _direction;
        this.startTime = _startTime;
        this.endTime = _endTime;
        this.startCord = _startCord;
//        Call function to calculate endCord TODO: Plug in the function from MK
        this.endCord = calEndCord(displacement, startCord);
    }

//    TODO: Check if this need to be static function
    public Coordinates calEndCord(double displacement, Coordinates startCord) {
//        Call function to calculate endCord TODO: Plug in the function from MK
        return startCord;
    }

    public String toString() {
        return ("Displacement: " + displacement
                + "\ndirection" + direction
                + "\nstartTime: " + startTime
                + "\nendTime: " + endTime
                + "\n" + startCord.toString()
                + "\n" + endCord.toString());
    }

}
