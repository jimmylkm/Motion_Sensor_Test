package fyp.motionsensortest;

/**
 * Created by Jimmy on 27/10/2015.
 */
public class Coordinates {
    private double x;
    private double y;
    private double lat;
    private double lng;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    public Coordinates(double _x, double _y, double _lat, double _lng) {
        this.x = _x;
        this.y = _y;
        this.lat = _lat;
        this.lng = _lng;
    }

    public Coordinates transformation(Coordinates cord) {
//        TODO: fill in with MK's transformation function
//        Dummy Return
        return cord;
    }

    public String toString() {
        return ("x,y,lat,lng: " + x + " " + y + " " + lat + " " + lng);
    }

}
