package com.crio.warmup.stock;


import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {

  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  // Task:
  // - Read the json file provided in the argument[0], The file is available in the classpath.
  // - Go through all of the trades in the given file,
  // - Prepare the list of all symbols a portfolio has.
  // - if "trades.json" has trades like
  // [{ "symbol": "MSFT"}, { "symbol": "AAPL"}, { "symbol": "GOOGL"}]
  // Then you should return ["MSFT", "AAPL", "GOOGL"]
  // Hints:
  // 1. Go through two functions provided - #resolveFileFromResources() and #getObjectMapper
  // Check if they are of any help to you.
  // 2. Return the list of all symbols in the same order as provided in json.

  // Note:
  // 1. There can be few unused imports, you will need to fix them to make the build pass.
  // 2. You can use "./gradlew build" to check if your code builds successfully.
  public static List<String> deSerialaiseJson(File file)
      throws URISyntaxException, DatabindException, IOException, StreamReadException {
    // List<String> symbols = new ArrayList<>();
    ObjectMapper ob = new ObjectMapper();
    ob.registerModule(new JavaTimeModule());
    ob.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    List<PortfolioTrade> list = ob.readValue(file, new TypeReference<List<PortfolioTrade>>() {});
    // PortfolioTrade[] tradeclass = ob.readValue(file,PortfolioTrade[].class);

    // for(PortfolioTrade pt :tradeclass){
    // symbol.add(pt.getSymbol());
    // }
    // list.stream().map((t) -> t.getSymbol()).forEach((t)->System.out.println(t));
    List<String> symbols = list.stream().map((t) -> t.getSymbol()).collect(Collectors.toList());
    return symbols;
  }



  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {
    // File fl = resolveFileFromResources(args[0]);
    File file = resolveFileFromResources(args[0]);
    List<String> list = deSerialaiseJson(file);
    return list;
  }




  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Now that you have the list of PortfolioTrade and their data, calculate annualized returns
  //  for the stocks provided in the Json.
  //  Use the function you just wrote #calculateAnnualizedReturns.
  //  Return the list of AnnualizedReturns sorted by annualizedReturns in descending order.

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
  // and deserialize the results in List<Candle>



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(Thread.currentThread().getContextClassLoader().getResource(filename).toURI())
        .toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }


  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  // Follow the instructions provided in the task documentation and fill up the correct values for
  // the variables provided. First value is provided for your reference.
  // A. Put a breakpoint on the first line inside mainReadFile() which says
  // return Collections.emptyList();
  // B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  // following the instructions to run the test.
  // Once you are able to run the test, perform following tasks and record the output as a
  // String in the function below.
  // Use this link to see how to evaluate expressions -
  // https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  // 1. evaluate the value of "args[0]" and set the value
  // to the variable named valueOfArgument0 (This is implemented for your reference.)
  // 2. In the same window, evaluate the value of expression below and set it
  // to resultOfResolveFilePathArgs0
  // expression ==> resolveFileFromResources(args[0])
  // 3. In the same window, evaluate the value of expression below and set it
  // to toStringOfObjectMapper.
  // You might see some garbage numbers in the output. Dont worry, its expected.
  // expression ==> getObjectMapper().toString()
  // 4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  // second place from top to variable functionNameFromTestFileInStackTrace
  // 5. In the same window, you will see the line number of the function in the stack trace window.
  // assign the same to lineNumberFromTestFileInStackTrace
  // Once you are done with above, just run the corresponding test and
  // make sure its working as expected. use below command to do the same.
  // ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

    String valueOfArgument0 = "trades.json";
    String resultOfResolveFilePathArgs0 = "trades.json";
    String toStringOfObjectMapper = "ObjectMapper";
    String functionNameFromTestFileInStackTrace = "mainReadFile";
    String lineNumberFromTestFileInStackTrace = "";


    return Arrays.asList(
        new String[] {valueOfArgument0, resultOfResolveFilePathArgs0, toStringOfObjectMapper,
            functionNameFromTestFileInStackTrace, lineNumberFromTestFileInStackTrace});
  }


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  //    and deserialize the results in List<Candle>

  public void collectSymbol(){
    
  } 

  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {
    String dateStr = args[1];
    LocalDate date = LocalDate.parse(dateStr);
    String authToken = "ecc972df43b1ab368fc3542782d8b8e96b3b4289";

    TiingoCandle [] tingoCand = null ;
    Map<String,TiingoCandle[]> tc = new TreeMap<String,TiingoCandle[]>();
    List<PortfolioTrade> pt = readTradesFromJson(args[0]);
    
    for(PortfolioTrade p : pt){
      String madeUrl = prepareUrl(p,date,authToken);
      tingoCand = new RestTemplate().getForObject(madeUrl,TiingoCandle[].class);
      tc.put(p.getSymbol(),tingoCand);
    }
    List<String> ansList = sortList(tc);

     return ansList;
  }

  public static List<String> sortList(Map<String,TiingoCandle[]> tc){
    return tc.entrySet()
    .stream()
    .sorted(Comparator.comparingDouble(e ->
            e.getValue()[e.getValue().length - 1].getClose()
    ))
    .map(Map.Entry::getKey)
    .collect(Collectors.toList());
  } 

  // TODO:
  //  After refactor, make sure that the tests pass by using these two commands
  //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
  //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile
  public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
    File file = resolveFileFromResources(filename);
    ObjectMapper ob = new ObjectMapper();
    ob.registerModule(new JavaTimeModule());
    List<PortfolioTrade> list =ob.readValue(file, new TypeReference<List<PortfolioTrade>>() {});
     return list;
  }


  // TODO:
  //  Build the Url using given parameters and use this function in your code to cann the API.
  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    // trade.getSymbol();
    String url = "https://api.tiingo.com/tiingo/daily/"
        + trade.getSymbol()
        + "/prices?startDate="
        + trade.getPurchaseDate()
        + "&endDate="
        + endDate
        + "&token="
        + token;
    String urlFormat2 = String.format(
          "https://api.tiingo.com/tiingo/daily/%s/prices?startDate=%s&endDate=%s&token=%s",
          trade.getSymbol(),
          trade.getPurchaseDate(),
          endDate,
          token
      );
     return url;
  }
  // TODO:
  //  Ensure all tests are passing using below command
  //  ./gradlew test --tests ModuleThreeRefactorTest
  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
     return candles.get(0).getOpen();
  }


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
     return candles.get(candles.size()-1).getClose();
  }

  public static String getToken(){
    return "ecc972df43b1ab368fc3542782d8b8e96b3b4289";
  }

  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
    Candle [] tingoCand = null ;
      String url = prepareUrl(trade,endDate,token) ;
      tingoCand = new RestTemplate().getForObject(url,TiingoCandle[].class);
      return Arrays.asList(tingoCand);
  }

  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
        String dateStr = args[1];
        LocalDate endDate = LocalDate.parse(dateStr);
        List<PortfolioTrade> pt = readTradesFromJson(args[0]);
        List<Candle> listcandle = null;
        List<AnnualizedReturn> listAnnualReturn = new ArrayList<>();
        for(PortfolioTrade p : pt){
          listcandle = fetchCandles(p, endDate, getToken());
          AnnualizedReturn anlizedret = calculateAnnualizedReturns(endDate,p,listcandle.get(0).getOpen(),listcandle.get(listcandle.size()-1).getClose());
          listAnnualReturn.add(anlizedret);
          }
          listAnnualReturn.sort(Comparator.comparingDouble((AnnualizedReturn value)->value.getAnnualizedReturn()).reversed());
        return listAnnualReturn;
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

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        Double totalReturn = (sellPrice - buyPrice)/buyPrice;
        double years = trade.getPurchaseDate().until(endDate, ChronoUnit.DAYS)/365.24;
        Double annualizedReturns = Math.pow((1 + totalReturn),(1 / years))-1 ;
        String symb =  trade.getSymbol();
      return new AnnualizedReturn(symb,annualizedReturns,totalReturn);
  }














  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Once you are done with the implementation inside PortfolioManagerImpl and
  //  PortfolioManagerFactory, create PortfolioManager using PortfolioManagerFactory.
  //  Refer to the code from previous modules to get the List<PortfolioTrades> and endDate, and
  //  call the newly implemented method in PortfolioManager to calculate the annualized returns.

  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.

  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
       String file = args[0];
       LocalDate endDate = LocalDate.parse(args[1]);
       List<PortfolioTrade> contents = readTradesFromJson(file);
       ObjectMapper objectMapper = getObjectMapper();
       PortfolioManager portfolioManager = PortfolioManagerFactory.getPortfolioManager(new RestTemplate());
       return portfolioManager.calculateAnnualizedReturn(contents, endDate);
  }



  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());

    // printJsonObject(mainReadFile(debugOutputs().toArray(new String[0])));

    // printJsonObject(mainReadFile(args));

    // printJsonObject(mainReadQuotes(args));

    // String userDate = "2100-02-24";
    // LocalDate userInputDate = LocalDate.parse(userDate);
    // System.out.println(userInputDate.isLeapYear());
    // LocalDate userInDate = LocalDate.parse(userDate);
    // System.out.println(userInDate.plusDays(5));

    // printJsonObject(mainCalculateSingleReturn(args));




    printJsonObject(mainCalculateReturnsAfterRefactor(args));



  }
}

