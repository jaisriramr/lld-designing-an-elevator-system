package elevatorsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import elevatorsystem.enums.Status;
public class ElevatorManager {
    private List<Elevator> elevators = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(8);
    
    public ElevatorManager(int numberOfLifts, int bottomFloor, int topFloor, int capacity) {
        for (int i = 0; i < numberOfLifts; i++) {
            elevators.add(new Elevator(i, capacity, topFloor, bottomFloor, topFloor, Status.IDLE));
        }
    }

    public void handleRequest(UserRequest userRequest) {
        Elevator assigned = assignLift(userRequest);
        if(assigned != null) {
            assigned.addRequest(userRequest.floorNum, userRequest.direction);
            
            executorService.submit(assigned::processRequests);
        }else {
            System.out.println("No lift available for request to floor " + userRequest.floorNum);
        }
    }

    private Elevator assignLift(UserRequest userRequest) {
        return elevators.stream().filter(Elevator::isAvailable).min(Comparator.comparingInt(l -> Math.abs(l.getCurrentFloor() - userRequest.floorNum))).orElse(null);
    }

    
}
