# Elevator System Low-Level Design

Design and implement an Elevator System that can handle multiple requests, move between floors, and manage direction and state efficiently.

## Functional And Non Functional Requirement

### Functional Requirement

- The elevator system should consist of multiple elevators serving multiple floors.
- Each elevator should have a capacity limit and should not exceed it.
- Users should be able to request an elevator from any floor and select a destination floor.
- The elevator system should efficiently handle user requests and optimize the movement of elevators to minimize waiting time.
- The system should prioritize requests based on the direction of travel and the proximity of the elevators to the requested floor.
- The elevators should be able to handle multiple requests concurrently and process them in an optimal order.
- The system should ensure thread safety and prevent race conditions when multiple threads interact with the elevators.

### Non Functional Requirement

- The elevator system should be highly available to respond to requests with minimal downtime.
- The system should be capable of processing a large number of concurrent requests efficiently.
- The design should be scalable to support an increasing number of elevators and floors.
- The system should gracefully handle failures of an individual elevator without affecting overall operation.
- User request acknowledgment and response (like elevator arriving) should occur within acceptable time limits.

## Core Entities

- **Elevator:** Responsible for managing its state, direction and processing user request.
- **ElevatorManger:** Handles incoming requests and pass it to the elevator.
- **UserRequest:** It represents a users request to a particular floor and the direction.
- **Direction (enum):** UP, DOWN, NONE.
- **Status (enum):** IDLE, MOVING, DOWN

## Class Design

### 1. Elevator

- **Fields:** currentFloor, topFloor, bottomFloor, direction, status, etc.
- **Methods:** processRequests(), addRequests(int floor, Direction direction), getStatus(), getDirection(), etc.

### 2. ElevatorManager

- **Fields:** List<Elevators> elevators, ExecutorService executorService.
- **Methods:** handleRequest(UserRequest userRequest), assignLift(User Request).

### 3. UserRequest

- **Fields:** floorNumber, direction.

### 4. Direction, Status (enum)

- **Direction:** UP, DOWN, NONE
- **Status:** IDLE, MOVING, DOWN

---

### UML Class Diagram

![Elevator system UML class diagram](./static/Elevator%20System.png)
