<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()
    		+":"+request.getServerPort()+path+"/";%>      


<!DOCTYPE html>
<html >
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>量化项目管理工具</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="QPM,QPMTools,量化项目管理工具,量化项目管理">
    <meta name="author" content="boruxu">

    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>
    <link href='css/scatterplot_styles.css' rel='stylesheet'>
    
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The fav icon 未解决之后修改-->
    <link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
        
            <a class="navbar-brand" href="index.html"> <img alt="QPMTools Logo" src="img/logo20.png" class="hidden-xs"/>
                <span>量化项目管理工具</span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs">用户名字长用户名字长</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">个人设置</a></li> 
                    <li class="divider"></li>
                    <li><a href="#">个人仓库</a></li> 
                    <li class="divider"></li>
                    <li><a href="login.html">登出</a></li>
                </ul>
            </div>

            <ul class="collapse navbar-collapse nav navbar-nav top-menu pull-right">
            <!--
                <li><a href="#"><i class="glyphicon glyphicon-globe"></i> Visit Site</a></li>
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> Dropdown <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>-->
                <li >
                    <form class="navbar-search pull-left">
                        <input placeholder="搜索" class="search-query form-control col-md-10" name="query"
                               type="text">
                    </form>
                </li>
            </ul>

        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">主菜单</li>
                        <li><a class="ajax-link" href="home"><i class="glyphicon glyphicon-home"></i><span>主页</span></a>
                        </li>
                        <li><a class="ajax-link" href="correlation"><i class="glyphicon glyphicon-eye-open"></i><span>相关性分析</span></a>
                        </li>
                        <li><a class="ajax-link" href="form.html"><i  class="glyphicon glyphicon-edit"></i><span>回归分析</span></a></li>
                        <li><a class="ajax-link" href="spc"><i class="glyphicon glyphicon-list-alt"></i><span>统计过程控制</span></a></li>
                        <li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span>蒙特卡洛模拟</span></a>
                        </li>
                        <li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span>层次分析法</span></a>
                        </li>
      
                    <label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">警告!</h4>

                <p>使用本网站的一些功能，你必须解除对 <a href="http://baike.baidu.com/view/16168.htm" target="_blank">JavaScript</a>
                    的限制</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
            <div>
            <!--显示访问路径-->
    <ul class="breadcrumb">
        <li>
            <a href="spc">统计过程控制</a>
        </li>
    </ul>
</div>
<div class="row">
     <div class="box col-md-12">
        <div class="box-inner row" style="margin-right:0px; margin-left:0px; ">
            <div class="box-header well col-lg-12 col-md-12" >
                <h2><i class="glyphicon glyphicon-info-sign"></i>统计过程控制输入</h2>           
                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content col-lg-12 col-md-12 " >
                      <ul class="nav nav-tabs " id="myTab">
                          <li class="active"><a href="#inputFromDB">从资产库获取输入数据</a></li>
                          <li class=""><a href="#inputByUser">自定义输入</a></li>
                      </ul>

                      <div id="myTabContent" class="tab-content  col-lg-12">
                           <div class="tab-pane active" id="inputFromDB">
                                <div class="control-group col-lg-10 col-md-10">
                                      <!--<label class="control-label  " for="selectTegs" >从资产库选择输入</label>-->

                                      <div class="controls" style=" margin-top:10px">
                                           <select id="selectTags" multiple class="form-control" >
                                               <optgroup label="组织名称">
                                                  <option>北航软件所过程改进小组</option>
                                                  <option>XXX公司1</option>
                                               </optgroup>
                                               <optgroup label="项目名称">
                                                  <option>量化项目管理工具</option>
                                                  <option>测试项目1</option>
                                               </optgroup>
                                               <optgroup label="相关性分析输入产品名">
                                                   <option>QPM测试输入</option>
                                               </optgroup>
                                           </select>
                                      </div>
                                  </div>
                           </div>
                           <div class="tab-pane " id="inputByUser">
                                <h3>设置输入
                                    <small>可以从资产库导入数据进行修改</small>
                                </h3>
                           </div>
                     </div>
                <div class="col-lg-12 col-md-12 ">
                        
                        <p class="center-block download-buttons" id="plot-button" >
                        <a  class="btn btn-primary btn-lg"  onClick=""><i
                                class="glyphicon glyphicon-globe glyphicon-white"></i>提交计算</a>
                        </p>
                       
               </div>
               
   
                          
           </div>
        </div>
    </div>
     
</div>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i>控制异常点</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting-alpha btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#"  class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
                <div class="col-lg-12 col-md-12 ">
                    <p>
                       <h4 style="color:#000">无异常<h2>
                    </p>
           
                </div>            
           </div>
        </div>
    </div>
</div>
<div class="row">  
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" id="spcplot" >
                <h2><i class="glyphicon glyphicon-info-sign"></i>控制图</h2>           
                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
            
                 <div class="col-lg-12 col-md-12 "  style=" text-align:left">样本均值</div>

                 <div class="col-lg-12 col-md-12 " id="spcSvg" >
                      
                      <svg id="svg_1">
                      </svg>
                </div>
                <div class="col-lg-12 col-md-12 "  style=" text-align:center">样本</div>
                <div class="col-lg-12 col-md-12 "  style=" text-align:left">样本均值</div>
                <div class="col-lg-12 col-md-12 "  >
                      
                      <svg id="svg_2">
                      </svg>
                </div>
                <div class="col-lg-12 col-md-12 "  style=" text-align:center">样本</div>  
     
                <div class="col-lg-12 col-md-12 ">
                        
                        <p class="center-block download-buttons" id="plot-button" >
                        <a href="/" class="btn btn-default btn-lg"><i
                                class="glyphicon glyphicon-download-alt"></i>下载报告</a>
                        </p>
                       
               </div>
   
                          
           </div>
        </div>
    </div>
   

</div>
    <!--设置控制准则框-->
    <div class="modal fade" id="alpha" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <!--<button type="button" class="close" data-dismiss="modal">×</button>-->
                    <h3>设置显著水平α值</h3>
                </div>
                <div class="modal-body">
                    <input id="alphaV" autofocus value="0.05" style=" text-align:center;"></input>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">设置</a>
                </div>
            </div>
        </div>
    </div>

   
</div><!--/.fluid-container-->
<hr class="col-md-12 col-lg-12">
    
<footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="" target="_blank">北航计算机学院软件所课题改进小组</a> 2014-? </p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">作者：<a
                href="">xuboru</a></p>
</footer>


<!-- external javascript -->

<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>
<script type="text/javascript" src="js/d3.js"></script>


<script type="text/javascript" src="js/spcD3.js"></script>
<style>
.dot {
  fill: #fff;
  stroke: steelblue;
}
text.dot {
  fill: #fff;
  stroke: black;
  opacity:0;
}
g.dot:hover text.dot{
  fill: #fff;
  stroke: black;
  opacity:1;
}
path.line {
fill: none;
stroke: steelblue;
stroke-width: 3;
} 
path.line_2 {
fill: none;
stroke: darkcyan;
stroke-width: 3;
}
line._0 {
fill: none;
stroke: orangered ;
stroke-width: 3;
}
line._1 {
fill: none;
stroke: yellowgreen;
stroke-width: 3;
}
line._2 {
fill: none;
stroke: orangered ;
stroke-width: 3;
}
</style>
<script type="text/javascript">

 var svgX="#svg_1";
 var svgR="#svg_2";
 var spcOutData={x:[10.948, 10.95, 10.94, 10.938, 10.974, 10.944, 10.936, 10.944, 10.95, 10.934, 10.954, 10.95, 10.96, 10.938, 10.968, 10.94, 10.964, 10.966, 10.96, 10.94, 10.954, 10.954, 10.946, 10.962, 10.942],
 time:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25],
 r:[0.08, 0.07, 0.06, 0.03, 0.1, 0.06, 0.08, 0.03, 0.04, 0.05, 0.04, 0.06, 0.04, 0.05, 0.11, 0.02, 0.04, 0.08, 0.07, 0.06, 0.03, 0.08, 0.03, 0.1, 0.04],
 xUCL:10.9837,
 xCL:10.9502,
 xLCL:10.9168,
 rUCL:0.1226,
 rCL:0.058,
 rLCL:0.0};
 var width=$("#spcplot").width();
 spcD3.size(width,400,50).XR(spcOutData,svgX,svgR);
  
 function empty(){
	 spcD3.empty(svgX,svgR);
	 }


</script>
   

</body>
</html>

