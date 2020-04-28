# Betting Information Scraper
> Scraps sport betting information from https://hintwise.com.

## Table of contents

- [Betting Information Scraper](#betting-information-scraper)
  - [Table of contents](#table-of-contents)
  - [General info](#general-info)
  - [Technologies](#technologies)
  - [Setup](#setup)
  - [Output file](#output-file)
  - [Authors and acknowledgment](#authors-and-acknowledgment)
  - [Task list](#task-list)
  - [Code examples](#code-examples)
  - [Project status](#project-status)

## General info
The motivation behind this project is to strengthen knowledge of Java Microsoft Excel and html parsing libraries.

## Technologies
* IntelliJ IDEA
* Java - version 11.0.2
* Apache POI - version 4.0.1
* jsoup - version 1.12.1 jar

## Setup
Step 1:

Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows).

Step 2: 

For not experienced users:

Download **BettingInfoScraper.zip** and extract all files into 
**C:\Users\\[your username]\IdeaProjects** created during IntelliJ IDEA installation.

For experienced users:

You know what to do :smiley:

Step 3: 

Create **LIBS** folder in **C:\Users\[your username]\IdeaProjects**
Download [apache poi 4.0.1.zip](https://archive.apache.org/dist/poi/release/bin/) and 
[jsoup 1.12.1.jar](https://jar-download.com/artifacts/org.jsoup/jsoup/1.12.1/source-code) 
libraries, extract their content into **C:\Users\[your username]\IdeaProjects\LIBS**

Step 4:

Open BettingInforScrapper in Intellij IDEA

![Open BettingInforScraper project](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/Open_Project.png)

Locate project folder and click **ok**

![Select folder](https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/Folder_path.png)

Step 5:

Add libraries to your project

Go to File->Project Structure->Libraries  press plus '+' in the top left corner to add libraries

<img src="https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_lib_1.png" width=70% height=50%>

First add **jsoup** library and click **ok**

<img src="https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_lib_2.png" width=70% height=50%>

Then add Apache POI libraries:

 **poi** folder includes libraries in a few folders (**poi/libs** and **poi/ooxml-lib** we will need all of them) and 
 in an initial **poi** folder itself, therefore, first add **jsoup** library and pres **ok** then repeat step 5 and add 
 **poi** libraries.


Add **.jar** files from **poi** folder

<img src="https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_poi_lib_1.png" width=70% height=50%>

Add **.jar** files from **poi/ooxml-lib** folder

<img src="https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_poi_lib_2.png" width=70% height=50%>

Add **.jar** files from **poi/lib** folder

<img src="https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/adding_poi_lib_3.png" width=70% height=50%>

Press **Apply** and then **ok** after all libraries were added.


Step 6: Run it.

Go to **src/root** right click on BettingInfoScraper file and click **Run 'BattingInfoScraper.main()'**

The output file will be saved in **BettingInfoScraper/logs** folder.



## Output file

If no errors occurred the output file should look like this

<img src="https://github.com/Atom0713/README_Images/blob/master/BettingInfoScraper/output-file.png" width=70% height=50%>

NOTE: Since this README file have been developed during the Corona virus global pandemic the output file has only one 
entry due to the cancellation of all sports events all over the world.


## Authors and acknowledgment

* **Artem Sliusarenko** - *Initial work* - [BettingInfoScraper](https://github.com/Atom0713/BettingInfoScraper)

## Task list
- [x] Parse soccer records
- [x] Implement output stream for soccer events

- [ ] Introduce code examples of how to manage scraping of 
sport sections {soccer, basketball, tennis, hockey} in main class.

- [ ] Parse basketball records
- [ ] Implement output stream for basketball events
- [ ] Parse hockey records
- [ ] Implement output stream for hockey events
- [ ] Parse tennis records
- [ ] Implement output stream for tennis events


## Code examples



## Project status

Project development slowed down due to lack of time and involment in other projects.


