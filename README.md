# My Personal Project: Catalog your music pool!

## A subtitle: music list

A *bulleted* list:
- item 1  ___create music pieces___
- item 2  ___catalog music___


An example of text with **bold** and *italic* fonts.  Note that the IntelliJ markdown previewer doesn't seem to render 
the bold and italic fonts correctly but they will appear correctly on GitHub.

This software can be used to record the information of music like the information individual performer or group of
performers, music pieces information. People can use this music application to store their favorite performers, music
pieces.This application would provide convenience for people who would like to listen to music and catalog music.

I would like to create this application because I am interested in music. I always listen to music in my free time, so I
would like to have that kind of application to help me store my favorite music and catalog music too. It can also good
for me to store my own music pieces information.

A subtitle: User Stories
•As a user, I want to be able to add a individual artist or add group artist.
•As a user, I want to be able to remove music
•As a user, I want to be able to remove artist
•As a user, I want to be able to view the list of artist
•As a user, I want to be able to view the list of music
•As a user, I want to be able to add information about artist
•As a user, I want to be able to add information about music

•one that expresses the user's ability to save the state of the application to file.
•another that expresses the user's ability to reload that state from file and resume exactly where they left off at a
later time.


INSTRUCTION for Grader
YOU CAN GENERATE FIRST REQUIRED EVENT BY...
1. for adding: you are allow to type or select corresponding things in textfield to add. if you leave some information
in blank, there will be a error dialog show up. I set up member field to be uneditable first because I want to use
design pattern to solve it later. adding valid audio path can play music, and the audio(format wav)
need to be saved in my audio package so that the APP can find the file

add ........ to my music table

YOU CAN GENERATE SECOND REQUIRED EVENT BY....
2 for removing: there is a row number in my table. you are allow to type corresponding number to remove that row.
 but if you type invalid information, then error dialog will show up

VISUAL COMPONENT
3 I have a cute cat background image

AUDIO COMPONENT
4 like in MUMUMusic.json file, there is a audio path of the corresponding music. copy it into the "audio path" textfield
you can play it and stop it. but if you type a wrong path, nothing will be play.

SAVE STATE
5 you can save the file to current file like MuMuMusic.json. or you can save to another file by typing (xxx.json)

LOAD STATE
6 you can load file  by typing the file name like (xxx.json), then the information should be load into the table


TABLE
7 you also can edit some information from the table, Music style and comefrom I create by combo box. favorite artist
and favoriate music I created by check box. the rest information I created by textField. row number can not be edit.
and member information cannot be editable for now too.  their is a sort selector underneath the each table subtitle

thanks for checking and have fun to listen to music :)
I am expecting to get near 100 based on the requirement and code coverage.




"Phase 4: Task 2"
I choose to do the option 2, type hierarchy. I design a abstract class artist, and two subclasses(Individual and Group).
Inside the artist class, there is an abstract method(getMemberInfo). Individual and Group they both override this method.


Phase 4: Task 3
When I design how to add member information, I want to add a members field inside Music class, so Music can access to
members(ArrayList<Individual>). But this will cause a coupling problem,like there are two ways that can get Artist
information. Also, it is a bad idea to let Music to have members information when considering cohesion. In my other
classes, I use composite pattern relationship to increase the cohesion and decrease coupling. I also change classes
name to have a basic easier understanding about each class information..


There is a UML diagram for better understanding!
Thanks for checking:)



