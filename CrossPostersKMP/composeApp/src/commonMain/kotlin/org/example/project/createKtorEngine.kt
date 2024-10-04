package org.example.project

import io.ktor.client.engine.HttpClientEngineFactory

expect fun createKtorEngine(): HttpClientEngineFactory<*>