
# Firi SDK for Kotlin

Welcome to the **Firi SDK for Kotlin**, an easy-to-use SDK for interacting with the Firi cryptocurrency trading platform. This SDK allows developers to integrate Firi's functionality into their Kotlin-based applications with minimal effort.

---

## Features

- **Seamless Integration**: Easily integrate Firi's trading, portfolio, and account management features into your Kotlin projects.
- **Comprehensive API Coverage**: Supports a wide range of Firi API endpoints for trading, fetching market data, managing wallets, and more.
- **Simple and Intuitive**: Built with Kotlin idioms and best practices to ensure ease of use and clean code.

---

## Installation

### Gradle
Add the following to your `build.gradle.kts` file:
```kotlin
dependencies {
    implementation("com.github.echokrist:Firi-SDK-Kotlin:latest-version")
}
```

Replace `latest-version` with the latest version of the SDK available on the repository.

### Maven
Add the dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>com.github.echokrist</groupId>
    <artifactId>Firi-SDK-Kotlin</artifactId>
    <version>latest-version</version>
</dependency>
```

---

## Usage

### Initialization
Start by initializing the SDK with your API key and secret:
```kotlin
import com.firi.sdk.FiriSDK

val firiSDK = FiriSDK(
    apiVersion = "latest-api-version",
    apiKey = "your-api-key",
    apiClientId = "your-api-client-id",
    apiSecretKey = "your-api-secret-key"
)
```

### Fetch Account Balance
```kotlin
val balance = firiSDK.getUserWalletBalances()
println("Your user balances: $balance")
```

### Place an Order
```kotlin
val orderResponse = firiSDK.createOrder(
    symbol = "BTCNOK",
    amount = 0.01,
    price = 500000.0,
    orderType = "LIMIT"
)
println("Order placed: $orderResponse")
```

### Fetch Market Data
```kotlin
val marketData = firiSDK.getMarketData("BTCNOK")
println("Market Data: $marketData")
```

---

## Requirements

- **Kotlin**: Version 1.8 or later
- **JDK**: OpenJDK 11 or higher
- Internet access to interact with Firi's APIs

---

## Development

### Contributing
Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request.

### Running Tests
To run tests, use the following command:
```bash
./gradlew test
```

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgements

- Thanks to the Firi team for providing comprehensive API documentation.
- Kotlin community for best practices and support.

---

For more details, visit the [official Firi API documentation](https://developers.firi.com/).
