package prove02;
import java.awt.Color;

public class Zombie extends Creature implements Movable, Aggressor  {
    public Zombie() {
        _health = 1;
    }

    @Override
    Shape getShape() {
        return Shape.Square;
    }

    @Override
    Color getColor() {
        return Color.BLUE;
    }

    @Override
    Boolean isAlive() {
        return _health > 0;
    }

    @Override
    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(10);
        }
    }

    @Override
    public void move() {
        _location.x++;
    }
}
