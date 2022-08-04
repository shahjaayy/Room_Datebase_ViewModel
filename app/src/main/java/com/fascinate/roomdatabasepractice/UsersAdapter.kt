package com.fascinate.roomdatabasepractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var list = ArrayList<UsersEntity>()

    fun setData(list: ArrayList<UsersEntity>)
    {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user_data, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class UsersViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvID: TextView
        private val tvEmail: TextView
        private val tvName: TextView
        private val tvDate: TextView

        init {
            // Define click listener for the ViewHolder's View.
            tvID = view.findViewById(R.id.row_id)
            tvDate = view.findViewById(R.id.row_date)
            tvName = view.findViewById(R.id.row_name)
            tvEmail = view.findViewById(R.id.row_email)
        }

        fun bind(entity: UsersEntity)
        {
            entity.let {
                tvID.text = it.uId.toString()
                tvName.text = "${it.firstName} ${it.lastName}"
                tvEmail.text = it.email
                tvDate.text = it.date.toString()
            }
        }
    }

}