package com.tyomashin.workmanagersample.Geofence

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.*

class SampleWorker(context : Context, params: WorkerParameters) : Worker(context, params){
    override fun doWork(): Result {

        // WorkManager経由で実行する処理を書く
        Log.d("doWork", "now")
        // 現在地の取得
        val locationClient = LocationServices.getFusedLocationProviderClient(applicationContext).let {
            it.lastLocation.addOnSuccessListener { location: Location? ->
                var locatioinStr: String = location?.latitude.toString() + location?.longitude.toString()
                Log.d("location", locatioinStr)

                val geofencingClient = LocationServices.getGeofencingClient(applicationContext)
                val geofence = Geofence.Builder()
                    .setRequestId("Geofence")
                    .setCircularRegion(35.681098, 139.767062, 100f)     // 東京駅から半径100m
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                    .build()
                val request = GeofencingRequest.Builder()
                    .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                    .addGeofence(geofence)
                    .build()
                val pendingIntent = PendingIntent.getService(
                    this,
                    0,
                    Intent(this, GeofenceTransitionsIntentService::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT)

                geofencingClient.addGeofences(request, pendingIntent)?.also {
                    it.addOnSuccessListener { Toast.makeText(this, "addOnSuccess", Toast.LENGTH_SHORT).show() }
                    it.addOnFailureListener { Toast.makeText(this, "addOnFailure", Toast.LENGTH_SHORT).show() }
            }

        }
        /*
        val locationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        locationClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                // (a)LastLocation が得られたときの処理
            } else {
                val request = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(500)
                    .setFastestInterval(300)

                locationClient.requestLocationUpdates(request,object : LocationCallback(){
                    override fun onLocationResult(p0: LocationResult?) {
                        super.onLocationResult(p0)

                    }
                }
            }),null)
            }
               */
        return Result.success()
    }
}