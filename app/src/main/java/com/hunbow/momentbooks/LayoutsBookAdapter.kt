package com.hunbow.momentbooks;

import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hunbow.momentbooks.databinding.BookItemBinding

class LayoutsViewsAdapter(private val books: List<LayoutsBook>): RecyclerView.Adapter<LayoutsViewsAdapter.LayoutsBookHolder>() {

    class LayoutsBookHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = BookItemBinding.bind(item)
        fun bind(book: LayoutsBook) = with(binding) {
            Glide.with(itemView.context)
                .load(book.imageUrl.toString())
                .into(bookItemImage)

            bookItemTitle.text = book.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayoutsBookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return LayoutsBookHolder(view)
    }

    override fun onBindViewHolder(holder: LayoutsBookHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

}