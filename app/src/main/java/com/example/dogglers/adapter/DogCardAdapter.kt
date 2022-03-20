/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource.dogs
import com.example.dogglers.databinding.GridListItemBinding
import com.example.dogglers.databinding.VerticalHorizontalListItemBinding
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: List<Dog> = dogs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (layout == Layout.GRID) {
            GridViewHolder.from(parent)
        } else {
            LinearViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resources = context?.resources
        val currentData = data[position]
        val dog = Dog(
            currentData.imageResourceId,
            currentData.name,
            resources?.getString(R.string.dog_age, currentData.age).toString(),
            resources?.getString(R.string.dog_hobbies, currentData.hobbies).toString()
        )
        when (holder) {
            is LinearViewHolder -> holder.bind(dog)
            is GridViewHolder -> holder.bind(dog)
        }
    }

    override fun getItemCount(): Int = data.size

    class LinearViewHolder private constructor(
        val binding: VerticalHorizontalListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.apply {
                dogImage.setImageResource(dog.imageResourceId)
                dogNameText.text = dog.name
                dogAgeText.text = dog.age
                dogHobbyText.text = dog.hobbies
            }
        }

        companion object {
            fun from(parent: ViewGroup): LinearViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VerticalHorizontalListItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return LinearViewHolder(binding)
            }
        }
    }

    class GridViewHolder private constructor(
        val binding: GridListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.apply {
                dogImage.setImageResource(dog.imageResourceId)
                dogNameText.text = dog.name
                dogAgeText.text = dog.age
                dogHobbyText.text = dog.hobbies
            }
        }

        companion object {
            fun from(parent: ViewGroup): GridViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridListItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return GridViewHolder(binding)
            }
        }
    }
}
