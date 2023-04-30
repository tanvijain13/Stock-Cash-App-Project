## Stock Cash Android Application
##### The Stock Cash App is a simple and user-friendly mobile application for displaying and searching for stocks data interface for accessing and searching for stock data. It uses MVVM architectural pattern which ensures that the app is easy to maintain and scale, and its integration with the Retrofit library to fetch the stock data from a remote API allows for efficient and reliable API calls. The app then displays it to the user in a RecyclerView, which can be filtered by search queries entered by the user.

##### The app uses the Model-View-ViewModel (MVVM) architectural pattern, which separates the presentation layer (View) from the business logic (ViewModel) and the data model (Model) of the application.

##### The Model layer is represented by the StockData class and the StocksApiService interface, which handle the retrieval and parsing of stock data from the remote API. The ViewModel layer is represented by the MainViewModel class, which contains the business logic for the app, including making the API calls and processing the data. The View layer is represented by the MainActivity class, which handles the presentation of the stock data to the user through the UI.

##### The MainActivity class sets up the UI components, including the RecyclerView for displaying the stock data and the SearchView for filtering the data. It also creates instances of the Retrofit library and the MainViewModel class. The MainViewModel class handles the API calls and data processing, and updates the UI by passing the data to the MainActivity class.

##### The SearchView allows the user to filter the displayed stock data by entering search queries. When the user enters a search query, the onQueryTextChange method of the SearchView's OnQueryTextListener is called, which filters the stock data based on the query and updates the RecyclerView to display the filtered results. If no matching results are found, a toast message is displayed to the user.

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
##### 2. 