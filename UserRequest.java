package elevatorsystem;

import elevatorsystem.enums.Direction;

public class UserRequest {
    public int floorNum;
    public Direction direction;

    public UserRequest(int floorNum, Direction direction) {
        this.floorNum = floorNum;
        this.direction = direction;
    }
}
