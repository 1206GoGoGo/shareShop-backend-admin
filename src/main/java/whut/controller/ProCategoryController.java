package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ProductCategory;
import whut.service.ProCategoryService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/category")
public class ProCategoryController {
	
	@Autowired
	public ProCategoryService proCategoryService;
	
	//获取第一层级分类列表
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(int pageindex, int pagesize) {
		return proCategoryService.getList(pageindex, pagesize);
	}
	
	//根据父分类ID获取子分类列表
	@RequestMapping(value = "/getListByParentId", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByParentId(String id) {
		return proCategoryService.getListByParentId(id);
	}
	
	//新增分类
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData add(@RequestBody ProductCategory productCategory) {		
		return proCategoryService.add(productCategory);
			
	}
	
	//修改分类
	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData modify(@RequestBody ProductCategory productCategory){
		return proCategoryService.modify(productCategory);
		
	}
	
	//删除分类,其下有子分类时判断
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody ResponseData delete(String id){
		return proCategoryService.delete(id);
	}
	
	//删除分类,其下有子分类也删，修改其下子分类状态都为0
	@RequestMapping(value = "/deleteConfirm", method = RequestMethod.GET)
	public @ResponseBody ResponseData deleteConfirm(String id){
		return proCategoryService.deleteConfirm(id);
		
	}
	
}
