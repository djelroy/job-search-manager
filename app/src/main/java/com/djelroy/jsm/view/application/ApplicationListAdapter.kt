package com.djelroy.jsm.view.application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.djelroy.jsm.R
import com.djelroy.jsm.data.Application

class ApplicationListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<ApplicationListAdapter.ApplicationViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var applications = emptyList<Application>() // Cached copy of applications

    class ApplicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val company = itemView.findViewById<TextView>(R.id.tv_company)
        val titleTextView = itemView.findViewById<TextView>(R.id.tv_title)
        val descriptionTextView = itemView.findViewById<TextView>(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ApplicationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) {
        val current = applications[position]
        holder.company.text = current.company
        holder.titleTextView.text = current.title
        holder.descriptionTextView.text = current.description
    }

    internal fun setApplications(applications: List<Application>) {
        this.applications = applications
        notifyDataSetChanged()
    }

    override fun getItemCount() = applications.size
}