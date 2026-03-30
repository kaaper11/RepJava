package pd07;

import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Resource harryPotter = new Book("12d", "Harry Potter", 10.0, ResourceType.BOOK, "J.K. Rowling");
        Resource wiedzmin3 = new ComputerGame("23cd", "Widzmin 3", 3.50, ResourceType.COMPUTER_GAME, "RPG");
        Resource hobbit = new Book("13d", "Hobbit", 12.3, ResourceType.BOOK, "J.R.R. Tolkien");
        Resource cs2 = new ComputerGame("24cd", "Cs2", 8.10, ResourceType.COMPUTER_GAME, "FPS");

        System.out.println("Ilość stworzonych zasobów: " + Resource.resourceCount);
        System.out.println();

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
        Collections.sort(resourcesSystem.getRentals(), Comparator.comparing(x -> x.getResource().getName()));
        System.out.println(resourcesSystem);
        System.out.println();

        resourcesSystem.allResourcesPrice();
        resourcesSystem.countByStatus(ResourceStatus.ACTIVE);
        resourcesSystem.countByStatus(ResourceStatus.COMPLETED);
    }
}
