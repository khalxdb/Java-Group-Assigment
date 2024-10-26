# Design Documentation: Music Library Management System
## Project Overview
The **Music Library** Management System is a Java-based application that provides an interactive command-line interface for users to manage their music collections efficiently. It allows users to create playlists, add songs to playlists, and manage track queues with options for shuffling and navigating through songs.

## **Functional Requirements**
### **1. Song and Playlist Management**

- **Unique Song Library**:
  - The system maintains a **unique collection** of all songs inputted into the library.
  - User can added song to the library.

- **Playlist Creation and Management**:
  - Users can create playlists, add songs to them, and perform various actions such as displaying, searching, and removing playlists.
  
### **2. Playback and Queue Management**

- **Queue Control**:
  - Users can **queue songs** with the ability to navigate to the `next`, `previous`, or `shuffle` songs in the queue.

- **Player Integration**:
  - The application features a **playback controller** that manages song queues and highlights the currently playing track.

### **3. Command Parsing and User Input**

- **General Command Parsing**:
  - Commands for actions across states follow a `<action> <number>` format and are parsed using `CommandParserUtil` to ensure correct formatting.
  
- **Context-Specific Commands**:
  -`ShowSongState` support unique commands, such as `add "<title>" by "<artist>"`, enabling users to add new songs directly to the library.

### **4. Data Output**

- **Saving and Loading Library**:
  - Data is persisted using a CSV format that stores song and playlist information for future access.
  - The system reads two columns (artist and title) on input and outputs three columns (playlist name, song title, artist) for saved playlists.


---

# **Non-Functional Requirements**

### **1. Data Persistence and Compatibility**

- **CSV Format**:
  - Saves data in **CSV format** for easy import/export with spreadsheet applications and other data-processing tools.

- **File Accessibility**:
  - Ensures that files are **easily accessible** for sharing and backup.

### **2. Performance and Efficiency**

- **Low Latency**:
  - The application efficiently processes command inputs and outputs for a smooth user experience.

- **Memory Management**:
  - Optimized for **minimal resource usage**, making it accessible on systems with limited processing power.

### **3. Flexibility and Extensibility**

- **Dynamic Adjustments**:
  - Users can **update playlists and song information** dynamically, with changes immediately reflected in the system.

- **Modular Structure**:
  - Designed for easy updates and additions to accommodate future feature enhancements, such as expanded search capabilities. This is done through the use of the State Design Pattern.

### **4. Error Handling and Validation**

- **Command Validation**:
  - The `CommandParserUtil` validates all command inputs to prevent incorrect or unsupported actions.

- **Conflict Resolution**:
  - Ensures that users are informed of **invalid operations** (e.g., duplicate playlists or songs), guiding them to make corrections.

- **User Feedback**:
  - Provides **informative error messages** to aid users in navigating errors and input mistakes.

### **5. Documentation and Usability**

- **User Guide**:
  - Provides a comprehensive **step-by-step guide** on application usage and features, including common commands.
- **User-Friendly Interface:**
  - The application ensure **clear and intuitive command-line interactions**.

- **Developer Documentation**:
  - Includes detailed class and method descriptions for **easy reference and future development**.

---
## **Technical Specifications**
### **1. Programming Language and Environment**
- **Language**:
  - Developed using **Java SE 11 or higher**.

- **Development Tools**:
  - Utilizes **JUnit** for testing.


### **2. Data Structures**

- **Song Representation**:
  - Each song is represented as a **class object** with attributes for title and artist.

- **Circular Doubly Linked List**:
  - Used for playlist navigation and playback queue management, allowing efficient traversal and shuffling.

### **3. Algorithms and Logic**

- **Queue Navigation Algorithm**:
  - Provides `next`, `previous`, and `shuffle` options for seamless playlist navigation.

- **Command Parsing and Validation**:
  - Checks for correct command formats (e.g., `<action> <number>`) and extracts parameters to streamline user input processing.

### **4. Testing and Quality Assurance**
 **Unit Testing**:
  - Tests the `SongManager` class for playlist management, song addition, playback control, 
- **CSV Validation**:
  - Verifies that generated CSV files adhere to correct formatting and are readable by spreadsheet applications.

## **Project Milestones**

### **Milestone 1: Core Functionality Implementation**

- **Duration**:
- **Deliverables**:
  - Implement `SongManager`, `PlaylistManager`, and basic playlist and song functionality.

### **Milestone 2: Playback Queue and Navigation**

- **Duration**:
- **Deliverables**:
  - Develop playback functionality for `next`, `previous`, and `shuffle` actions.
  - Integrate circular doubly linked list for song navigation.

### **Milestone 3: Data Persistence**

- **Duration**: 
- **Deliverables**:
  - Implement CSV import/export for playlists and songs.

### **Milestone 4: Testing and Optimization**

- **Duration**: 
- **Deliverables**:
  - Conduct unit and integration testing, refine performance, and implement error handling.

### **Milestone 5: Documentation and Finalization**

- **Duration**:
- **Deliverables**:
  - Complete user and developer documentation, finalize project, and prepare for demo.

---
## **Sample CSV Output**
### **Sample Playlist Output**
```csv
Playlist Name,Song Title,Artist
favorite,My Way,Frank Sinatra
favorite,Fly me to the Moon,Frank Sinatra
favorite,Thriller,Michael Jackson
favorite,I Wanna Dance with Somebody,Whitney Houston
favorite,Someone Like You,Adele
Classic Hits,Thriller,Michael Jackson
Classic Hits,I Wanna Dance with Somebody,Whitney Houston
Classic Hits,Rocket Man,Elton John
Pop Songs,Someone Like You,Adele
Pop Songs,Whole Lotta Love,Led Zeppelin
```

Below is a sample CSV output generated by the application, demonstrating how the program store the playlists 
The first oclumns is the playlist name then follow by the song title and artist.
- **`favorite`** Playlist contains the following songs:
  - My Way by Frank Sinatra
  - Fly me to the Moon by Frank Sinatra
  - Thriller by Michael Jackson
  - I Wanna Dance with Somebody by Whitney Houston
  - Someone Like You by Adele
# **Class Structure: Music Library Management System**

## **1. Entry Point**

### **Client**
- **Purpose**: Serves as the entry point of the application. Contains the `main` method, which initializes the system and starts the application loop.

---

## **2. Core Application Context**

### **MusicSimulator**
- **Purpose**: Acts as the main context of the application. It maintains the current state and runs the main application loop.
- **Key Responsibilities**:
  - Manages the current `State` of the application.
  - Provides access to shared resources like `SongManager` and `ConsoleManager`.
  - Controls the flow of the application by delegating tasks to different states.

---

## **3. State Management**

### **State Interface**
- **Purpose**: Defines the contract for all state classes in the application.
- **Key Responsibilities**:
  - Declares methods that all concrete state classes must implement, such as `display()` and `handleInput()`.

### **Concrete State Classes**
- **MainMenuState**
  - **Purpose**: Manages user interactions at the main menu.
- **CreateState**
  - **Purpose**: Handles the creation of new playlists.
- **ShowSongState**
  - **Purpose**: Displays the list of available songs and handles song-related actions.
- **ShowPlaylistsState**
  - **Purpose**: Displays the list of playlists and handles playlist selection.
- **EditPlaylistState**
  - **purpose**: allow for the abilities to add new songs into the program
- **ViewPlaylistState**
  - **Purpose**: Shows the songs within a selected playlist and allows actions on them.
- **PlayQueueState**
  - **Purpose**: Manages the playback controls and the current play queue.
- **SearchState**
  - **Purpose**: Provides functionality for searching songs by artist or title.
- **SaveState**
  - **Purpose**: Handles saving the current song library and playlists to a file.

---

## **4. Models**

### **Song**
- **Purpose**: Represents an individual song in the music library.
- **Attributes**:
  - `String title`
  - `String artist`

### **Playlist**
- **Purpose**: Represents a collection of songs (a playlist).
- **Attributes**:
  - `String name`
  - `ArrayList<Song>` or a custom linked list of songs.

### **Node**
- **Purpose**: Represents a node in a linked list data structure.
- **Attributes**:
  - `Song song`
  - `Node next`
  - `Node prev`

---

## **5. Data Structures**

### **CircularDoublyLinkedList**
- **Purpose**: Custom data structure used for managing the play queue and playlist navigation.
- **Key Features**:
  - Allows traversal in both directions (next and previous).
  - Supports shuffling of songs.

---

## **6. Managers and Controllers**

### **SongManager**
- **Purpose**: Acts as the central controller for managing songs, playlists, and playback.
- **Key Responsibilities**:
  - Manages the song library and playlists.
  - Handles song playback and queue management.
  - Interacts with `PlaylistManager` and `Player` for specific functionalities.

### **PlaylistManager**
- **Purpose**: Manages the collection of playlists.
- **Key Responsibilities**:
  - Creates, deletes, and retrieves playlists.
  - Adds songs to playlists.

### **SongLibrary**
- **Purpose**: Maintains the unique collection of all songs in the system.
- **Key Responsibilities**:
  - Stores and manages the master list of songs.
  - Provides methods to add, remove, and search songs.

### **DataManager**
- **Purpose**: Handles data persistence for the application.
- **Key Responsibilities**:
  - Loads songs and playlists from files.
  - Saves the current state of songs and playlists to files.

---

## **7. Utilities and Helpers**

### **ConsoleManager**
- **Purpose**: Manages console input and output operations.
- **Key Responsibilities**:
  - Displays messages and menus to the user.
  - Captures user input.
  - Handles console formatting and clearing.

### **Player**
- **Purpose**: Manages song playback and the play queue.
- **Key Responsibilities**:
  - Controls playback actions like play, pause, next, previous, and shuffle.
  - Maintains the current song and play queue.

### Playlistaction helper 
- purpose: help with actoins that associated with playlists
  
### Command parserutil 
- purpose: parse the command to validated and ensure that the command is correct

---

## **8. Testing**

### **SongManagerTest**
- **Purpose**: Contains test cases for validating the functionality of the `SongManager` class.
- **Key Responsibilities**:
  - Tests methods related to song and playlist management.
  - Ensures that the business logic works as expected.

---

## **Summary**
Please Check the UML Diagram for a representation on how the class are connected together.
Class organization:

- **Core Classes**: `MusicSimulator`, `Client`
- **State Management**: `State` interface and various concrete state classes like `MainMenuState`, `ShowSongState`, etc.
- **Models**: `Song`, `Playlist`, `Node`
- **Managers**: `SongManager`, `PlaylistManager`, `SongLibrary`, `DataManager`
- **Utilities**: `ConsoleManager`, `Player`, `CircularDoublyLinkedList`
- **Testing**: `SongManagerTest`


# Instructions for Running the Program

### 1. Setup Requirements
- Ensure [JDK 8 or higher](https://www.oracle.com/java/technologies/javase-downloads.html) is installed.
- Clone or download the project files, including the `bin` directory with compiled classes.

### 2. Running the Program
- Open a terminal and navigate to the root directory of the project.
- Run the program using the following command:
  ```bash
  java -cp bin app.Client

- Open up the project in vscode and click run on the Client.java file.

### 3. Navigating the Program:
- The program itself contain instruction on the commands that can be use and error handling help to user to figure out the instruction as well. 

### 4. Saving output:
- Within the program you can save output by typing the save command and specifiying the sources or name
- Otherwise the program doesn't save the output 

### 5. Exiting the Program
- To exit, use the exit command in the program or press Ctrl + C to terminate.

---
## Next Steps
- **Requirement Review:**
  - More requirements could be implemented
- **Feedback and Iteration:**
  - Incorporate user feedback throughout the development process to refine and enhance the application's functionality and usability.



