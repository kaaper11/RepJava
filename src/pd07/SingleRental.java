package pd07;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleRental implements Comparable<SingleRental> {

    private final RentableResource resource;
    private int rentDays;
    private ResourceStatus status;

    @Override
    public String toString() {
        return "\n" + resource + ", " + rentDays + ", " + status;
    }

    @Override
    public int compareTo(SingleRental o) {
        return this.resource.compareTo(o.resource);
    }

    public String getResourceName() {
        return resource.getName();
    }
}
