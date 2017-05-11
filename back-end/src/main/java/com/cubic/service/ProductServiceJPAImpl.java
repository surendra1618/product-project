package com.cubic.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.entity.ProductEntity;
import com.cubic.exception.DuplicateException;
import com.cubic.exception.InvalidInputException;
import com.cubic.rest.ProductValidator;
import com.cubic.vo.ProductVO;

@Service
@Transactional
public class ProductServiceJPAImpl implements ProductService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ProductService service;

	@Autowired
	private ProductMapper mapper;

	@Autowired
	ProductValidator productValidator;

	public ProductVO save(ProductVO vo) {
		ProductEntity entity = null;

		if (productValidator.checkNull(vo)) {
			String str = "Sorry! All the required fields (*) must be entered";
			throw new InvalidInputException(str);
		}

		if (!productValidator.checkManufacturerEnum(vo)) {
			String str = "Sorry! Invalid Manufacturer. Please select a different Manufacturer";
			throw new InvalidInputException(str);
		}

		if (!productValidator.checkCategoryEnum(vo)) {
			String str = "Sorry! Invalid Category. Please select a different Category";
			throw new InvalidInputException(str);
		}

		if (!productValidator.checkUPCFormat(vo)) {
			String str = "Sorry! Invalid UPC Format. Please enter UPC Code in this format 0-12345-67890-1";
			throw new InvalidInputException(str);
		}

		if (vo.getPk() == null) {

			if (service.checkPostDuplicate(vo.getName(), vo.getUpcCode()) > 0) {
				String str = "POST: Duplicate Name or UPC Code Found. Product name and UPC Code must be unique";
				throw new DuplicateException(str);
			}

			entity = mapper.mapToProductEntity(vo);

		} else {

			if (service.checkPutDuplicate(vo.getPk(), vo.getName(), vo.getUpcCode()) > 0) {
				String str = "PUT: Duplicate Name or UPC Code Found. Product name and UPC Code must be unique";
				throw new DuplicateException(str);
			}

			entity = em.find(ProductEntity.class, vo.getPk());
			entity = mapper.mapToProductEntity(entity, vo);

		}

		em.persist(entity);
		vo.setPk(entity.getPk());

		return vo;
	}

	public List<ProductVO> searchByName(final String name) {
		String queryParam = name.trim() + "%";
		TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.SearchByName", ProductEntity.class);
		query.setParameter("p1", queryParam);

		List<ProductEntity> entities = query.getResultList();

		return mapper.mapToProductVOList(entities);
	}

	public List<ProductVO> searchByUPC(final String upcCode) {
		String queryParam = upcCode.trim() + "%";
		TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.SearchByUPC", ProductEntity.class);
		query.setParameter("p1", queryParam);

		List<ProductEntity> entities = query.getResultList();

		return mapper.mapToProductVOList(entities);
	}

	public List<ProductVO> searchByProductNumber(final Long productNumber) {
		Long queryParam = productNumber;
		TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.SearchByProductNumber",
				ProductEntity.class);
		query.setParameter("p1", queryParam);

		List<ProductEntity> entities = query.getResultList();

		return mapper.mapToProductVOList(entities);
	}

	public List<ProductVO> searchAll() {
		TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.SearchAll", ProductEntity.class);
		List<ProductEntity> entities = query.getResultList();
		return mapper.mapToProductVOList(entities);

	}

	public Long checkPostDuplicate(final String name, final String upcCode) {
		String queryParamName = name.trim();
		String queryParamUPC = upcCode.trim();

		TypedQuery<Long> query = em.createNamedQuery("ProductEntity.SearchByExactNameForPost", Long.class);
		query.setParameter("p1", queryParamName);
		query.setParameter("p2", queryParamUPC);

		return query.getSingleResult();
	}

	public Long checkPutDuplicate(final Long pk, final String name, final String upcCode) {
		Long queryParamPk = pk;
		String queryParamName = name.trim();
		String queryParamUPC = upcCode.trim();

		TypedQuery<Long> query = em.createNamedQuery("ProductEntity.SearchByExactNameForPut", Long.class);
		query.setParameter("p1", queryParamPk);
		query.setParameter("p2", queryParamName);
		query.setParameter("p3", queryParamUPC);

		return query.getSingleResult();
	}

	public ProductVO findProduct(Long pk) {
		ProductEntity entity = em.find(ProductEntity.class, pk);
		return mapper.mapToProductVO(entity);
	}

	public void remove(Long pk) {
		ProductEntity entity = em.find(ProductEntity.class, pk);
		em.remove(entity);
	}

}
