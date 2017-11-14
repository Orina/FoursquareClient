# Project  - *Foursquare Client*

**Foursquare Client** is an android app that allows a user to search for city venues. The app utilizes [Foursquare web API](https://developer.foursquare.com).

The app implemented Model-View-ViewModel android design pattern in conjunction with Android Data Binding Library.

## User Stories

The following **required** functionality is completed:

* [X] The main screen contains a list with 5 cities of your choice (loaded from json file in assets)
* [X] When the user clicks on a city, user will be sent to a screen that displays a list of venues in the city (max=25). An item in the list should show the venue name, a thumbnail, location and categories. It should also indicate if the venue was bookmarked or not.
* [X] When the user clicks on a venue, user will be taken to a screen that displays a grid of photos for the venue (max=50). Screen will also display a Bookmark button. When user clicks the Bookmark button, update the button to selected state. When the user returns to the previous screen, it should update the Bookmark state of the venue in the list.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## How to use this app

Add 2 string resources with your Foursquare credentials:

- `<string name="foursquare_client_id">YOUR_CLIENT_ID</string>`
- `<string name="foursquare_client_secret">YOUR_CLIENT_SECRET</string>`

## Open-source libraries used

- [Fresco](http://frescolib.org/) - An image management library 
- [Retrofit networking library](http://square.github.io/retrofit/) - Http client for Android
- [GSON library](https://github.com/google/gson)- A Java serialization/deserialization library that can convert Java Objects into JSON and back.
- [Dagger2](https://github.com/google/dagger) - A fast dependency injector for Android and Java

## License

    Copyright 2017 Elmira Andreeva 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

