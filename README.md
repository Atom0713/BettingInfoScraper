# Betting Information Scraper
> Scraps sports betting information from https://hintwise.com.

## Table of contents

- [Betting Information Scraper](#betting-information-scraper)
  - [Table of contents](#table-of-contents)
  - [General info](#general-info)
  - [Technologies](#technologies)
  - [Setup](#setup)
  - [Output file](#output-file)
  - [Authors and acknowledgment](#authors-and-acknowledgment)
  - [Project status](#project-status)

## General info
The motivation behind this project is to strenthen knowledge of Java Microsof Excel and html parsing libraries.

## Technologies
* IntelliJ IDEA
* Java - version 11.0.2
* Apache POI - version 4.0.1
* jsoup - version 1.12.1 jar

## Setup
Step 1:

Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)

Step 2: 

Download **BettngInforScraper.zip** and extract all files into **C:\Users\\[your username]\IdeaProjects**

Step 3: 

Create **LIBS** folder in **C:\Users\[your username]\IdeaProjects**
Download [apache poi 4.0.1.zip](https://archive.apache.org/dist/poi/release/bin/) and [jsoup 1.12.1.jar](https://jar-download.com/artifacts/org.jsoup/jsoup/1.12.1/source-code) libraries, extract their content into **C:\Users\[your username]\IdeaProjects\LIBS**

Step 4:

Open BettingInforScrapper in Intellij IDEA

![Open BettingInforScraper project](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/Open_Project.png)

Locate project folder and click **ok**

![Select folder](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/Folder_path.png)

Step 5:

Add libraries to your project

Go to File->Project Structure->Libraries  press plus '+' at the top left corner to add libraries

![Adding libraries](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_lib_1.png =200x250)

First add **jsoup** library and click **ok**

![Adding libraries](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_lib_2.png =200x250)

Then add Apache POI libraries:

 **poi** folder includes libraries in a few folders (**poi/libs** and **poi/ooxml-lib** we will need all of them) and in an initial **poi** folder itself, therefore, first add **jsoup** library and pres **ok** then repeat step 5 and add **poi** libraries.


Add ***.jar** files from **poi** folder

![Adding Apache POI libraries](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_poi_lib_1.png)

Add ***.jar** files from **poi/ooxml-lib** folder

![Adding Apache POI libraries](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_poi_lib_2.png)

Add ***.jar** files from **poi/lib** folder

![Adding Apache POI libraries](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_poi_lib_3.png)

Press *Apply* and then **ok** after all libraries are added.


Step 6: Run it.

Go to **src/root** right click on BettingInforScraper file and click **Run 'BattingInfoScraper.main()'**

The output file will be saved in **BettingInfoScraper/logs** folder.



## Output file

If no errors occured the output file should look like this

![Output file](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/output-file.png)

NOTE: Since this README file was created during the Coronavirus global pandemic the output file has only one entry due to the cancelation of all sports events all over the world.


## Authors and acknowledgment

* **Artem Sliusarenko** - *Initial work* - [BettingInfoScraper](https://github.com/Atom0713/BettingInfoScraper)


## Project status