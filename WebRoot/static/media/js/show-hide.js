$(function(){
$("label").css({"font-size":"12px","cursor":"default","color":"#666"})
$("input").css({"font-size":"12px","font-family":"'微软雅黑','宋体'"})
$("select").css({"font-size":"12px","font-family":"'微软雅黑','宋体'"})
$(".caption").css("color","#4D90FE")
$("[data-toggle=tab]").css("font-size","14px")
$(".btn").css({"font-size":"12px","font-family":"微软雅黑"})
$("ul.breadcrumb li a").css("font-size","12px")
$("ul.page-sidebar-menu li a span").css("font-size","14px")
})

function tab1show()
{
var tab1=document.getElementById("infomanage-1-add");
    tab1content=document.getElementById("infomange-1-add-content");
tab1content.style.display="block";//显示隐藏层
tab1.style.display="none";//隐藏层
}

function tab1back()
{
document.getElementById("infomanage-1-add").style.display="block";
document.getElementById("infomange-1-add-content").style.display="none";   
}

function tab2show()
{
var tab2=document.getElementById("infomanage-2-add");
    tab2content=document.getElementById("infomange-2-add-content");
    tabNavi=document.getElementById("tabNavi");
tab2content.style.display="block";//显示隐藏层
tab2.style.display="none";//隐藏层
//tabNavi.style.display="none";//隐藏层
}

function tab2back()
{
document.getElementById("infomanage-2-add").style.display="block";
document.getElementById("infomange-2-add-content").style.display="none";   
}

function tab3show()
{
var tab3=document.getElementById("infomanage-3-add");
    tab3content=document.getElementById("infomange-3-add-content");
tab3content.style.display="block";//显示隐藏层
tab3.style.display="none";//隐藏层
}

function tab3back()
{
document.getElementById("infomanage-3-add").style.display="block";
document.getElementById("infomange-3-add-content").style.display="none";   
}

function permissionshow()
{
var tab3=document.getElementById("permission-add");
    tab3content=document.getElementById("permission-add-content");
tab3content.style.display="block";//显示隐藏层
tab3.style.display="none";//隐藏层
}

function permissionback()
{
document.getElementById("permission-add").style.display="block";
document.getElementById("permission-add-content").style.display="none";   
}

function warningshow()
{
var tab3=document.getElementById("warning-add");
    tab3content=document.getElementById("warning-add-content");
tab3content.style.display="block";//显示隐藏层
tab3.style.display="none";//隐藏层
}

function warningback()
{
document.getElementById("warning-add").style.display="block";
document.getElementById("warning-add-content").style.display="none";   
}
