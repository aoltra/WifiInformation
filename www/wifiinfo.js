var wifiinfo = {  
  
  getBSSID: function(successCallback, errorCallback){  
    cordova.exec(successCallback,  
        errorCallback,  
        "WifiInformation",  
        "getBSSID",
        []
    );  
      
  }  
}  

module.exports = wifiinfo;