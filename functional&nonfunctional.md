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