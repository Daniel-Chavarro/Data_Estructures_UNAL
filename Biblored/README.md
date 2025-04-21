# Biblored Library Management System

## Overview

Biblored is a comprehensive library management system for the Bogotá District Network of Public Libraries. The system allows for the management of libraries, users, and various types of materials (books, audiovisual materials, etc.). It implements the MVC (Model-View-Controller) architectural pattern and the DAO (Data Access Object) pattern for data management.

## Project Structure

The project follows a standard Maven structure and is organized according to the MVC pattern:

```
Biblored/
├── src/
│   └── main/
│       └── java/
│           └── biblored/
│               ├── controller/
│               │   └── Controller.java
│               ├── model/
│               │   ├── DAO/
│               │   │   ├── InterfaceDAO.java
│               │   │   ├── LibraryDAO.java
│               │   │   ├── MaterialDAO.java
│               │   │   └── UserDAO.java
│               │   ├── generic/
│               │   │   ├── Address.java
│               │   │   ├── AudioVisual.java
│               │   │   ├── Book.java
│               │   │   ├── CompactDisk.java
│               │   │   ├── Documentary.java
│               │   │   ├── Film.java
│               │   │   ├── Library.java
│               │   │   ├── Material.java
│               │   │   ├── Phone.java
│               │   │   └── User.java
│               │   ├── LibraryNetwork.java
│               │   └── Status.java
│               └── view/
│                   └── ConsoleView.java
└── pom.xml
```

## Design Patterns

### MVC Pattern

- **Model**: Represents the data and business logic of the application
  - `LibraryNetwork`: Central model class that manages all data
  - `DAO` classes: Handle data persistence
  - `generic` classes: Define the domain entities

- **View**: Handles the user interface
  - `ConsoleView`: Provides a console-based user interface

- **Controller**: Mediates between the Model and View
  - `Controller`: Processes user input and updates the model and view accordingly

### DAO Pattern

The Data Access Object pattern is used to separate the data persistence logic from the business logic:

- `InterfaceDAO`: Defines the standard operations for data access
- `LibraryDAO`: Handles library data
- `MaterialDAO`: Handles material data
- `UserDAO`: Handles user data

## Key Features

1. **Library Management**
   - Add, view, update, and delete libraries
   - List all libraries

2. **User Management**
   - Register, view, update, and delete users
   - List all users

3. **Material Management**
   - Add different types of materials (books, films, documentaries, etc.)
   - View, update, and delete materials
   - List all materials by type

4. **Borrowing and Returning**
   - Borrow materials from libraries
   - Return materials to libraries
   - View borrowed materials by library

## Class Hierarchy

### Material Hierarchy

```
Material
├── Book
└── AudioVisual
    ├── Film
    ├── Documentary
    └── CompactDisk
```

## Key Relationships

- **User-Material**: A user can borrow one material at a time
- **Library-Material**: A library owns multiple materials
- **Library-Borrowed Materials**: A library tracks which materials are borrowed

## Class Diagram
[![](https://img.plantuml.biz/plantuml/svg/lLXVRzis47_Nfz3RrAtf1p26e4wS3GQIhaYwFEoDbguELueqIAf1blMxZqUHzkbesS265GXYwlUxF_SxaHIbzyuBwuTUrLToeuo5hgf-g5VmMMgefVPWFuiMNBKVrkiyMYr_-_dJPVrQrVLFekiQlwW_lLdK6sCK28se1T4ribjWeC1yojXIgIPSBwqLBxVI-H06gM7N2G_PuMrT1kn0mS62SDz8rQ27tbNultxmmWykbhXyNAvlbrUtDs5yjtvuM7_uDOpkRnvkxXzlLfC0hHBE1StnjxwL6oliomVmpyP-YR4kr8Y53YmIZsDa1WSsmh_7GSH2aLyYTXVaMIbKmiNGIVDLkW6HaluiLNyCRqs_4wrVINSqQcVQeGVjiQQInZkLAcwIYhq4skjeY1eQL3JAAVYh8An1TOSiXSto8B8XWazu_19qDoZUdJ2wFZJ1DQGZftnMXyuqf4jp7blmgJ5amYKayTWLGk9bOh0rXCaP4akRCck4VPXdIGFcZQZY6QCiKeARhicaz998PHO8NHAzZCvfBalHSvP4-Rm9bpzhoNiWOTRqMvLM-xNHtXgbm8uhlJNQ6GKB973mAE4PSJNP5HPiby3KSMF5V5-DxEB2b6wdnCkTaFeEz42e52rGMcm1Y-K6k0ZdsJxfcBVO66lDyptumHR9lmP9Lv67BdV4NRqaHQM2pAp02wbSiOqG1xBPi8Gi1c5R2y93BWIPF2ObP2ZxvmuSZ4d4yRboJ0yeE4eewVnIgVtAe9o5RM31ukpdgX0h9EK2a2opKvG8f97e3inBH7IIAstB3C8LnI2oDp26h2qkaFMz59aDkD22eI8ovSefUypBncbSC7GDvwKwBcBN2gsnXm_Z09VdpafS9iw9BJJz-1iCV82tAMBQG4PihNU3RtOszCOJkt6GBF5ej0KxOvhOGvtBhhFWNAhHMu2zEI9ww3TWAT9A_qAl_vAxQzF1nCGCsbjYbIK0-6PIhzl3D3KN_o4MmJqpJ4ladjUXMEQNG2A3-Vmn2YhSCiu4iuIZzC8vuwmrnRJv-6GqrA-aYxovmeBW6BzBj9IZMYXNvc6QbfYnZ488MjD_ltsq8Na_7rkIZWzHikYdRHddyI9FwBXw-GFbr2DwtM6561tBYzSf7LCZUjw91DAIfZwf9dHBm-IN2Lxzr9qz64-UNDYELJwJtjQJhPoUmzYULleUEKGgTRuwVhx1r6TlAFH2AWhiWkkpiTq4m-Mo65VD-Ld0vNo7Et-3AfXJHeb2xlOnQMMUX63TuFxO47IEW-YRGTgo_tMMibFJ-Pb9mfrxkcRc_BOm-2TZAT85r_jqV8BWU6jQeLO1H98V8oCcz7OGsqbO5z_f4S7Vxx63H1tSAyDK9VTSKRrO8LMCGRZ5R8EcSSc9DGrJkBatU-QUkiea4OBrdSaQEn-yoJrmUtXwVuhfWuVJ-lLm7TyA7-DR8ONnm4gloMcLmkIeIc3oJOP-hv7WvbvmHTULJPl_baNxphzebS7vTwtvH6U-SP4zV2zj21UkU2lFckZpPbvDDE1Ioe1S1mz-NW7VG_Og-hF715HJxbidLMMZKbcPe5HN9liNto8jAE6bqUv9xboz4GwwskZQFmLgKE3GTAsVGdWlTCligxXMBYx-lZn88Sk5CN75H8ZOL6JTb2HPFIKPAyiVVMM_Kn3ZnEegii3UlGkEzADmbUTdoP3xNhAO4IioJc8Irhl6RFw4rhlGREU5zZA4wi95tPe0sA7rnhwfoEUR7oyk9c-2d6FVTIjsZIP4vJ4QTSvnL6dcaSZFRmRcALd7vb53WiHp0xaUTrXistAxjR0Lfzecmf_tLcu63wxMnjUjXQCzZF4DqL0lQdwGgyOvlWFTWMvVvfAI53tu9zCvUel25BYiSniuZbKKO4xFYTmiZkUMiHj4GntZzhU3J6MAPj-2oS5AedeFkXjwzGy0)](https://editor.plantuml.com/uml/lLXVRzis47_Nfz3RrAtf1p26e4wS3GQIhaYwFEoDbguELueqIAf1blMxZqUHzkbesS265GXYwlUxF_SxaHIbzyuBwuTUrLToeuo5hgf-g5VmMMgefVPWFuiMNBKVrkiyMYr_-_dJPVrQrVLFekiQlwW_lLdK6sCK28se1T4ribjWeC1yojXIgIPSBwqLBxVI-H06gM7N2G_PuMrT1kn0mS62SDz8rQ27tbNultxmmWykbhXyNAvlbrUtDs5yjtvuM7_uDOpkRnvkxXzlLfC0hHBE1StnjxwL6oliomVmpyP-YR4kr8Y53YmIZsDa1WSsmh_7GSH2aLyYTXVaMIbKmiNGIVDLkW6HaluiLNyCRqs_4wrVINSqQcVQeGVjiQQInZkLAcwIYhq4skjeY1eQL3JAAVYh8An1TOSiXSto8B8XWazu_19qDoZUdJ2wFZJ1DQGZftnMXyuqf4jp7blmgJ5amYKayTWLGk9bOh0rXCaP4akRCck4VPXdIGFcZQZY6QCiKeARhicaz998PHO8NHAzZCvfBalHSvP4-Rm9bpzhoNiWOTRqMvLM-xNHtXgbm8uhlJNQ6GKB973mAE4PSJNP5HPiby3KSMF5V5-DxEB2b6wdnCkTaFeEz42e52rGMcm1Y-K6k0ZdsJxfcBVO66lDyptumHR9lmP9Lv67BdV4NRqaHQM2pAp02wbSiOqG1xBPi8Gi1c5R2y93BWIPF2ObP2ZxvmuSZ4d4yRboJ0yeE4eewVnIgVtAe9o5RM31ukpdgX0h9EK2a2opKvG8f97e3inBH7IIAstB3C8LnI2oDp26h2qkaFMz59aDkD22eI8ovSefUypBncbSC7GDvwKwBcBN2gsnXm_Z09VdpafS9iw9BJJz-1iCV82tAMBQG4PihNU3RtOszCOJkt6GBF5ej0KxOvhOGvtBhhFWNAhHMu2zEI9ww3TWAT9A_qAl_vAxQzF1nCGCsbjYbIK0-6PIhzl3D3KN_o4MmJqpJ4ladjUXMEQNG2A3-Vmn2YhSCiu4iuIZzC8vuwmrnRJv-6GqrA-aYxovmeBW6BzBj9IZMYXNvc6QbfYnZ488MjD_ltsq8Na_7rkIZWzHikYdRHddyI9FwBXw-GFbr2DwtM6561tBYzSf7LCZUjw91DAIfZwf9dHBm-IN2Lxzr9qz64-UNDYELJwJtjQJhPoUmzYULleUEKGgTRuwVhx1r6TlAFH2AWhiWkkpiTq4m-Mo65VD-Ld0vNo7Et-3AfXJHeb2xlOnQMMUX63TuFxO47IEW-YRGTgo_tMMibFJ-Pb9mfrxkcRc_BOm-2TZAT85r_jqV8BWU6jQeLO1H98V8oCcz7OGsqbO5z_f4S7Vxx63H1tSAyDK9VTSKRrO8LMCGRZ5R8EcSSc9DGrJkBatU-QUkiea4OBrdSaQEn-yoJrmUtXwVuhfWuVJ-lLm7TyA7-DR8ONnm4gloMcLmkIeIc3oJOP-hv7WvbvmHTULJPl_baNxphzebS7vTwtvH6U-SP4zV2zj21UkU2lFckZpPbvDDE1Ioe1S1mz-NW7VG_Og-hF715HJxbidLMMZKbcPe5HN9liNto8jAE6bqUv9xboz4GwwskZQFmLgKE3GTAsVGdWlTCligxXMBYx-lZn88Sk5CN75H8ZOL6JTb2HPFIKPAyiVVMM_Kn3ZnEegii3UlGkEzADmbUTdoP3xNhAO4IioJc8Irhl6RFw4rhlGREU5zZA4wi95tPe0sA7rnhwfoEUR7oyk9c-2d6FVTIjsZIP4vJ4QTSvnL6dcaSZFRmRcALd7vb53WiHp0xaUTrXistAxjR0Lfzecmf_tLcu63wxMnjUjXQCzZF4DqL0lQdwGgyOvlWFTWMvVvfAI53tu9zCvUel25BYiSniuZbKKO4xFYTmiZkUMiHj4GntZzhU3J6MAPj-2oS5AedeFkXjwzGy0)
This is not a final design due to the insane amount of methods in a single class.

## How to Run

1. Compile the project:
   ```
   javac -d target/classes src/main/java/biblored/model/Status.java src/main/java/biblored/model/generic/*.java src/main/java/biblored/model/DAO/*.java src/main/java/biblored/model/LibraryNetwork.java src/main/java/biblored/view/ConsoleView.java src/main/java/biblored/controller/Controller.java
   ```

2. Run the application:
   ```
   java -cp target/classes biblored.controller.Controller
   ```


```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

Make sure to use Java 11 for compilation and execution to maintain compatibility.
