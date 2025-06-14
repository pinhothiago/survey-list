package com.thiagopinho.surveylist.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagopinho.surveylist.R
import com.thiagopinho.surveylist.model.Survey
import com.thiagopinho.surveylist.ui.adapter.SurveyAdapter
import com.thiagopinho.surveylist.ui.component.SurveyStartBottomSheet
import com.thiagopinho.surveylist.utils.Paginator
import com.thiagopinho.surveylist.utils.generateMockSurveys

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SurveyAdapter
    private val fullList = generateMockSurveys(100)
    private val visibleList = mutableListOf<Survey?>()
    private lateinit var paginator: Paginator<Survey>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = SurveyAdapter(visibleList) {
            showBottomSheetDialog()
        }

        paginator = Paginator(
            pageSize = 10,
            data = fullList,
            onLoading = {
                visibleList.add(null)
                adapter.notifyItemInserted(visibleList.size - 1)
            },
            onPageLoaded = { newItems ->
                val loadingIndex = visibleList.indexOf(null)
                if (loadingIndex != -1) {
                    visibleList.removeAt(loadingIndex)
                    adapter.notifyItemRemoved(loadingIndex)
                }

                visibleList.addAll(newItems)
                adapter.notifyItemRangeInserted(visibleList.size - newItems.size, newItems.size)
            }
        )

        paginator.loadNextPage()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)

                val layoutManager = rv.layoutManager as LinearLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()

                if (!paginator.isLoading && lastVisible == visibleList.size - 1) {
                    paginator.loadNextPage()
                }

            }
        })
    }

    private fun showBottomSheetDialog() {
        SurveyStartBottomSheet(this) {
            Toast.makeText(this, "Pesquisa iniciada!", Toast.LENGTH_SHORT).show()
        }.show()
    }

}
