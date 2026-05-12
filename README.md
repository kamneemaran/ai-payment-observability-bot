# ai-payment-observability-bot
Core features:  
1. Transaction lookup
2. Provider responses
3. Recharge plans
4. Failure summaries
5. Analytics queries
6. Service logs
7. Stack traces

### AI-Powered Recharge Support & Analytics Assistant

Tech Stack: Java, Dropwizard, PostgreSQL, OpenAI API, Gemini API, REST APIs

* Built an AI-powered recharge operations assistant capable of analyzing transaction failures, recharge plans, provider issues, and user transaction history using natural language prompts.
* Designed AI orchestration workflows with intent classification, entity extraction, strategy/factory-based handler routing, and contextual response generation.
* Implemented multi-provider LLM abstraction supporting both OpenAI and Gemini with configurable prompt pipelines and provider-specific request/response handling.
* Developed dynamic SQL query engine supporting flexible search on transaction ID, user ID, payment mode, operator, status, and recharge plan attributes.
* Integrated structured context injection from PostgreSQL and provider logs to improve response accuracy and reduce generic LLM outputs.
* Built extensible backend architecture using DAO, Strategy Pattern, Factory Pattern, and conversational query processing workflows.
