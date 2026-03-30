package pd07;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        RentableResource harryPotter = new Book("12d", "Harry Potter", new BigDecimal("10.0"), "J.K. Rowling");
        RentableResource wiedzmin3 = new ComputerGame("23cd", "Widzmin 3", new BigDecimal("3.50"), "RPG");
        RentableResource hobbit = new Book("13d", "Hobbit", new BigDecimal("12.3"), "J.R.R. Tolkien");
        RentableResource cs2 = new ComputerGame("24cd", "Cs2", new BigDecimal("8.10"), "FPS");


        ResourcesSystem resourcesSystem = new ResourcesSystem();
        resourcesSystem.addResource(harryPotter, 10, ResourceStatus.COMPLETED);
        resourcesSystem.addResource(wiedzmin3, 50, ResourceStatus.ACTIVE);
        resourcesSystem.addResource(hobbit, 15, ResourceStatus.ACTIVE);
        resourcesSystem.addResource(cs2, 100, ResourceStatus.COMPLETED);

        System.out.println(resourcesSystem);
        System.out.println();

        System.out.println("Sortowanie po cenie bazowej");
        Collections.sort(resourcesSystem.getRentals());
        System.out.println(resourcesSystem);
        System.out.println();

        System.out.println("Sortowanie po nazwie");
        Collections.sort(resourcesSystem.getRentals(), Comparator.comparing(SingleRental::getResourceName));
        System.out.println(resourcesSystem);
        System.out.println();

        resourcesSystem.allResourcesPrice();
        resourcesSystem.countByStatus(ResourceStatus.ACTIVE);
        resourcesSystem.countByStatus(ResourceStatus.COMPLETED);
    }
}
