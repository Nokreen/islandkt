package island;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PredatorTest {

    @Test
    void testPredatorCanEatHerbivore() {
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");

        assertTrue(wolf.canEat(rabbit));
    }

    @Test
    void testPredatorCannotEatPredator() {
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");
        Predator fox = new Predator("Fox", 8, 30, 2, "🦊");

        assertFalse(wolf.canEat(fox));
    }

    @Test
    void testPredatorCannotEatPlant() {
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");
        Plant plant = new Plant();

        assertFalse(wolf.canEat(plant));
    }

    @Test
    void testPredatorEatIncreasesSaturation() {
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");

        double initialSaturation = wolf.currentSaturation;
        boolean ate = wolf.eat(rabbit);

        if (ate) {
            assertTrue(wolf.currentSaturation > initialSaturation);
            assertFalse(rabbit.isAlive());
        }
    }

    @Test
    void testPredatorReproduceWithHighSaturation() {
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");
        wolf.currentSaturation = wolf.maxSaturation * 0.8; // выше 50%

        Animal baby = wolf.reproduce();

        // Может родиться, а может и нет (из-за вероятности)
        // Проверяем, что если родился — это хищник того же вида
        if (baby != null) {
            assertTrue(baby instanceof Predator);
            assertEquals("Wolf", baby.getType());
        }
    }

    @Test
    void testPredatorNoReproduceWithLowSaturation() {
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");
        wolf.currentSaturation = wolf.maxSaturation * 0.3; // ниже 50%

        Animal baby = wolf.reproduce();

        assertNull(baby);
    }

    @Test
    void testPredatorMove() {
        Island island = new Island();
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");

        int startX = 5;
        int startY = 5;

        // Просто проверяем, что move не выбрасывает исключение
        assertDoesNotThrow(() -> wolf.move(island, startX, startY));
    }
}