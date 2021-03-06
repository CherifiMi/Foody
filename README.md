<h1 align="center">Foody</h1>

<p align="center">  
 🥗Foody is a modern *recipe book* app made in Android Studio with Hilt, Coroutines, ViewModel, ViewBinding, Room, RetroFit, and Material Design based on MVVM architecture.
</p>
</br>




<img width="1446" alt="foody" src="https://user-images.githubusercontent.com/98290339/157385747-5b0ce85f-be53-4a52-9d34-06f98c7ab75f.png">


#### Ui Made in [Figma](https://www.figma.com/file/mtCF10n9wphsv1JdKG00uQ/Ux-and-shi?node-id=231%3A252)


## Tech stack & Open-source libraries

- Minimum SDK level 27
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Architecture
    - MVVM Architecture (View - ViewBinding - ViewModel - Model)
    - Repository Pattern (Local/Remote)

- [Coordinator Layout](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout)
- [View Pager](https://developer.android.com/training/animation/vp2-migration) for the daiteles activity
- [Material Ui](https://material.io/)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Room components](https://developer.android.com/training/data-storage/room) for the database
- [View binding](https://developer.android.com/topic/libraries/view-binding) to bind data :)
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) to save the meal preferences when closing the app
- [Recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview) to sort the data
- [Retrofit](https://github.com/square/retrofit) the get the data from the api
- [Coroutines](https://developer.android.com/kotlin/coroutines) to handel the api requests
- [Lifecycle](https://developer.android.com/guide/fragments/lifecycle)
- [Coil](https://github.com/coil-kt/coil) for loading the food images from a url
- [Gson](https://github.com/google/gson) to convert the data list into a Json file to save into the DataBase
- [Shimmer](https://facebook.github.io/shimmer-android/) for the glow effect when loading
- [Jsoup](https://jsoup.org/) to convert the Html data to a normal text



## What I learned
- using Hilt
- using retrofit to get data from "https://spoonacular.com/food-api"
- use room database to save the favorite recipes 
- using navigation control
- using page handler to set a page tab view
- using safe args
- using data store to save the meal preferences 
- using attrs to use dark theme and light theme
- using animation to animate the buttons in the bottom bar
- Costume Views:
    -the Bottom animated bar, made by [Shape Shifter](https://shapeshifter.design/) and the [Animated drawable](https://developer.android.com/guide/topics/graphics/drawable-animation)



## Final app

![Ui](https://user-images.githubusercontent.com/98290339/152101155-f80f641f-1787-403c-a8b6-32b153af4c7b.png)

https://user-images.githubusercontent.com/98290339/152108945-091ff5ab-391e-4dbf-b0fd-44e15e521f4f.mp4

https://user-images.githubusercontent.com/98290339/152109033-f5fa549f-3c90-4dd1-857e-e8ddc0ed2732.mp4




## Architecture
Foody is based on the MVVM architecture and the Repository pattern.
![image](https://user-images.githubusercontent.com/98290339/152096381-2a8898d3-c351-4032-979d-ebc836e46332.png)

## Open Api

Foody uses the [Spoonacular RESTful Api](https://spoonacular.com/food-api) for getting a list of recipes.
Every recipe contains:
- the food picter
- the extended ingredients
- the source Url
- a summary of the food
- the diet type: vegan, glutenFree ...
- the meal type: drink, dessert ...



