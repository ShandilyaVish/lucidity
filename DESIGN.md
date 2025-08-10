# Delivery Optimization System - Simple Design Guide

## What This System Does
This system helps delivery executives find the fastest way to deliver multiple food orders. Think of it like a smart GPS that plans the best route when you have to visit multiple places.

## How It Works (High Level)

### Main Problem
A delivery person named Rohan gets 2 orders:
- Order 1: Pick up from Spice Garden → Deliver to Priya
- Order 2: Pick up from Pizza Corner → Deliver to Arjun

**Question:** What's the fastest way to complete both deliveries?

### Solution Approach
1. **Calculate all distances** between locations using GPS coordinates
2. **Consider restaurant prep times** (food might not be ready when you arrive)
3. **Try different order sequences** and pick the fastest one
4. **Return the optimal plan** with step-by-step instructions

## System Components (Simple View)

```
📱 DriverRunner (Main App)
    ↓
🧠 BatchOptimizationService (Brain)
    ↓
📐 DistanceService (Distance Calculator)
    ↓
🍽️ RestaurantService (Restaurant Data)
```

### What Each Part Does:
- **DriverRunner**: Shows the demo and results
- **BatchOptimizationService**: Finds the best delivery route
- **DistanceService**: Calculates distances between any two places
- **RestaurantService**: Stores restaurant info (location, prep time)

## Core Algorithm (Simple Explanation)

### Step 1: List All Options
For each undelivered order, calculate:
- Time to travel to restaurant
- Time to wait (if food isn't ready)
- Time to travel to customer
- **Total time = travel + wait + delivery**

### Step 2: Pick the Best Option
Choose the order that results in the **shortest total completion time** for all deliveries.

### Step 3: Repeat
Keep assigning orders until all are delivered.

### Example:
```
Rohan's location: (12.934, 77.548)

Option A: Deliver to Priya first
- Go to Spice Garden: 6 minutes
- Wait for food: 12 minutes (18min prep - 6min travel)
- Deliver to Priya: 7 minutes
- Then deliver Arjun's order: 67 minutes
- TOTAL: 25 + 87 = 112 minutes

Option B: Deliver to Arjun first
- Go to Pizza Corner: 34 minutes
- Wait for food: 0 minutes (25min prep < 34min travel)
- Deliver to Arjun: 35 minutes
- Then deliver Priya's order: 43 minutes
- TOTAL: 69 + 43 = 112 minutes

Both options take the same time, so we pick Option A (first in the list).
```

## Key Classes (Simple View)

### GeoLocation
```java
class GeoLocation {
    double latitude;   // Like 12.934521
    double longitude;  // Like 77.548392
}
```
**Purpose:** Store GPS coordinates of any location.

### Order
```java
class Order {
    String customerName;      // "Priya"
    GeoLocation customerLocation;  // Where to deliver
    String restaurantId;      // Which restaurant
}
```
**Purpose:** Store one customer's order details.

### Restaurant
```java
class Restaurant {
    String name;              // "Spice Garden"
    GeoLocation location;     // Restaurant's GPS location
    int prepTimeMinutes;      // How long to cook food
}
```
**Purpose:** Store restaurant info.

### DeliveryPerson
```java
class DeliveryPerson {
    String name;              // "Rohan"
    GeoLocation currentLocation;  // Where they are now
}
```
**Purpose:** Store delivery person details.

## Distance Calculation

We use the **Haversine Formula** to calculate distances between GPS coordinates:

```java
// Input: Two GPS locations
// Output: Distance in kilometers
double distance = calculateDistance(location1, location2);

// Convert to travel time (assuming 20 km/hour speed)
double travelTime = distance * 60 / 20;  // minutes
``` 