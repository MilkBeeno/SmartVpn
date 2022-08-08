package com.milk.smartvpn.ui.act

import android.os.Bundle
import android.view.View
import com.milk.simple.ktx.*
import com.milk.smartvpn.R
import com.milk.smartvpn.databinding.ActivityLaunchBinding

class LaunchActivity : AbstractActivity() {
    private val binding by lazy { ActivityLaunchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeView()
    }

    private fun initializeView() {
        immersiveStatusBar(false)
        binding.ivSelect.isSelected = true
        binding.ivSelect.setOnClickListener(this)
        binding.tvStart.setOnClickListener(this)
        binding.tvPrivacy.setSpannableClick(
            Pair(
                string(R.string.launch_privacy),
                colorClickableSpan(color(R.color.FF0DC2FF)) {
                    WebActivity.create(
                        this, "https://justfuncall.com/terms.html"
                    )
                })
        )
    }

    override fun onMultipleClick(view: View) {
        super.onMultipleClick(view)
        when (view) {
            binding.ivSelect -> {
                binding.ivSelect.isSelected = !binding.ivSelect.isSelected
            }
            binding.tvStart -> {
                if (binding.ivSelect.isSelected) {
                    MainActivity.create(this)
                    finish()
                } else showToast(string(R.string.launch_privacy_agreement))
            }
        }
    }
}