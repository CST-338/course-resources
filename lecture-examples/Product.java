/**
*Author: Drew A. Clinkenbeard
*Last updateDate: 14 – Feb – 2022
*Description:  An example used to talk about inheritance and using super and this
*/
public class Product {

  private String code;
  private String description;
  private double price;
  protected static int count = 0;  // number of Products for sale

  public Product(String code, String description, double price) {
    this.code = code;
    this.description = description;
    this.price = price;
  }

  public Product() {
    this("", "", 0.0);
  }
}