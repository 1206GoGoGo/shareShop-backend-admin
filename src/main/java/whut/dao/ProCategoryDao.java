package whut.dao;

import java.util.List;
import java.util.Map;

import whut.pojo.ProductCategory;

public interface ProCategoryDao {

	public List<ProductCategory> getList(Map<String, Object> map);

	public void add(ProductCategory productCategory);

	public void modify(ProductCategory productCategory);

	public void delete(String id);

	public ProductCategory ifCategoryExist(String categoryCode);

	public List<ProductCategory> ifHaveChild(String id);

	

}
