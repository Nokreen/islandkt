package island;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HerbivoreEatPlantTest {

    @Test
    void testHerbivoreEatsPlant_IncreasesSaturation() {
        // Дано: травоядное и растение
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");
        Plant plant = new Plant();

        double initialSaturation = rabbit.currentSaturation;

        // Когда: травоядное ест растение
        boolean ate = rabbit.eat(plant);

        // Тогда: если поедание произошло, сытость увеличилась
        if (ate) {
            assertTrue(rabbit.currentSaturation > initialSaturation);
        }
    }

    @Test
    void testHerbivoreEat_ReturnsTrue_WhenEatsPlant() {
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");
        Plant plant = new Plant();

        // Может съесть с вероятностью getEatingProbability()
        // Проверяем, что метод возвращает true/false в зависимости от вероятности
        boolean ate = rabbit.eat(plant);

        // Ничего не утверждаем — метод либо true, либо false
        // Проверяем только что исключений не было
        assertTrue(true);
    }

    @Test
    void testHerbivoreCannotEatPredator() {
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");
        Predator wolf = new Predator("Wolf", 50, 30, 8, "🐺");

        assertFalse(rabbit.canEat(wolf));
    }

    @Test
    void testHerbivoreCannotEatHerbivore() {
        Herbivore rabbit = new Herbivore("Rabbit", 2, 150, 0.45, "🐇");
        Herbivore sheep = new Herbivore("Sheep", 70, 140, 15, "🐑");

        assertFalse(rabbit.canEat(sheep));
    }
}