package com.cubic.rest;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.cubic.entity.ProductEntity;
import com.cubic.util.Category;
import com.cubic.util.Manufacturer;
import com.cubic.vo.ProductVO;

@Component
public class ProductValidator {

	/*@Autowired
	ProductService service;*/

	/*******************************************************************************************************************
	 *************************** sets up ProductVO
	 ********************************************************************************************************************/

	public ProductVO setUpProductVO(ProductEntity entity, ProductVO vo) {

		if (entity.getCreatedDate() == null) {
			vo.setCreatedDate(new Date());
		}else{
			vo.setCreatedDate(entity.getCreatedDate());
		}

		if (vo.getActiveStatus()) {

			if (entity.getActiveDate() == null) {
				vo.setActiveDate(new Date());
				vo.setInactiveDate(null);
			}else{
				vo.setActiveDate(entity.getActiveDate());
			}

			if (vo.getQtyCurrent() != null && vo.getQtyThreshold() != null && vo.getQtyOrder() != null) {
				if (vo.getQtyCurrent() < vo.getQtyThreshold()) {
					vo.setQtyCurrent(vo.getQtyCurrent() + vo.getQtyOrder());
				}
			}

		} else {

			if (entity.getInactiveDate() == null) {
				vo.setActiveDate(null);
				vo.setInactiveDate(new Date());
			}else{
				vo.setInactiveDate(entity.getInactiveDate());
			}

		}

		return vo;

	}

	/*******************************************************************************************************************
	 *************************************** Null Entry Validation
	 ********************************************************************************************************************/

	public boolean checkNull(ProductVO vo) {
		boolean isNull = false;

		if (vo.getActiveStatus()
				&& (vo.getQtyCurrent() == null || vo.getQtyThreshold() == null || vo.getQtyOrder() == null)) {
			isNull = true;
		}

		if (vo.getCategory() == null || vo.getManufacturer() == null || vo.getName() == null
				|| vo.getUpcCode() == null) {
			isNull = true;
		}

		return isNull;

	}

	/*******************************************************************************************************************
	 ************************************* Manufacturer Entry Validation
	 ********************************************************************************************************************/

	public boolean checkManufacturerEnum(ProductVO vo) {
		boolean checkManufacturerEnum = false;

		for (Manufacturer manufacturer : Manufacturer.values()) {
			if (vo.getManufacturer().equalsIgnoreCase(manufacturer.name())) {
				checkManufacturerEnum = true;
				break;
			}
		}

		return checkManufacturerEnum;

	}

	/*******************************************************************************************************************
	 *********************************** Category Entry Validation
	 ********************************************************************************************************************/

	public boolean checkCategoryEnum(ProductVO vo) {
		boolean checkCategoryEnum = false;

		for (Category category : Category.values()) {
			if (vo.getCategory().equalsIgnoreCase(category.name())) {
				checkCategoryEnum = true;
				break;
			}
		}

		return checkCategoryEnum;

	}

	/*******************************************************************************************************************
	 ************************************* UPC Format Validation
	 ********************************************************************************************************************/

	public boolean checkUPCFormat(ProductVO vo) {
		String upcCode = vo.getUpcCode();
		boolean UPCFormat = false;

		if (upcCode.length() == 15 && upcCode.charAt(1) == '-' && upcCode.charAt(7) == '-'
				&& upcCode.charAt(13) == '-') {
			UPCFormat = true; // 0-12345-67890-0
		}

		return UPCFormat;

	}

}
