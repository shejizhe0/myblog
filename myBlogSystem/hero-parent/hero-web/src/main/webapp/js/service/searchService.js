//搜索服务层
app.service("searchService",function($http){
	this.search=function(searchMap){
		return $http.post('../blogsearch/search.do',searchMap);
	}
});