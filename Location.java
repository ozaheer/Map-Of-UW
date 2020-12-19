// --== CS400 File Header Information ==--
// Name: Yash Ramchandani
// Email: yramchandani@wisc.edu
// Team: FC
// TA: Abhay
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class Location extends Object{
    private String name;
    private boolean isBuilding;

    public Location(String name, boolean isBuilding) {
        this.name = name;
        this.isBuilding = isBuilding;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsBuilding() {
        return this.isBuilding;
    }

    /**
     * Overrides the equals function to check if the names of the locations are the same
     */
    @Override
    public boolean equals(Object obj) {
        Location location = (Location) obj;
        return this.name.equals(location.name);
    }

    public String toString() {
        return this.name;
    }

    /**
     * Overrides the hashtable hashcode method to hash using the name of the location rather than the location object
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}