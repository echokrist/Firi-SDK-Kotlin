package sdk

import balance.IBalanceService
import main.deposit.IDespoitService
import market.IMarketService
import order.IOrderService
import time.ITimeService
import transaction.ITransactionService

public interface IFiriSDK : ITimeService, IBalanceService, ITransactionService, IDespoitService, IMarketService, IOrderService {
    public val apiVersion: String
    public val apiKey: String
    public val apiClientId: String
    public val apiSecretKey: String
}