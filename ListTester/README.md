# LinkedList Tester

A Java application for testing and benchmarking different LinkedList implementations. This project allows you to compare the performance of various linked list data structures and visualize the results.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [LinkedList Implementations](#linkedlist-implementations)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Benchmarking](#benchmarking)
- [Visualization](#visualization)
- [Contributing](#contributing)

## Overview

This project provides a framework for testing and benchmarking different LinkedList implementations. It follows the Model-View-Controller (MVC) architecture pattern and includes a console-based user interface for interacting with the linked lists and running benchmarks.

The main goal is to compare the performance of different linked list implementations across various operations and data sizes, helping users understand the trade-offs between different data structure designs.

## Features

- **Multiple LinkedList Implementations**: Includes several linked list variants (with/without tail, singly/doubly linked)
- **Interactive Console Interface**: Manipulate linked lists through a user-friendly console menu
- **Comprehensive Benchmarking**: Test performance across different operations and data sizes
- **Data Export**: Export benchmark results to CSV for further analysis
- **Visualization**: Generate time diagrams to visualize performance characteristics
- **Extensible Design**: Easily add new linked list implementations

## Project Structure

The project follows the MVC architecture pattern:

```
src/
├── main/
│   ├── java/
│   │   └── listTesterProgram/
│   │       ├── controller/        # Application controllers
│   │       │   └── benchmark/     # Benchmarking functionality
│   │       ├── model/             # Data models and implementations
│   │       │   ├── abstractModels/# Interfaces and abstract classes
│   │       │   ├── concrete/      # Concrete implementations
│   │       │   ├── creators/      # Factory pattern implementations
│   │       │   └── exceptions/    # Custom exceptions
│   │       └── view/              # User interface components
│   └── python/                    # Python scripts for visualization
│       └── graphicsResults/       # Generated visualizations
└── test/                          # Unit tests
```

## LinkedList Implementations

The project includes the following LinkedList implementations:

1. **LinkedLinkedListWithoutTail**: A singly linked list without a tail pointer
2. **LinkedLinkedListWithTail**: A singly linked list with a tail pointer
3. **DoubleLinkedLinkedListWithoutTail**: A doubly linked list without a tail pointer
4. **DoubleLinkedLinkedListWithTail**: A doubly linked list with a tail pointer

Each implementation supports the following operations:
- `pushFront(value)`: Add an element to the front of the list
- `pushBack(value)`: Add an element to the back of the list
- `popFront()`: Remove and return the front element
- `popBack()`: Remove and return the back element
- `find(value)`: Find a node containing the specified value
- `erase(value)`: Remove the first occurrence of the specified value
- `addAfter(node, value)`: Add a value after the specified node
- `addBefore(node, value)`: Add a value before the specified node

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Python 3.x (for visualization)
- Python libraries: pandas, matplotlib, seaborn, numpy

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/ListTester.git
   cd ListTester
   ```

2. Compile the Java code:
   ```
   javac -d bin src/main/java/listTesterProgram/**/*.java
   ```

3. Install Python dependencies (for visualization):
   ```
   pip install pandas matplotlib seaborn numpy
   ```

## Usage

### Running the Application

Run the main application:

```
java -cp bin listTesterProgram.controller.ListTesterApp
```

### Main Menu

The application provides the following options:

1. **Select List Type**: Choose which LinkedList implementation to use
2. **Manipulate List**: Perform operations on the selected list
3. **Run Benchmarks**: Benchmark the performance of different implementations
4. **Exit**: Exit the application

### List Operations

When manipulating a list, you can:

1. Push Front: Add an element to the front
2. Push Back: Add an element to the back
3. Pop Front: Remove the front element
4. Pop Back: Remove the back element
5. Find: Find an element
6. Erase: Remove an element
7. Add After: Add after a specific node
8. Add Before: Add before a specific node
9. Clear List: Remove all elements
10. Back to Main Menu: Return to the main menu

## Benchmarking

The benchmarking functionality allows you to:

1. **Run Benchmarks**: Test all operations across different data sizes
2. **Export Results to CSV**: Save benchmark results for further analysis
3. **Find Fastest Implementations**: Identify which implementation performs best for each operation
4. **Back to Main Menu**: Return to the main menu

### Benchmark Parameters

- **Data Sizes**: 100, 1,000, 10,000, and 100,000 elements
- **Warmup Iterations**: 5 iterations to warm up the JVM
- **Benchmark Iterations**: 10 iterations for each operation

## Visualization

The project includes Python scripts to visualize benchmark results:

1. Run benchmarks and export results to CSV
2. Execute the visualization script:
   ```
   python src/main/python/graphicsResults/visualize_results.ipynb
   ```

The script generates time diagrams showing:
- Performance of each operation across different list types
- Performance of each list type across different operations
- Time complexity analysis (log-log plots)

Visualizations are saved in the `src/main/python/graphicsResults` directory.

## Contributing

Contributions are welcome! Here are some ways you can contribute:

1. Add new LinkedList implementations
2. Improve existing implementations
3. Enhance the benchmarking methodology
4. Add new visualization options
5. Improve the user interface

Please feel free to submit pull requests or open issues to discuss potential improvements.

## License

This project is licensed under the GPL License - see the LICENSE file for details.
