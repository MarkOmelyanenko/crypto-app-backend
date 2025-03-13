package dev.march.financepetproject.repository;

import dev.march.financepetproject.entity.Transaction;
import dev.march.financepetproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUser(User user);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.user.id = :userId AND t.asset.symbol = :symbol")
    double getTotalOwnedAssets(@Param("userId") Long userId, @Param("symbol") String symbol);

    @Query("SELECT t.asset.symbol, t.asset.name, SUM(t.amount) AS totalAmount, t.asset.price, " +
            "(CASE WHEN SUM(t.amount) > 0 THEN SUM(t.amount * t.priceAtPurchase) / SUM(t.amount) ELSE 0 END) AS avgPurchasePrice " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "GROUP BY t.asset.symbol, t.asset.name, t.asset.price " +
            "HAVING SUM(t.amount) > 0")
    List<Object[]> getUserPortfolio(@Param("userId") Long userId);

    // List<Transaction> findByUsername(String username);

}
