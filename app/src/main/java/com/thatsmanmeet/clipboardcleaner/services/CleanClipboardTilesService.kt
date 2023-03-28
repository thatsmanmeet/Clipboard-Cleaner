package com.thatsmanmeet.clipboardcleaner.services



import android.content.Intent
import android.graphics.drawable.Icon
import android.os.IBinder
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.thatsmanmeet.clipboardcleaner.IntentActivity
import com.thatsmanmeet.clipboardcleaner.R

class CleanClipboardTilesService : TileService() {

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
        val cleanTitle = qsTile
        cleanTitle.label = "Clean Clipboard"
        cleanTitle.state = Tile.STATE_INACTIVE
        cleanTitle.icon = Icon.createWithResource(this,R.drawable.ic_clean)
        cleanTitle.updateTile()

    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        try {
            val intent = Intent(this@CleanClipboardTilesService, IntentActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            startActivityAndCollapse(intent)
        }catch (e:Exception){
            Log.d("Exception", "onClick: $e ")
        }
    }
}