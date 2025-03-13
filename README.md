# CryptoApp - Backend

This is the backend of CryptoApp, a cryptocurrency trading platform built using **Spring Boot**. It provides authentication, asset management, portfolio tracking, and trading functionalities via a RESTful API.

## Frontend Repository

The frontend for this project is available at: [CryptoApp Frontend](https://github.com/MarkOmelyanenko/crypto-app-frontend)


## Features

- User authentication (JWT-based login and registration)
- Cryptocurrency asset management
- Buy and sell cryptocurrencies
- Track user portfolio and transaction history
- Retrieve portfolio analytics
- Real-time cryptocurrency price updates using Binance API + WebSockets

## Technologies Used

- **Backend Framework:** Spring Boot
- **Security:** Spring Security, JWT Authentication
- **Database:** PostgreSQL
- **External API:** Binance API for real-time cryptocurrency prices

## API Endpoints

### Authentication

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Log in and receive a JWT token

### Asset Management

- `GET /api/assets` - Retrieve all available assets
- `GET /api/assets/{symbol}` - Get details of a specific asset

### Trading

- `POST /api/transactions/{username}/buy?symbol={symbol}&amount={amount}` - Buy an asset
- `POST /api/transactions/{username}/sell?symbol={symbol}&amount={amount}` - Sell an asset
- `GET /api/transactions/{username}/history` - Get transaction history
- `GET /api/transactions/{username}/portfolio` - Get user portfolio

### User Management

- `GET /api/users/{username}` - Get user details
- `GET /api/users/{username}/balance` - Retrieve account balance
- `GET /api/users/{username}/portfolio/analytics` - Get portfolio analytics

## TODO

- Improve security measures
- Implement caching for frequently accessed data
- Enhance API response handling

## Contributors

- **Mark Naliuka** (Developer)

