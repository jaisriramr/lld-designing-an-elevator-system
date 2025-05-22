package elevatorsystem;

import elevatorsystem.enums.Direction;

public class ElevatorDemo {
    public static void main(String[] args) {
        ElevatorManager manager = new ElevatorManager(5, 0, 10, 5);

        manager.handleRequest(new UserRequest(3, Direction.UP));
        manager.handleRequest(new UserRequest(7, Direction.DOWN));
        manager.handleRequest(new UserRequest(1, Direction.UP));
        manager.handleRequest(new UserRequest(5, Direction.DOWN));
        manager.handleRequest(new UserRequest(9, Direction.UP));
    }
}
