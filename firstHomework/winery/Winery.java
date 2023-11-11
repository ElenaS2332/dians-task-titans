package firstHomework.winery;

public class Winery {

    public long ID;
    public Double latitude;
    public Double longitude;
    public String coordinateType;

    public String type;

    public String name;
    public Winery() {
    }

    public Winery(long id, String name, Double latitude, Double longitude) {
        this.ID = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("\nName is: %s\nLatitude is: %f\nLongitude is: %f\n",
                name, latitude, longitude);
    }
}
