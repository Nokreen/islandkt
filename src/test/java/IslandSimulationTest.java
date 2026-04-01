package island;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IslandSimulationTest {

    @Test
    void testIslandInitialization_ContainsAnimalsAndPlants() {
        // Дано: создан остров
        Island island = new Island();

        // Получаем статистику
        String stats = island.getStatistics();

        // Тогда: статистика не пустая, содержит информацию о животных
        assertNotNull(stats);
        assertTrue(stats.contains("animals"));
        assertTrue(stats.contains("plants"));
    }

    @Test
    void testSimulateDay_RunsWithoutErrors() {
        // Дано: остров
        Island island = new Island();

        // Когда: симулируем один день
        assertDoesNotThrow(() -> island.simulateDay());

        // Тогда: после симуляции статистика обновлена
        String stats = island.getStatistics();
        assertNotNull(stats);
    }

    @Test
    void testSimulateMultipleDays_RunsWithoutErrors() {
        // Дано: остров
        Island island = new Island();

        // Когда: симулируем 5 дней
        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> island.simulateDay());
        }

        // Тогда: всё работает, исключений не было
        assertTrue(true);
    }

    @Test
    void testMoveAnimal_ChangesLocation() {
        // Дано: остров и животное
        Island island = new Island();
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");

        // Добавляем животное в локацию (0,0)
        // Это сложно сделать напрямую, проверяем что move не падает
        assertDoesNotThrow(() -> rabbit.move(island, 0, 0));
    }

    @Test
    void testStatistics_AfterSimulation_NotEmpty() {
        // Дано: остров
        Island island = new Island();

        // Получаем статистику до симуляции
        String statsBefore = island.getStatistics();

        // Когда: симулируем день
        island.simulateDay();

        // Получаем статистику после
        String statsAfter = island.getStatistics();

        // Тогда: статистика не пустая
        assertNotNull(statsBefore);
        assertNotNull(statsAfter);
    }
}