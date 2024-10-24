# Design Documentation: Music Library Management System
## Project Overview
The Song library is a java based application, aims to provide an interactive command line interface for user to manage their music collection efficiently. The system allows users to create playlists, add song to those playlists, and manage their favorite track

## Problem statement:
In the problem, there are songs by artists (artists can be bands or solo artists), and these songs can be singles or belong to albums. You are required to create song catalogues or libraries or playlists with ability to go to the previous song or the next song, as well as shuffle play.

## Initial Functional Requirements
- Unique Song List: The systems maintains a unique set of all inputted song
- Playlist Functionality: Playlists are collections that stored the song from the unique song list.
- Queue Management: Users can queue songs, with functionality to go next, previous, and shuffle.
- Terminal Interface: A terminal-based interface allows for user interaction.
- Library Tracking: The system tracks both the song list and the playlists.


Possible Extra Feature
- Search Feature: Users can search for songs by artist, name, or album.
- Album Management: Albums are treated like playlists, enabling similar functionality.
Playback Control

## Initial Non-Functional Requirements:
- Persistent Storage: The system saves the song library and playlists using file input/output (eg CSV)
- Reliability: handle error of wrong command
- Performances: quick response to the command allow for smooth interaction

#### User Flow Interaction
Ideally loaded in a the terminal by calling the file for the interface
1. Added or load some preloaded songs and playlist
2. Interaction : user can use the following pseudo command: create, play, show, exit, add.
- create a new playlist, 
- add song to song list
- add song to a specific playlist 
- show all song
- show all playlist , then show song in playlist
- play a single song, in a queue then added more song in queue
- play a playlist / album
- exit out of the program  
3 Reliability: handle error of wrong command
4. Exist application 

## Technology
- Programming Language and Environment: Java
- Data Structure: Using Doubly Linked List and ArrayList for Storing song and playlists
- Testing: 
- Documentation: Java doc, user guide

## System Design
###
 Image of the class structure to be changed
![alt text](image-1.png)

## Project MileStone
### Milestone 1: Initial System design and core Requirements 
### MileStone 2: Core Functionality Implementation
### complete the song manager class
### Break song manager class to delegated the task even further
### Make Unit testing for the song manager class
### Implemented basic interfaces
### Input CSV 

### Implemneted circular linked list 
### Ouput CSV
### completete the console intercaees
### Refactors the class based on different design pattern

---
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

---

## **8. Testing**

### **SongManagerTest**
- **Purpose**: Contains test cases for validating the functionality of the `SongManager` class.
- **Key Responsibilities**:
  - Tests methods related to song and playlist management.
  - Ensures that the business logic works as expected.

---

## **Summary**
Class organization:

- **Core Classes**: `MusicSimulator`, `Client`
- **State Management**: `State` interface and various concrete state classes like `MainMenuState`, `ShowSongState`, etc.
- **Models**: `Song`, `Playlist`, `Node`
- **Managers**: `SongManager`, `PlaylistManager`, `SongLibrary`, `DataManager`
- **Utilities**: `ConsoleManager`, `Player`, `CircularDoublyLinkedList`
- **Testing**: `SongManagerTest`





Responsibility: 
### Vathana Khun:
- Setting up the initial documentation
- came up with the class structure
- Plan out the overview of the project, documented initial requirement
- Complete the Initial class structure
- delegated class songManager into different class
- Make Unit testing for the song manager class
- Implemented basic interfaces
- Make Input CSV 
- Implemneted circular linked list 
- Implemented Ouput CSV
- completete the console intercaees
- Refactors the class based on different design pattern
  
### Sunny And Angus
- complete the song manager class

### Kahlid