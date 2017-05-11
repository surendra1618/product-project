package com.cubic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubic.entity.ProductEntity;
import com.cubic.exception.DataNotFoundException;
import com.cubic.rest.ProductValidator;
import com.cubic.vo.ProductVO;

@Component
public class ProductMapper {

	@Autowired
	ProductValidator productValidator;

	public ProductEntity mapToProductEntity(ProductEntity entity, ProductVO vo) {

		vo = productValidator.setUpProductVO(entity, vo);

		entity.setName(vo.getName());
		entity.setUpcCode(vo.getUpcCode());

		entity.setPrice(vo.getPrice());
		entity.setVersion(vo.getVersion());
		entity.setDescription(vo.getDescription());

		entity.setManufacturer(vo.getManufacturer());
		entity.setCategory(vo.getCategory());

		entity.setCreatedDate(vo.getCreatedDate());
		entity.setActiveDate(vo.getActiveDate());
		entity.setInactiveDate(vo.getInactiveDate());

		entity.setQtyCurrent(vo.getQtyCurrent());
		entity.setQtyThreshold(vo.getQtyThreshold());
		entity.setQtyOrder(vo.getQtyOrder());

		return entity;

	}

	public ProductEntity mapToProductEntity(ProductVO vo) {
		ProductEntity entity = new ProductEntity();
		entity = mapToProductEntity(entity, vo);

		return entity;

	}

	public ProductVO mapToProductVO(ProductEntity entity) {
		ProductVO vo = new ProductVO();

		vo.setPk(entity.getPk());
		vo.setProductNum(entity.getProductNum());

		vo.setName(entity.getName());
		vo.setUpcCode(entity.getUpcCode());

		vo.setPrice(entity.getPrice());
		vo.setVersion(entity.getVersion());
		vo.setDescription(entity.getDescription());

		vo.setCategory(entity.getCategory());
		vo.setManufacturer(entity.getManufacturer());

		vo.setCreatedDate(entity.getCreatedDate());
		vo.setActiveDate(entity.getActiveDate());
		vo.setInactiveDate(entity.getInactiveDate());

		vo.setQtyCurrent(entity.getQtyCurrent());
		vo.setQtyThreshold(entity.getQtyThreshold());
		vo.setQtyOrder(entity.getQtyOrder());

		if (entity.getActiveDate() != null) {
			vo.setActiveStatus(true);
		}

		return vo;
	}

	public List<ProductVO> mapToProductVOList(final List<ProductEntity> entities) {
		List<ProductVO> results = new ArrayList<ProductVO>();

		if (entities.size() == 0) {
			String str = "No Matching Product found in the database.";
			throw new DataNotFoundException(str);
		}

		for (ProductEntity entity : entities) {
			results.add(mapToProductVO(entity));
		}

		return results;
	}

}
