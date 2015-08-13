/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*------------------------------------------------------------------------------------------*/

/**
 * 回复留言 note
 */

function replyNote(id) {
    div = document.getElementById(id);
    div.className="show-reply-note";
}

function cancelReNote(id) {
    /*
     * 回复内容设为空
     */
    testarea = document.getElementsByName("renotecontent");
    for(i=0; i<testarea.length; i++) {
        testarea[i].textContent="";
    }
    div = document.getElementById(id);
    div.className="hide-reply-note";
}

function confirmReNote(name) {
    //    text = document.getElementById("renotecontent");
    //    a = document.getElementById("addreply");
    //    a.href = a.href  + "&&renotecontent=" + text.value;
    subform = document.getElementsByTagName("form");
    for(i=0;i<subform.length;i++) {
        if(subform[i].name == name) {
            subform[i].submit();
        }
    }
}
/*-------------------------------------------------------------------------------*/

/**
 * 管理分类页面 newstype
 */

function showEditType(id) {
    div = document.getElementById(id);
    div.className="show-edit-type";
    //hide
    hid = "pre" + id;
    hdiv = document.getElementById(hid);
    hdiv.className="hide-edit-type";
}

function cancelEditType(id) {
    text = document.getElementsByName("type");
    for(i=0; i<text.length; i++) {
        text[i].textContent="";
    }
    div = document.getElementById(id);
    div.className="hide-edit-type";
    hid = "pre" + id;
    hdiv = document.getElementById(hid);
    hdiv.className="show-edit-type";
}

function showAddType() {
    div = document.getElementById("show-add-news-type");
    //addtype为显示
    div.className="show-add-type";
}

function cancelAddType() {
    text = document.getElementsByName("type");
    for(i=0; i<text.length; i++) {
        text[i].textContent="";
    }
    div = document.getElementById("show-add-news-type");
    //showaddtype为隐藏
    div.className="hide-add-type";
}

/**
 * 日志添加、预览
 */
function previewSubmit(cid) {
    newsform = document.getElementsByName("newsform");
    newsform[0].action = "previewnews.do?cid=" + cid;
    newsform[0].submit();
}
function addSubmit(cid) {
    newsform = document.getElementsByName("newsform");
    newsform[0].action = "addnews.do?cid=" + cid;
    newsform[0].submit();
}
function previewMSubmit(cid,newsID) {
    //修改日志的预览，与增加时的区别在于参数M
    newsform = document.getElementsByName("newsform");
    newsform[0].action = "previewnews.do?method=M&&cid=" + cid + "&&newsID=" + newsID;;
    newsform[0].submit();
}
function morifySubmit(cid) {
    newsform = document.getElementsByName("newsform");
    newsform[0].action = "motifynews.do?cid=" + cid;
    newsform[0].submit();
}

/**
 * 个人信息修改与预览 personalinfo.jsp
 */
function showBasicInfo() {
    div = document.getElementById("basic-info");
    div.className="show";
    hdiv1 = document.getElementById("blog-info");
    hdiv1.className="hide";
    hdiv2 = document.getElementById("preview-info");
    hdiv2.className="hide";
}
function showBlogInfo() {
    hdiv1 = document.getElementById("basic-info");
    hdiv1.className="hide";
    div = document.getElementById("blog-info");
    div.className="show";
    hdiv2 = document.getElementById("preview-info");
    hdiv2.className="hide";
}
function showPreviewInfo() {
    hdiv1 = document.getElementById("basic-info");
    hdiv1.className="hide";
    hdiv2 = document.getElementById("blog-info");
    hdiv2.className="hide";
    div = document.getElementById("preview-info");
    div.className="show";
}

/**
 * 好友管理页面
 */
function showFriendInfo() {
    div = document.getElementById("friend-info");
    div.className="show";
    hdiv1 = document.getElementById("friend-request");
    hdiv1.className="hide";
    hdiv2 = document.getElementById("friend-track");
    hdiv2.className="hide";
    hdiv3 = document.getElementById("my-track");
    hdiv3.className="hide";
    a1 = document.getElementById("friend-info-a");
    a1.className="current-tab";
    a2 = document.getElementById("friend-request-a");
    a2.className="not-current-tab";
    a3 = document.getElementById("friend-track-a");
    a3.className="not-current-tab";
    a4 = document.getElementById("my-track-a");
    a4.className="not-current-tab";
}
function showFriendRequest() {
    hdiv1 = document.getElementById("friend-info");
    hdiv1.className="hide";
    div = document.getElementById("friend-request");
    div.className="show";
    hdiv2 = document.getElementById("friend-track");
    hdiv2.className="hide";
    hdiv3 = document.getElementById("my-track");
    hdiv3.className="hide";
    a1 = document.getElementById("friend-info-a");
    a1.className="not-current-tab";
    a2 = document.getElementById("friend-request-a");
    a2.className="current-tab";
    a3 = document.getElementById("friend-track-a");
    a3.className="not-current-tab";
    a4 = document.getElementById("my-track-a");
    a4.className="not-current-tab";
}
function showFriendTrack() {
    hdiv1 = document.getElementById("friend-info");
    hdiv1.className="hide";
    hdiv2 = document.getElementById("friend-request");
    hdiv2.className="hide";
    div = document.getElementById("friend-track");
    div.className="show";
    hdiv3 = document.getElementById("my-track");
    hdiv3.className="hide";
    a1 = document.getElementById("friend-info-a");
    a1.className="not-current-tab";
    a2 = document.getElementById("friend-request-a");
    a2.className="not-current-tab";
    a3 = document.getElementById("friend-track-a");
    a3.className="current-tab";
    a4 = document.getElementById("my-track-a");
    a4.className="not-current-tab";
}
function showMyTrack() {
    hdiv1 = document.getElementById("friend-info");
    hdiv1.className="hide";
    hdiv2 = document.getElementById("friend-request");
    hdiv2.className="hide";
    hdiv3 = document.getElementById("friend-track");
    hdiv3.className="hide";
    div = document.getElementById("my-track");
    div.className="show";
    a1 = document.getElementById("friend-info-a");
    a1.className="not-current-tab";
    a2 = document.getElementById("friend-request-a");
    a2.className="not-current-tab";
    a3 = document.getElementById("friend-track-a");
    a3.className="not-current-tab";
    a4 = document.getElementById("my-track-a");
    a4.className="current-tab";
}

/**
 * 心情页面
 */
function showFriendMood() {
    div = document.getElementById("friend-mood");
    div.className="show";
    hdiv1 = document.getElementById("my-mood");
    hdiv1.className="hide";
    a1 = document.getElementById("friend-mood-a");
    a1.className="current-tab";
    a2 = document.getElementById("my-mood-a");
    a2.className="not-current-tab";
}
function showMyMood() {
    hdiv1 = document.getElementById("friend-mood");
    hdiv1.className="hide";
    div = document.getElementById("my-mood");
    div.className="show";
    a1 = document.getElementById("friend-mood-a");
    a1.className="not-current-tab";
    a2 = document.getElementById("my-mood-a");
    a2.className="current-tab";
}
/**
  * HOME
 */
function showHomeTrack() {
    div = document.getElementById("home-friend-track");
    div.className="show";
    hdiv1 = document.getElementById("home-my-track");
    hdiv1.className="hide";
}
function showMyHomeTrack() {
    hdiv1 = document.getElementById("home-friend-track");
    hdiv1.className="hide";
    div = document.getElementById("home-my-track");
    div.className="show";
}