import org.junit.Test;

import static org.junit.Assert.assertThrows;

import static org.testng.AssertJUnit.assertEquals;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();

        Product first = new Product(1, "Сыр", 250);
        Product second = new Product(2, "Энергетический напиток Берн", 118);

        repo.add(first);
        repo.add(second);

        repo.removeById(1);

        Product[] actual = repo.findAll();

        assertEquals(1, actual.length);
        assertEquals(second, actual[0]);
    }

    @Test
    public void shouldThrowWhenRemovingMissingProduct() {
        ShopRepository repo = new ShopRepository();

        repo.add(new Product(1, "Сыр", 250));

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repo.removeById(10)
        );

        assertEquals(
                "Element with id: 10 not found",
                exception.getMessage()
        );
    }
}