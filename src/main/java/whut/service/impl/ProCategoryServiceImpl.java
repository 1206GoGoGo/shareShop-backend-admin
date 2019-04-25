package whut.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ProCategoryDao;
import whut.pojo.ProductCategory;
import whut.service.ProCategoryService;
import whut.utils.ResponseData;


@Service
public class ProCategoryServiceImpl implements ProCategoryService{

	@Autowired
	private ProCategoryDao proCategoryDao;
	
	@Override
	public ResponseData getList() {
		// TODO Auto-generated method stub		
		List<ProductCategory> list = proCategoryDao.getList();
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData add(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		
		ProductCategory productCategorynew = new ProductCategory();
		//根据传来的父分类id获取其所属层级
		if(proCategoryDao.getCategoryById(productCategory.getParentId()).getCategoryLevel() == null)
			return new ResponseData(400,"Level is null",null);
		int level = proCategoryDao.getCategoryById(productCategory.getParentId()).getCategoryLevel();
		productCategorynew.setCategoryLevel((byte) (level+1));
		productCategorynew.setCategoryName(productCategory.getCategoryName());
		productCategorynew.setParentId(productCategory.getParentId());
		productCategorynew.setCategoryStatus(productCategory.getCategoryStatus());
		proCategoryDao.add(productCategorynew);
		return new ResponseData(200,"add success",null);
	}

	@Override
	public ResponseData modify(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		proCategoryDao.modify(productCategory);
		return new ResponseData(200,"modify success",null);
	}

	@Override
	public ResponseData delete(String id) {
		// TODO Auto-generated method stub
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.getListByParentId(id);
		if(list.size() == 0) {
			proCategoryDao.delete(id);
			return new ResponseData(200,"delete success",null);
		}
		return new ResponseData(406,"There are subcategories under this category",null);
	}


	@Override
	public ResponseData deleteConfirm(String id) {
		// TODO Auto-generated method stub
		proCategoryDao.delete(id);
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.getListByParentId(id);
		for(int i = 0; i < list.size(); i++) {
			proCategoryDao.delete(list.get(i).getCategoryId().toString());
			List<ProductCategory> list1 = new ArrayList<>();
			list1 = proCategoryDao.getListByParentId(list.get(i).getCategoryId().toString());
			if(list1.size() > 0) {
				for(int j = 0; j < list1.size(); j++) {
					proCategoryDao.delete(list1.get(j).getCategoryId().toString());
				}
			}
		}
		return new ResponseData(200,"delete success",null);
	}

	@Override
	public ResponseData getListByParentId(String id) {
		// TODO Auto-generated method stub
		List<ProductCategory> list = new ArrayList<>();
		list = proCategoryDao.getListByParentId(id);
		if(list.size() == 0) {
			return new ResponseData(406,"There are no subcategories",null);
		}
		return new ResponseData(200,"success",list);
	}

	@Override
	public ResponseData modifyStatusNoShow(String id) {
		// TODO Auto-generated method stub
		proCategoryDao.modifyStatusNoShow(id);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData getCategoryByName(String name) {
		// TODO Auto-generated method stub
		List<ProductCategory> list = proCategoryDao.getCategoryByName(name);
		if(list.size() == 0) {
			return new ResponseData(400,"No similar category was found",null);
		}
		return new ResponseData(200,"success",list);
	}

	@Override
	public ResponseData getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		ProductCategory productCategory = proCategoryDao.getCategoryById(categoryId);
		if(productCategory == null) {
			return new ResponseData(400,"This Category is not found",null);
		}
		return new ResponseData(200,"success",productCategory);
	}

	@Override
	public ResponseData getCategoryByChildrenID(String id) {
		// TODO Auto-generated method stub
		ProductCategory productCategory = proCategoryDao.getCategoryByChildrenID(id);
		if(productCategory == null) {
			return new ResponseData(400,"This Category is not found",null);
		}
		return new ResponseData(200,"success",productCategory);
	}

}
