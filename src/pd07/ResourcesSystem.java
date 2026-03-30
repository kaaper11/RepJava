package pd07;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
@Getter
public class ResourcesSystem {
    private final ArrayList<SingleRental> rentals = new ArrayList<>();

    public void addResource(RentableResource resource, int rentDays, ResourceStatus status) {
        rentals.add(new SingleRental(resource, rentDays, status));
    }

    public void allResourcesPrice() {
        double fullPrice = 0.0;
        for (SingleRental rental : rentals) {
            fullPrice += rental.getResource().calculatePrice(rental.getRentDays());
        }
        System.out.println("Łączny koszt wszytskich wypożyczeń: " + fullPrice + " zł\n");
    }

    public void countByStatus(ResourceStatus status) {
        int statusCount = 0;
        for (SingleRental rental : rentals) {
            if (rental.getStatus().equals(status)) {
                statusCount++;
            }
        }
        System.out.println("Ilość wypożyczeń o statusie " + status + ": " + statusCount + '\n');
    }
}
