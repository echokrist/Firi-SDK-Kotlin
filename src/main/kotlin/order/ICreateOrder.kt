package order

public interface ICreateOrder {
    public val market: String
    public val type: String
    public val price: Double
    public val amount: Double
}
