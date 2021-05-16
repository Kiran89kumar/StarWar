# Star War Finder App
Simple and scalable android Application with latest android technologies.

[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

<p align="center">
<img src="https://github.com/Kiran89kumar/StarWar/blob/main/pics/app_icon.png" width="240">
</p>


## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/Kiran89kumar/StarWar.git
```

# About

StarWar Finder is an simple Android application which is used to find the Star war characters and their details.
App contains 2 screens:
* 1. Search Screen: This is the home screen where user can able to search the Star war characters. At launch app will be loaded with main profiles, based on the search characters UI will be updated. 
    Concepts used: 
    * a. RxSubject for listing the text changes in the search view.
    * b. RxJava Debounce - Set the debounce timer value to "300" millisec
    * c. Data binding - Avoiding number of lines of code in view layer, so that it will help in covering UT.
    * d. Safe args - Sending clicked character information to next fragment so that available information will be show in detail screen.
* 2. Details Screen: After clicking on particular character, detail screen will be loaded with few basic informtions. In the detail screen app will fetch films and planet deatils for multiple API's and update the data.
    Concepts used: 
    * b. RxJava Zip - As Films is available in the form of array so we need to make array of API calls to get it. So ZIP will help in achieving this. Even taken care if one of the API fails remaining/ passed API's responses will be shown to UI.
    * c. Data binding - Avoiding number of lines of code in view layer, so that it will help in covering UT.

# Screenshots

<img src="https://github.com/Kiran89kumar/StarWar/blob/main/pics/search_page.png" width="250"> <img src="https://github.com/Kiran89kumar/StarWar/blob/main/pics/not_found.png" width="250"><img src="https://github.com/Kiran89kumar/StarWar/blob/main/pics/detail_page.png" width="250">

# Technologies Used

Technologies used in this project are:

* <b>Android clean architecture</b>: A strong base architecture is extremely important for an app to scale and meet the expectation of the user base. This architecture was proposed in 2012 by Robert C. Martin(Uncle Bob) in <a href="http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html">clean code blog</a>
<p align="center">
<img src="https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png" width="500">
</p>


* <b>Andorid Architecture Components</b>: Used andorid provided architecture components to make developer job easy -
  *  a. MVVM
  *  b. ViewModel
  *  c. ViewModelFactory
  *  d. Live Data
  *  e. Navigation Components - Used for Navigation from one screen to other

*  Dagger: Dagger is an dependency injection framwork. 
*  Retrofit + RxJava: To make API calls Retrofit with RXjava is used. Used Interceptors for Error handling.
*  MockK: Used MockK for Mocking and UT tests.
*  Safeargs: Used to pass data from one screen to other using Navigation components
*  Kotlin
*  Kotlin DSL

# API

Api is provided by swapi.dev website

