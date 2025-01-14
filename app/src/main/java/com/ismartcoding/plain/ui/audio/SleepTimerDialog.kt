package com.ismartcoding.plain.ui.audio

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.SeekBar
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ismartcoding.lib.helpers.CoroutinesHelper.withIO
import com.ismartcoding.lib.helpers.FormatHelper
import com.ismartcoding.plain.R
import com.ismartcoding.plain.data.preference.AudioSleepTimerFinishLastPreference
import com.ismartcoding.plain.data.preference.AudioSleepTimerFutureTimePreference
import com.ismartcoding.plain.data.preference.AudioSleepTimerMinutesPreference
import com.ismartcoding.plain.databinding.DialogSleepTimerBinding
import com.ismartcoding.plain.features.audio.AudioPlayer
import com.ismartcoding.plain.services.AudioPlayerService
import com.ismartcoding.plain.features.audio.AudioServiceAction
import com.ismartcoding.plain.features.locale.LocaleHelper
import com.ismartcoding.plain.ui.BaseBottomSheetDialog
import com.ismartcoding.plain.ui.extensions.setSafeClick
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class SleepTimerDialog() : BaseBottomSheetDialog<DialogSleepTimerBinding>() {
    private var seekProgress: Int = 0
    private var timerUpdater: TimerUpdater? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        binding.shouldFinishLastAudio.apply {
            isChecked = AudioSleepTimerFinishLastPreference.get(context)
            setOnCheckedChangeListener { _, checked ->
                lifecycleScope.launch {
                    withIO { AudioSleepTimerFinishLastPreference.putAsync(context, checked) }
                }
            }
        }

        binding.seekBar.apply {
            seekProgress = AudioSleepTimerMinutesPreference.get(context)
            updateTimeDisplayTime()
            progress = seekProgress
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (i < 1) {
                    seekBar.progress = 1
                    return
                }
                seekProgress = i
                updateTimeDisplayTime()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                lifecycleScope.launch {
                    withIO {
                        AudioSleepTimerMinutesPreference.putAsync(context, seekProgress)
                    }
                }
            }
        })
    }

    fun updateUI() {
        val context = requireContext()
        if (AudioSleepTimerFutureTimePreference.get(context) > SystemClock.elapsedRealtime()) {
            binding.seekBar.isVisible = false
            binding.shouldFinishLastAudio.isVisible = false
            timerUpdater = TimerUpdater(WeakReference(this))
            timerUpdater?.start()
            binding.start.text = getString(R.string.stop)
            binding.start.setSafeClick {
                lifecycleScope.launch {
                    timerUpdater?.cancel()
                    val previous = makeTimerPendingIntent(PendingIntent.FLAG_NO_CREATE)
                    val am = context.getSystemService<AlarmManager>()
                    am?.cancel(previous)
                    previous.cancel()
                    AudioPlayer.instance.pendingQuit = false
                    withIO { AudioSleepTimerFutureTimePreference.putAsync(context, 0) }
                    updateTimeDisplayTime()
                    updateUI()
                }
            }
        } else {
            binding.seekBar.isVisible = true
            binding.shouldFinishLastAudio.isVisible = true
            binding.start.text = getString(R.string.start)
            binding.start.setSafeClick {
                lifecycleScope.launch {
                    withIO {
                        AudioSleepTimerFutureTimePreference.putAsync(
                            context,
                            SystemClock.elapsedRealtime() + AudioSleepTimerMinutesPreference.get(context) * 60 * 1000
                        )
                    }
                    context.getSystemService<AlarmManager>()?.setExact(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        AudioSleepTimerFutureTimePreference.get(context),
                        makeTimerPendingIntent(PendingIntent.FLAG_CANCEL_CURRENT)
                    )
                    updateUI()
                }
            }
        }
    }

    fun updateTimeDisplayTime() {
        binding.minutes.text = LocaleHelper.getStringF(R.string.x_min, "num", seekProgress)
    }

    override fun onPause() {
        super.onPause()
        timerUpdater?.cancel()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun makeTimerPendingIntent(flag: Int): PendingIntent {
        return PendingIntent.getService(
            requireActivity(), 0, makeTimerIntent(), flag or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun makeTimerIntent(): Intent {
        val context = requireContext()
        val intent = Intent(requireActivity(), AudioPlayerService::class.java)
        return if (AudioSleepTimerFinishLastPreference.get(context)) {
            intent.setAction(AudioServiceAction.PENDING_QUIT.name)
        } else intent.setAction(AudioServiceAction.QUIT.name)
    }

    private inner class TimerUpdater(val dialog: WeakReference<SleepTimerDialog>) : CountDownTimer(
        AudioSleepTimerFutureTimePreference.get(requireContext()) - SystemClock.elapsedRealtime(),
        1000
    ) {
        override fun onTick(millisUntilFinished: Long) {
            dialog.get()?.let {
                if (it.isActive) {
                    it.binding.minutes.text = FormatHelper.formatDuration(millisUntilFinished / 1000)
                }
            }
        }

        override fun onFinish() {
            lifecycleScope.launch {
                withIO { AudioSleepTimerFutureTimePreference.putAsync(requireContext(), 0) }
                dialog.get()?.let {
                    if (it.isActive) {
                        it.updateTimeDisplayTime()
                        it.updateUI()
                    }
                }
            }

        }
    }
}