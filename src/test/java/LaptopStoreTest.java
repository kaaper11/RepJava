import org.junit.jupiter.api.Test;
import zl11.LaptopStore;
import zl11.OrderStats;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LaptopStoreTest {

    @Test
    void shouldBuyLaptopsWhenEnoughQuantityAvailable() {
        //given
        LaptopStore laptopStore = new LaptopStore();

        //when
        OrderStats orderStat = laptopStore.buyLaptop(5);

        //then
        assertThat(orderStat)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(new OrderStats(true, 5));
    }

    @Test
    void shouldReturnFalseWhenNotEnoughLaptopsAvailable() {
        //given
        LaptopStore laptopStore = new LaptopStore();

        //when
        OrderStats orderStat = laptopStore.buyLaptop(11);

        //then
        assertThat(orderStat)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(new OrderStats(false, 0));
    }

    @Test
    void shouldDecreaseAvailableQuantityAfterPurchase() {
        //given
        LaptopStore laptopStore = new LaptopStore();

        //when
        laptopStore.buyLaptop(5);

        //then
        assertThat(laptopStore.getAvailableQuantity()).isEqualTo(5);
    }

    @Test
    void shouldNotChangeQuantityWhenPurchaseFails() {
        //given
        LaptopStore laptopStore = new LaptopStore();

        //when
        laptopStore.buyLaptop(100);

        //then
        assertThat(laptopStore.getAvailableQuantity()).isEqualTo(10);
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsNegative() {
        //given
        LaptopStore laptopStore = new LaptopStore();

        //others
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> laptopStore.buyLaptop(-10));
    }
}
