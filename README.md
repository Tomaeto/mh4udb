# mh4udb
Monster Hunter 4 Ultimate Database

Java program for compiling Monster Hunter 4 Ultimate's monster hitzone data and displaying through graphical interface. UI contains buttons for each large monster in the game that display the damage type and element hitzone data for each of the monster's parts. JSON files containing data scraped from the MH4U Kiranico website, and the monster icons taken from in-game files. Uses the Jackson API for parsing JSON files.

Note: Crimson Fatalis and White Fatalis do not have any hitzone data as none was stored in the JSON files obtained from Kiranico, hitzone data is assumed to be the same as Fatalis.

Repo structure:
src - contains core code files
  src/dataTransferObjects - contains code files for accessing JSON files in data folder as well as classes for storing data for Monsters and Monster Parts
  src/mh4udb - contains main class that builds and displays the GUI
  
data - contains data files
  data/monsterInfo - contains JSON files containing info for every monster
  data/monsterIcons - contains icon files for monsters
  
lib - contains JAR files for the Jackson API used by the dataAccessor class to parse JSON files
