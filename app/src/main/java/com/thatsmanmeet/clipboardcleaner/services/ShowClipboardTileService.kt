package com.thatsmanmeet.clipboardcleaner.services

import android.content.Intent
import android.graphics.drawable.Icon
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.thatsmanmeet.clipboardcleaner.R
import com.thatsmanmeet.clipboardcleaner.ShowIntentActivity

class ShowClipboardTileService : TileService() {

    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
        val showTitle = qsTile
        showTitle.label = "Show Clipboard"
        showTitle.state = Tile.STATE_INACTIVE
        showTitle.icon = Icon.createWithResource(applicationContext,R.drawable.ic_show)
        showTitle.updateTile()

    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        try {
            val intent = Intent(this, ShowIntentActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                startActivityAndCollapse(it)
            }
        }catch (e:Exception){
            Log.d("Exception", "onClick: $e ")
        }
    }
}