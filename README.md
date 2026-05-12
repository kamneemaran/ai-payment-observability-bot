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

### AI-Powered Recharge Support & Analytics Assistant

Tech Stack: Java, Dropwizard, PostgreSQL, OpenAI API, Gemini API, REST APIs

* AI-powered recharge operations assistant capable of analyzing transaction failures, recharge plans, provider issues, and user transaction history using natural language prompts.
* AI orchestration workflows with intent classification, entity extraction, strategy/factory-based handler routing, and contextual response generation.
* Multi-provider LLM abstraction supporting both OpenAI and Gemini with configurable prompt pipelines and provider-specific request/response handling.
* Dynamic SQL query engine supporting flexible search on transaction ID, user ID, payment mode, operator, status, and recharge plan attributes.
* Extensible backend architecture using DAO, Strategy Pattern, Factory Pattern, and conversational query processing workflows.
{
    "response": "Transaction failed due to provider timeout during execution phase."
}
```
