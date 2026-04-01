package island;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    // Вспомогательный класс для тестирования абстрактного Animal
    private static class TestAnimal extends Animal {
        public TestAnimal(double weight, int maxPerCell, double maxSaturation, String emoji) {
            super(weight, maxPerCell, maxSaturation, emoji);
        }

        @Override
        public boolean canEat(Object food) { return false; }

        @Override
        public boolean eat(Object food) { return false; }

        @Override
        public void move(Island island, int x, int y) {}

        @Override
        public Animal reproduce() { return null; }

        @Override
        public String getType() { return "Test"; }
    }

    @Test
    void testAnimalInitialization() {
        Animal animal = new TestAnimal(10.0, 5, 100.0, "🐾");

        assertEquals(10.0, animal.getWeight());
        assertEquals(5, animal.maxPerCell);
        assertEquals(100.0, animal.maxSaturation);
        assertEquals(50.0, animal.currentSaturation);
        assertTrue(animal.isAlive());
        assertEquals("🐾", animal.getEmoji());
    }

    @Test
    void testDecreaseSaturation_Alive() {
        Animal animal = new TestAnimal(10.0, 5, 100.0, "🐾");
        animal.currentSaturation = 30.0;

        animal.decreaseSaturation();

        assertEquals(20.0, animal.currentSaturation);
        assertTrue(animal.isAlive());
    }

    @Test
    void testDecreaseSaturation_ExactlyZero() {
        Animal animal = new TestAnimal(10.0, 5, 100.0, "🐾");
        animal.currentSaturation = 10.0;

        animal.decreaseSaturation();

        assertEquals(0.0, animal.currentSaturation);
        assertFalse(animal.isAlive());
    }

    @Test
    void testDecreaseSaturation_BelowZero() {
        Animal animal = new TestAnimal(10.0, 5, 100.0, "🐾");
        animal.currentSaturation = 5.0;

        animal.decreaseSaturation();

        assertEquals(-5.0, animal.currentSaturation);
        assertFalse(animal.isAlive());
    }

    @Test
    void testDie() {
        Animal animal = new TestAnimal(10.0, 5, 100.0, "🐾");
        assertTrue(animal.isAlive());

        animal.die();

        assertFalse(animal.isAlive());
    }

    @Test
    void testDecreaseSaturation_AlreadyDead() {
        Animal animal = new TestAnimal(10.0, 5, 100.0, "🐾");
        animal.die();
        animal.currentSaturation = 50.0;

        animal.decreaseSaturation();

        assertFalse(animal.isAlive());
    }
}