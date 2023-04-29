package com.example.stockscashapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockscashapp.data.models.Stock

// The StockAdapter class is responsible for creating views for each item in the RecyclerView and binding data to those views.
class StockAdapter(private val stockList: List<Stock>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    // The ViewHolder class represents a single item view in the RecyclerView.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tickerTextView: TextView = itemView.findViewById(R.id.tickerTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val currencyView: TextView = itemView.findViewById(R.id.currencyView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
    }

    // This method creates a new ViewHolder instance for a new item view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return ViewHolder(itemView)
    }

    // This method is called by the RecyclerView when it needs to update the contents of a ViewHolder.
    // It binds the data from the corresponding Stock object to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = stockList[position]

        holder.tickerTextView.text = currentItem.ticker
        holder.nameTextView.text = currentItem.name
        holder.currencyView.text = currentItem.currency.let { if (it == "USD") "$" else it }
        holder.priceTextView.text = "${(currentItem.current_price_cents/ 100.0)}"
        holder.quantityTextView.text = (currentItem.quantity?.toString()?: "0") + " shares"

    }

    // This method returns the total number of items in the dataset held by the adapter
    override fun getItemCount(): Int {
        return stockList.size
    }

    // This method updates the adapter's filteredList with a new list of stocks, and notifies the adapter that the data has changed
    var filteredList: List<Stock> = stockList

    fun filterList(filteredList: List<Stock>) {
        this.filteredList = filteredList
        notifyDataSetChanged()
    }


}
