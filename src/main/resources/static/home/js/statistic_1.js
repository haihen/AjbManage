function getCookie(name) {
    var arr = document.cookie
        .match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null)
        return unescape(arr[2]);
    return "";
}
function getFid() {
    return getCookie('fid');
}
function getUid() {
    return getCookie(getFid() + 'UID');
}
var pageOpenStart = new Date();
function writeRefCgi() {
    var pageCloseStart = new Date();
    var fromUrl = escape(document.referrer);
    var viewUrl = escape(window.location);
    var type="1";

    var fid="";
    if(fid==""){
        fid=getFid();
    }
    var uid="";
    if(uid==""){
        uid=getUid();
    }
    var keyword="";
    if(keyword==""){
        keyword=escape(document.title);
    }else{
        keyword=escape(keyword);
    }
    var title="";
    if(title==""){
        title=escape(document.title);
    }else{
        title=escape(title);
    }
    var fromurl="";
    if(fromurl==""){
        fromurl=fromUrl;
    }else{
        fromurl=escape(fromurl);
    }
    var viewurl="";
    if(viewurl==""){
        viewurl=viewUrl;
    }else{
        viewurl=escape(viewurl);
    }

    var ref_u = '../statistic.cxgt.chaoxing.com/cgi-bin/ref.cgi?type='+ type +'&fid=' + fid + '&uid=' + uid + '&keyword=' + keyword + '&view_title=' + title + '&fromurl=' + fromurl + '&viewurl='
        + viewurl + '&viewtime='
        + (pageCloseStart.getTime() - pageOpenStart.getTime()) + '&r='
        + (new Date()).getTime();
    (new Image()).src = ref_u;
}
window.onunload = function() {
//	writeRefCgi();
};
window.onload = function() {
//        writeRefCgi();
};
writeRefCgi();