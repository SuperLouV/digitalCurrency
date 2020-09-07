# digitalCurrency
## The interview project of Chainalysis
#### Introduce
  Used to show real-time of BTC and ETH prices, data got from Huobi.com and Kraken.com
#### Requirments
  1. Prices of Bitcoin and Ethereum from two different exchanges/sources (any). 
  Differentiate buy and sell price clearly 
  2. Recommendations on which exchange one should buy and/or sell.
  Recommend where to buy and where to sell. Both can be different exchanges
#### The API reference
1. https://api.huobi.pro/market/history/trade?symbol=ethusdt&size=20  
2. https://api.huobi.pro/market/history/trade?symbol=btcusdt&size=20  
3. https://api.kraken.com/0/public/Trades?pair=BTCUSD  
4. https://api.kraken.com/0/public/Trades?pair=ETHUSD  
## Program logic and structure 
### Front-end: Ajax/HTML/CSS/Bootstrap
 1. Index.html as main page, Ajax requests data updates to the background every three seconds, request URL is **(/v1/data)**  
 2. Generated tables using Ajax  
 3. There are **3** tables in main page, the left table shows the real-time BTC price and ETH price from Huobi API, the middle tabls shows the real-time BTC price and ETH price from Kraken API, the right table used to recommend lowest price to buy and highest price to sell and resource links
 4. Huobi data updated every 3 seconds, Kraken data updated every 30 secodes.
### Back-end: SpringBoot+Java
1. Model: Entity of coins，automatically generate data tables in digitalCurrency database
2. Dao: Inherit the JPA interface  
3. Service: Connect Controller to Dao  
4. Controller: Data interaction with the front-end
### Database: MySQL 
Database name: digitalCurrency  **You need to create your own database**  
Table: coins  
Parameter:  

    private String coin_id;//KEY   type is UUID

    private String coin_type;//BTC or ETH

    private double coin_price;//USD

    private String coin_direction;//sell or buy

    private String coin_source;//huobi or kraken
### Guide
1. Create database digitalCurrency in MySQL  
2. Set username and password of MySQL in src/main/resources/application.yml
