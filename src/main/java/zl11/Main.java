package zl11;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        LaptopStore laptopStore = new LaptopStore();
        List<Order> orderList = List.of(new Order("name1", 2),
                new Order("name2", 3),
                new Order("name3", 4),
                new Order("name4", 5));

        List<CompletableFuture<OrderStats>> futures = orderList.stream()
                .map(order -> CompletableFuture.supplyAsync(() ->
                        laptopStore.buyLaptop(order.getQuantity()))).toList();

        futures.forEach(future -> {
            OrderStats orderStats = future.join();
            System.out.println(orderStats);
        });
    }
}
