# Supremacy Game - Market Backend

## Features:

* Keeps ledger of the player transactions  (Only current turn's transactions are visible
* Allows override of ledger entries in case of mistakes
* Maintains the resource market prices
* Manages exchanges of money/resources between players
* Keeps tally and displays the current turn and current turn's actions

## Running

Launch the server using `java -jar SupremacyAppServer.jar`. On start, the server will randomly initialize the market. Navigate a web browser to the hostname of the machine running the server on port 8080 - e.g. http://localhost:8080/

The server exposes REST API with various end-points:

* /buy?amount=1&to=MARKET&type=MINERAL
* /sell?amount=1&to=RUSSIA&type=OIL

Todo: more end-points for more functionality. Ability to log-in as a country.
