package com.cubic.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductSearchResult {

	private List<ProductVO> products;

	public List<ProductVO> getProducts() {
		if (products == null) {
			products = new ArrayList<ProductVO>();
		}
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

}
