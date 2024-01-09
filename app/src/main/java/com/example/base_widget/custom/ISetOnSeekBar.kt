package com.example.base_widget.custom

interface ISetOnSeekBar {
    fun onProgressChanged(seekbar: CustomSeeBar, value: Int)
    fun onStartTrackingTouch(seekbar: CustomSeeBar)
    fun onStopTrackingTouch(seekbar: CustomSeeBar)
}