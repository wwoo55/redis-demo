//根据传递过来的参数e获取对应的值
function getParameter(e) {
    var reg = new RegExp("(^|&)" + e + "=([^&]*)(&|$)","i");
    var r = location.search.substr(1).match(reg);
    if (r!=null) return (r[2]); return null;
}