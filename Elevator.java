package elevatorsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

import elevatorsystem.enums.Direction;
import elevatorsystem.enums.Status;

public class Elevator {
    private int id;
    private int capacity;
    private int topFloor;
    private int bottomFloor;
    private int currentFloor;
    private Status status;
    private Direction direction;
    private List<UserRequest> requests;
    private final PriorityQueue<Integer> upQueue = new PriorityQueue<>(); // Min-heap
    private final PriorityQueue<Integer> downQueue = new PriorityQueue<>(Comparator.reverseOrder()); // Max-heap
    private ReentrantLock lock = new ReentrantLock();

    public Elevator(int id, int capacity, int topFloor, int bottomFloor, int currentFloor, Status status) {
        this.id = id;
        this.capacity = capacity;
        this.topFloor = topFloor;
        this.bottomFloor = bottomFloor;
        this.currentFloor = currentFloor;
        this.status = status;
    }

    public void addRequest(int floor, Direction direction) {
        lock.lock();
        try {
            if (floor == currentFloor) return; // already at the floor

        if (floor > currentFloor) {
            upQueue.offer(floor);
        } else {
            downQueue.offer(floor);
        }

        // Assign direction only if IDLE or NONE
        if (this.direction == Direction.NONE || this.status == Status.IDLE) {
            if (!upQueue.isEmpty() && currentFloor < upQueue.peek()) {
                this.direction = Direction.UP;
            } else if (!downQueue.isEmpty() && currentFloor > downQueue.peek()) {
                this.direction = Direction.DOWN;
            }
        }

        } finally {
            lock.unlock();
        }
    }

    public void processRequests() {
        lock.lock();
        try {
            
            while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            if (direction == Direction.UP) {
                while (!upQueue.isEmpty()) {
                    int targetFloor = upQueue.poll();
                    moveTo(targetFloor);
                }
                direction = !downQueue.isEmpty() ? Direction.DOWN : Direction.NONE;
            } else if (direction == Direction.DOWN) {
                while (!downQueue.isEmpty()) {
                    int targetFloor = downQueue.poll();
                    moveTo(targetFloor);
                }
                direction = !upQueue.isEmpty() ? Direction.UP : Direction.NONE;
            } else {
                break; // nothing to do
            }
        }

            status = Status.IDLE;
            direction = Direction.NONE;
        } finally {
            lock.unlock();
        }
    }

    public void moveTo(int targetFloor) {
        this.status = Status.MOVING;
        // this.direction = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;
        System.out.println("Elevator " + id + " moving from floor " + currentFloor + " to floor " + targetFloor);
        currentFloor = targetFloor;
    }

    public Status getStatus() {
        return status;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return status != Status.DOWN;
    }

}
