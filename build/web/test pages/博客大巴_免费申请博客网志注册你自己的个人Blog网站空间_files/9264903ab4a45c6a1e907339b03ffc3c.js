document.write("<div id='guanggao9264903ab4a45c6a1e907339b03ffc3c'></div>");
function ad9264903ab4a45c6a1e907339b03ffc3c(){this.i=0;
this.imgsrc="/useruploads/images/adv_img/2010/04/23/1271995456_adv_img_4568453.jpg,/useruploads/images/adv_img/2010/04/23/1271995489_adv_img_48916a5.jpg,/useruploads/images/adv_img/2010/04/22/1271916155_adv_img_155484a.jpg,/useruploads/images/adv_img/2010/04/23/1271995862_adv_img_8626e77.jpg";
this.imgurl="http://pindao.blogbus.com/shenghuo/201004226270.html,http://pindao.blogbus.com/xingzhe/201004226273.html,http://pindao.blogbus.com/sejie/201004226286.html,http://fashion.blogbus.com/logs/62364317.html";
this.imgid="218,215,216,217";
this.adv_text=",,,";
this.type="img,img,img,img";
this.width="618,618,618,618";
this.height="160,160,160,160";
this.array_src=this.imgsrc.split(",");
this.array_url=this.imgurl.split(",");
this.array_imgid=this.imgid.split(",");
this.array_adv_text=this.adv_text.split(",");
this.array_type=this.type.split(",");
this.array_width=this.width.split(",");
this.array_height=this.height.split(",");
}


var getdata9264903ab4a45c6a1e907339b03ffc3c=new ad9264903ab4a45c6a1e907339b03ffc3c();
guanggaoshow9264903ab4a45c6a1e907339b03ffc3c();
var adCur;
var numCount = getdata9264903ab4a45c6a1e907339b03ffc3c.array_src.length;
function selectnum()
{
    for(var a=0;a<numCount;a++)
    {
        var object = document.getElementById("adSel_"+a);
        object.style.backgroundColor='#89bf29';
    }
}
document.write('<div style="margin:2px auto;background-color:#ddd;float:right;padding:1px 0;"><div style="float:left;">');

for(var i=0;i<getdata9264903ab4a45c6a1e907339b03ffc3c.array_src.length;i++)
{
document.write('<span onclick="selectnum();this.style.color=\'#fff\';this.style.backgroundColor=\'#f79417\';this.style.padding=\'1px 10px\';this.style.margin=\'1px\';this.style.cursor=\'pointer\';adCur=this;getdata9264903ab4a45c6a1e907339b03ffc3c.i='+i+';guanggaoshow9264903ab4a45c6a1e907339b03ffc3c()" style="background-color:#89bf29;color:#fff;padding:1px 10px;margin:1px;cursor:pointer;" id="adSel_'+i+'">'+(i+1)+'</span>');
adCur=document.getElementById('adSel_0');
adCur.style.color='#fff';
adCur.style.backgroundColor='#f79417';
adCur.style.padding='1px 10px';
adCur.style.margin='1px';
adCur.style.cursor='pointer';
}
document.write('</div></div><div style="clear:both;"></div>');
function guanggaoshow9264903ab4a45c6a1e907339b03ffc3c()
{var getdata=getdata9264903ab4a45c6a1e907339b03ffc3c;var str='';str="<a href='http://pro.blogbus.com/state.php?id="+getdata9264903ab4a45c6a1e907339b03ffc3c.array_imgid[getdata9264903ab4a45c6a1e907339b03ffc3c.i]+"' target='_blank'>";
if(getdata9264903ab4a45c6a1e907339b03ffc3c.array_type[getdata9264903ab4a45c6a1e907339b03ffc3c.i].indexOf("text")>=0)
{
str+=getdata9264903ab4a45c6a1e907339b03ffc3c.array_adv_text[getdata9264903ab4a45c6a1e907339b03ffc3c.i];
str+="</a>";
}else{
var xxx=getdata.array_src[getdata9264903ab4a45c6a1e907339b03ffc3c.i];
if(xxx.indexOf("swf")>0)
{
str="<embed border='0' showdisplay='0' showcontrols='1' autostart='1' style='width:"+getdata9264903ab4a45c6a1e907339b03ffc3c.array_width[getdata9264903ab4a45c6a1e907339b03ffc3c.i]+"px;height:"+getdata9264903ab4a45c6a1e907339b03ffc3c.array_height[getdata9264903ab4a45c6a1e907339b03ffc3c.i]+"px' autorewind='0' playcount='0' src='http://pro.blogbus.com"+getdata9264903ab4a45c6a1e907339b03ffc3c.array_src[getdata9264903ab4a45c6a1e907339b03ffc3c.i]+"' type='application/x-shockwave-flash'></embed>";
}else{
if(getdata9264903ab4a45c6a1e907339b03ffc3c.array_src[getdata9264903ab4a45c6a1e907339b03ffc3c.i] != '') {
str+="<img src='http://pro.blogbus.com"+getdata9264903ab4a45c6a1e907339b03ffc3c.array_src[getdata9264903ab4a45c6a1e907339b03ffc3c.i]+"' border=0/>";
}
str+="</a>";
}}
document.getElementById('guanggao9264903ab4a45c6a1e907339b03ffc3c').innerHTML=str;getdata9264903ab4a45c6a1e907339b03ffc3c.i++;
if(getdata9264903ab4a45c6a1e907339b03ffc3c.i>getdata9264903ab4a45c6a1e907339b03ffc3c.array_src.length-1)
{getdata9264903ab4a45c6a1e907339b03ffc3c.i=0;}
}
setInterval("guanggaoshow9264903ab4a45c6a1e907339b03ffc3c()",10000);