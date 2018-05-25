package de.chinguyen.immersive;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class ImmersiveTile extends TileService {

    int errorCounter = 0;

    public void onClick() {
        super.onClick();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED) {
            if (Settings.Global.getString(this.getContentResolver(), "policy_control").equals("immersive.full="))
                Settings.Global.putString(this.getContentResolver(), "policy_control", "immersive.full=*");
            else
                Settings.Global.putString(this.getContentResolver(), "policy_control", "immersive.full=");

        } else {
            errorCounter++;
            if (errorCounter == 0)
                Toast.makeText(this, "Please grant permission", Toast.LENGTH_SHORT).show();
            if (errorCounter > 1)
                Toast.makeText(this, "Open Immersive App for help", Toast.LENGTH_SHORT).show();
        }
        updateImmersiveTile();
    }

    public void onTileRemoved() {
        super.onTileRemoved();
    }

    public void onTileAdded() {
        updateImmersiveTile();
    }

    public void onStartListening() {
        updateImmersiveTile();
    }

    public void onStopListening() {
        super.onStopListening();
    }

    private void updateImmersiveTile() {
        Tile immersiveTile = getQsTile();

        if (Settings.Global.getString(this.getContentResolver(), "policy_control").equals("immersive.full=")) {
            immersiveTile.setState(Tile.STATE_INACTIVE);
            immersiveTile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile_immersive));
            immersiveTile.updateTile();
        } else {
            immersiveTile.setState(Tile.STATE_ACTIVE);
            immersiveTile.setIcon(Icon.createWithResource(this, R.drawable.ic_tile_immersive));
            immersiveTile.updateTile();
        }
    }
}
