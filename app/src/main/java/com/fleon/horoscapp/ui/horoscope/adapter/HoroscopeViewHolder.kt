package com.fleon.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fleon.horoscapp.databinding.ItemHoroscopeBinding
import com.fleon.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)
    fun bind(horoscopeInfo: HoroscopeInfo) {
        binding.imgHoroscope.setImageResource(horoscopeInfo.img)
        binding.textViewHoroscope.text = binding.textViewHoroscope.context.getString(horoscopeInfo.name)
    }
}