package com.effectivemobile.courses.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.courses.R
import com.effectivemobile.courses.databinding.ItemCoursesBinding
import com.effectivemobile.domain.models.Course

class CourseAdapter(
    private val onBookmarkClicked: (Course) -> Unit
) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCoursesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CourseViewHolder(
        private val binding: ItemCoursesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            with(binding) {
                tvTitle.text = course.title
                tvDescription.text = course.description
                tvPrice.text = "${course.price} ₽"
                tvRate.text = course.rate
                tvStartDate.isVisible = course.startDate.isNotEmpty()
                tvStartDate.text = course.startDate
                btnBookmark.setImageResource(if (course.hasLike) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark)

                btnBookmark.setOnClickListener {
                    onBookmarkClicked(course)
                }
            }
        }
    }
}

class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}