
package com.crio.warmup.stock;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.dto.TotalReturnsDto;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.*;
import com.fasterxml.jackson.core.type.TypeReference; 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import java.time.temporal.ChronoUnit;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {
  
  
  public static String getToken(){
    String token = "9878dd2ed7e493e94eb5e4e27ad893810af8be3b";
    return token;
  }
  // TODO: CRIO_TASK_MODULE_JSON_PARSING - Done
  //  Task:
  //       - Read the json file provided in the argument[0], The file is available in the classpath.
  //       - Go through all of the trades in the given file,
  //       - Prepare the list of all symbols a portfolio has.
  //       - if "trades.json" has trades like
  //         [{ "symbol": "MSFT"}, { "symbol": "AAPL"}, { "symbol": "GOOGL"}]
  //         Then you should return ["MSFT", "AAPL", "GOOGL"]
  //  Hints:
  //    1. Go through two functions provided - #resolveFileFromResources() and #getObjectMapper
  //       Check if they are of any help to you.
  //    2. Return the list of all symbols in the same order as provided in json.

  //  Note:
  //  1. There can be few unused imports, you will need to fix them to make the build pass.
  //  2. You can use "./gradlew build" to check if your code builds successfully.

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException { 
    
    String input_trades = args[0];

    List<String> symbols = new ArrayList<String>(args.length);
    
    List<PortfolioTrade> portfolioTrades = readTradesFromJson(input_trades);
    
    for(PortfolioTrade trade : portfolioTrades){
      String symbol = trade.getSymbol();
      symbols.add(symbol);
    }
    return symbols;
  }


  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.










  // TODO: CRIO_TASK_MODULE_REST_API
  //  Find out the closing price of each stock on the end_date and return the list
  //  of all symbols in ascending order by its close value on end date.

  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(
        Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }


  // TODO: CRIO_TASK_MODULE_JSON_PARSING - Done
  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "/home/crio-user/workspace/sandeep-roy2014-ME_QMONEY_V2/qmoney/bin/main/trades.json";
     String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@1573f9fc";
     String functionNameFromTestFileInStackTrace = "mainReadFile()";
     String lineNumberFromTestFileInStackTrace = "29";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {

    /*
     * Approach - 
     * 1. Deserialize trades.json to PortfolioTrades
     * 2. Create a function mainReadQuotesHelper, which takes args and PortfolioTrades and returns a deserialized TotalReturnsDto
     * 3. Sort TotalReturnsDto using a Comparator of closingPrices
     * 4. Iterate over TotalReturnsDto, add each stock symbols to symbols list
     * 5. return symbols list
     * 
     * Explanation :
     * Step 1 - This is self explanatory, I had already done it in earlier module
     * 
     * Step 2 - mainReadQuotesHelper(), takes two arguments -> the whole argument passed on build "trades.json 2020-01-01" & object of PortfolioTrades
     *          and returns a deserialized TotalReturnsDto
     *         
     *        - We need to iterate over PortfoilioTrades first, since for each stock symbol we need to find the historic price
     *        
     *        - Then we can prepare our URL with base_url + stock symbol + query_path + start_end = purchaseDate + end_date = args[1] + token
     * 
     *        - Since we have the required URL, need to deserialze the API get call to TiingoCandle using RestTemplate.getForObject()
     * 
     *        - Now if only a valid response is returned then only we need to proceed
     *       
     *        - So if reponse id all good, using symbol from Trade & closingPrice from TiingoCandle
     *          We create a object of TotalReturnsDto to hold both symbol & closingPrice
     *       
     *          This could have been much easier if earlier told which class to use for what purpose!!!!!
     * 
     * Step 3 - A new comparator is required in TotalReturnsDto class to compare closingPrices in ascending order, general stuff not so complex
     * 
     * Step 4 & 5 - Self explainatory
     * 
     * This modiule lacks explanation about what exactly needs to be done.
     * So much of time was spent to figuring out how to make a relation between stock symbol & closing price.
     * If there was an explanation about the classes, theier purpose, when to use what, it would have been a lot easier. 
     *  
     */
    

    List<String> symbols = new ArrayList<>();
    
    
    File input_trades = resolveFileFromResources(args[0]);
    ObjectMapper objectMapper = PortfolioManagerApplication.getObjectMapper();
    List<PortfolioTrade> trades = objectMapper.readValue(input_trades, new TypeReference<List<PortfolioTrade>>() {});

     
    List<TotalReturnsDto> list_totalReturnsDto = create__TotalReturnsDto_list(args, trades);
    Collections.sort(list_totalReturnsDto, TotalReturnsDto.closingPriceComparator);

    for(TotalReturnsDto trd : list_totalReturnsDto){
      symbols.add(trd.getSymbol());
    }
    return symbols;
  }

  public static List<TotalReturnsDto> create__TotalReturnsDto_list(String[] args, List<PortfolioTrade> trades) throws IOException, URISyntaxException {
    List<TotalReturnsDto> totalReturnsDtos = new ArrayList<>();

    RestTemplate restTemplate = new RestTemplate();

    for(PortfolioTrade trade : trades){
      String url = "https://api.tiingo.com/tiingo/daily/" + trade.getSymbol() + 
                    "/prices?startDate=" + trade.getPurchaseDate().toString() +
                    "&endDate=" + args[1] + 
                    "&token=" + getToken();
    
      TiingoCandle[] tiingoCandle = restTemplate.getForObject(url, TiingoCandle[].class);
      
      if(tiingoCandle != null){
        totalReturnsDtos.add(new TotalReturnsDto(trade.getSymbol(), tiingoCandle[tiingoCandle.length - 1].getClose()));
      }
    }
    return totalReturnsDtos;
  }

  // TODO:
  //  After refactor, make sure that the tests pass by using these two commands
  //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
  //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile
  public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
    File input_trades = resolveFileFromResources(filename);
    ObjectMapper objectMapper = PortfolioManagerApplication.getObjectMapper();
    List<PortfolioTrade> list_trades = objectMapper.readValue(input_trades, new TypeReference<List<PortfolioTrade>>() {});
    return list_trades;
  }


  // TODO:
  //  Build the Url using given parameters and use this function in your code to cann the API.
  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    return "https://api.tiingo.com/tiingo/daily/" + trade.getSymbol() + "/prices?startDate=" + trade.getPurchaseDate() + "&endDate=" + endDate + "&token=" + token;
  }
  // TODO:
  //  Ensure all tests are passing using below command
  //  ./gradlew test --tests ModuleThreeRefactorTest
  public static Double getOpeningPriceOnStartDate(List<Candle> candles) {
    Double result = candles.get(0).getOpen();
    return result;
  }


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
    return candles.get(candles.size()-1).getClose();
  }


  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
    List<Candle> candles = new ArrayList<>(); 

    String Url = prepareUrl(trade, endDate, token);

    RestTemplate restTemplate = new RestTemplate();

    TiingoCandle[] tingo = restTemplate.getForObject(Url, TiingoCandle[].class);

    for(TiingoCandle t : tingo ){
      candles.add(t);
    }

    return candles;
  }

  // Annualized returns of the portfolio stocks
  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args) throws IOException, URISyntaxException {
    // 1. Find endDate from args[1]
    // 2. Create PortfolioTrade List to desirialize trades.json
    // 3. Get symbol & purchaseDate/ startDate from PortfolioTrade and endDate from args[1]
    // 4. For each trades make a url and call it to deserialize to TiingoCandle
    // 5. Check for reponse if null - decrese endDate by 1 and call deserialize again.
    // 6. If not null - create constructor
    // return list after sorting it in ascending order of getAnnualizedreturn
    List<AnnualizedReturn> annualizedReturn_list = new ArrayList<>(); 
    LocalDate end_date = LocalDate.parse(args[1]);
    List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
    String token = getToken();

    for(PortfolioTrade trade : portfolioTrades){
      List<Candle> candles = fetchCandles(trade, end_date, token);
      Double closingPrice = getClosingPriceOnEndDate(candles);
      if(closingPrice == null){
        end_date = end_date.minusDays(1);
      }
      AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(end_date, trade, getOpeningPriceOnStartDate(candles), getClosingPriceOnEndDate(candles));
      
      annualizedReturn_list.add(annualizedReturn);
    }
    Collections.sort(annualizedReturn_list, new Comparator<AnnualizedReturn>() {

      @Override
      public int compare(AnnualizedReturn arg0, AnnualizedReturn arg1) {
        // TODO Auto-generated method stub
        return arg1.getAnnualizedReturn().compareTo(arg0.getAnnualizedReturn());
      }
    });
    return annualizedReturn_list;
  }

  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn

  public static AnnualizedReturn calculateAnnualizedReturns(
    LocalDate endDate, PortfolioTrade trade, Double buyPrice, Double sellPrice
    ) {
      double no_of_years = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate)/365d;
        
      double totalReturn = (double) (sellPrice - buyPrice) / buyPrice;
      
      double annualizedReturn = (double) Math.pow(1 + totalReturn, 1 / no_of_years) - 1;
      
      return new AnnualizedReturn(trade.getSymbol(), annualizedReturn, totalReturn);
    }
 





















  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Once you are done with the implementation inside PortfolioManagerImpl and
  //  PortfolioManagerFactory, create PortfolioManager using PortfolioManagerFactory.
  //  Refer to the code from previous modules to get the List<PortfolioTrades> and endDate, and
  //  call the newly implemented method in PortfolioManager to calculate the annualized returns.

  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.

  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args) throws Exception {
    String file = args[0];
    LocalDate endDate = LocalDate.parse(args[1]);
    String contents = readFileAsString(file);
    ObjectMapper objectMapper = getObjectMapper();
    
    List<AnnualizedReturn> list_AnnualizedReturns = new ArrayList<>();

    List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
    
    RestTemplate template = new RestTemplate();
    
    PortfolioManager portfolioManager = new PortfolioManagerImpl(template);
    
    list_AnnualizedReturns = portfolioManager.calculateAnnualizedReturn(portfolioTrades, endDate);

    return list_AnnualizedReturns;
  }

  private static String readFileAsString(String file) {
    return null;
  }

  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());
    printJsonObject(mainCalculateReturnsAfterRefactor(args));
  }
}

