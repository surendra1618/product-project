package com.cubic.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.service.ProductService;
import com.cubic.vo.ProductSearchResult;
import com.cubic.vo.ProductVO;

@Service
@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRest {

	@Autowired
	private ProductService service;

	@POST
	public Response create(final ProductVO vo) {
		ProductVO result = service.save(vo);
		return Response.status(201).entity(result).build();

	}

	@PUT
	public Response update(final ProductVO vo) {
		ProductVO result = service.save(vo);
		return Response.ok().entity(result).build();
	}

	@GET
	@Path("/{id}")
	public Response findProduct(@PathParam("id") final Long pk) {
		ProductVO vo = service.findProduct(pk);
		return Response.ok().entity(vo).build();
	}

	@GET
	public Response getAll() {
		List<ProductVO> vos = service.searchAll();
		ProductSearchResult results = new ProductSearchResult();
		results.setProducts(vos);
		return Response.ok().entity(results).build();
	}

	@GET
	@Path("/name")
	public Response searchName(@QueryParam("searchName") final String name) {
		List<ProductVO> vos = service.searchByName(name);
		ProductSearchResult results = new ProductSearchResult();
		results.setProducts(vos);
		return Response.ok().entity(results).build();
	}

	@GET
	@Path("/upc")
	public Response searchUPC(@QueryParam("searchUPC") final String name) {
		List<ProductVO> vos = service.searchByUPC(name);
		ProductSearchResult results = new ProductSearchResult();
		results.setProducts(vos);
		return Response.ok().entity(results).build();
	}

	@GET
	@Path("/number")
	public Response searchProductNumber(@QueryParam("searchNumber") final Long productNumber) {
		List<ProductVO> vos = service.searchByProductNumber(productNumber);
		ProductSearchResult results = new ProductSearchResult();
		results.setProducts(vos);
		return Response.ok().entity(results).build();
	}

	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") final Long pk) {
		service.remove(pk);
		return Response.noContent().build();
	}

}
