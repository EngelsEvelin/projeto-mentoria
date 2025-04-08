package desafio.java.springboot.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import desafio.java.springboot.model.Transaction;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();
    private static final int TIME_WINDOW_SECONDS = 60;

    private long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

    public void addTransaction(Transaction transaction) {
        validateTransaction(transaction);
        transactions.add(transaction);
        System.out.println("Transação adicionada: " + transaction.getValor() + " - " + transaction.getDataHora());
        cleanOldTransactions();
        updateStatistics();
    }

    public void clearTransactions() {
        transactions.clear();
        updateStatistics();
    }

    public long getCount() {
        return count;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    
    public DoubleSummaryStatistics getStatistics() {
        cleanOldTransactions();
        return transactions.stream()
            .mapToDouble(Transaction::getValor)
            .collect(DoubleSummaryStatistics::new, DoubleSummaryStatistics::accept, DoubleSummaryStatistics::combine);
    }

    private void cleanOldTransactions() {
        if (transactions.isEmpty()) {
            return;
        }

        
        OffsetDateTime now = OffsetDateTime.now(OffsetDateTime.now().getOffset());
        OffsetDateTime latestTransactionTime = transactions.stream()
            .map(Transaction::getDataHora)
            .max(OffsetDateTime::compareTo)
            .orElse(now);

        
        transactions.removeIf(t -> latestTransactionTime.minusSeconds(TIME_WINDOW_SECONDS).isAfter(t.getDataHora()));

        System.out.println("Transações após limpeza: " + transactions.size());
    }

    private void validateTransaction(Transaction transaction) {
        if (transaction == null || transaction.getDataHora() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A transação e sua data não podem ser nulas.");
        }

        if (transaction.getValor() < 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O valor da transação não pode ser negativo.");
        }

        if (transaction.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "A data da transação não pode estar no futuro.");
        }
    }

    private void updateStatistics() {
        DoubleSummaryStatistics stats = transactions.stream()
            .mapToDouble(Transaction::getValor)
            .collect(DoubleSummaryStatistics::new, DoubleSummaryStatistics::accept, DoubleSummaryStatistics::combine);

        this.count = stats.getCount();
        this.sum = stats.getSum();
        this.avg = stats.getCount() > 0 ? stats.getAverage() : 0.0;
        this.min = stats.getCount() > 0 ? stats.getMin() : 0.0;
        this.max = stats.getCount() > 0 ? stats.getMax() : 0.0;
    }
}
