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

##### 2. `StockAdapter` class uses the `StocksApiService` to fetch stock data and update the UI with the retrieved data. It extends the `RecyclerView.Adapter` class and is responsible for displaying a list of `Stock` objects in a RecyclerView.
###### - `ViewHolder` class is defined to represent a single item view in the RecyclerView, with references to each view in the item layout.
###### - `onCreateViewHolder()` method is called when a new ViewHolder is created for a new item view in the RecyclerView. It inflates the item layout and returns a new instance of the `ViewHolder` class.
###### - `onBindViewHolder()` method is called by the RecyclerView when it needs to update the contents of a ViewHolder. It gets the corresponding `Stock` object and binds its data to the views in the ViewHolder.

###### - `getItemCount()` method returns the total number of items in the dataset held by the adapter.

###### - `filteredList` variable holds a filtered list of Stock objects and filterList() method updates the adapter's filteredList with a new list of stocks, and notifies the adapter that the data has changed.

#### MVVM architecture in the app
##### 1. Model : This represents the data and business logic of the app, in this case, the data models for the stocks `Stock` and `StockData` and the API service, `StocksApiService` that provides the data.
##### 2. View: This is the UI layer of the app, in this case, the activities and fragments that display the data to the user. In this app it is `MainActivity`.
##### 3. `MainViewModel` class is part of the ViewModel layer of the MVVM architecture in the app and extends the `ViewModel` class and communicates with the `StocksApiService` class to get the portfolio data from the server. The `getPortfolioData()` function is a suspend function that uses the Retrofit library to make an asynchronous network call to get the portfolio data.

##### The `GsonBuilder()` method is used to create a Gson instance with lenient parsing, which allows for a relaxed JSON syntax. The `awaitResponse()` method is used to wait for the response from the server, and the response body is returned if it is successful. If there is an exception or error during the API call, the `catch` block logs the error message.
