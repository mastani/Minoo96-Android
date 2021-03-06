package ir.minoo96.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private Context context;
    private String pref_file = "PREF";

    public SharedPreferenceHelper(Context context, String pref_name) {
        this.context = context;
        this.pref_file = pref_name;
    }

    /**
     * Set a string shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setString(String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(pref_file, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set a integer shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setInt(String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(pref_file, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set a Boolean shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setBoolean(String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(pref_file, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public String getString(String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(pref_file, 0);
        return settings.getString(key, defValue);
    }

    /**
     * Get a integer shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public int getInt(String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(pref_file, 0);
        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public boolean getBoolean(String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(pref_file, 0);
        return settings.getBoolean(key, defValue);
    }
}