package whut.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ProductInfo;
import whut.service.ProInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/pro/info")
public class ProInfoController {
	
	@Autowired
	public ProInfoService proInfoService;
	
	
	//获取所有商品列表
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList() {
		return proInfoService.getList();

	}
	
	//根据商品id获取某商品详情
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetail(String id){
		return proInfoService.getDetail(id);
		
	}
	
	//根据商品码id获取某商品详情
	@RequestMapping(value = "/getDetailByCode", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetailByCode(String id){
		return proInfoService.getDetailByCode(id);

	}
	
	//添加新商品信息
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData add(@RequestBody ProductInfo productInfo){		
		return proInfoService.add(productInfo);
	}
	
	//根据商品名称查找商品
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody ResponseData search(String name){
		return proInfoService.search(name);
	}
	
	//修改某商品属性
	@RequestMapping(value = "/modify", method = RequestMethod.POST, consumes= "application/json")
	public @ResponseBody ResponseData modify(@RequestBody ProductInfo productInfo){
		return proInfoService.modify(productInfo);
		
	}
	
	//根据商品id修改某商品审核状态
	@RequestMapping(value = "/modifyAuditStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyAuditStatus(String id, String status){
		return proInfoService.modifyAuditStatus(id, status);
		
	}
	
	//根据商品id修改某商品上下架状态
	@RequestMapping(value = "/modifyShelfStatus", method = RequestMethod.GET)
	public @ResponseBody ResponseData modifyShelfStatus(String id, String status){
		return proInfoService.modifyShelfStatus(id, status);
		
	}
	
	//根据分类获取商品列表
	@RequestMapping(value = "/getListByCategory", method = RequestMethod.GET)
	public @ResponseBody ResponseData getListByCategory(String id){
		return proInfoService.getListByCategory(id);
		
	}
	
}
	

