
package com.crio.warmup.stock.portfolio;

import com.crio.warmup.stock.PortfolioManagerApplication;
import com.crio.warmup.stock.dto.PortfolioTrade;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerFactory {

  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Implement the method to return new instance of PortfolioManager.
  //  Remember, pass along the RestTemplate argument that is provided to the new instance.

  public static PortfolioManager getPortfolioManager(RestTemplate restTemplate) {
    
   PortfolioManagerImpl portfolioManager = new PortfolioManagerImpl(restTemplate);
  //  portfolioManager.calculateAnnualizedReturn(portfolioTrades, endDate);
   return portfolioManager;
  }
}
