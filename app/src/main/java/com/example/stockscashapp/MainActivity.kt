package com.example.stockscashapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockscashapp.api.StocksApiService
import com.example.stockscashapp.data.models.Stock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //Creating a Retrofit instance that will be used to make the API calls.
    val retrofit = Retrofit.Builder()
        .baseUrl("https://storage.googleapis.com/cash-homework/cash-stocks-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Creating an instance of the API service
    val apiService = retrofit.create(StocksApiService::class.java)
    //Creating an instance of the ViewModel
    val viewModel = MainViewModel(apiService)

    private lateinit var searchView: SearchView
    private lateinit var stockRecyclerView: RecyclerView
    private var adapter: StockAdapter?=null
    private var stocksList = ArrayList<Stock>()

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing the searchView and stockRecyclerView variables
        searchView = findViewById(R.id.search_view)
        stockRecyclerView = findViewById(R.id.stockRecyclerView)

        //Fetching stock data from the API
        CoroutineScope(Dispatchers.IO).launch {
            val portfolioData = viewModel.getPortfolioData()

            //Updating the UI with the fetched data
            runOnUiThread {
                portfolioData?.let { data ->
                    // Parse the stock list data into StockItem list
                    stocksList = ArrayList(data.stocks.map {
                        Stock(
                            it.ticker,
                            it.name,
                            it.currency,
                            it.current_price_cents,
                            it.quantity,
                            it.current_price_timestamp
                        )
                    })

                    // Set up the RecyclerView
                    adapter = StockAdapter(stocksList)
                    stockRecyclerView.adapter = adapter
                    stockRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }

        //Adding an OnQueryTextListener to the searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Returning false to indicate that the query has not been handled
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Creating a filtered list of stocks based on the search query
                val filteredList = ArrayList<Stock>()
                val searchText = newText?.lowercase() ?: ""
                var matchingPosition = -1

                for ((index, stock) in stocksList.withIndex()) {
                    //Checking if the stock ticker or name contains the search query
                    if (stock.ticker.lowercase().contains(searchText) || stock.name.lowercase().contains(searchText)) {
                        filteredList.add(stock)
                        matchingPosition = index
                    }
                }

                //Scrolling to the matching position if a matching item is found
                if (matchingPosition != -1) {
                    stockRecyclerView.scrollToPosition(matchingPosition)
                    toast?.cancel() // Cancelling the toast if it is already displayed
                } else {
                    //Displaying a toast message if no matching item is found
                    if (newText?.isNotBlank() == true) { // Check if the search query is not blank
                        toast?.cancel() // Cancelling the toast if it is already displayed
                        toast = Toast.makeText(this@MainActivity, getString(R.string.toast_text), Toast.LENGTH_SHORT)
                        toast?.show()
                    }
                }

                //Filtering the adapter's data list based on the search query
                adapter?.filterList(filteredList)
                return true
            }
        })

    }
}
