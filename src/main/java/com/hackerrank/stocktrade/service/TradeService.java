package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.exception.BadRequestException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ioannou
 */
@Service
public class TradeService {

    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public void deleteAllTrades() {
        tradeRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    public void createTrade(Trade trade) {
        System.out.println("tradeeeeeeee" + trade.getId());
        System.out.println("symbol" + trade.getStockSymbol());
        if (tradeRepository.findOne(trade.getId()) != null) {
            System.out.println("mpoulo" + trade.getId());
            throw new BadRequestException("Trade with id exists");
        }
        User user = userRepository.findOne(trade.getUser().getId());
        if( user == null) {
            user = userRepository.save(trade.getUser());
            trade.setUser(user);
        } else {
            trade.setUser(user);
        }
        tradeRepository.save(trade);
    }

    public List<Trade> getAllTradesOrderedByIdAsc() {
        return tradeRepository.findAllByOrderByIdAsc();
    }

    public List<Trade> getAllTradesByUserId(Long userId) {
        return tradeRepository.findByUserIdOrderByIdAsc(userId);
    }

    public List<Trade> getAllTradesByStock(String stockSymbol) {
        return tradeRepository.findAllByStockSymbol(stockSymbol);
    }

    public List<Trade> findAllByStartAndEndDate(Timestamp startDate, Timestamp endDate) {
        return tradeRepository.findAllByStartAndEndDate(startDate, endDate);
    }


    public Float findMax(Timestamp startDate, Timestamp endDate, String symbol) {
        return tradeRepository.findMax(startDate, endDate, symbol);
    }

    public Float findMin(Timestamp startDate, Timestamp endDate, String symbol) {
        return tradeRepository.findMin(startDate, endDate, symbol);
    }
}
