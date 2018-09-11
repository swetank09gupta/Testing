public class CompanyDetails {
    String name;
    long currentBid;
    long timeStamp;
    long lowest;
    long highest;

    public CompanyDetails(String name, long currentBid, long timeStamp) {
        this.name = name;
        this.currentBid = currentBid;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(long currentBid) {
        this.currentBid = currentBid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getLowest() {
        return lowest;
    }

    public void setLowest(long lowest) {
        this.lowest = lowest;
    }

    public long getHighest() {
        return highest;
    }

    public void setHighest(long highest) {
        this.highest = highest;
    }

    @Override
    public String toString() {
        return "CompanyDetails{" +
                "name='" + name + '\'' +
                ", currentBid=" + currentBid +
                ", timeStamp=" + timeStamp +
                ", lowest=" + lowest +
                ", highest=" + highest +
                '}';
    }
}