package com.limitless.services.engage.sellers.product.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_prices_mapper")
public class ProductPricesMapper {
	@Id
	@GeneratedValue
	@Column(name="PPM_ID")
	private Integer ppmId;
	@Column(name="PRODUCT_ID")
	private Integer productId;
	@Column(name="PRODUCT_SIZE_NUMBER")
	private Integer productSizeNumber;
	@Column(name="PRODUCT_SIZE_TEXT")
	private String productSizeText;
	@Column(name="PRODUCT_COLOR")
	private String productColor;
	@Column(name="PRODUCT_PRICE")
	private float productPrice;
	@Column(name="DISCOUNT_RATE")
	private double discountRate;
	@Column(name="IS_DEFAULT")
	private Integer isDefault;
	@Column(name="IMAGE1")
	private String image1;
	@Column(name="IMAGE2")
	private String image2;
	@Column(name="IMAGE3")
	private String image3;
	@Column(name="IMAGE4")
	private String image4;
	@Column(name="IMAGE5")
	private String image5;
	@Column(name="IMAGE6")
	private String image6;
	@Column(name="IMAGE7")
	private String image7;
	@Column(name="IMAGE8")
	private String image8;
	@Column(name="IMAGE9")
	private String image9;
	@Column(name="IMAGE10")
	private String image10;
	
	public Integer getPpmId() {
		return ppmId;
	}
	public void setPpmId(Integer ppmId) {
		this.ppmId = ppmId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getProductSizeNumber() {
		return productSizeNumber;
	}
	public void setProductSizeNumber(Integer productSizeNumber) {
		this.productSizeNumber = productSizeNumber;
	}
	public String getProductSizeText() {
		return productSizeText;
	}
	public void setProductSizeText(String productSizeText) {
		this.productSizeText = productSizeText;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getImage6() {
		return image6;
	}
	public void setImage6(String image6) {
		this.image6 = image6;
	}
	public String getImage7() {
		return image7;
	}
	public void setImage7(String image7) {
		this.image7 = image7;
	}
	public String getImage8() {
		return image8;
	}
	public void setImage8(String image8) {
		this.image8 = image8;
	}
	public String getImage9() {
		return image9;
	}
	public void setImage9(String image9) {
		this.image9 = image9;
	}
	public String getImage10() {
		return image10;
	}
	public void setImage10(String image10) {
		this.image10 = image10;
	}
}
