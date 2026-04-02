package pd07;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;

@ToString
@Getter
public class ResourcesSystem {
    private final ArrayList<SingleRental> rentals = new ArrayList<>();

    public void addResource(RentableResource resource, int rentDays, ResourceStatus status) {
        rentals.add(new SingleRental(resource, rentDays, status));
    }

    public void allResourcesPrice() {
        BigDecimal fullPrice = new BigDecimal(0);
        for (SingleRental rental : rentals) {
            fullPrice = fullPrice.add(rental.getResource().calculatePrice(rental.getRentDays()));
        }
        System.out.println("Łączny koszt wszytskich wypożyczeń: " + fullPrice.setScale(2) + " zł\n");
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
