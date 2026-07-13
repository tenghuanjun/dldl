new Vue({
    el: '#xy-assistant-pc',
    data: {
        ispc: !SDK.CMOB(),
        activeName: 'ok',   // 默认选中项
        tableData: [],
        timestamp: 0,
        serverOk: [],
        serverWait: [],
        gameName: "",
        gameID: 0,
    },
    methods: {

        handleEdit: function (index, row, thispage) {
            if (thispage) {
                location.href = ('/' + this.gameID + '/?sid=' + row.serverID);
            }else{
                window.open('/' + this.gameID + '/?sid=' + row.serverID, "_blank");
            }
        },

        // 切换卡片
        handleClick: function (tab, event) {
            var _this = this;
            if (tab.name == "ok") {
                _this.tableData = _this.serverOk;
            } else if (tab.name == "wait") {
                _this.tableData = _this.serverWait;
            }
        },

        gotoIndex: function () {
            window.location.href = "/"
        },

        GatData: function (gid) {
            var _this = this;
            var parm = {
                cmd: "get.server.list",
                gid: gid,
            };
            SDK.Ajax(parm, function (jon) {
                if (jon.code == 1) {

                    _this.gameName = jon.data.gname;
                    _this.gameID = jon.data.gameid;
                    var tt = [];
                    _this.timestamp = (new Date()).getTime();
                    $.each(jon.data.server, function (i, v) {

                        var yy = v.opentime.substr(0, 4);
                        var mm = v.opentime.substr(5, 2);
                        var dd = v.opentime.substr(8, 2);
                        var shi = v.opentime.substr(11, 2);
                        var fen = v.opentime.substr(14, 2);
                        var miao = v.opentime.substr(17, 2);

                        var date = yy + "-" + mm + "-" + dd + " " + shi + ":" + fen + ":" + miao;
                        if (_this.ispc) {
                            var dateS = yy + "年" + mm + "月" + dd + "日 " + shi + "时" + fen + "分";
                        } else {
                            var dateS = mm + "月" + dd + "日 " + shi + "时" + fen + "分";
                        }
                        var ot = _this.DateSix(date);

                        tt.push({
                            dateSJZ: ot,
                            date: dateS,
                            server: v.servername,
                            serverID: v.serverid,
                            serverType: v.servertype,
                            is_open: v.is_open,
                        });


                    })
                    _this.chuli(tt);

                } else {
                    window.location.href = "/";
                }
            })
        },

        chuli: function (tt) {
            var _this = this;
            $.each(tt, function (i, v) {
                if (v.dateSJZ < _this.timestamp) {
                    _this.serverOk.push(v);
                } else {
                    _this.serverWait.push(v);
                }
            })
            _this.handleClick({ name: "ok" })
            if (tt.length == 1 && _this.serverOk.length == 1) {
                _this.handleEdit(0,_this.serverOk[0],true);
            }
        },

        DateSix: function (date) {

            date = date.replace(/-/g, '/');
            var timestamp = new Date(date).getTime();
            return (timestamp);
        }




    },
    mounted: function () {
        SDK.AjaxPath = "/api/system/";

        var _this = this;
        var gid = SDK.GUP("gid");
        if (gid) {
            this.GatData(gid);
        }
    }

})
