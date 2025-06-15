package com.thiagopinho.surveylist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thiagopinho.surveylist.R
import com.thiagopinho.surveylist.model.Survey

class SurveyAdapter(
    private val surveys: MutableList<Survey?>,
    private val onRespondClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SURVEY = 0
        private const val TYPE_LOADING = 1
    }

    inner class SurveyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tvTitle)
        val duration = view.findViewById<TextView>(R.id.tvDuration)
        val reward = view.findViewById<TextView>(R.id.tvReward)
        val button = view.findViewById<Button>(R.id.btnRespond)
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun getItemViewType(position: Int): Int {
        return if (surveys[position] == null) TYPE_LOADING else TYPE_SURVEY
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_SURVEY) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_survey, parent, false)
            SurveyViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val context = holder.itemView.context

        if (holder is SurveyViewHolder) {
            val survey = surveys[position]!!
            holder.title.text = survey.title
            holder.title.contentDescription = survey.title

            val durationText = context.getString(R.string.survey_duration, survey.duration)
            holder.duration.text = durationText
            holder.duration.contentDescription = durationText

            val rewardText = context.getString(R.string.survey_reward, survey.reward)
            holder.reward.text = rewardText
            holder.reward.contentDescription = rewardText

            val buttonText = context.getString(R.string.btn_respond)
            holder.button.text = buttonText
            holder.button.contentDescription = "$buttonText à pesquisa ${survey.title}"

            holder.button.setOnClickListener { onRespondClick() }
        }
    }

    override fun getItemCount() = surveys.size

}