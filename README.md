
# Firi SDK for Kotlin

Welcome to the **Firi SDK for Kotlin**, an easy-to-use SDK for interacting with the [Firi](https://firi.com/affiliate?referral=22ccc3f1)
 cryptocurrency trading platform. This SDK allows developers to integrate [Firi's](https://firi.com/affiliate?referral=22ccc3f1) functionality into their Kotlin-based applications with minimal effort.

--- 

## Start Investing in Cryptocurrency with Firi! 🚀

#### ⚠️ **IMPORTANT NOTE (READ BEFORE REGISTERING):**  
- Buying and selling cryptocurrency involves **high risk**, and past performance is **not a guarantee** of future results.
- You must be at least **18 years old** to register. 
- **BankID** or **MitID** is required to verify your identity.  

Stay informed and invest responsibly! 💡

If you want to start investing in cryptocurrency with [Firi](https://firi.com/affiliate?referral=22ccc3f1), you can use my **[Referral Link](https://firi.com/affiliate?referral=22ccc3f1)** and receive **55 kr in welcome bonus!** 🎉  

Or scan the QR code below to get started:

![Firi Referral](https://github.com/user-attachments/assets/d11a9dc9-370c-418e-8364-8e6b74c5d3d9)
 

---

## Features

- **Seamless Integration**: Easily integrate [Firi's](https://firi.com/affiliate?referral=22ccc3f1) trading, portfolio, and account management features into your Kotlin projects.
- **Comprehensive API Coverage**: Supports a wide range of [Firi's](https://developers.firi.com/) API endpoints for trading, fetching market data, managing wallets, and more.
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


### Build applicaiton
To build locally, use the following command:
```bash
./gradlew test
```

### Running Tests
To run tests, use the following command:
```bash
./gradlew test
```

---

### Upgrading the gradle wrapper
Find the newest version of gradle here: https://gradle.org/releases/ Then run this command:
(Remeber to repealce $gradleVersjon with the newest version)
```shell script
./gradlew wrapper --gradle-version $gradleVersjon
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgements

- Thanks to the Firi team for providing comprehensive API documentation.
- Kotlin community for best practices and support.

---

For more details, visit the [official Firi API documentation](https://developers.firi.com/).

---

## Donate

If you find this project helpful, consider supporting its development with crypto donations:

Bitcoin (BTC - Network = Bitcoin network): bc1qfkgfyr0s3hcca9x6aql7xxktzzl7vx82z4e4a9

![Bitcoin wallet QR-code](https://github.com/user-attachments/assets/69be05e0-e038-449a-a646-15058c09a32c)

---

Cardano (Cardano - Network = Cardano network): addr1qyknlvfy3e30dunqrc7e3qtkgc3gdlwa5a8wuvnltncw24kqtdqwarj7we5latygaveylr8kah0yz6wck7eg0mp20dkqp4mcau

![Cardano wallet QR-code](https://github.com/user-attachments/assets/74f9258f-d244-49f4-956e-a7834eb162de)

---

Ethereum (ETH - Network = ERC20): 0xdA5B88a6D8F74100a0074d27d09Afd988E1B58d1

![Ethereum wallet QR-code](https://github.com/user-attachments/assets/1d9f7552-fb25-4d90-8edb-2c3a964e25b9)

---

Stellar (XLM - Network = Stellar network) GDXNWYFNOESHEQGDUUC2ASOX4E2X3AEXBJJUFS7MBPVNXIVXXFWIJL7S - (Destination tag) 2576692763

![Stellar wallet QR-code](https://github.com/user-attachments/assets/bdbde912-bd32-4f9d-8360-5925a3ea3c2f)

---

Polkado (DOT - Network = Polkadot network) : 1eTouCDKhwiPDLSFid3FTs8eThhJrHAedYjeUtzRdhy2kWq


![Polkadot wallet QR-code](https://github.com/user-attachments/assets/c63f29ad-a55a-4877-ad03-139feb1c2dd5)
