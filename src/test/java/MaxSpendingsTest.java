import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class MaxSpendingsTest {
    private MaxSpendings maxSpendings;

    @BeforeEach
    void setUp() throws IOException {
        maxSpendings = new MaxSpendings();
        maxSpendings.add("булка", 200);
        maxSpendings.add("сухарики", 100);
        maxSpendings.add("стул", 4_000);
    }

    @Test
    @DisplayName("Тест выбора максимальных затрат")
    void pickMax() {
        String expected = "{\"sum\":4000,\"Max category\":\"другое\"}";
        Assertions.assertEquals(expected, maxSpendings.pickMax());
    }

    @Test
    @DisplayName("Тест чтения файла")
    void readFile() {
        File file = new File("test.tsv");
        String expectedResult = "{стол=мебель, часы=аксессуар, другое=другое}";
        Assertions.assertEquals(expectedResult, MaxSpendings.readFile(file).toString());
    }

}
