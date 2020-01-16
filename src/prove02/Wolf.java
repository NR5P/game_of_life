package prove02;

import java.awt.*;
import java.util.Random;

public class Wolf extends Creature implements Movable, Aware, Aggressor, Spawner {
    Random _rand;
    String[] directions = {"above", "right", "below", "left"};
    private int preferredDirection = 0;
    private boolean canSpawn = false;

    public Wolf() {
        _rand = new Random();
        preferredDirection = _rand.nextInt(4);
        System.out.println(preferredDirection);
    }

    public Wolf(int locationX, int locationY) {
        this.setLocation(new Point(--locationX, locationY));
    }

    @Override
    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(5);
            canSpawn = !(target.isAlive());
        }
    }

    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        boolean creatureFound = false;

        // if creature is found in the preferred direction no need to change directions
        switch(preferredDirection) {
            case 0:
                if (above != null)
                    creatureFound = true;
                break;
            case 1:
                if (right != null)
                    creatureFound = true;
                break;
            case 2:
                if (below != null)
                    creatureFound = true;
                break;
            case 3:
                if (left != null)
                    creatureFound = true;
                break;
            default:
                break;
        }

        // if no creature found check other directions
        if (creatureFound == false) {
            if (above != null)
                preferredDirection = 0;
            else if (right != null)
                preferredDirection = 1;
            else if (below != null)
                preferredDirection = 2;
            else if (left != null)
                preferredDirection = 3;
        }
    }

    @Override
    Shape getShape() {
        return Shape.Square;
    }

    @Override
    Color getColor() {
        return Color.gray;
    }

    @Override
    Boolean isAlive() {
        return true;
    }

    @Override
    public void move() {
        switch(preferredDirection) {
            case 0:
                _location.x++;
                break;
            case 1:
                _location.x--;
                break;
            case 2:
                _location.y++;
                break;
            case 3:
                _location.y--;
                break;
            default:
                break;
        }
    }

    @Override
    public Creature spawnNewCreature() {
        if (canSpawn) {
            return new Wolf(this._location.x, this._location.y);
        }
        return null;
    }
}
