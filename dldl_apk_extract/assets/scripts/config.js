var loadingView= window.loadingView;
if(loadingView)
{
    loadingView.loadingAutoClose=true;
    loadingView.showTextInfo=false;
    loadingView.bgColor("#000000");
    loadingView.setFontColor("#ffffff");
    loadingView.setTips(["新世界的大门即将打开", "敌军还有30秒抵达战场", "妈妈说，心急吃不了热豆腐"]);
}

//在这进行设定
conch.config.pushAtlasLimitSize=256;
conchConfig.atlasNum = 3;
var nMem = conchConfig.getTotalMem();
if (nMem <= 524288) {
    //conchConfig.atlasNum = 10;
    conchConfig.maxTextureMemSize = 84 * 1024 * 1024;
}
else if (nMem > 524288 && nMem <= 1048576) {
    //conchConfig.atlasNum = 14;
    conchConfig.maxTextureMemSize = 128 * 1024 * 1024;
}
else if (nMem > 1048576 && nMem<=1500000) {
    //conchConfig.atlasNum = 18;
    conchConfig.maxTextureMemSize = 168 * 1024 * 1024;
}
else{
    //conchConfig.atlasNum = 18;
    conchConfig.maxTextureMemSize = 200 * 1024 * 1024;
}


//字体改成middle，而不是top方式
conch.config.setGraphicsTextBaseLine(3);

//http超时设置短一点
if(conchConfig.setDownloadConnTimeout)
{
    conchConfig.setDownloadConnTimeout(4);//连接超时，单位秒
    console.log("change http connect timeout");
//conchConfig.setDownloadOptTimeout(18);//操作超时
//fr.setConnTimeout(2);单文件
//fr.setOptTimeout(1000);
}


//如果初始化过程出现异常，执行以下处理
window.onLayaInitError=function(e){
    var errorCount=localStorage.getItem("layaInitErrorCounter");
    errorCount=Number(errorCount?errorCount:"0");
    ++errorCount;

    //计算错误码
    var errorCode=-1;//未知错误
    switch(e)
    {
        case "filetable":errorCode=2;break;//dcc的filetable下载不到
        case "loadText(url)":errorCode=3;break;//index.html下载不到
    }
    if(e&&e.indexOf("scripts_")==0)//index里面的script下载不到,错误码减10表示第几个文件
        errorCode=10+parseInt(e.split("_")[1]);
    if(e&&e.indexOf("assetsid")==0)//dcc版本号文件下载不到
        errorCode=1;

    //发到控制台
    var errorMsg="onLayaInitError error:"+e+" counter:"+errorCount;
    console.log(errorMsg);

    if(errorCount<=5)
    {
        localStorage.setItem("layaInitErrorCounter",errorCount);
        console.log("retry after ms:"+1000);
        setTimeout(function(){
            window.location.reload();
            
        },1000);
    }
    else
    {
        //发到报错服务器
        var http= new window.XMLHttpRequest();
        http.open("post","http://www.error.dtsh5.5jli.com/error",true);
        http.responseType="text";
        http.send("error=onLayaInitError20171030 errorCode:"+errorCode+" error:"+e+" platform:"+window.navigator.userAgent);
        /*http.onload=function(e){
            console.log("logserver http.onload"+JSON.stringify(e) );
        }*/
   
        localStorage.setItem("layaInitErrorCounter",0);
        //window.g_showAlert("提示","网络异常，请退出游戏并检查您的网络设置后再次打开游戏","确定",function(){console.log("test btn1");window.g_exit();} );
        alert("游戏资源加载失败，请打开游戏重试."+errorCode);
    }
    
}

window.g_showAlert=(function(){
    var onIOS=!!window.navigator.userAgent.match(/\(i[^;]+;(U;)? CPU.+Mac OS X/);
    var bridge=window.PlatformClass.createClass(onIOS?"SdkMgr":"com.jiguang.h5.SdkMgr");
     
     return function(title,cxt,btn1,onClickBtn1,btn2,onClickBtn2){
         var obj={};
         obj["title"]=title;
         obj["cxt"]=cxt;
         if(btn1)obj["btn1"]=btn1;
         if(btn2)obj["btn2"]=btn2;
         var json=JSON.stringify(obj);
         
         bridge.callWithBack(function(btnIdx){
             console.log("g_showAlert oncall:"+btnIdx);
             btnIdx=parseInt(btnIdx);
             if(btnIdx==0)
                 onClickBtn1();
             else
                 onClickBtn2();
         },onIOS?"OnShowAlert:":"OnShowAlert",json);
     }
})();

window.g_exit=(function(){
    var onIOS=!!window.navigator.userAgent.match(/\(i[^;]+;(U;)? CPU.+Mac OS X/);
    var bridge=window.PlatformClass.createClass(onIOS?"SdkMgr":"com.jiguang.h5.SdkMgr");
     
     return function(){
         bridge.callWithBack(function(){},onIOS?"OnExit:":"OnExit","");
     }
})();

/*window.g_testAlert=function(){
    if(window["g_showAlert"])
    window.g_showAlert("提示","是否退出","否",function(){console.log("test btn1");},"是",function(){console.log("test btn2"); window.g_exit();});
}
window.g_testAlert();*/
