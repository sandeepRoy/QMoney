
package com.crio.warmup.stock.portfolio;

import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.*;

import com.crio.warmup.stock.PortfolioManagerApplication;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerImpl implements PortfolioManager {

  RestTemplate restTemplate;
  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility


  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility
  public PortfolioManagerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  //TODO: CRIO_TASK_MODULE_REFACTOR
  // 1. Now we want to convert our code into a module, so we will not call it from main anymore.
  //    Copy your code from Module#3 PortfolioManagerApplication#calculateAnnualizedReturn
  //    into #calculateAnnualizedReturn function here and ensure it follows the method signature.
  // 2. Logic to read Json file and convert them into Objects will not be required further as our
  //    clients will take care of it, going forward.

  // Note:
  // Make sure to exercise the tests inside PortfolioManagerTest using command below:
  // ./gradlew test --tests PortfolioManagerTest

  //CHECKSTYLE:OFF






  private Comparator<AnnualizedReturn> getComparator() {
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }

  //CHECKSTYLE:OFF

  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Extract the logic to call Tiingo third-party APIs to a separate function.
  //  Remember to fill out the buildUri function and use that.


  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to) throws JsonProcessingException {
    List<Candle> candles = new ArrayList<>(); 
    String token = PortfolioManagerApplication.getToken();
    String startDate = from.toString();
    String endDate = to.toString();
    String Url = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?startDate=" + startDate.toString() + "&endDate=" + endDate.toString() + "&token=" + token;
    

    RestTemplate restTemplate = new RestTemplate();

    TiingoCandle[] tingo = restTemplate.getForObject(Url, TiingoCandle[].class);

    for(TiingoCandle t : tingo ){ 
      candles.add(t);
    }
    return candles;
  }

  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
       String uriTemplate = "https:api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
            + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";
      return uriTemplate;
  }


  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades, LocalDate endDate) {
    List<AnnualizedReturn> annualizedReturn_list = new ArrayList<>(); 

    for(PortfolioTrade trade : portfolioTrades){

      try{

        List<Candle> candles = getStockQuote(trade.getSymbol(), trade.getPurchaseDate(), endDate);
      
        Double closingPrice = PortfolioManagerApplication.getClosingPriceOnEndDate(candles);
        
        if(closingPrice == null){
          endDate = endDate.minusDays(1);
        }
      
        AnnualizedReturn annualizedReturn = PortfolioManagerApplication.calculateAnnualizedReturns(
          endDate, 
          trade, 
          PortfolioManagerApplication.getOpeningPriceOnStartDate(candles),
          PortfolioManagerApplication.getClosingPriceOnEndDate(candles)
        );
      
        annualizedReturn_list.add(annualizedReturn);
      }catch(Exception e){ e.printStackTrace();}
    }
    Collections.sort(annualizedReturn_list, new Comparator<AnnualizedReturn>() {

      @Override
      public int compare(AnnualizedReturn arg0, AnnualizedReturn arg1) {
        return arg1.getAnnualizedReturn().compareTo(arg0.getAnnualizedReturn());
      }
    });
    return annualizedReturn_list;
  }
}
