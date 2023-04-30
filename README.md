## Stock Cash Android Application

![Stock Cash App](https://heytanvi.com/wp-content/uploads/2023/04/Screenshot_20230430-015304_Stocks-Cash-App_samsung-galaxys20ultra-cosmicblack-portrait.png "Stock Cash App") | ![Stock Cash App main page](https://heytanvi.com/wp-content/uploads/2023/04/Screenshot_20230430-112710_Stocks-Cash-App_samsung-galaxys20ultra-cosmicblack-portrait.png "Stock Cash App main page")
:---:
*Stock Cash App*


##### The Stock Cash App is a simple and user-friendly mobile application for displaying and searching for stocks data interface for accessing and searching for stock data. It uses MVVM architectural pattern which ensures that the app is easy to maintain and scale, and its integration with the Retrofit library to fetch the stock data from a remote API allows for efficient and reliable API calls. The app then displays it to the user in a RecyclerView, which can be filtered by search queries entered by the user.

##### The app uses the Model-View-ViewModel (MVVM) architectural pattern, which separates the presentation layer (View) from the business logic (ViewModel) and the data model (Model) of the application.

##### The Model layer is represented by the StockData class and the StocksApiService interface, which handle the retrieval and parsing of stock data from the remote API. The ViewModel layer is represented by the MainViewModel class, which contains the business logic for the app, including making the API calls and processing the data. The View layer is represented by the MainActivity class, which handles the presentation of the stock data to the user through the UI.

##### The MainActivity class sets up the UI components, including the RecyclerView for displaying the stock data and the SearchView for filtering the data. It also creates instances of the Retrofit library and the MainViewModel class. The MainViewModel class handles the API calls and data processing, and updates the UI by passing the data to the MainActivity class.

##### The SearchView allows the user to filter the displayed stock data by entering search queries. When the user enters a search query, the onQueryTextChange method of the SearchView's OnQueryTextListener is called, which filters the stock data based on the query and updates the RecyclerView to display the filtered results. If no matching results are found, a toast message is displayed to the user.
### The architectural approach you took and why
##### **The first task was to setup the MainActivity with the basic UI architecture with a SearchView**
##### **Next I thought to try and fetch the response from the given API and show it to the MainActivity without any filtered UI.**

#### API service setup to fetch stock data
#####  The `StocksApiService` class is used to define the HTTP requests and process the response. The `StockAdapter` class uses the` StocksApiService` to fetch stock data and update the UI with the retrieved data.
##### 1. `StocksApiService `  class is built using the Retrofit library, which simplifies the process of making network requests in Android.The `getStockData` function has a GET request. The function returns a `Response<StockData>` object, which contains the deserialized JSON response.
##### The `StocksApi` object is responsible for creating the `StocksApiService` instance. It uses the `Retrofit.Builder()` to build the Retrofit instance, which is configured with the base URL and the GsonConverterFactory. Finally, the StocksApiService is created using `retrofit.create()`.
###### 
`private val retrofit = Retrofit.Builder()
.baseUrl(BASE_URL)
.addConverterFactory(GsonConverterFactory.create())
.build()
val service: StocksApiService = retrofit.create(StocksApiService::class.java)`

##### **The next target was to show the fetched stock data in the UI in a `CardView` format and `RecyclerView`**
##### 2. `StockAdapter` class uses the `StocksApiService` to fetch stock data and update the UI with the retrieved data. It extends the `RecyclerView.Adapter` class and is responsible for displaying a list of `Stock` objects in a RecyclerView.
###### - `ViewHolder` class is defined to represent a single item view in the RecyclerView, with references to each view in the item layout.
###### - `onCreateViewHolder()` method is called when a new ViewHolder is created for a new item view in the RecyclerView. It inflates the item layout and returns a new instance of the `ViewHolder` class.
###### - `onBindViewHolder()` method is called by the RecyclerView when it needs to update the contents of a ViewHolder. It gets the corresponding `Stock` object and binds its data to the views in the ViewHolder.

###### - `getItemCount()` method returns the total number of items in the dataset held by the adapter.

###### - `filteredList` variable holds a filtered list of Stock objects and filterList() method updates the adapter's filteredList with a new list of stocks, and notifies the adapter that the data has changed.

##### **Once the UI looked clean I changed and added a couple of functionalities in the SearchView to search and bring up the name of the stock that is being searched**

##### **I also checked for the malformed and empty JSON responses and how to deal with it by adding a try-catch block to handle any exceptions that may occur while fetching data from the API. If an exception is caught, an error message is logged to the console and null is returned if it is Malformed.**

##### **Lastly when everything looked good, I added a splash screen to the code before it lands on the MainActivity**

#### MVVM architecture in the app
##### 1. Model : This represents the data and business logic of the app, in this case, the data models for the stocks `Stock` and `StockData` and the API service, `StocksApiService` that provides the data.
##### 2. View: This is the UI layer of the app, in this case, the activities and fragments that display the data to the user. In this app it is `MainActivity`.
##### 3. ViewModel: `MainViewModel` class is part of the ViewModel layer of the MVVM architecture in the app and extends the `ViewModel` class and communicates with the `StocksApiService` class to get the portfolio data from the server. The `getPortfolioData()` function is a suspend function that uses the Retrofit library to make an asynchronous network call to get the portfolio data.

##### The `GsonBuilder()` method is used to create a Gson instance with lenient parsing, which allows for a relaxed JSON syntax. The `awaitResponse()` method is used to wait for the response from the server, and the response body is returned if it is successful. If there is an exception or error during the API call, the `catch` block logs the error message.

### The trade offs you made and why
##### - In the case of this stock app, some trade-offs were made. One of the trade-offs was between the app's performance and its complexity. To improve the app's performance, some features were left out, and the design was kept simple. For instance, the app does not have the ability to buy or sell stocks or offer real-time stock prices.

##### - The reason for building this stock app was to demonstrate how to use the MVVM architecture, Retrofit library, and other modern Android development best practices to build a functional and efficient app. The app provides a straightforward way to view the user's portfolio, with real-time stock data.

### How to run this project
#####  Here are some general instructions on how to run this in Android Studio:

###### 1. Clone this repository
###### 2. Open Android Studio and select "Open an existing Android Studio project" from the main menu.
###### 3. Navigate to the directory where the project is stored and select the project folder.
###### 4. Wait for Android Studio to load and build the project.
###### 5. Connect an Android device to your computer or start an emulator.
###### 6. Click the "Run" button in the toolbar or go to "Run" > "Run app" from the main menu.
###### 7. Select the device or emulator you want to run the app on and click "OK".
###### 8. Wait for the app to install and launch on the selected device or emulator.
