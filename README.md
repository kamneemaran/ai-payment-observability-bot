# ai-payment-observability-bot
Core features:  
1. Transaction lookup
2. Provider responses
3. Recharge plans
4. Failure summaries
5. Analytics queries
6. Service logs
7. Stack traces

# Run Command

```bash
mvn clean package
```

```bash
java -jar target/ai-payment-observability-bot-1.0-SNAPSHOT.jar server config.yml
```

# Sample API

## POST

```
http://localhost:8080/query
```

## Request

```
{
    "query": "why did txn TXN123 fail"
}
```

## Response

```json
{
    "response": "Transaction failed due to provider timeout during execution phase."
}
```
