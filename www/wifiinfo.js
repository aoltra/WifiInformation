var wifiinfo = {  
  
  getBSSID: function(successCallback, errorCallback){  
    cordova.exec(successCallback,  
        errorCallback,  
        "WifiInformation",  
        "getBSSID",
        "getSSID",
        []
    );  
      
  }  
}  

module.exports = wifiinfo;