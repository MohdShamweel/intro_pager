package com.shamweel.trimaplus.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_data_store")

class AppDataStore(
    context: Context
) {

    companion object {
        private val APP_LOGIN_SUCCESS = booleanPreferencesKey("key_app_login_success")
    }

    private val applicationContext = context.applicationContext

    val userLoggedIn: Flow<Boolean?>
    get() = applicationContext.dataStore.data.map { preference ->
        preference[APP_LOGIN_SUCCESS] ?: false
    }

    suspend fun setLogin(login: Boolean){
        applicationContext.dataStore.edit { preference ->
            run{
                preference[APP_LOGIN_SUCCESS] = login
            }
        }
    }

    suspend fun clear(){
        applicationContext.dataStore.edit {
            it.clear()
        }
    }

}