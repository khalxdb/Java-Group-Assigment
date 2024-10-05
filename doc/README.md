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

Responsiblities: 
Vathana Khun: plan out the class structure and overall structure on how the program should work
## System Design
###
 Image of the class structure
![alt text](image-1.png)

## Project MileStone
Milestone 1: Initial System design and core Requirements
Milestone 2: Core Functionality Implementation

**Song Class**
- Purpose: Represents an individual song in the music library.

**Node Class**
- Purpose: Serves as a node in the doubly linked list, holding a Song object and references to the next and previous nodes.

**DoublyLinkedList Class**
- Purpose: Manages a list of SongNode objects, allowing navigation through songs.

**Playlist Class**
- Purpose: Represents a playlist containing a collection of songs. Also made up of the doublelinkedlist

**SongManager Class**
- Purpose: Acts as the central controller for user interactions, managing playlists, songs, and the temporary queue. 


