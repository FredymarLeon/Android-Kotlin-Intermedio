package com.fleon.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.fleon.horoscapp.R
import com.fleon.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.fleon.horoscapp.domain.model.HoroscopeModel.Aquarius
import com.fleon.horoscapp.domain.model.HoroscopeModel.Aries
import com.fleon.horoscapp.domain.model.HoroscopeModel.Cancer
import com.fleon.horoscapp.domain.model.HoroscopeModel.Capricorn
import com.fleon.horoscapp.domain.model.HoroscopeModel.Gemini
import com.fleon.horoscapp.domain.model.HoroscopeModel.Leo
import com.fleon.horoscapp.domain.model.HoroscopeModel.Libra
import com.fleon.horoscapp.domain.model.HoroscopeModel.Pisces
import com.fleon.horoscapp.domain.model.HoroscopeModel.Sagittarius
import com.fleon.horoscapp.domain.model.HoroscopeModel.Scorpio
import com.fleon.horoscapp.domain.model.HoroscopeModel.Taurus
import com.fleon.horoscapp.domain.model.HoroscopeModel.Virgo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        horoscopeDetailViewModel.getHoroscope(args.type)
        initUI()

    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivButtonBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        is HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                        is HoroscopeDetailState.Error -> errorState()
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.progressBarDetail.isVisible = true
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.progressBarDetail.isVisible = false
        binding.tvTitleDetail.text = state.sign
        binding.tvBodyDetail.text = state.prediction

        val image = when (state.horoscopeModel) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }

        binding.ivDetail.setImageResource(image)
    }

    private fun errorState() {
        binding.progressBarDetail.isVisible = false
    }
}