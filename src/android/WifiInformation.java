package com.uhuru.cordova.wifiinformation;  
  
import org.apache.cordova.CallbackContext;  
import org.apache.cordova.CordovaPlugin;  
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject;  
import android.net.wifi;
  
public class WifiInformation extends CordovaPlugin {  
  
	private static final String LOG_TAG = "WifiInformation";

	public static final String ACTION_SSID = "getBSSID"; 

 	private interface WifiOp {
        void run() throws Exception;
    }

	/**
	 * Executes the request and returns whether the action was valid.
	 *
	 * @param action 			The action to execute.
	 * @param args 				JSONArray of arguments for the plugin.
	 * @param callbackContext	The callback context used when calling back into JavaScript.
	 * @return True if the action was valid, false otherwise.
	 */
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) 
    	throws JSONException {
 		
 		if (action.equals("getBSSID")) {
 			threadhelper( new WifiOp( ){
                public void run() throws Exception {
                    WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    String BSSID = wifiInfo.getBSSID();
                    callbackContext.success(BSSID);
                }
            },callbackContext);
         
        }

        return false;
    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    	super.initialize(cordova, webView);
    }

    /* 
     * helper to execute functions async and handle the result codes
     */
    private void threadhelper(final WifiOp f, final CallbackContext callbackContext){
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    f.run();
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}