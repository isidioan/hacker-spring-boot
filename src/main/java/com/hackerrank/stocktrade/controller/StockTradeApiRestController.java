package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.StockDto;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.service.TradeService;
import com.hackerrank.stocktrade.service.UserService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockTradeApiRestController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserService userService;

    @DeleteMapping(value = "/erase")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTrades() {
        tradeService.deleteAllTrades();
    }


    @PostMapping(value = "/trades", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrade(@RequestBody Trade trade) {
        tradeService.createTrade(trade);
    }

    @GetMapping(value = "/trades", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Trade> getAllTrades() {
        return tradeService.getAllTradesOrderedByIdAsc();
    }

    @GetMapping(value = "/trades/users/{userId}", produces = "application/json")
    public ResponseEntity getAllTradesFromUser(@PathVariable("userId") Long userId) {
        User user = userService.findUserById(userId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(tradeService.getAllTradesByUserId(user.getId()));
    }


    @GetMapping(value = "/stocks/{stockSymbol}/price", produces = "application/json")
    public ResponseEntity getAllTradesFromUser(@PathVariable("stockSymbol") String stockSymbol,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) throws ParseException {
        if (tradeService.getAllTradesByStock(stockSymbol).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(start);
        Date date2 = dateFormat.parse(end);*/

        Timestamp startDate = Timestamp.valueOf(start.atStartOfDay());
        Timestamp endDate = Timestamp.valueOf(end.atTime(LocalTime.MAX));

        if (tradeService.findAllByStartAndEndDate(startDate, endDate).isEmpty()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("message", "There are no trades in the given date range");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }

        Float max = tradeService.findMax(startDate, endDate, stockSymbol);

        Float min = tradeService.findMin(startDate,endDate, stockSymbol);

        return ResponseEntity.status(HttpStatus.OK).body(new StockDto(stockSymbol, max, min));
    }
}
