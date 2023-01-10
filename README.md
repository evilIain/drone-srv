## Drones


### Introduction

Start application

```
mvn clean install -DskipTests
```

Run Tests:

1. Install and start Docker (any version, was tested on 4.15.0)
2. Run tests

To start application:

1. Compose docker image from docker-compose.yml.
    Go to directory src/test/resources and run command ```docker-compose up -d```
2. Start application with "local" profile active.
   ```-Dspring.profiles.active=local``` or simply point local in latest Intellij IDEA versions in Spring Boot application start.

---

### How to use

Method ```registerDrone POST "/api/v1.0/drone"```:

Input:

```
{
    "serialNumber": "ABCD1234",
    "model": "Lightweight",
    "weightLimit": 500,
    "batteryCapacity": 100
}
```

Output:

```
{
    "id": "fd6bd5c9-0f21-49ce-9a29-b65aeec3314c",
    "serialNumber": "ABCD1234",
    "model": "Lightweight",
    "weightLimit": 500,
    "batteryCapacity": 100,
    "state": "IDLE"
}
```

---

Method ```loadMedications POST "/api/v1.0/drone/{droneId}"```:

Input: List of Medication object: name, weight, code, image. 
All validation constraints are checked and will throw exception if violated.

Input:

```
{
   "medications": [
       {
           "name": "Bandage",
           "weight": 90,
           "code": "12345_ADS"
       }
   ]
}
```

Output:

```
{
    {
    "id": "fd6bd5c9-0f21-49ce-9a29-b65aeec3314c",
    "serialNumber": "ABCD1234",
    "model": "Lightweight",
    "weightLimit": 500,
    "batteryCapacity": 100,
    "state": "LOADING",
    "medications": [
        {
            "name": "Bandage",
            "weight": 90,
            "code": "12345_ADS"
        }
    ]
}
}
```

---

Method ```getLoadedMedications GET "/api/v1.0/drone/{droneId}"```:

Output:

```
{
    "medications": [
        {
            "name": "Bandage",
            "weight": 90,
            "code": "12345ads"
        },
        {
            "name": "Bandage",
            "weight": 90,
            "code": "12345ads"
        },
        {
            "name": "Bandage",
            "weight": 90,
            "code": "12345_ADS"
        }
    ]
}
```

---

---

Method ```getAvailableDrones GET "/api/v1.0/drones"```

Prints drones that are in IDLE or LOADING state and have more than 25% battery charge.

Output:

```
[
    {
        "id": "b52e7b4e-5925-46eb-b332-d5c4ef70a800",
        "serialNumber": "ABCD1234",
        "weightLimit": 400,
        "batteryCapacity": 100,
        "state": "LOADING"
    },
    {
        "id": "fd6bd5c9-0f21-49ce-9a29-b65aeec3314c",
        "serialNumber": "ABCD1234",
        "weightLimit": 500,
        "batteryCapacity": 100,
        "state": "LOADING"
    }
]
```

---

Method ```getDroneBatteryCapacity GET "/api/v1.0/drone/{droneId}/battery"```

Prints droneId and batteryCapacity.

Output:

```
{
    "id": "fd6bd5c9-0f21-49ce-9a29-b65aeec3314c",
    "batteryCapacity": 100
}
```

---

Scheduler for checking battery capacity runs every hour and scans all drones. Located in service/scheduler directory.
Prints log to file and duplicates it to console. 
If needed print to file can be turned off and console print be used in external log viewer like Kibana.

---

### Tests

There are positive and negative test scenarios, including validation violation.
To run tests Testcontainers were used.
