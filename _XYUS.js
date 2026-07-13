function GetCenter(url,parm,func) {
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }else{
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState == 4){
            if (xmlhttp.status == 200) {
                func(xmlhttp.response || xmlhttp.responseText);
            }else{
                console.log("初始化失败："+xmlhttp.status);
            }
        }
    }
    xmlhttp.open("POST",url,false);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send(parm);
};
GetCenter("/api/center/", "cmd=get.rz.list", function (jon) {
    jon = JSON.parse(jon);
    console.log(jon);
    if (jon.code == 1) {
        window._XYUS_CONF = jon.data;
        document.write('<scr' + 'ipt src="' + _XYUS_CONF.PATH + "/api/sdk/xy.sdk.min.js" + '"></sc' + 'ript>');
      
    }else{

        console.log("初始化失败："+jon.code+jon.msg);
    }
});