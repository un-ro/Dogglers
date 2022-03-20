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
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource.dogs
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val data: List<Dog> = dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        fun bind(dog: Dog) {
            val dogImage: ImageView = itemView.findViewById(R.id.dog_image)
            val dogName: TextView = itemView.findViewById(R.id.dog_name_text)
            val dogAge: TextView = itemView.findViewById(R.id.dog_age_text)
            val dogHobbies: TextView = itemView.findViewById(R.id.dog_hobby_text)

            dogImage.setImageResource(dog.imageResourceId)
            dogName.text = dog.name
            dogAge.text = dog.age
            dogHobbies.text = dog.hobbies
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val layout = if (layout == Layout.GRID) {
            R.layout.grid_list_item
        } else {
            R.layout.vertical_horizontal_list_item
        }

        return DogCardViewHolder(
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val resources = context?.resources
        val dog = data[position]
        holder.bind(
            Dog(
                dog.imageResourceId,
                dog.name,
                resources?.getString(R.string.dog_age, dog.age).toString(),
                resources?.getString(R.string.dog_hobbies, dog.hobbies).toString()
            )
        )
    }
}
