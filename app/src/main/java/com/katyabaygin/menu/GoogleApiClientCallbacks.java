package com.katyabaygin.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;

public interface GoogleApiClientCallbacks {
    void onConnected(@Nullable Bundle bundle);

    void onConnectionSuspended(int i);

    void onConnectionFailed(@NonNull ConnectionResult connectionResult);
}

