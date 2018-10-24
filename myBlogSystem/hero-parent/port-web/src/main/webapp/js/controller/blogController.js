 //控制层 
app.controller('blogController' ,function($scope,$controller  ,$location ,blogService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		blogService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		blogService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	
	
	//查询实体 
	$scope.findOne=function(){		
	var id= $location.search()['id'];//获取参数值
		if(id==null){
			return ;
		}		
		blogService.findOne(id).success(
			function(response){
				$scope.entity= response;	
				
		        //向富文本编辑器添加商品介绍
				editor.html($scope.entity.replyhit);
		        
			}
		);				
	}
	//查询实体 
	$scope.findBlog=function(){		
	    var id= $location.search()['id'];//获取参数值
		if(id==null){
			return ;
		}		
		blogService.findOne(id).success(
			function(response){
				$scope.entity= response;	
		        //向富文本编辑器添加商品介绍
		        editor.html($scope.entity.replyhit);
		   
			}
		);				
	}
	//保存 
	$scope.save=function(){	
		//提取文本编辑器的值
		$scope.entity.replyhit=editor.html();
	    var serviceObject;//服务层对象  	
	    
	     if( $scope.entity.title==null || $scope.entity.title==""){
	    	    alert("标题不能为空");
	    	}else{
			      
			                
						     if($scope.entity.id!=null){//如果有ID
									 serviceObject=blogService.update( $scope.entity ); //修改  
									 alert("修改成功1");
								}else{
								   serviceObject=blogService.add( $scope.entity  );//增加
										 alert("增加成功2");
								}	
									
								serviceObject.success(
								function(response){
									if(response.success){
									
										//重新查询 
									    $scope.entity={};
										editor.html('');//清空富文本编辑器
							         }else{
										//alert(response.message);
									 }
								}		
							  );
		     }
					  		
    }
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		blogService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 	
	//搜索
	$scope.search=function(page,rows){			
		blogService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	

	//搜索跳转
	$scope.jump=function(){
		location.href="page-search.html#?keywords="+$scope.keywords;
	}

	
	
	
    
});	
