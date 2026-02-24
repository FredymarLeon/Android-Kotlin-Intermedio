package com.fleon.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.fleon.horoscapp.databinding.ItemHoroscopeBinding
import com.fleon.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)
    fun bind(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.imgHoroscope.setImageResource(horoscopeInfo.img)
        binding.textViewHoroscope.text =
            binding.textViewHoroscope.context.getString(horoscopeInfo.name)

        binding.containerHoroscope.setOnClickListener {
            startRotationAnimation(
                binding.imgHoroscope,
                newLambda = { onItemSelected(horoscopeInfo) }
            )
        }
    }
}

private fun startRotationAnimation(view: View, newLambda: () -> Unit) {
    view.animate().apply {
        duration = 500
        interpolator = LinearInterpolator()
        rotationBy(360f)
        withEndAction { newLambda() }
        start()
    }
}