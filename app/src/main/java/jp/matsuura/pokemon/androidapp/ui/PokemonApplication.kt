package jp.matsuura.pokemon.androidapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import jp.matsuura.pokemon.androidapp.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
