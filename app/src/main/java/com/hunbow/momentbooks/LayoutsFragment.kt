package com.hunbow.momentbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hunbow.momentbooks.databinding.FragmentLayoutsBinding
import java.io.InputStreamReader
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentLayoutsBinding
private lateinit var booksAdapter: LayoutsViewsAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [LayoutsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LayoutsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayoutsBinding.inflate(inflater, container, false)

        init()

        return binding.root
//        return inflater.inflate(R.layout.fragment_layouts, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Layouts.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = LayoutsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    private fun init() {
        // Чтение замоканных данных из JSON файла в папке assets
        val booksList = loadMockData()
        val categories = getCategoties();
//        val createBookItem = LayoutsBook(URL("https://momentsbook.ru/assets/glossy.jpg"), "");
//        val newBookList = listOf(createBookItem) + booksList
        // Установка адаптера
        val booksAdapter = LayoutsViewsAdapter(booksList)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = booksAdapter
        }
    }

    private fun loadMockData(): List<LayoutsBook> {
        val inputStream = requireContext().assets.open("books_mock.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<LayoutsBook>>() {}.type
        return Gson().fromJson(reader, type)
    }

    private fun getCategoties(): List<String> {
        return listOf(
            "Все", "Свадебные", "Романтические", "Путешествия", "Детские", "Простые", "Семейные"
        )
    }

}