package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    public List<Trade> findAllByOrderByIdAsc();

    public List<Trade> findByUserIdOrderByIdAsc(Long id);


    public List<Trade> findAllByStockSymbol(String stockSymbol);

    @Query(value = "select t from Trade t where t.tradeTimestamp >= ?1 and t.tradeTimestamp <= ?2")
    public List<Trade> findAllByStartAndEndDate(Timestamp startDate, Timestamp endDate);

    @Query(value = "select max(t.stockPrice) from Trade t where t.tradeTimestamp >= ?1 and t.tradeTimestamp <= ?2 and t.stockSymbol = ?3")
    public Float findMax(Timestamp startDate, Timestamp endDate, String symbol);

    @Query(value = "select min(t.stockPrice) from Trade t where t.tradeTimestamp >= ?1 and t.tradeTimestamp <= ?2 and t.stockSymbol = ?3")
    public Float findMin(Timestamp startDate, Timestamp endDate, String symbol);
}
