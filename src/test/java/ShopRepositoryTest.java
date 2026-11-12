import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    // --- ТЕСТЫ ДЛЯ ЗАДАНИЯ 1 (Удаление) ---

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Хлеб", 50);
        repo.add(product);

        repo.removeById(1);

        Assertions.assertArrayEquals(new Product[0], repo.findAll());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExisting() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Хлеб", 50));

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(2); // ID 2 не существует
        });
    }

    // --- ТЕСТЫ ДЛЯ ЗАДАНИЯ 2* (Добавление) ---

    @Test
    public void shouldAddNewProduct() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Хлеб", 50);

        repo.add(product);

        Assertions.assertArrayEquals(new Product[]{product}, repo.findAll());
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenAddingDuplicate() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Хлеб", 50);
        repo.add(product); // Добавили первый раз

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(new Product(1, "Молоко", 80)); // Пытаемся добавить с тем же ID
        });
    }
}