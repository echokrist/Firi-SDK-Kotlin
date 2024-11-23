package sdk

import balance.IBalanceService
import main.deposit.IDespoitService
import market.IMarketService
import order.IOrderService
import time.ITimeService
import transaction.ITransactionService

interface IFiriSDK : ITimeService, IBalanceService, ITransactionService, IDespoitService, IMarketService, IOrderService {
    val apiVersion: String
    val apiKey: String
    val apiClientId: String
    val apiSecretKey: String
}