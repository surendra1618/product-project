package com.cubic.service;

import java.util.List;

import com.cubic.vo.ProductVO;

public interface ProductService {

	ProductVO save(final ProductVO vo);

	List<ProductVO> searchAll();//not good

	List<ProductVO> searchByName(final String name);

	List<ProductVO> searchByProductNumber(final Long productNumber);

	List<ProductVO> searchByUPC(final String upcCode);

	Long checkPostDuplicate(final String name, final String upcCode);

	Long checkPutDuplicate(final Long pk, final String name, final String upcCode);

	ProductVO findProduct(final Long pk);

	void remove(final Long pk);

}
