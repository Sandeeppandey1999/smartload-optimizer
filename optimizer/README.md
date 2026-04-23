# SmartLoad Optimization API

## Tech Stack
- Java 17
- Spring Boot
- REST API
- Docker

---
## **Approach**
Solved using Bitmask (2^n subsets)
Applied multi-constraint filtering
Used pruning for performance optimization
Ensured:
weight constraint
volume constraint
route compatibility
hazmat isolation
⚡ Complexity

Time Complexity: O(2^n × n)
Handles up to 22 orders efficiently (< 1 sec)

 **Features**
Stateless API (no DB)
Integer-based money handling (cents)
Input validation
Edge case handling

## How to Run

```bash
git clone https://github.com/Sandeeppandey1999/smartload-optimizer.git
cd smartload-optimizer

docker compose up --build
