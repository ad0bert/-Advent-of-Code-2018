package main.day13;

import java.util.List;

public class Day13Cart {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, STRAIGHT
    }

    public Direction direction;
    public int x, y;
    public Direction nextDirection = Direction.LEFT;

    public Day13Cart(char c, int x, int y) {
        this.x = x;
        this.y = y;
        switch (c) {
        case '<':
            this.direction = Direction.LEFT;
            break;
        case '>':
            this.direction = Direction.RIGHT;
            break;
        case 'v':
            this.direction = Direction.DOWN;
            break;
        case '^':
            this.direction = Direction.UP;
            break;
        }
    }

    public char getCart() {
        char c = ' ';
        if (this.direction == Direction.UP) {
            c = '^';
        }
        if (this.direction == Direction.DOWN) {
            c = 'v';
        }
        if (this.direction == Direction.LEFT) {
            c = '<';
        }
        if (this.direction == Direction.RIGHT) {
            c = '>';
        }
        return c;
    }

    private void moveAlongDirection() {
        if (this.direction == Direction.UP) {
            this.x--;
        }
        if (this.direction == Direction.DOWN) {
            this.x++;
        }
        if (this.direction == Direction.LEFT) {
            this.y--;
        }
        if (this.direction == Direction.RIGHT) {
            this.y++;
        }
    }

    public void move(char curr) {
        switch (curr) {
        case '|':
        case '-':
            break;
        case '/':
            if (this.direction == Direction.UP) {
                this.direction = Direction.RIGHT;
                break;
            }
            if (this.direction == Direction.DOWN) {
                this.direction = Direction.LEFT;
                break;
            }
            if (this.direction == Direction.LEFT) {
                this.direction = Direction.DOWN;
                break;
            }
            if (this.direction == Direction.RIGHT) {
                this.direction = Direction.UP;
                break;
            }
            break;
        case '\\':
            if (this.direction == Direction.UP) {
                this.direction = Direction.LEFT;
                break;
            }
            if (this.direction == Direction.DOWN) {
                this.direction = Direction.RIGHT;
                break;
            }
            if (this.direction == Direction.LEFT) {
                this.direction = Direction.UP;
                break;
            }
            if (this.direction == Direction.RIGHT) {
                this.direction = Direction.DOWN;
                break;
            }
            break;
        case '+':
            if (this.nextDirection == Direction.LEFT) {
                this.nextDirection = Direction.STRAIGHT;
                if (this.direction == Direction.UP) {
                    this.direction = Direction.LEFT;
                    break;
                }
                if (this.direction == Direction.DOWN) {
                    this.direction = Direction.RIGHT;
                    break;
                }
                if (this.direction == Direction.LEFT) {
                    this.direction = Direction.DOWN;
                    break;
                }
                if (this.direction == Direction.RIGHT) {
                    this.direction = Direction.UP;
                    break;
                }

                break;
            }
            if (this.nextDirection == Direction.RIGHT) {
                this.nextDirection = Direction.LEFT;
                if (this.direction == Direction.UP) {
                    this.direction = Direction.RIGHT;
                    break;
                }
                if (this.direction == Direction.DOWN) {
                    this.direction = Direction.LEFT;
                    break;
                }
                if (this.direction == Direction.LEFT) {
                    this.direction = Direction.UP;
                    break;
                }
                if (this.direction == Direction.RIGHT) {
                    this.direction = Direction.DOWN;
                    break;
                }

                break;
            }
            if (this.nextDirection == Direction.STRAIGHT) {
                this.nextDirection = Direction.RIGHT;
                break;
            }
            break;
        }
        moveAlongDirection();
    }

    public boolean hasCollision(List<Day13Cart> carts) {
        for (Day13Cart cart : carts) {
            if ((cart.x == this.x) && (cart.y == this.y) && (this != cart)) {
                return true;
            }
        }
        return false;
    }

    public Day13Cart getCollision(List<Day13Cart> carts) {
        for (Day13Cart cart : carts) {
            if ((cart.x == this.x) && (cart.y == this.y) && (this != cart)) {
                return cart;
            }
        }
        return null;
    }

}
