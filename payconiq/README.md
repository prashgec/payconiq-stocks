**Stock Application**

application is having for rest services exposed:
1. GET /api/stocks (get a list of stocks) 
2. GET /api/stocks/1 (get one stock
from the list) 
3. PUT /api/stocks/1 (update the price of a single stock)
4. POST /api/stocks (create a stock)

**UI**

One UI page is available which is using rest end point "/api/stocks" which will list all the available stocks.
this UI page can be accessed at URL "_http://hostname:port/api/stocks/view_"

**Configuration**
Current configuration (port no) is specified in application.properties.
