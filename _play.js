var bindFlag = false;
$(document).ready(function () {
    set_account();
    init_float();
    gameLoadPage('#game_if');
    listen();
    share.init();
    setFloatSize();
    setTimeout(function () {
     //实名认证
     authentication();
     //中宣部上下线
     authentication_loginout();
    }, 2000);
    //微信分享
    $(document).on("click touchstart", "#weixin_share", function () {
        $("#weixin_share").remove();
    });
});

//子帐号
function set_account() {
    if ($("#account").length > 0) {
        $('.add-account .question').on('click', function () {
            $('.popup2').show();
        })
        $('.popup2 .button').on('click', function () {
            $('.popup2').hide();
        })
        $('.add-account .add,.text').on('click', function () {
            $('.popup1 .title').text('增加小号');
            $('#subutton').addClass('addname');
            $('.popup1').show();
        })
        $('.account-item .change-name').on('click', function () {
            $('.popup1 .title').text('修改小号');
            $('#subutton').removeClass('addname');
            $('#subutton').addClass('changename');
            $('#aid').val($(this).data('aid'));
            $('#account_name').val($(this).data('name'));
            $('.popup1').show();
        })
        $('.popup1 .button .left').on('click', function () {

            $('#subutton').removeClass('changename');
            $('#subutton').removeClass('addname');
            $('#aid').val('');
            $('#account_name').val('');
            $('.popup1').hide();
        })
        $('#subutton').on('click', function () {
            //$("#subutton").addClass('disabled').attr('disabled', true);

            var aid = $('#aid').val();
            var name = $('#account_name').val();
            var id = $('#id').val();
            var formhash = $('input[name="formhash"]').val();

            var post = {
                aid: aid,
                id: id,
                name: name,
                formhash: formhash
            };
            ajax_post("/index.php?ac=play&op=account&inajax=1&id=" + gameInfoJs.id, post, function (response) {
                if (response.status == 'success') {
                    message_fade_out("info", response.msg, 3);
                    setTimeout(function () {
                        if (response.url != '') {
                            url = response.url;
                        }
                        window.location.href = url;
                    }, response.second * 1000);
                    $('.popup1').hide();
                } else {
                    message_fade_out("error", response.msg, 3);
                }
            });
        })
    }
}
//初始化 悬浮球
function init_float() {
    if (gameInfoJs.screen == 'vertical') {
        var windW = $(window).width(),
            leftOff = -20,
            rightOff = 20;

        if (windW >= 640) {
            leftOff = -10;
            rightOff = 10;
        }
    } else {
        var windH = $(window).height(),
            leftOff = 20,
            rightOff = -20;
    }
    //悬浮球
    //展开侧滑页面
    mTouch('#icon_left','#yhqReminder','#icon_left2').on('tap', function () {
        $(this).hide();
        get_leftFloat();
    })
    //关闭侧滑页面
    mTouch('.float_close, .float_page-mask').on('tap', function () {
        // $("#float_content").html("");
        $("#icon_left").show();
        $(".float_page").animate({
            left: "-100%"
        }, 100);
        $('.float_page-mask').hide();
    })


    var assistiveLeft, assistiveRight, timerid;
    var stickEdge = function (el) {
        var right = parseInt(el.style.right) || 0,
            width = parseInt(el.offsetWidth) || 0,
            windowWith = (document.documentElement || document.body).offsetWidth;
        if (right > (windowWith - width) / 2) {
            if ($(".bottom_box").css("display", 'block'))
                right = windowWith - width + 10;
            else
                right = windowWith - width + rightOff;
        } else {
            if ($(".bottom_box").css("display", 'block'))
                right = -10;
            else
                right = leftOff;
        }
        el.style.transition = 'all .2s';
        el.style['transition'] = 'all .2s';
        el.style.right = right + 'px';
        timerid = setTimeout(function () {
            el.style.transition = 'all .5s';
            el.style['transition'] = 'all .5s';

        }, 2000);
    };
    //浮球的移动
    mTouch('#icon_left.vertical,#icon_left.horizontal').on('swipestart', function (e) {
            clearTimeout(timerid);
            e.stopPropagation();
            this.style.transition = 'none';
            this.style['transition'] = 'none';
            assistiveLeft = parseInt(this.style.right) || 0;
            assistiveTop = parseInt(this.style.top) || 0;
            return false;
        })
        .on('swiping', function (e) {
            e.stopPropagation();
            this.style.right = assistiveLeft - e.mTouchEvent.moveX + 'px';
            this.style.top = assistiveTop + e.mTouchEvent.moveY + 'px';
        })
        .on('swipeend', function () {
            stickEdge(this);
            var windowHeight = $(window).height(),
                limitTop = -parseInt(($(this).parent().offset().top)),
                limitBottom = windowHeight - $(this).parent().offset().top - $(this).height(),
                top = parseInt($(this).css('top'));
            if (top > limitBottom) {
                top = limitBottom - 10;
            } else if (top < limitTop) {
                top = limitTop + 10;
            }

            this.style.top = top + 'px';
        });
}

//获取侧滑页面并显示
function get_leftFloat(url) {

    $(".float_page").animate({
        left: "0%"
    }, 100);
    $('.float_page-mask').show();
    if(url){
        window.frames["leftFrame"].location=url;
    }


    //
    //
    //$.ajax({
    //    type: "get",
    //    url: "/index.php?ac=float&gameId=" + gameInfoJs.id + "&op=my",
    //    async: false,
    //    dataType: "html",
    //    success: function (data) {
    //        $(".float_page").animate({
    //            left: "0%"
    //        }, 100);
    //        $('.float_page-mask').show();
    //        window.frames["leftFrame"].location.reload(true);
    //    }
    //});
}

//页面加载时创建加载页面
function gameLoadPage(ele) {
    if ($("#gameIframe").length > 0) {
        var self = this;
        this.ele = $(ele);
        console.log(typeof(loginUser.avatar));
        if(typeof(loginUser.avatar)!='undefined'&&typeof(loginUser.userName)!='undefined')
        {
         this.loadingMessage = $('<div class="loading-message" style="transform: translate(-50%, -0.2rem);"><div class="cover"><img src="' + loginUser.avatar + '"></div><span class="name"><em>' + loginUser.userName + '&nbsp;&nbsp;</em>欢迎进入游戏</span></div>');
        }
        this.progressBar = $('<div class="progress_bar" style="width:60%;margin:auto;height:7px;background:#ececec;margin-top:10px"></div>');
        this.only = $('<div style="height:7px;width:0;background:#41c8cc;"></div>');
        //console.log(this.only);
        this.progressBar.append(this.only)
        this.loadPage = $('<div class="loading-game" id="loading-game"><div class="tablecell"><img class="loading-game-logo" src="' + gameInfoJs.icon + '"><div class="loading-text" style="margin-top:12%">游戏加载中...</div></div><img class="loading_logo" style="" src="' + setInfo.logo + '"/></div>')
        this.loadPage.find('.loading-text').after(this.progressBar);
        $('body').append(self.loadPage, self.loadingMessage)
        this.timer = setInterval(function () {
            var state = self.ele.get(0).ownerDocument.readyState;

            if (loginUser.uid != 0) {
                if (state == 'loading') {
                    self.only.animate({
                        'width': '33%'
                    }, 300)
                } else if (state == 'interactive') {
                    self.only.animate({
                        'width': '66%'
                    }, 300)
                } else if (state == 'complete') {
                    self.only.animate({
                        'width': '100%'
                    }, 300, function () {
                        clearInterval(self.timer);
                        setTimeout(function () {
                            self.loadPage.hide();
                            if(typeof(self.loadingMessage)!='undefined')
                            {
                             self.loadingMessage.hide();
                            }

                        }, 300)

                    })
                }
            }
        }, 300)
    }
}
//绑定用户
function bindUser(delay) {
    if (loginUser.userBindPhoneStatus == 'no' && setInfo.bindPhoneStatus != 'no') {
        bindPhone(delay);
        return false;
    } else if (loginUser.userBindAuthenticationStatus == 'no' && setInfo.bindAuthenticationStatus != 'no') {
        bindAuthentication(delay);
        return false;
    } else {
        return true;
    }
}

function bindClose(type) {
    $("#bind").hide();
    bindFlag = false;

    if (type == 'phone') {
        bindAuthentication(200);
        userBindPhoneStatus = 'yes';
        $(".bind-phone").hide();
    } else if(type == 'authenticationBox'){
        $("#authenticationBox").hide();
    } else if(type =='yhqReminder'){
        $("#yhqReminder").hide();
    } else {
        userBindAuthenticationStatus = 'yes';
        $(".bind-idCard").hide();
    }
}

//绑定实名
function bindAuthentication(delay) {
    if (userBindAuthenticationStatus == 'no' && bindAuthenticationStatus != 'no') {
        setTimeout(function () {
            if (!bindFlag) {
                //弹出绑定手机窗口
                $("#bind").show();
                $("#bind .login-channel").hide();
                $(".bind-idCard").show();
                bindFlag = true;
            }
        }, delay);
    }
}
//绑定手机
function bindPhone(delay) {
    if (userBindPhoneStatus == 'no' && bindPhoneStatus != 'no') {
        setTimeout(function () {
            if (!bindFlag) {
                $("#bind").show();
                $("#bind .login-channel").hide();
                $(".bind-phone").show();
                bindFlag = true;
            }
            //弹出绑定手机窗口
        }, delay);
    }
}

/***监听**/
function listen() {
    window.addEventListener("message", function (event) {
        if (gameInfoJs.debug) {
            console.log("%c" + event.data.operation, "color:red;font-size:20px;");
            console.log(event.data.param);
        }
        if (event && event.data) {
            switch (event.data.operation) {
                case "config": {
                    //初始化信息
                    if (event.data.param.gameId) {
                        if (event.data.param.gameId != gameInfoJs.id) {
                            message_fade_out("error", "游戏ID 有误！");
                        }
                    }
                    break;
                }
                case "pay": {
                    pay.init();
                    console.log(event);
                    if (bindUser(200)) {
                        console.log(event.data);
                        pay.listen(event.data.param);
                    };
                    //呼出支付方式
                    break;
                }
                case "share": {

                    //如果是微信就呼出 分享指导
                    if (typeof (event.data.param) == "object") {
                        share.set(event.data.param.title, event.data.param.desc, event.data.param.imgUrl);
                        //facebook等需要设置其meta
                        $(".share_title").attr('content', event.data.param.title);
                        $(".share_image").attr('content', event.data.param.imgUrl);
                        $(".share_description").attr('content', event.data.param.desc);
                    }
                    console.log(event.data);
                    break;
                }
                case "role": {
                    console.log(event.data.param);
                    role.listen(event.data.param);
                    break;
                }
                default: {

                }
            }
        }
    }, false);
}

function backtoapp() {
    if (setInfo.driver == "app") {
        if (setInfo.os == "ios") {
            window.webkit.messageHandlers.iosGameBackAction.postMessage({
                status: 'success'
            });
        }else
        {
            SDK.payCompletion('exitGame');
        }
    }else
    {
        top.location.href="/";
    }
}

function hide_share(event) {
    $("#shareBox,.shareBox").hide();
    event.stopPropagation();

}

function stopPropagation(event) {
    event.stopPropagation();
}

var sub = function (str, n) {
    var r = /[^\x00-\xff]/g;
    if (str.replace(r, "mm").length <= n) {
        return str;
    }
    var m = Math.floor(n / 2);
    for (var i = m; i < str.length; i++) {
        if (str.substr(0, i).replace(r, "mm").length >= n) {
            return str.substr(0, i) + "...";
        }
    }
    return str;
}


function setFloatSize() {
    function setSize() {
        var windowHeight = window.innerHeight,
            windowWidth = window.innerWidth;
        if (windowHeight < windowWidth) {
            $('.float_page').css('width', '56%')
        } else {
            $('.float_page').css('width', '80%')
        }
    };
    setSize()
    $(window).resize(function () {
        setSize()
    })
}


/**
 * 中富部实名认证和绑定手机号功能
 */
 function authentication() {
    if(loginUser.accountId>0)
    {
    $.ajax({
        type: "get",
        url: "/index.php?ac=authentication&gameId=" + gameInfoJs.id + "",
        async: false,
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.status == 'success') {
                if (data.msg.type != 'no') {
                    if (data.msg.status == 'already') //已完成验证
                    {
                        $("#authenticationBox").hide();
                    } else if (data.msg.status == 'success') {
                        message_fade_out("info", "实名认证成功", data.second);
                    } else if (data.msg.status == 'false') {
                        message_fade_out("error", "实名认证失败，重新填写", data.second);
                        $("#authenticationBox").html(data.msg.html);
                        $("#authenticationBox").show();
                        bindSubmit();
                    } else {
                        $("#authenticationBox").html(data.msg.html);
                        $("#authenticationBox").show();
                        bindSubmit();
                    }
                } else {
                    $("#authenticationBox").hide();
                }
            }

        }
    });
}
}
/**
 * 游戏的登录和登出时间
 */
function authentication_loginout() {
    var timer = setInterval(function () {
        var url = "/index.php?ac=authentication&op=loginout&type=login&gameId=" + gameInfoJs.id + "";
        var post='';
        ajax_post(url, post, function (callback) {
            console.log(callback);
        });
    },1*60*1000);//1分钟更新一次游戏访问时间
}

function bindSubmit() {
    $(".bind-submit").click(function () {
        var demo = $(this).parents("form");
        var url = demo.attr("action");
        var post = demo.serialize();
        post = post + "&formhash=" + $("#formhash").val();
        ajax_post(url, post, function (callback) {
            if (callback.status == 'success') {
                message_fade_out("info", callback.msg, callback.second);
                $("#authenticationBox").hide();
            } else {
                message_fade_out("error", callback.msg, callback.second);
            }
        })
    })
}




