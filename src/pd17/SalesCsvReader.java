package pd17;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SalesCsvReader {
    public List<Product> read(Path path) throws IOException {
        return Files.lines(path)
                .skip(1)
                .map(this::createProduct)
                .filter(Objects::nonNull)
                .toList();
    }

    private Product createProduct(String line) {
        String[] fields = line.split(",");

        try {
            return new Product(fields[0],
                    fields[1],
                    fields[2],
                    Category.valueOf(fields[3].toUpperCase()),
                    Integer.parseInt(fields[4]),
                    new BigDecimal(fields[5]));
        } catch (NumberFormatException e) {
            System.err.println("Niepoprawne dane do parsowania!");
            return null;
        } catch (Exception e) {
            System.err.println("Nieprawidłowy wiersz: " + line);
            return null;
        }
    }
}
