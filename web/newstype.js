/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showEditType(id) {
    div = document.getElementById(id);
    div.className="preedittype";
    hid = "pre" + id;
    hdiv = document.getElementById(hid);
    hdiv.className="edittype";
}

function cancelEditType(id) {
    /*
     * 回复内容设为空
     */
    testarea = document.getElementsByName("renotecontent");
    for(i=0; i<testarea.length; i++) {
        testarea[i].testarea="";
    }
    div = document.getElementById(id);
    div.className="renote";
}

function confirmEditType(name) {
//    text = document.getElementById("renotecontent");
//    a = document.getElementById("addreply");
//    a.href = a.href  + "&&renotecontent=" + text.value;
    subform = document.getElementsByTagName("form");
    for(i=0;i<subform.length;i++) {
        if(subform[i].name == name) {
            subform[i].submit();
        }
    }
    //name.submit();
    //alert(a.href);
}


